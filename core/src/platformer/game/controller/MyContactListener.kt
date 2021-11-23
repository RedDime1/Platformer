package platformer.game.controller
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.Manifold
import com.badlogic.gdx.physics.box2d.ContactImpulse




class MyContactListener : ContactListener {

    var world:World?=null

    constructor(world: World) : super()



    override fun beginContact(contact: Contact?) {

    }

    override fun endContact(contact: Contact?) {

    }

    override fun preSolve(contact: Contact?, oldManifold: Manifold?) {

    }

    override fun postSolve(contact: Contact?, impulse: ContactImpulse?) {

    }

}