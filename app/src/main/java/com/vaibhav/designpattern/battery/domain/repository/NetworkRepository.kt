package com.vaibhav.designpattern.battery.domain.repository

interface NetworkRepository {
    suspend fun getNetworkUsage(startTime: Long, endTime: Long): Map<String, Long>
}
