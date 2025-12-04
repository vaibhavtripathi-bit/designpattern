package com.vaibhav.designpattern.battery.data.repository

import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import com.vaibhav.designpattern.battery.domain.model.BatteryModel
import com.vaibhav.designpattern.battery.domain.repository.BatteryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar

import com.vaibhav.designpattern.battery.domain.repository.NetworkRepository
import com.vaibhav.designpattern.util.AndroidLogger
import com.vaibhav.designpattern.util.Logger

class BatteryRepositoryImpl(
    private val context: Context,
    private val networkRepository: NetworkRepository = NetworkRepositoryImpl(context),
    private val logger: Logger = AndroidLogger()
) : BatteryRepository {

    override suspend fun getBatteryUsage(topX: Int, lastMinutes: Int): List<BatteryModel> {
        val endTime = System.currentTimeMillis()
        val startTime = endTime - (lastMinutes * 60 * 1000)
        return getBatteryUsage(startTime, endTime, topX)
    }

    override suspend fun getBatteryUsage(startTime: Long, endTime: Long, topX: Int): List<BatteryModel> {
        return withContext(Dispatchers.IO) {
            logger.d("vaibhav", "getBatteryUsage: startTime=$startTime, endTime=$endTime, topX=$topX")
            val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
            val packageManager = context.packageManager

            // Query events from 2 hours before startTime to catch apps already in foreground
            val lookbackTime = startTime - (2 * 60 * 60 * 1000) 
            val usageEvents = usageStatsManager.queryEvents(lookbackTime, endTime)
            val event = android.app.usage.UsageEvents.Event()
            val appUsageMap = mutableMapOf<String, Long>()
            val startTimes = mutableMapOf<String, Long>()
            val activePackages = mutableSetOf<String>()
            var boundaryCrossed = false

            logger.d("vaibhav", "Iterating events from lookbackTime=$lookbackTime...")
            while (usageEvents.hasNextEvent()) {
                usageEvents.getNextEvent(event)
                
                //logger.d("vaibhav", "Event: pkg=${event.packageName}, type=${event.eventType}, time=${event.timeStamp}")

                if (event.timeStamp < startTime) {
                    // Processing events before the requested window to determine initial state
                    when (event.eventType) {
                        android.app.usage.UsageEvents.Event.MOVE_TO_FOREGROUND -> {
                            activePackages.add(event.packageName)
                        }
                        android.app.usage.UsageEvents.Event.MOVE_TO_BACKGROUND -> {
                            activePackages.remove(event.packageName)
                        }
                    }
                } else {
                    // We have crossed into the requested window [startTime, endTime]
                    if (!boundaryCrossed) {
                        logger.d("vaibhav", "Crossed startTime boundary. Active packages: $activePackages")
                        activePackages.forEach { pkg ->
                            startTimes[pkg] = startTime
                        }
                        activePackages.clear()
                        boundaryCrossed = true
                    }

                    when (event.eventType) {
                        android.app.usage.UsageEvents.Event.MOVE_TO_FOREGROUND -> {
                            logger.d("vaibhav", "FG Event: ${event.packageName} at ${event.timeStamp}")
                            startTimes[event.packageName] = event.timeStamp
                        }
                        android.app.usage.UsageEvents.Event.MOVE_TO_BACKGROUND -> {
                            val start = startTimes[event.packageName]
                            if (start != null) {
                                val duration = event.timeStamp - start
                                logger.d("vaibhav", "BG Event: ${event.packageName} at ${event.timeStamp}. Duration: ${duration}ms")
                                appUsageMap[event.packageName] = (appUsageMap[event.packageName] ?: 0L) + duration
                                startTimes.remove(event.packageName)
                            } else {
                                logger.d("vaibhav", "BG Event without start time: ${event.packageName} at ${event.timeStamp}")
                            }
                        }
                    }
                }
            }

            // Handle case where no events occurred after startTime (boundary never crossed in loop)
            if (!boundaryCrossed) {
                logger.d("vaibhav", "No events after startTime. Active packages: $activePackages")
                activePackages.forEach { pkg ->
                    startTimes[pkg] = startTime
                }
            }

            // Handle apps still in foreground at endTime
            startTimes.forEach { (packageName, start) ->
                val duration = endTime - start
                if (duration > 0) {
                    logger.d("vaibhav", "Ongoing usage at endTime: $packageName. Duration: ${duration}ms")
                    appUsageMap[packageName] = (appUsageMap[packageName] ?: 0L) + duration
                }
            }

            val totalUsageTime = appUsageMap.values.sum()
            logger.d("vaibhav", "Total calculated usage time: $totalUsageTime ms")

            // Fetch network usage
            val networkUsageMap = networkRepository.getNetworkUsage(startTime, endTime)

            val finalList = appUsageMap.entries
                .sortedByDescending { it.value }
                .take(topX)
                .mapNotNull { entry ->
                    try {
                        val packageName = entry.key
                        val timeInForeground = entry.value
                        
                        if (timeInForeground == 0L) return@mapNotNull null

                        val appInfo = packageManager.getApplicationInfo(packageName, 0)
                        val appName = packageManager.getApplicationLabel(appInfo).toString()
                        
                        val percentage = if (totalUsageTime > 0L) {
                            (timeInForeground.toDouble() / totalUsageTime.toDouble()) * 100
                        } else {
                            0.0
                        }

                        BatteryModel(
                            packageName = packageName,
                            appName = appName,
                            usagePercentage = percentage,
                            usageDurationMinutes = timeInForeground / 1000 / 60,
                            networkUsageBytes = networkUsageMap[packageName] ?: 0L,
                            icon = null 
                        )
                    } catch (e: PackageManager.NameNotFoundException) {
                        null
                    }
                }
            
            logger.d("vaibhav", "Returning ${finalList.size} items")
            finalList.forEach {
                logger.d("vaibhav", "App: ${it.appName}, Usage: ${it.usageDurationMinutes}m, ${it.usagePercentage}%")
            }
            
            finalList
        }
    }
}
