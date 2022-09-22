package ch.skyfy.json5configlib.example5.config

import ch.skyfy.json5configlib.ConfigData
import ch.skyfy.jsonconfiglib.example5.config.AConfig
import ch.skyfy.jsonconfiglib.example5.config.DefaultAConfig
import java.nio.file.Paths

object Configs {

    val CONFIG = ConfigData.invoke<AConfig>(Paths.get("C:\\temp\\aconfig.json5"), "example5/aconfig.json5", true)

//    val CONFIG = ConfigData.invoke<AConfig, DefaultAConfig>(Paths.get("C:\\temp\\aconfig.json5"), true)
}