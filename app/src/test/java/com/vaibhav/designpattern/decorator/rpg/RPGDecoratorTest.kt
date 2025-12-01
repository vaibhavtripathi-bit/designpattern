package com.vaibhav.designpattern.decorator.rpg

import org.junit.Test
import org.junit.Assert.assertEquals

class RPGDecoratorTest {

    @Test
    fun `test BaseHero`() {
        val hero: Character = BaseHero()
        assertEquals(10, hero.getAttack())
        assertEquals(10, hero.getDefense())
        assertEquals("Hero", hero.getDescription())
    }

    @Test
    fun `test Hero with Sword and Shield`() {
        var hero: Character = BaseHero()
        hero = Sword(hero)
        hero = Shield(hero)

        // Attack: 10 + 10 = 20
        // Defense: 10 + 10 = 20
        assertEquals(20, hero.getAttack())
        assertEquals(20, hero.getDefense())
        assertEquals("Hero, Sword, Shield", hero.getDescription())
    }

    @Test
    fun `test Dual Wielding Swords`() {
        var hero: Character = BaseHero()
        hero = Sword(hero)
        hero = Sword(hero)

        // Attack: 10 + 10 + 10 = 30
        assertEquals(30, hero.getAttack())
        assertEquals("Hero, Sword, Sword", hero.getDescription())
    }

    @Test
    fun `test Order of Composition - Berserk AFTER Sword`() {
        // (Hero + Sword) * Berserk
        var hero: Character = BaseHero() // 10
        hero = Sword(hero) // 10 + 10 = 20
        hero = Berserk(hero) // 20 * 2 = 40

        assertEquals(40, hero.getAttack())
        assertEquals("Hero, Sword, Berserk", hero.getDescription())
    }

    @Test
    fun `test Order of Composition - Berserk BEFORE Sword`() {
        // (Hero * Berserk) + Sword
        var hero: Character = BaseHero() // 10
        hero = Berserk(hero) // 10 * 2 = 20
        hero = Sword(hero) // 20 + 10 = 30

        assertEquals(30, hero.getAttack())
        assertEquals("Hero, Berserk, Sword", hero.getDescription())
    }

    @Test
    fun `test Helmet Trade-off`() {
        var hero: Character = BaseHero() // 10 Att, 10 Def
        hero = Helmet(hero)

        // Helmet: +5 Defense, -2 Attack
        assertEquals(8, hero.getAttack()) // 10 - 2
        assertEquals(15, hero.getDefense()) // 10 + 5
        assertEquals("Hero, Helmet", hero.getDescription())
    }

    @Test
    fun `test Curse Subtractive Effect`() {
        var hero: Character = BaseHero() // 10 Att, 10 Def
        hero = Sword(hero) // 20 Att, 10 Def
        hero = Curse(hero)

        // Curse: -5 Attack, -5 Defense
        assertEquals(15, hero.getAttack()) // 20 - 5
        assertEquals(5, hero.getDefense()) // 10 - 5
        assertEquals("Hero, Sword, Cursed", hero.getDescription())
    }
}
