package com.vaibhav.designpattern.mediator.workflow

// Base Step
abstract class WorkflowStep(protected var mediator: WorkflowMediator? = null) {
    fun registerMediator(mediator: WorkflowMediator) {
        this.mediator = mediator
    }
}

// Concrete Steps
class OrderValidation : WorkflowStep() {
    fun validateOrder() {
        println("OrderValidation: Validating order...")
        // ... complex logic ...
        println("OrderValidation: Order valid.")
        mediator?.notify(this, "validated")
    }
}

class Payment : WorkflowStep() {
    fun processPayment() {
        println("Payment: Processing payment...")
        // ... logic ...
        println("Payment: Payment successful.")
        mediator?.notify(this, "paid")
    }
}

class Inventory : WorkflowStep() {
    fun reserveStock() {
        println("Inventory: Checking and reserving stock...")
        // ... logic ...
        println("Inventory: Stock reserved.")
        mediator?.notify(this, "reserved")
    }
}

class Shipping : WorkflowStep() {
    fun shipOrder() {
        println("Shipping: Creating shipping label...")
        // ... logic ...
        println("Shipping: Shipped.")
        mediator?.notify(this, "shipped")
    }
}

class Notification : WorkflowStep() {
    fun sendConfirmation() {
        println("Notification: Sending email...")
        // ... logic ...
        println("Notification: Email sent.")
        mediator?.notify(this, "sent")
    }
}

// The Mediator (Coordinator)
class WorkflowCoordinator : WorkflowMediator {
    val validation = OrderValidation()
    val payment = Payment()
    val inventory = Inventory()
    val shipping = Shipping()
    val notification = Notification()
    
    // Track status for testing
    var currentStatus = "Started"
        private set

    init {
        validation.registerMediator(this)
        payment.registerMediator(this)
        inventory.registerMediator(this)
        shipping.registerMediator(this)
        notification.registerMediator(this)
    }

    fun startOrder() {
        currentStatus = "Validating"
        validation.validateOrder()
    }

    override fun notify(sender: WorkflowStep, event: String) {
        when (sender) {
            validation -> if (event == "validated") {
                currentStatus = "Payment Processing"
                payment.processPayment()
            }
            payment -> if (event == "paid") {
                currentStatus = "Inventory Reservation"
                inventory.reserveStock()
            }
            inventory -> if (event == "reserved") {
                currentStatus = "Shipping"
                shipping.shipOrder()
            }
            shipping -> if (event == "shipped") {
                currentStatus = "Notifying"
                notification.sendConfirmation()
            }
            notification -> if (event == "sent") {
                currentStatus = "Completed"
                println("WorkflowCoordinator: Order workflow finished.")
            }
        }
    }
}
