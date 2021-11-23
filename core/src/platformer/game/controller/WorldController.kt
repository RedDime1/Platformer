package platformer.game.controller

import platformer.game.model.Player
import platformer.game.model.GWorld


class WorldController {

    constructor(GWorld: GWorld){
        player = GWorld.getPlayer()
    }

    enum class Keys {
        LEFT, RIGHT, UP, DOWN
    }


    var player: Player? = null

    var keys: MutableMap<Keys, Boolean> = HashMap()

    init {
        keys[Keys.LEFT] = false
        keys[Keys.RIGHT] = false
        keys[Keys.UP] = false
        keys[Keys.DOWN] = false
    }

    fun leftPressed() {
        keys.put(Keys.LEFT, true)
    }

    fun rightPressed() {
        keys.put(Keys.RIGHT, true)
    }

    fun upPressed() {
        keys.put(Keys.UP, true)
    }

    fun downPressed() {
        keys.put(Keys.DOWN, true)
    }

    fun leftReleased() {
        keys.put(Keys.LEFT, false)
    }

    fun rightReleased() {
        keys.put(Keys.RIGHT, false)
    }

    fun upReleased() {
        keys.put(Keys.UP, false)
    }

    fun downReleased() {
        keys.put(Keys.DOWN, false)
    }


    fun update(delta: Float) {
        processInput()
        player!!.update(delta)
    }

    fun resetWay() {
        rightReleased()
        leftReleased()
        downReleased()
        upReleased()
    }

    private fun processInput() {
        if (keys[Keys.LEFT]!!) player!!.getDirection().x = -Player.SPEED
        if (keys[Keys.RIGHT]!!) player!!.getDirection().x = Player.SPEED
        if (keys[Keys.UP]!!) player!!.getDirection().y = Player.SPEED
        if (keys[Keys.DOWN]!!) player!!.getDirection().y = -Player.SPEED


        if (keys[Keys.LEFT]!! && keys[Keys.RIGHT]!! || !keys[Keys.LEFT]!! && !keys[Keys.RIGHT]!!) player!!.getDirection().x =
            0f
        if (keys[Keys.UP]!! && keys[Keys.DOWN]!! || !keys[Keys.UP]!! && !keys[Keys.DOWN]!!) player!!.getDirection().y =
            0f
    }
}