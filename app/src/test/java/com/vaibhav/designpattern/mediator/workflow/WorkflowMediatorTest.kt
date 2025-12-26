package com.vaibhav.designpattern.mediator.workflow

import org.junit.Test
import org.junit.Assert.*

class WorkflowMediatorTest {

    @Test
    fun testOrderWorkflowSequence() {
        val coordinator = WorkflowCoordinator()
        
        assertEquals("Initial status should be Started", "Started", coordinator.currentStatus)
        
        // Start the chain
        coordinator.startOrder()
        
        // Since our simple components print and immediately notify, the chain should run to completion locally
        // In a real async system, this would be different. Here it proves the mediator wiring works.
        assertEquals("Workflow should be completed", "Completed", coordinator.currentStatus)
    }
}
