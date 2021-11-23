package platformer.game.screens

import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.Application.ApplicationType

import com.badlogic.gdx.Gdx

import platformer.game.model.Player

import platformer.game.controller.WorldController
import platformer.game.model.GWorld
import platformer.game.view.WorldRenderer
import com.badlogic.gdx.graphics.GL20









class GameScreen : Screen, InputProcessor{
    private var GWorld: GWorld? = null
    private var renderer: WorldRenderer? = null
    private var controller: WorldController? = null

    private var width = 0
    private  var height = 0

    override fun show() {
        GWorld = GWorld()
        renderer = WorldRenderer(GWorld)
        controller = WorldController(GWorld!!)
        Gdx.input.inputProcessor = this
    }

    override fun touchDragged(x: Int, y: Int, pointer: Int): Boolean {
        ChangeNavigation(x, y)
        return false
    }

    fun touchMoved(x: Int, y: Int): Boolean {
        return false
    }

    override fun mouseMoved(x: Int, y: Int): Boolean {
        return false
    }

    override fun scrolled(amountX: Float, amountY: Float): Boolean {
        return false;
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun resize(width: Int, height: Int) {
        renderer!!.setSize(width, height)
        this.width = width
        this.height = height
    }

    override fun hide() {
        Gdx.input.inputProcessor = null
    }

    override fun pause() {}

    override fun resume() {}

    override fun dispose() {
        Gdx.input.inputProcessor = null
    }


    override fun keyDown(keycode: Int): Boolean {
        return true
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        controller!!.update(delta)
        renderer!!.render()
    }

    override fun keyUp(keycode: Int): Boolean {
        return true
    }

    private fun ChangeNavigation(x: Int, y: Int) {
        controller!!.resetWay()
        if (height - y > controller!!.player!!.getPosition().y * renderer!!.ppuY) controller!!.upPressed()
        if (height - y < controller!!.player!!.getPosition().y * renderer!!.ppuY) controller!!.downPressed()
        if (x < controller!!.player!!.getPosition().x * renderer!!.ppuX) controller!!.leftPressed()
        if (x > (controller!!.player!!.getPosition().x + Player.SIZE) * renderer!!.ppuX) controller!!.rightPressed()
    }

    override fun touchDown(x: Int, y: Int, pointer: Int, button: Int): Boolean {
        if (Gdx.app.type != ApplicationType.Android) return false
        ChangeNavigation(x, y)
        return true
    }

    override fun touchUp(x: Int, y: Int, pointer: Int, button: Int): Boolean {
        if (Gdx.app.type != ApplicationType.Android) return false
        controller!!.resetWay()
        return true
    }

}