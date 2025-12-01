package com.vaibhav.designpattern.decorator.rpg

/**
 * Interface for RPG Characters.
 */
interface Character {
    fun getAttack(): Int
    fun getDefense(): Int
    fun getDescription(): String
}

/**
 * Concrete implementation of a basic hero.
 */
class BaseHero : Character {
    override fun getAttack(): Int {
        return 10
    }
    override fun getDefense(): Int {
        return 10
    }
    override fun getDescription(): String {
        return "Hero"
    }
}
