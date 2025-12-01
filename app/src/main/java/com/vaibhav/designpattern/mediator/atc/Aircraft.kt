package com.vaibhav.designpattern.mediator.atc

abstract class Aircraft(protected val mediator: ATCMediator, val flightNumber: String) {
    abstract fun land()
    abstract fun takeOff()
}

class Flight(mediator: ATCMediator, flightNumber: String) : Aircraft(mediator, flightNumber) {
    
    var status: String = "In Air"

    override fun land() {
        if (mediator.requestLanding(this)) {
            status = "Landed"
            println("Flight $flightNumber: Successfully Landed.")
            // In a real scenario, we might release the runway after some time or via another call.
            // For this simple example, we assume the tower needs to be manually cleared or we add a method here.
            // Let's assume the flight releases it immediately for simplicity in this specific method flow, 
            // OR better, we expose a way to release it.
            // For the test case "only one can land", we should NOT release it immediately here.
            // The ControlTower needs a separate release method.
        } else {
            println("Flight $flightNumber: Landing denied. Waiting.")
        }
    }

    override fun takeOff() {
        if (mediator.requestTakeoff(this)) {
            status = "In Air"
            println("Flight $flightNumber: Successfully Took Off.")
        } else {
            println("Flight $flightNumber: Takeoff denied. Waiting.")
        }
    }
}
