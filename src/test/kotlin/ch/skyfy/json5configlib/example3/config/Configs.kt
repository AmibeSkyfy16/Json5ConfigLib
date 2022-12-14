package ch.skyfy.json5configlib.example3.config

import ch.skyfy.json5configlib.ConfigData
import java.nio.file.Paths

object Configs {

    /**
     * In this example, we use a class called DefaultConfig that will generate a default configuration
     */
    val CONFIG = ConfigData.invoke<Config, DefaultConfig>(Paths.get("C:\\temp\\games.json5"), true)
}