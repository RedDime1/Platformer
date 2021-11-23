package platformer.game

import com.badlogic.gdx.backends.android.AndroidApplication
import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import platformer.game.Platformer

class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()
        config.useAccelerometer = false;
        config.useCompass = false;
        config.useWakelock = true;
        initialize(Platformer(), config)
    }
}