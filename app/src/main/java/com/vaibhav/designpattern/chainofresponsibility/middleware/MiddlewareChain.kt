package com.vaibhav.designpattern.chainofresponsibility.middleware

object MiddlewareChain {
    fun execute(request: ApiRequest) {
        val logger = LoggerHandler()
        val auth = AuthHandler()
        val validator = ValidationHandler()
        val controller = ControllerHandler()

        // Chain: Logger -> Auth -> Validator -> Controller
        logger.setNext(auth)
            .setNext(validator)
            .setNext(controller)

        logger.handle(request)
    }
}
