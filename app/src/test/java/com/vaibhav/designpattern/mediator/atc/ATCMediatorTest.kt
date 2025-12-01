package com.vaibhav.designpattern.mediator.atc

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse

class ATCMediatorTest {

    @Test
    fun `test Runway Management`() {
        val tower = ControlTower()
        val flight1 = Flight(tower, "AC101")
        val flight2 = Flight(tower, "BA202")

        tower.registerFlight(flight1)
        tower.registerFlight(flight2)

        // 1. Flight 1 requests landing -> Granted
        flight1.land()
        assertEquals("Landed", flight1.status)

        // 2. Flight 2 requests landing -> Denied (Runway busy)
        flight2.land()
        assertEquals("In Air", flight2.status)

        // 3. Release runway (manually for this test scenario as discussed)
        tower.releaseRunway()

        // 4. Flight 2 requests landing again -> Granted
        flight2.land()
        assertEquals("Landed", flight2.status)
    }

    @Test
    fun `test Takeoff Management`() {
        val tower = ControlTower()
        val flight1 = Flight(tower, "AC101")
        val flight2 = Flight(tower, "BA202")

        // 1. Flight 1 takes off -> Granted
        flight1.takeOff()
        assertEquals("In Air", flight1.status)

        // 2. Flight 2 takes off -> Denied
        flight2.takeOff()
        // Status remains whatever it was (default "In Air", but let's say it wanted to take off implies it was on ground, 
        // but our simple class defaults to "In Air". 
        // Let's just check the boolean return of the mediator directly for clarity or trust the print/status logic if we had "On Ground" state.
        // For this simple test, we can verify the tower state indirectly by trying another action.
        
        // Let's try to land Flight 1 immediately -> Denied because Flight 1 just took off and occupied runway (in our simple logic)
        // In reality, takeoff clears runway after some time, but our logic locks it until release.
        val canLand = tower.requestLanding(flight1)
        assertFalse(canLand)

        tower.releaseRunway()
        val canLandNow = tower.requestLanding(flight1)
        assertTrue(canLandNow)
    }
}
