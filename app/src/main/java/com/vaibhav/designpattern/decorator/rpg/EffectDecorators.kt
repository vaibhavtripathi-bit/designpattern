package com.vaibhav.designpattern.decorator.rpg

class Berserk(character: Character) : CharacterDecorator(character) {
    // Multiplies current attack by 2
    override fun getAttack(): Int {
        return super.getAttack() * 2
    }
    
    // Halves defense
    override fun getDefense(): Int {
        return super.getDefense() / 2
    }
    
    override fun getDescription(): String {
        return super.getDescription() + ", Berserk"
    }
}

class Curse(character: Character) : CharacterDecorator(character) {
    // Reduces stats
    override fun getAttack(): Int {
        return super.getAttack() - 5
    }
    override fun getDefense(): Int {
        return super.getDefense() - 5
    }
    
    override fun getDescription(): String {
        return super.getDescription() + ", Cursed"
    }
}
