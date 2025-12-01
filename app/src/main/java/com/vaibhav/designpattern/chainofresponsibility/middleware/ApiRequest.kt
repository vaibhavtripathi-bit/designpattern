package com.vaibhav.designpattern.chainofresponsibility.middleware

data class ApiRequest(
    val path: String,
    val method: String,
    val headers: Map<String, String>,
    val body: String
)
