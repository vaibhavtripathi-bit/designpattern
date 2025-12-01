package com.vaibhav.designpattern.memento.browser

/**
 * Originator: The Browser.
 */
class Browser {
    var currentUrl: String = "about:blank"

    fun visit(url: String) {
        currentUrl = url
    }

    fun save(): BrowserState {
        return BrowserState(currentUrl)
    }

    fun restore(state: BrowserState) {
        currentUrl = state.url
    }
}
