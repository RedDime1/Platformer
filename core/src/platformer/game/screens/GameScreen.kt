package platformer.game.screens

import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.Application.ApplicationType

import com.badlogic.gdx.Gdx

import platformer.game.model.Player
import platformer.game.model.World
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.GL30
import com.badlogic.gdx.graphics.g2d.TextureRegion

import com.badlogic.gdx.graphics.Texture

import com.badlogic.gdx.graphics.g2d.SpriteBatch

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.viewport.Viewport


class GameScreen : Screen, InputProcessor{
    var cam: OrthographicCamera? = null

    //ShapeRenderer renderer = new ShapeRenderer();
    var world: World? = null
    private var spriteBatch: SpriteBatch? = null
    var texture: Texture? = null
    var textureRegions: MutableMap<String, TextureRegion> = HashMap()

    var width = 0
    var height = 0

    override fun show() {
        World.CAMERA_WIDTH = World.CAMERA_HEIGHT * Gdx.graphics.width / Gdx.graphics.height
        cam = OrthographicCamera(World.CAMERA_WIDTH, World.CAMERA_HEIGHT)
        SetCamera(World.CAMERA_WIDTH / 2f, World.CAMERA_HEIGHT / 2f)
        spriteBatch = SpriteBatch()
        loadTextures()
        world = World(Gdx.graphics.width, Gdx.graphics.height, false, spriteBatch, textureRegions)
        Gdx.input.inputProcessor = world
    }

    private fun loadTextures() {
        texture = Texture(Gdx.files.internal("images/atlas (1).png"))
        var tmpLeftRight = TextureRegion.split(texture, texture!!.width / 2, texture!!.height / 2)
        val left2 = tmpLeftRight[0][0].split(
            tmpLeftRight[0][0].regionWidth / 2,
            tmpLeftRight[0][0].regionHeight
        )
        val left = left2[0][0].split(left2[0][0].regionWidth / 4, left2[0][0].regionHeight / 8)
        textureRegions.put("player", left[0][0])
        textureRegions.put("brick1", left[0][1])
        textureRegions.put("brick2", left[1][0])
        textureRegions.put("brick3", left[1][1])
        textureRegions.put("navigation-arrows", tmpLeftRight[0][1])
        val rightbot = tmpLeftRight[1][1].split(
            tmpLeftRight[1][1].regionWidth / 2,
            tmpLeftRight[1][1].regionHeight / 2
        )
        textureRegions.put("khob", rightbot[0][1])
    }

    override fun touchDragged(x: Int, y: Int, pointer: Int): Boolean {
        //ChangeNavigation(x,y);
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

    fun SetCamera(x: Float, y: Float) {
        cam!!.position[x, y] = 0f
        cam!!.update()
    }

    override fun resize(width: Int, height: Int) {
        this.width = width
        this.height = height
        world!!.setViewport(object : Viewport(){
            init {
                screenWidth=width
                screenHeight=height
                super.update(width, height, true)
            }
        })
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
        Gdx.gl.glClearColor(232f / 255, 232f / 255, 232f / 255, 232f / 255)
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT)
        world!!.update(delta)
        world!!.draw()
    }

    override fun keyUp(keycode: Int): Boolean {
        return true
    }

    override fun touchDown(x: Int, y: Int, pointer: Int, button: Int): Boolean {
        return if (Gdx.app.type != ApplicationType.Android) false else true
        //ChangeNavigation(x,y);
    }

    override fun touchUp(x: Int, y: Int, pointer: Int, button: Int): Boolean {
        return if (Gdx.app.type != ApplicationType.Android) false else true
        //controller.resetWay();
    }
}