package ch.skyfy.json5configlib.example2.config

import ch.skyfy.json5configlib.Validatable
import io.github.xn32.json5k.SerialComment
import kotlinx.serialization.Serializable

@Serializable
data class Home(
    @SerialComment("Position x") var x: Int,
    var y: Int,
    var z: Int,
    var pitch: Float,
    var yaw: Float,
    var name: String,
    var nested: Nested = Nested("1", Nested2("2"))
) : Validatable

@Serializable
data class Nested(val name: String, val nested2: Nested2) : Validatable

@Serializable
data class Nested2(var name: String) : Validatable