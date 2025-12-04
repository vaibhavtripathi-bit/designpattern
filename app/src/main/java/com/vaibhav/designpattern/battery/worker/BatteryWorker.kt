package com.vaibhav.designpattern.battery.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.vaibhav.designpattern.battery.data.repository.BatteryRepositoryImpl

class BatteryWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            Log.d(TAG, "Starting background battery usage check")
            
            // In a real app with Hilt/Dagger, we would inject this.
            // For now, we instantiate directly as per the current architecture.
            val repository = BatteryRepositoryImpl(applicationContext)
            
            // Fetch stats for the last 60 minutes
            val batteryUsage = repository.getBatteryUsage(topX = 5, lastMinutes = 60)
            
            if (batteryUsage.isEmpty()) {
                Log.d(TAG, "No battery usage data found for the last 60 minutes")
            } else {
                Log.d(TAG, "Top Battery Consumers (Last 60m):")
                batteryUsage.forEach { model ->
                    Log.d(TAG, "App: ${model.appName} (${model.packageName}) - Usage: ${model.usagePercentage.format(2)}%")
                }
            }

            Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Error tracking battery usage", e)
            Result.failure()
        }
    }

    private fun Double.format(digits: Int) = "%.${digits}f".format(this)

    companion object {
        private const val TAG = "BatteryWorker"
    }
}
