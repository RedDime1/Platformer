package platformer.game.model

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.graphics.g2d.SpriteBatch


class Player : Actor{
    enum class State {
        NONE, WALKING, DEAD
    }
    enum class Direction {
        LEFT, RIGHT, UP, DOWN, NONE
    }
    companion object {
        val SPEED = 2f
        val SIZE = 1f
    }

    var world: World? = null
    private var position: Vector2 = Vector2()
    private var velocity = Vector2()
    private var form: Rectangle = Rectangle()
    var state = State.NONE
    var facingLeft = true

    constructor(position: Vector2, world: platformer.game.model.World) {
        this.world=world
        this.position = position
        setHeight(SIZE*world.ppuY);
        setWidth(SIZE*world.ppuX);
        setX(position.x*world.ppuX);
        setY(position.y*world.ppuY);
        addListener(object : InputListener() {
            override fun touchDown(
                event: InputEvent?,
                x: Float,
                y: Float,
                pointer: Int,
                button: Int
            ): Boolean {
                return true
            }

            override fun touchUp(
                event: InputEvent?,
                x: Float,
                y: Float,
                pointer: Int,
                button: Int
            ) {
            }
        })
    }

    fun getPosition(): Vector2 {
        return position;
    }
    fun getVelocity(): Vector2 {
        return velocity;
    }
    fun getForm():Rectangle{
        return form;
    }

    fun update(delta: Float) {
        position.add(velocity.scl(delta))
        setX(position.x*world!!.ppuX);
        setY(position.y*world!!.ppuY);
    }

    fun draw(batch: SpriteBatch, parentAlfa: Float) {
        if (this == world!!.selectedActor) {
            batch.setColor(0.5f, 0.5f, 0.5f, 0.5f)
        }
        batch.draw(world!!.textureRegions!!.get("player"), x, y, width, height)
        batch.setColor(1f, 1f, 1f, 1f)
    }

    override fun hit(x: Float, y: Float, touchable: Boolean): Actor? {
        return if (x > 0 && x < width && y > 0 && y < height) this else null
    }

    fun resetWay() {
        rightReleased()
        leftReleased()
        downReleased()
        upReleased()
        getVelocity().x = 0F
        getVelocity().y = 0F
    }
    fun processInput() {
        if (direction.get(Keys.LEFT)!!) getVelocity().x = -SPEED
        if (direction.get(Keys.RIGHT)!!) getVelocity().x = SPEED
        if (direction.get(Keys.UP)!!) getVelocity().y = SPEED
        if (direction.get(Keys.DOWN)!!) getVelocity().y = -SPEED
        if (direction.get(Keys.LEFT)!! && direction.get(Keys.RIGHT)!! ||
            !direction.get(Keys.LEFT)!! && !direction.get(Keys.RIGHT)!!
        ) getVelocity().x = 0F
        if (direction.get(Keys.UP)!! && direction.get(Keys.DOWN)!! ||
            !direction.get(Keys.UP)!! && !direction.get(Keys.DOWN)!!
        ) getVelocity().y = 0F
    }

    enum class Keys {
        LEFT, RIGHT, UP, DOWN
    }

    var direction: MutableMap<Keys, Boolean> = HashMap()
    init {
        direction[Keys.LEFT] = false
        direction[Keys.RIGHT] = false
        direction[Keys.UP] = false
        direction[Keys.DOWN] = false
    }

    fun leftPressed() {
        direction.put(Keys.LEFT, true)
    }

    fun rightPressed() {
        direction.put(Keys.RIGHT, true)
    }

    fun upPressed() {
        direction.put(Keys.UP, true)
    }

    fun downPressed() {
        direction.put(Keys.DOWN, true)
    }

    fun leftReleased() {
       direction.put(Keys.LEFT, false)
    }

    fun rightReleased() {
        direction.put(Keys.RIGHT, false)
    }

    fun upReleased() {
        direction.put(Keys.UP, false)
    }

    fun downReleased() {
        direction.put(Keys.DOWN, false)
    }

}