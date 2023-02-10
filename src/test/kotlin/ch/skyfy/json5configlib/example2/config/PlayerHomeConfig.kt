package ch.skyfy.json5configlib.example2.config

import ch.skyfy.json5configlib.Defaultable
import ch.skyfy.json5configlib.Validatable
import kotlinx.serialization.Serializable

@Serializable
data class PlayersHomesConfig(var players: MutableList<Player>, var str: String = "") : Validatable {
    override fun validateImpl(errors: MutableList<String>) {
        players.forEach { it.validateImpl(errors) } // validation for player object
    }
}

class DefaultPlayerHomeConfig : Defaultable<PlayersHomesConfig> {
    override fun getDefault() = PlayersHomesConfig(
        mutableListOf(
            Player(
                mutableListOf(
                    Home(
                        1,
                        1,
                        1,
                        1f,
                        1f,
                        "firstHome",
                        Nested("n1", Nested2("n2"))
                    )
                ),
                "1234",
                1, 1,
                1
            )
        )
    )
}