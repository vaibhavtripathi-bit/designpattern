package com.vaibhav.designpattern.memento.game

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

class GameSaveTest {

    @Test
    fun `test Save and Load Checkpoints`() {
        val game = Game()
        val saveManager = SaveManager()

        // Start Game
        game.play(1, 100, "Sword")
        saveManager.saveGame("Level 1 Start", game.save())

        // Progress
        game.play(1, 80, "Sword")
        game.play(2, 100, "Bow")
        saveManager.saveGame("Level 2 Start", game.save())

        // Progress more and die (or want to reload)
        game.play(2, 0, "Bow")

        // Load Level 1
        val level1State = saveManager.loadGame("Level 1 Start")
        assertNotNull(level1State)
        game.load(level1State!!)
        assertEquals(1, game.level)
        assertEquals(100, game.health)
        assertEquals("Sword", game.weapon)

        // Load Level 2
        val level2State = saveManager.loadGame("Level 2 Start")
        assertNotNull(level2State)
        game.load(level2State!!)
        assertEquals(2, game.level)
        assertEquals(100, game.health)
        assertEquals("Bow", game.weapon)
    }
}
