package com.vaibhav.designpattern.chainofresponsibility.loan

abstract class LoanApprover {
    protected var nextApprover: LoanApprover? = null

    fun setNext(approver: LoanApprover): LoanApprover {
        this.nextApprover = approver
        return approver
    }

    abstract fun approveLoan(amount: Int)
}

class Clerk : LoanApprover() {
    override fun approveLoan(amount: Int) {
        if (amount <= 10_000) {
            println("Clerk: Approved loan of $amount")
        } else {
            println("Clerk: Amount $amount exceeds limit. Escalating to Manager...")
            nextApprover?.approveLoan(amount)
        }
    }
}

class Manager : LoanApprover() {
    override fun approveLoan(amount: Int) {
        if (amount <= 50_000) {
            println("Manager: Approved loan of $amount")
        } else {
            println("Manager: Amount $amount exceeds limit. Escalating to Director...")
            nextApprover?.approveLoan(amount)
        }
    }
}

class Director : LoanApprover() {
    override fun approveLoan(amount: Int) {
        println("Director: Approved loan of $amount")
    }
}

object LoanApprovalChain {
    fun processLoan(amount: Int) {
        val clerk = Clerk()
        val manager = Manager()
        val director = Director()

        clerk.setNext(manager).setNext(director)

        println("--- Processing Loan: $amount ---")
        clerk.approveLoan(amount)
    }
}
