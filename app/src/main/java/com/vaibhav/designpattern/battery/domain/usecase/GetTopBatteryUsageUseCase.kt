package com.vaibhav.designpattern.battery.domain.usecase

import com.vaibhav.designpattern.battery.domain.model.BatteryModel
import com.vaibhav.designpattern.battery.domain.repository.BatteryRepository

class GetTopBatteryUsageUseCase(
    private val repository: BatteryRepository
) {
    suspend operator fun invoke(topX: Int, lastMinutes: Int): List<BatteryModel> {
        return repository.getBatteryUsage(topX, lastMinutes)
    }
}
