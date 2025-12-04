package com.vaibhav.designpattern.battery.domain.manager

import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import androidx.core.content.ContextCompat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.isNull
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.eq

class BatteryTriggerManagerTest {

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var intent: Intent

    private lateinit var manager: BatteryTriggerManager

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        
        // Mock battery intent
        `when`(
            ContextCompat.registerReceiver(
                context,
                isNull(),
                any(),
                ContextCompat.RECEIVER_NOT_EXPORTED
            )).thenReturn(intent)
        `when`(intent.getIntExtra(eq(BatteryManager.EXTRA_LEVEL), any())).thenReturn(80)
        `when`(intent.getIntExtra(eq(BatteryManager.EXTRA_SCALE), any())).thenReturn(100)
        
        manager = BatteryTriggerManager(context)
    }

    @Test
    fun `startTracking initializes state`() {
        // Act
        manager.startTracking()

        // Assert
        assertTrue(manager.isTracking.value)
    }

    @Test
    fun `stopTracking returns null if not tracking`() {
        // Act
        val result = manager.stopTracking()

        // Assert
        assertNull(result)
    }

    @Test
    fun `stopTracking returns correct data`() {
        // Arrange
        `when`(intent.getIntExtra(eq(BatteryManager.EXTRA_LEVEL), any()))
            .thenReturn(80) // Start level
            .thenReturn(75) // End level
            
        manager.startTracking()

        // Act
        val result = manager.stopTracking()

        // Assert
        assertNotNull(result)
        assertFalse(manager.isTracking.value)
        assertEquals(5, result?.third) // 80 - 75 = 5
    }
}
