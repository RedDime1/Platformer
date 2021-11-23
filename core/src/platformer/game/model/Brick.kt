package platformer.game.model

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2




class Brick {

    companion object{
        val SIZE = 1f
    }

    private var position:Vector2 = Vector2()
    private var form: Rectangle = Rectangle()

    constructor(position: Vector2) {
        this.position = position
        this.form.height=SIZE;
        this.form.width=SIZE;
    }

    fun getPosition(): Vector2 {
        return position
    }

    fun getForm(): Rectangle {
        return form
    }





}