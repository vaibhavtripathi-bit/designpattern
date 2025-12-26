package com.vaibhav.designpattern.mediator.gamesystem

import org.junit.Test
import org.junit.Assert.*

class GameSystemMediatorTest {

    @Test
    fun testCoinCollectionScenario() {
        val game = GameLevelMediator()
        
        assertEquals("Score should be 0", 0, game.score.score)
        
        // Player collects coin -> Updates Score (10) -> Plays Sound (mocked by print)
        game.player.collectCoin()
        assertEquals("Score should increase by 10", 10, game.score.score)
    }

    @Test
    fun testAchievementUnlockScenario() {
        val game = GameLevelMediator()
        
        // Collect 5 coins (50 points)
        repeat(5) { game.player.collectCoin() }
        
        assertEquals("Score should be 50", 50, game.score.score)
        assertTrue("Achievement should be unlocked at 50 points", game.achievements.achievementUnlocked)
    }
    
    @Test
    fun testEnemyHitScenario() {
        val game = GameLevelMediator()
        game.player.collectCoin() // +10
        game.player.hitEnemy()    // -5
        
        assertEquals("Score should be 5", 5, game.score.score)
    }
}
