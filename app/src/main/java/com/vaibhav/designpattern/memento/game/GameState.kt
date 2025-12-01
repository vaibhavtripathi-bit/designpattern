package com.vaibhav.designpattern.memento.game

/**
 * Memento: Immutable snapshot of the game state.
 */
data class GameState(val level: Int, val health: Int, val weapon: String)
