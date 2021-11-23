package platformer.game.view

import com.badlogic.gdx.graphics.OrthographicCamera

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import platformer.game.model.GWorld
import com.badlogic.gdx.graphics.g2d.TextureRegion

import com.badlogic.gdx.graphics.Texture

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.Gdx
import kotlin.collections.HashMap
import platformer.game.model.Brick
import platformer.game.model.Player








class WorldRenderer {
    var CAMERA_WIDTH = 8f
    var CAMERA_HEIGHT = 5f

    private var spriteBatch: SpriteBatch? = null

    var texture: Texture? = null

    var textureRegions: MutableMap<String, TextureRegion> = HashMap()

    private var GWorld: GWorld? = null

    var cam: OrthographicCamera? = null

    var renderer = ShapeRenderer()

    var width = 0
    var height = 0
    var ppuX = 0f
    var ppuY = 0f

    constructor(GWorld: GWorld?) {
        this.spriteBatch = SpriteBatch()
        this.GWorld = GWorld
        cam = OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT)

        SetCamera(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f)
        loadTextures()
    }

    fun setSize(w: Int, h: Int) {
        width = w
        height = h
        ppuX = width.toFloat() / CAMERA_WIDTH
        ppuY = height.toFloat() / CAMERA_HEIGHT
    }

    fun SetCamera(x: Float, y: Float) {
        cam!!.position[x, y] = 0f
        cam!!.update()
    }

    fun render() {
        spriteBatch!!.begin()
        drawBricks()
        drawPlayer()
        spriteBatch!!.end()
    }

    private fun drawBricks() {
        var i = 0
        for (brick in GWorld!!.getBricks()) {
            spriteBatch!!.draw(
                textureRegions["brick" + (i % 3 + 1)],
                brick.getPosition().x * ppuX,
                brick.getPosition().y * ppuY,
                Brick.SIZE * ppuX,
                Brick.SIZE * ppuY
            )
            ++i
        }
    }


    private fun drawPlayer() {
        spriteBatch!!.draw(
            textureRegions["player"],
            GWorld!!.getPlayer()!!.getPosition().x * ppuX,
            GWorld!!.getPlayer()!!
                .getPosition().y * ppuY,
            Player.SIZE * ppuX,
            Player.SIZE * ppuY
        )
    }

    private fun loadTextures() {
        texture = Texture(Gdx.files.internal("images/atlas01.png"))
        val tmp = TextureRegion.split(texture, texture!!.width / 2, texture!!.height / 2)
        textureRegions.put("player", tmp[0][0])
        textureRegions.put("brick1", tmp[0][1])
        textureRegions.put("brick2", tmp[1][0])
        textureRegions.put("brick3", tmp[1][1])
    }
}