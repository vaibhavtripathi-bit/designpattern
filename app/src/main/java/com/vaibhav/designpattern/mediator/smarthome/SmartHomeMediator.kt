package com.vaibhav.designpattern.mediator.smarthome

interface SmartHomeMediator {
    fun notify(sender: SmartDevice, event: String)
}

class HomeHub : SmartHomeMediator {
    lateinit var light: Light
    lateinit var coffeePot: CoffeePot
    lateinit var alarm: Alarm

    override fun notify(sender: SmartDevice, event: String) {
        if (sender == alarm && event == "trigger") {
            println("Alarm triggered! Waking up household...")
            light.turnOn()
            coffeePot.startBrewing()
        }
        // Can add more logic here, e.g., if Light turns off, maybe check something else
    }
}
