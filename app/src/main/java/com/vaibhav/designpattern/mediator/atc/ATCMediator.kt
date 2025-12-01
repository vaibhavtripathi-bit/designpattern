package com.vaibhav.designpattern.mediator.atc

interface ATCMediator {
    fun registerFlight(flight: Aircraft)
    fun requestLanding(flight: Aircraft): Boolean
    fun requestTakeoff(flight: Aircraft): Boolean
}

class ControlTower : ATCMediator {
    private val flights = mutableListOf<Aircraft>()
    private var isRunwayFree = true

    override fun registerFlight(flight: Aircraft) {
        flights.add(flight)
    }

    override fun requestLanding(flight: Aircraft): Boolean {
        return if (isRunwayFree) {
            isRunwayFree = false
            true
        } else {
            false
        }
    }

    override fun requestTakeoff(flight: Aircraft): Boolean {
        return if (isRunwayFree) {
            isRunwayFree = false
            true
        } else {
            false
        }
    }
    
    fun releaseRunway() {
        isRunwayFree = true
    }
}
