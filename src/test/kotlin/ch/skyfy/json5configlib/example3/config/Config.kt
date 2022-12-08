package ch.skyfy.json5configlib.example3.config

import ch.skyfy.json5configlib.Defaultable
import ch.skyfy.json5configlib.Validatable

@kotlinx.serialization.Serializable
data class Config(
    val dayOfAuthorizationOfThePvP: Int,
    val dayOfAuthorizationOfTheEntryInTheNether: Int,
    val allowEnderPearlAssault: Boolean
) : Validatable

@Suppress("unused")
class DefaultConfig : Defaultable<Config> {
    override fun getDefault() = Config(2, 4, false)
}