package com.vaibhav.designpattern.battery.domain.model

data class BatteryModel(
    val packageName: String,
    val appName: String,
    val usagePercentage: Double,
    val usageDurationMinutes: Long,
    val networkUsageBytes: Long = 0L,
    val icon: Any? = null // Can be Drawable or ImageVector, kept generic for now
)
