package platformer.game.model

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.physics.box2d.World
import platformer.game.controller.MyContactListener


class GWorld {
    private var bricks = mutableListOf<Brick>()
    private var player: Player? = null
    var world: World? = null

    var width =0
    var height = 0

    constructor(){
        width = 30
        height = 8
        world = World(Vector2(0f, -20f), true)
        createWorld()
    }

    fun getBricks(): MutableList<Brick> {
        return bricks
    }
    fun getPlayer(): Player? {
        return player
    }


    fun createWorld() {
        player = Player(Vector2(6f, 2f))
        bricks.add(Brick(Vector2(0f, 0f)))
        bricks.add(Brick(Vector2(1f, 0f)))
        bricks.add(Brick(Vector2(2f, 0f)))
        bricks.add(Brick(Vector2(3f, 0f)))
        bricks.add(Brick(Vector2(4f, 0f)))
        bricks.add(Brick(Vector2(5f, 0f)))
        bricks.add(Brick(Vector2(6f, 0f)))
        bricks.add(Brick(Vector2(7f, 0f)))
    }


}