package com.vaibhav.designpattern.mediator.smarthome

abstract class SmartDevice(protected val mediator: SmartHomeMediator, val name: String)

class Alarm(mediator: SmartHomeMediator) : SmartDevice(mediator, "AlarmClock") {
    fun trigger() {
        println("Alarm ringing!")
        mediator.notify(this, "trigger")
    }
}

class Light(mediator: SmartHomeMediator) : SmartDevice(mediator, "LivingRoomLight") {
    var isOn = false
        private set

    fun turnOn() {
        isOn = true
        println("Light turned ON.")
        mediator.notify(this, "turnOn")
    }

    fun turnOff() {
        isOn = false
        println("Light turned OFF.")
        mediator.notify(this, "turnOff")
    }
}

class CoffeePot(mediator: SmartHomeMediator) : SmartDevice(mediator, "CoffeePot") {
    var isBrewing = false
        private set

    fun startBrewing() {
        isBrewing = true
        println("Coffee Pot started brewing.")
        mediator.notify(this, "brewing")
    }
}
