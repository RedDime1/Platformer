package platformer.game.model

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2




class Player{
    enum class State {
        NONE, WALKING, DEAD
    }

    companion object {
        val SPEED = 2f
        val SIZE = 0.7f
    }

    private var position: Vector2 = Vector2()
    private var direction = Vector2()
    private var form: Rectangle = Rectangle()
    var state = State.NONE

    constructor(position: Vector2) {
        this.position = position
        this.form.height=SIZE
        this.form.width=SIZE
    }

    fun getPosition(): Vector2 {
        return position;
    }
    fun getDirection(): Vector2 {
        return direction;
    }
    fun getForm():Rectangle{
        return form;
    }

    fun update(delta: Float) {
        position.add(direction.scl(delta))

    }


}