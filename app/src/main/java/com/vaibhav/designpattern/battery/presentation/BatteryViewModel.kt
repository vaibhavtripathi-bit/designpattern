package com.vaibhav.designpattern.battery.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.vaibhav.designpattern.battery.domain.manager.BatteryTriggerManager
import com.vaibhav.designpattern.battery.domain.model.BatteryModel
import com.vaibhav.designpattern.battery.domain.repository.BatteryRepository
import com.vaibhav.designpattern.battery.domain.usecase.GetTopBatteryUsageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class BatteryUiState {
    object Loading : BatteryUiState()
    data class Success(
        val batteryUsage: List<BatteryModel>,
        val sessionBatteryDrop: Int = 0,
        val isTracking: Boolean = false
    ) : BatteryUiState()
    data class Error(val message: String) : BatteryUiState()
}

class BatteryViewModel(
    application: Application,
    private val getTopBatteryUsageUseCase: GetTopBatteryUsageUseCase,
    private val batteryRepository: BatteryRepository
) : AndroidViewModel(application) {

    private val triggerManager = BatteryTriggerManager(application)

    private val _uiState = MutableStateFlow<BatteryUiState>(BatteryUiState.Loading)
    val uiState: StateFlow<BatteryUiState> = _uiState.asStateFlow()

    fun loadBatteryUsage(topX: Int = 10, lastMinutes: Int = 60) {
        viewModelScope.launch {
            _uiState.value = BatteryUiState.Loading
            try {
                val usageList = getTopBatteryUsageUseCase(topX, lastMinutes)
                _uiState.value = BatteryUiState.Success(
                    batteryUsage = usageList,
                    sessionBatteryDrop = 0,
                    isTracking = false
                )
            } catch (e: Exception) {
                _uiState.value = BatteryUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun startTracking() {
        triggerManager.startTracking()
        _uiState.value = BatteryUiState.Success(
            batteryUsage = emptyList(),
            sessionBatteryDrop = 0,
            isTracking = true
        )
    }

    fun stopAndAnalyze() {
        viewModelScope.launch {
            val result = triggerManager.stopTracking()
            if (result != null) {
                val (startTime, endTime, batteryDrop) = result
                _uiState.value = BatteryUiState.Loading
                try {
                    val usageList = batteryRepository.getBatteryUsage(startTime, endTime, 10)
                    _uiState.value = BatteryUiState.Success(
                        batteryUsage = usageList,
                        sessionBatteryDrop = batteryDrop,
                        isTracking = false
                    )
                } catch (e: Exception) {
                    _uiState.value = BatteryUiState.Error(e.message ?: "Unknown error")
                }
            }
        }
    }
}
