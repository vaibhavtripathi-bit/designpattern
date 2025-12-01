package com.vaibhav.designpattern.memento.game

/**
 * Originator: The game object with internal state.
 */
class Game {
    var level: Int = 1
    var health: Int = 100
    var weapon: String = "Sword"

    fun play(newLevel: Int, newHealth: Int, newWeapon: String) {
        level = newLevel
        health = newHealth
        weapon = newWeapon
    }

    fun save(): GameState {
        return GameState(level, health, weapon)
    }

    fun load(state: GameState) {
        level = state.level
        health = state.health
        weapon = state.weapon
    }
    
    override fun toString(): String {
        return "Level: $level, Health: $health, Weapon: $weapon"
    }
}
