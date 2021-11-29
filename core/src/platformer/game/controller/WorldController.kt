package platformer.game.controller

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import platformer.game.model.Player
import platformer.game.model.World
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class WalkingControl : Actor{

    var SIZE = 4f
    var CSIZE = 3f

    var CIRCLERADIUS = 1.5f
    var CONTRLRADIUS = 3f
    var angle = 0f
    var world: World? = null
    private var offsetPosition = Vector2()
    private var position = Vector2()
    private var bounds: Rectangle = Rectangle()

    constructor(pos: Vector2?, world: World) {
        position = pos!!
        bounds.width = SIZE
        bounds.height = SIZE
        this.world = world
        getOffsetPosition()!!.x = 0F
        getOffsetPosition()!!.y = 0F
        height = SIZE * world.ppuY
        width = SIZE * world.ppuX
        x = position.x * world.ppuX
        y = position.y * world.ppuY
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

            //ïðè ïåðåòàñêèâàíèè
            override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
                withControl(x, y)
            }

            //óáèðàåì ïàëåö ñ ýêðàíà
            override fun touchUp(
                event: InputEvent?,
                x: Float,
                y: Float,
                pointer: Int,
                button: Int
            ) {
                getOffsetPosition()!!.x = 0F
                getOffsetPosition()!!.y = 0F
            }
        })
    }

    fun draw(batch: SpriteBatch, parentAlfa: Float) {
        batch.draw(world!!.textureRegions!!["navigation-arrows"], x, y, width, height)
        batch.draw(
            world!!.textureRegions!!["khob"],
            (position.x + SIZE/2 - CSIZE / 2 + getOffsetPosition()!!.x) as Float * world!!.ppuX,
            (position.y + SIZE / 2 - CSIZE / 2 + getOffsetPosition()!!.y) as Float * world!!.ppuY,
            CSIZE * world!!.ppuX, CSIZE * world!!.ppuY
        )
    }

    override fun hit(x: Float, y: Float, touchable: Boolean): Actor? {
        //Ïðîöåäóðà ïðîâåðêè. Åñëè òî÷êà â ïðÿìîóãîëüíèêå àêò¸ðà, âîçâðàùàåì àêò¸ðà.
        return if (x > 0 && x < width && y > 0 && y < height) this else null
    }


    fun getPosition(): Vector2? {
        return position
    }

    fun getOffsetPosition(): Vector2? {
        return offsetPosition
    }

    fun getBounds(): Rectangle? {
        return bounds
    }

    enum class Keys {
        LEFT, RIGHT, UP, DOWN
    }


    fun withControl(x: Float, y: Float) {

        //òî÷êà êàñàíèÿ îòíîñèòåëüíî öåíòðà äæîéñòèêà
        val calcX = x / world!!.ppuX - SIZE / 2
        val calcY = y / world!!.ppuY - SIZE / 2

        //îïðåäåëÿåì ëåæèò ëè òî÷êà êàñàíèÿ â îêðóæíîñòè äæîéñòèêà
        if (calcX * calcX + calcY * calcY <= CONTRLRADIUS * CONTRLRADIUS) {
            world!!.resetSelected()

            //ïðåäåëÿåì óãîë êàñàíèÿ
            var angle = Math.atan((calcY / calcX).toDouble()) * 180 / Math.PI

            //óãîë áóäåò â äèàïîçîíå [-90;90]. Óäîáíåå ðàáîòàòü, åñëè îí â äèàïîçîíå [0;360]
            //ïîýòîìó ïîøàìàíèì íåìíîãî
            if (angle > 0 && calcY < 0) angle += 180.0
            if (angle < 0) if (calcX < 0) angle = 180 + angle else angle += 360.0

            //â çàâèñèìîñòè îò óãëà, óêàçûâàíèåì íàïðàâëåíèå, êóäà äâèãàòü èãðîêà
            if (angle > 40 && angle < 140) (world!!.selectedActor as Player?)!!.upPressed()
            if (angle > 220 && angle < 320) (world!!.selectedActor as Player?)!!.downPressed()
            if (angle > 130 && angle < 230) (world!!.selectedActor as Player?)!!.leftPressed()
            if (angle < 50 || angle > 310) (world!!.selectedActor as Player?)!!.rightPressed()


            //äâèãàåì èãðîêà
            (world!!.selectedActor as Player?)!!.processInput()
            angle = (angle * Math.PI / 180)
            getOffsetPosition()!!.x =
                (if (calcX * calcX + calcY * calcY > 1f) Math.cos(angle) * 0.75f else calcX) as Float
            getOffsetPosition()!!.y =
                (if (calcX * calcX + calcY * calcY > 1f) Math.sin(angle) * 0.75f else calcY) as Float
        } else {
            world!!.resetSelected()
            getOffsetPosition()!!.x = 0f
            getOffsetPosition()!!.y = 0f
        }
    }
}