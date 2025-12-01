package com.vaibhav.designpattern.chainofresponsibility.middleware

import org.junit.Test

class MiddlewareChainTest {

    @Test
    fun `test Successful Request`() {
        println("--- Scenario: Valid Request ---")
        val request = ApiRequest(
            path = "/api/users",
            method = "POST",
            headers = mapOf("Authorization" to "Bearer valid_token"),
            body = "{ \"name\": \"John\" }"
        )
        MiddlewareChain.execute(request)
    }

    @Test
    fun `test Auth Failure`() {
        println("\n--- Scenario: Invalid Token ---")
        val request = ApiRequest(
            path = "/api/users",
            method = "POST",
            headers = mapOf("Authorization" to "Bearer invalid_token"),
            body = "{ \"name\": \"John\" }"
        )
        MiddlewareChain.execute(request)
    }

    @Test
    fun `test Validation Failure`() {
        println("\n--- Scenario: Empty Body ---")
        val request = ApiRequest(
            path = "/api/users",
            method = "POST",
            headers = mapOf("Authorization" to "Bearer valid_token"),
            body = ""
        )
        MiddlewareChain.execute(request)
    }
}
