package com.vaibhav.designpattern.chainofresponsibility.middleware

class LoggerHandler : MiddlewareHandler() {
    override fun handle(request: ApiRequest): Boolean {
        println("Logger: Request received for ${request.method} ${request.path}")
        return nextHandler?.handle(request) ?: true
    }
}

class AuthHandler : MiddlewareHandler() {
    override fun handle(request: ApiRequest): Boolean {
        val authHeader = request.headers["Authorization"]
        if (authHeader == "Bearer valid_token") {
            println("Auth: Token verified.")
            return nextHandler?.handle(request) ?: true
        } else {
            println("Auth: 401 Unauthorized - Invalid or missing token.")
            return false // Stop chain
        }
    }
}

class ValidationHandler : MiddlewareHandler() {
    override fun handle(request: ApiRequest): Boolean {
        if ((request.method == "POST" || request.method == "PUT") && request.body.isEmpty()) {
            println("Validator: 400 Bad Request - Body cannot be empty.")
            return false // Stop chain
        }
        println("Validator: Body valid.")
        return nextHandler?.handle(request) ?: true
    }
}

class ControllerHandler : MiddlewareHandler() {
    override fun handle(request: ApiRequest): Boolean {
        println("Controller: 200 OK - Processing business logic.")
        return true
    }
}
