package com.vaibhav.designpattern.memento.game

/**
 * Caretaker: Manages named save slots.
 */
class SaveManager {
    private val saves = mutableMapOf<String, GameState>()

    fun saveGame(name: String, state: GameState) {
        saves[name] = state
    }

    fun loadGame(name: String): GameState? {
        return saves[name]
    }
}
