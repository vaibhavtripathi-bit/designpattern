package com.vaibhav.designpattern.mediator.gamesystem

// Base Game Object
abstract class GameObject(protected var mediator: GameMediator? = null) {
    fun registerMediator(mediator: GameMediator) {
        this.mediator = mediator
    }
}

// 1. The Player - The main sender of events
class Player : GameObject() {
    fun jump() {
        println("Player: Jumped!")
        mediator?.notify(this, "jump")
    }

    fun collectCoin() {
        println("Player: Collected a coin!")
        mediator?.notify(this, "coinCollected")
    }

    fun hitEnemy() {
        println("Player: Hit an enemy!")
        mediator?.notify(this, "enemyHit")
    }
}

// 2. Sound System - Reacts to events
class SoundSystem : GameObject() {
    fun playSound(sound: String) {
        println("SoundSystem: Playing '$sound' sound.")
    }
}

// 3. Score System - Reacts to events
class ScoreSystem : GameObject() {
    var score: Int = 0
        private set

    fun addPoints(points: Int) {
        score += points
        println("ScoreSystem: Score updated to $score")
    }
}

// 4. Achievement System - Reacts to Score events (complex interaction)
class AchievementSystem : GameObject() {
    var achievementUnlocked: Boolean = false
        private set

    fun unlockAchievement(name: String) {
        if (!achievementUnlocked) { // Simple "first achievement" logic
            achievementUnlocked = true
            println("AchievementSystem: UNLOCKED ACHIEVEMENT: '$name'!")
        }
    }
}

// The Concrete Mediator
class GameLevelMediator : GameMediator {
    val player = Player()
    val sound = SoundSystem()
    val score = ScoreSystem()
    val achievements = AchievementSystem()

    init {
        player.registerMediator(this)
        sound.registerMediator(this)
        score.registerMediator(this)
        achievements.registerMediator(this)
    }

    override fun notify(sender: GameObject, event: String) {
        when (event) {
            "jump" -> {
                sound.playSound("jump_sfx")
            }
            "coinCollected" -> {
                sound.playSound("coin_sfx")
                score.addPoints(10)
                checkScoreAchievements()
            }
            "enemyHit" -> {
                sound.playSound("damage_sfx")
                score.addPoints(-5)
            }
        }
    }

    private fun checkScoreAchievements() {
        if (score.score >= 50) {
            achievements.unlockAchievement("Coin Master")
        }
    }
}
