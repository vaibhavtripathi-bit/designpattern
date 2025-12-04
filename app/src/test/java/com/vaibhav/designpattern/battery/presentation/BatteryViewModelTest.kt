package com.vaibhav.designpattern.battery.presentation

import android.app.Application
import com.vaibhav.designpattern.battery.domain.manager.BatteryTriggerManager
import com.vaibhav.designpattern.battery.domain.model.BatteryModel
import com.vaibhav.designpattern.battery.domain.repository.BatteryRepository
import com.vaibhav.designpattern.battery.domain.usecase.GetTopBatteryUsageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class BatteryViewModelTest {

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var repository: BatteryRepository

    @Mock
    private lateinit var triggerManager: BatteryTriggerManager

    private lateinit var useCase: GetTopBatteryUsageUseCase
    private lateinit var viewModel: BatteryViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        
        useCase = GetTopBatteryUsageUseCase(repository)
        viewModel = BatteryViewModel(application, useCase, repository, triggerManager)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadBatteryUsage updates state to Success`() = runTest {
        // Arrange
        val mockData = listOf(
            BatteryModel("com.pkg", "App", 10.0, 5, 100, null)
        )
        `when`(repository.getBatteryUsage(10, 60)).thenReturn(mockData)

        // Act
        viewModel.loadBatteryUsage(10, 60)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val state = viewModel.uiState.value
        assertTrue(state is BatteryUiState.Success)
        assertEquals(mockData, (state as BatteryUiState.Success).batteryUsage)
    }

    @Test
    fun `loadBatteryUsage updates state to Error on exception`() = runTest {
        // Arrange
        val errorMessage = "Test Error"
        `when`(repository.getBatteryUsage(10, 60)).thenThrow(RuntimeException(errorMessage))

        // Act
        viewModel.loadBatteryUsage(10, 60)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val state = viewModel.uiState.value
        assertTrue(state is BatteryUiState.Error)
        assertEquals(errorMessage, (state as BatteryUiState.Error).message)
    }

    @Test
    fun `startTracking calls triggerManager and updates state`() {
        // Act
        viewModel.startTracking()

        // Assert
        verify(triggerManager).startTracking()
        val state = viewModel.uiState.value
        assertTrue(state is BatteryUiState.Success)
        assertTrue((state as BatteryUiState.Success).isTracking)
    }

    @Test
    fun `stopAndAnalyze calls triggerManager and repository`() = runTest {
        // Arrange
        val startTime = 1000L
        val endTime = 2000L
        val batteryDrop = 5
        val mockData = listOf(
            BatteryModel("com.pkg", "App", 10.0, 5, 100, null)
        )
        
        `when`(triggerManager.stopTracking()).thenReturn(Triple(startTime, endTime, batteryDrop))
        `when`(repository.getBatteryUsage(startTime, endTime, 10)).thenReturn(mockData)

        // Act
        viewModel.stopAndAnalyze()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        verify(triggerManager).stopTracking()
        verify(repository).getBatteryUsage(startTime, endTime, 10)
        
        val state = viewModel.uiState.value
        assertTrue(state is BatteryUiState.Success)
        val successState = state as BatteryUiState.Success
        assertEquals(mockData, successState.batteryUsage)
        assertEquals(batteryDrop, successState.sessionBatteryDrop)
        assertEquals(false, successState.isTracking)
    }
}
