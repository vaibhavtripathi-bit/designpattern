package com.vaibhav.designpattern.battery.data.repository

import android.app.usage.NetworkStats
import android.app.usage.NetworkStatsManager
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.RemoteException
import android.util.Log
import com.vaibhav.designpattern.battery.domain.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepositoryImpl(
    private val context: Context
) : NetworkRepository {

    override suspend fun getNetworkUsage(startTime: Long, endTime: Long): Map<String, Long> {
        return withContext(Dispatchers.IO) {
            val networkStatsManager = context.getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager
            val packageUsage = mutableMapOf<String, Long>()

            try {
                // Query for Wi-Fi
                queryNetworkStats(
                    networkStatsManager,
                    ConnectivityManager.TYPE_WIFI,
                    startTime,
                    endTime,
                    packageUsage
                )

                // Query for Mobile Data
                queryNetworkStats(
                    networkStatsManager,
                    ConnectivityManager.TYPE_MOBILE,
                    startTime,
                    endTime,
                    packageUsage
                )

            } catch (e: Exception) {
                Log.e("NetworkRepository", "Error fetching network stats", e)
            }

            packageUsage
        }
    }

    private fun queryNetworkStats(
        networkStatsManager: NetworkStatsManager,
        networkType: Int,
        startTime: Long,
        endTime: Long,
        resultMap: MutableMap<String, Long>
    ) {
        try {
            // subscriberId is null for Wi-Fi, and usually null for Mobile in recent Android versions for summary queries
            val bucket = NetworkStats.Bucket()
            val querySummary = networkStatsManager.querySummary(
                networkType,
                null,
                startTime,
                endTime
            )

            while (querySummary.hasNextBucket()) {
                querySummary.getNextBucket(bucket)
                val uid = bucket.uid
                
                // Map UID to Package Name
                // Note: UID 0 is System, UID 1000 is Android OS, etc.
                // We will try to map to package name using PackageManager
                val packageName = getPackageNameForUid(uid)

                if (packageName != null) {
                    val totalBytes = bucket.rxBytes + bucket.txBytes
                    resultMap[packageName] = (resultMap[packageName] ?: 0L) + totalBytes
                }
            }
            querySummary.close()
        } catch (e: RemoteException) {
            Log.e("NetworkRepository", "RemoteException querying stats for type $networkType", e)
        } catch (e: SecurityException) {
            Log.e("NetworkRepository", "SecurityException - Missing permissions?", e)
        }
    }

    private fun getPackageNameForUid(uid: Int): String? {
        val packageManager = context.packageManager
        val packages = packageManager.getPackagesForUid(uid)
        return if (!packages.isNullOrEmpty()) {
            packages[0] // Return the first package name associated with this UID
        } else {
            null // Could be system UID or uninstalled app
        }
    }
}
