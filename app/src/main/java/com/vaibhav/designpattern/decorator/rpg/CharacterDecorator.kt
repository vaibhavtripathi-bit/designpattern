package com.vaibhav.designpattern.decorator.rpg

/**
 * Abstract decorator class for Characters.
 */
abstract class CharacterDecorator(protected val character: Character) : Character {
    override fun getAttack(): Int {
        return character.getAttack()
    }
    override fun getDefense(): Int {
        return character.getDefense()
    }
    override fun getDescription(): String {
        return character.getDescription()
    }
}
