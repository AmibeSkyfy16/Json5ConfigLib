package ch.skyfy.json5configlib.example4.config

import ch.skyfy.json5configlib.ConfigData
import java.nio.file.Paths

object Configs {

    /**
     * In this example we use a default JSON file located inside the jar
     */
    val CONFIG = ConfigData.invoke<Database>(Paths.get("C:\\temp\\database.json5"), "example4/database.json5", true)
}