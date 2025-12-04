package com.vaibhav.designpattern.battery.domain.manager

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BatteryTriggerManager(private val context: Context) {

    private val _isTracking = MutableStateFlow(false)
    val isTracking: StateFlow<Boolean> = _isTracking

    private var startTime: Long = 0
    private var startBatteryLevel: Int = -1

    fun startTracking() {
        startTime = System.currentTimeMillis()
        startBatteryLevel = getBatteryLevel()
        _isTracking.value = true
    }

    fun stopTracking(): Triple<Long, Long, Int>? {
        if (!_isTracking.value) return null
        _isTracking.value = false
        val endTime = System.currentTimeMillis()
        val endBatteryLevel = getBatteryLevel()
        
        val batteryDrop = if (startBatteryLevel != -1 && endBatteryLevel != -1) {
            startBatteryLevel - endBatteryLevel
        } else {
            0
        }

        return Triple(startTime, endTime, batteryDrop)
    }

    private fun getBatteryLevel(): Int {
        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
            context.registerReceiver(null, ifilter)
        }
        return batteryStatus?.let { intent ->
            val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            (level * 100 / scale.toFloat()).toInt()
        } ?: -1
    }
}
