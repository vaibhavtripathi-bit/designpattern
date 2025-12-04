package com.vaibhav.designpattern.battery.domain.repository

import com.vaibhav.designpattern.battery.domain.model.BatteryModel

interface BatteryRepository {
    suspend fun getBatteryUsage(topX: Int, lastMinutes: Int): List<BatteryModel>
    suspend fun getBatteryUsage(startTime: Long, endTime: Long, topX: Int): List<BatteryModel>
}
