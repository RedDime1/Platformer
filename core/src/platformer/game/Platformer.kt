package platformer.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.ScreenUtils
import platformer.game.model.Brick
import platformer.game.screens.GameScreen
import platformer.game.*



class Platformer : Game() {
    var game: GameScreen? = null
    override fun create() {
        game = GameScreen()
        setScreen(game)
    }
    fun goHome() {}
    override fun resize(width: Int, height: Int) {}
}