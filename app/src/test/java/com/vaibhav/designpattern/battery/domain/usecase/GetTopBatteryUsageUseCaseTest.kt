package com.vaibhav.designpattern.battery.domain.usecase

import com.vaibhav.designpattern.battery.domain.model.BatteryModel
import com.vaibhav.designpattern.battery.domain.repository.BatteryRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class GetTopBatteryUsageUseCaseTest {

    @Mock
    private lateinit var repository: BatteryRepository

    private lateinit var useCase: GetTopBatteryUsageUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        useCase = GetTopBatteryUsageUseCase(repository)
    }

    @Test
    fun `invoke calls repository with correct parameters`() = runTest {
        // Arrange
        val topX = 5
        val lastMinutes = 30
        val mockData = listOf(
            BatteryModel("com.pkg", "App", 10.0, 5, 100, null)
        )
        `when`(repository.getBatteryUsage(topX, lastMinutes)).thenReturn(mockData)

        // Act
        val result = useCase(topX, lastMinutes)

        // Assert
        verify(repository).getBatteryUsage(topX, lastMinutes)
        assertEquals(mockData, result)
    }
}
