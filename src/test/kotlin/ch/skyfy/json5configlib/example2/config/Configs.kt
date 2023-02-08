package ch.skyfy.json5configlib.example2.config

import ch.skyfy.json5configlib.ConfigData
import java.nio.file.Paths

object Configs {
    val PLAYERS_HOMES = ConfigData.invoke<PlayersHomesConfig, DefaultPlayerHomeConfig>(Paths.get("C:\\temp\\players-homes.json5"), true)

//    @JvmField
//    val GROUPS = ConfigData.invokeSpecial<Groups>(Paths.get("C:\\temp\\groups.json5"), true)

    @JvmField
    val GROUPS = ConfigData.invoke<Groups, DefaultGroups>(Paths.get("C:\\temp\\groups.json5"), true)
}