package com.vaibhav.designpattern.chainofresponsibility.loan

import org.junit.Test

class LoanApprovalTest {
    @Test
    fun `test Clerk Approval`() {
        LoanApprovalChain.processLoan(5000)
    }

    @Test
    fun `test Manager Approval`() {
        LoanApprovalChain.processLoan(25000)
    }

    @Test
    fun `test Director Approval`() {
        LoanApprovalChain.processLoan(100000)
    }
}
