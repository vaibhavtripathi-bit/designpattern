package com.vaibhav.designpattern.decorator.rpg

class Sword(character: Character) : CharacterDecorator(character) {
    override fun getAttack(): Int {
        return super.getAttack() + 10
    }
    override fun getDescription(): String {
        return super.getDescription() + ", Sword"
    }
}

class Shield(character: Character) : CharacterDecorator(character) {
    override fun getDefense(): Int {
        return super.getDefense() + 10
    }
    override fun getDescription(): String {
        return super.getDescription() + ", Shield"
    }
}

class Helmet(character: Character) : CharacterDecorator(character) {
    override fun getDefense(): Int {
        return super.getDefense() + 5
    }
    // Helmet reduces visibility/movement slightly, reducing attack
    override fun getAttack(): Int {
        return super.getAttack() - 2
    }
    override fun getDescription(): String {
        return super.getDescription() + ", Helmet"
    }
}
