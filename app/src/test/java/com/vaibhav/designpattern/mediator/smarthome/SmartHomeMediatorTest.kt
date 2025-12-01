package com.vaibhav.designpattern.mediator.smarthome

import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse

class SmartHomeMediatorTest {

    @Test
    fun `test Alarm Trigger Automates Home`() {
        val hub = HomeHub()
        val alarm = Alarm(hub)
        val light = Light(hub)
        val coffeePot = CoffeePot(hub)

        // Register devices with Hub
        hub.alarm = alarm
        hub.light = light
        hub.coffeePot = coffeePot

        // Initial state
        assertFalse(light.isOn)
        assertFalse(coffeePot.isBrewing)

        // Trigger Alarm
        alarm.trigger()

        // Verify automation
        assertTrue(light.isOn)
        assertTrue(coffeePot.isBrewing)
    }
}
