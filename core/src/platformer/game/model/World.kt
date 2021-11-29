package platformer.game.model

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.Viewport
import platformer.game.controller.WalkingControl


class World : Stage{
    private var player: Player? = null
    var ppuX = 0f
    var ppuY = 0f
    var selectedActor: Actor? = null
    var textureRegions: Map<String, TextureRegion>? = null
    companion object {
        var CAMERA_WIDTH = 12f
        var CAMERA_HEIGHT = 8f
    }

    fun update(delta: Float) {
        for (actor in this.actors) if (actor is Player) actor.update(delta)
    }

    constructor(x: Int, y: Int, b: Boolean, spriteBatch: SpriteBatch?, textureRegions: Map<String, TextureRegion>?):
            super(object: Viewport(){
                                       init {
                                           screenHeight=y
                                           screenWidth=x
                                           apply(b)
                                       }
                                    }, spriteBatch){
        this.textureRegions = textureRegions
        ppuX = width / CAMERA_WIDTH
        ppuY = height / CAMERA_HEIGHT
        addActor(Player(Vector2(4F, 2F), this))
        addActor(Player(Vector2(4F, 4F), this))
        addActor(WalkingControl(Vector2(0f, 0f), this))
    }

    fun setPP(x: Float, y: Float) {
        ppuX = x
        ppuY = y
    }

    fun getPlayer(): Player? {
        return player
    }

    override fun touchDown(x: Int, y: Int, pointer: Int, button: Int): Boolean {
        super.touchDown(x, y, pointer, button)
        return true
    }

    fun resetSelected() {
        if (selectedActor != null && selectedActor is Player) {
            (selectedActor as Player).resetWay()
        }
    }

    override fun touchUp(x: Int, y: Int, pointer: Int, button: Int): Boolean {
        super.touchUp(x, y, pointer, button)
        resetSelected()
        return true
    }

    override fun touchDragged(x: Int, y: Int, pointer: Int): Boolean {
        if (selectedActor != null) super.touchDragged(x, y, pointer)
        return true
    }

    override fun hit(x: Float, y: Float, touchable: Boolean): Actor? {
        val actor = super.hit(x, y, touchable)
        //åñëè âûáðàëè àêò¸ðà
        if (actor != null && actor is Player) //çàïîìèíàåì
            selectedActor = actor
        return actor
    }
}