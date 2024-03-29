@file:Suppress("UNUSED_VARIABLE")

package ch.skyfy.json5configlib.example2

import ch.skyfy.json5configlib.*
import ch.skyfy.json5configlib.example2.config.*
import ch.skyfy.json5configlib.example2.config.Configs.GROUPS
import kotlin.reflect.jvm.jvmName
import kotlin.test.Test

class Example2 {

    @Suppress("RemoveExplicitTypeArguments")
    @Test
    fun example2() {

        // First, you have to load the configs. After that we can access them from anywhere in the code
        // If this is the first time, then no JSON files representing the configs exist.
        // They will be generated from the classes that implement the Defaultable interface or else json files that are located inside the jar will be copied where they are supposed to be
        ConfigManager.loadConfigs(arrayOf(Configs::class.java))

        // TEST TO FIX A BUG ---------------
//        println("Reload")
//        ConfigManager.reloadConfig(GROUPS)
//        GROUPS.serializableData.list.firstOrNull { group -> group.name == "DEFAULT" }?.let { group ->
//            GROUPS.updateIterableNested(Group::members, group.members) { members ->
//                val name = "skyfy16"
//                if (!members.contains(name)) members.add(name)
//            }
//        }
//        if (0 == 0) {
//            Thread.sleep(10000)
//            return
//        }
        // TEST TO FIX A BUG --------------- FIXED

        // add a global notifier. This means that every time the config is updated, the code will be called
        Configs.PLAYERS_HOMES.registerOnUpdate { operation ->
            if (operation is SetOperation<PlayersHomesConfig, *>) {
                val kMutableProperty1 = operation.prop
                val oldValue = operation.oldValue
                val newValue = operation.newValue
                val database = operation.origin
                println("Hey, member property: ${kMutableProperty1.name} for ${database::class.jvmName} has been set from $oldValue to $newValue")
                println("Updating sideboard...")
                println("Updating game...")
                println()
            } else if (operation is UpdateIterableOperation<PlayersHomesConfig, *>) {
                val kMutableProperty1 = operation.prop
                val newValue = operation.newValue
                val playersHomesConfig = operation.origin
                println("Hey, UpdateList !")
                println("member property: ${kMutableProperty1.name} for origin ${playersHomesConfig::class.jvmName} has been updated")
                println()
            }

        }

        // You can also add a notifier on a custom property
        // Here we add a notifier on maxHomes property, mean each time url is set, the code below will be invoked
        Configs.PLAYERS_HOMES.registerOnUpdateOn(Player::maxHomes) { operation ->
            if (operation is SetOperation<PlayersHomesConfig, *>) {
                val kMutableProperty1 = operation.prop
                val oldValue = operation.oldValue
                val newValue = operation.newValue
                val database = operation.origin
                println("Hey, maxHomes has been modified to $newValue")
                println()
            }
        }

        // Register a callback called every time a config is reloaded
        Configs.PLAYERS_HOMES.registerOnReload {
            println("on reloaded")
        }

        val player = Configs.PLAYERS_HOMES.serializableData.players.first { it.uuid == "1234" }
        val home = player.homes.first { it.name == "firstHome" }
//        home.nested.nested2.name = "newName"


        Configs.PLAYERS_HOMES.updateNested(Nested2::name, home.nested.nested2, "Im a member of nested2 class")
//        Configs.PLAYERS_HOMES.update(PlayersHomesConfig::str, "newValue")

//        ConfigManager.save(Configs.PLAYERS_HOMES)


        // Now we can access the config
        val configData = Configs.PLAYERS_HOMES
        val playersHomesConfig = configData.serializableData

        configData.updateIterableNested<PlayersHomesConfig, PlayersHomesConfig, Player, MutableList<Player>>(PlayersHomesConfig::players, playersHomesConfig.players) {
            it.add(
                Player(
                    mutableListOf(Home(100, 100, 100, 0.0f, 0.0f, "secret base")),
                    "ebb5c153-3f6f-4fb6-9062-20ac564e7490", // uuid for skyfy16 (me)
                    5, // 5 for me, but by default its 3
                    0, // 0 for me, but by default its 15
                    0 // 0 for me, but by default its 5
                )
            )
            it.add(
                Player(
                    mutableListOf(Home(100, 100, 100, 0.0f, 0.0f, "secret base")),
                    "8faaf447-227f-486d-be86-789ec2acb507"
                )
            )
        }
        //or
        configData.updateIterable<PlayersHomesConfig, Player, MutableList<Player>>(PlayersHomesConfig::players) {
            it.add(
                Player(
                    mutableListOf(Home(100, 100, 100, 0.0f, 0.0f, "secret base")),
                    "8faaf447-227f-486d-be86-789ec2acb507"
                )
            )
        }


        configData.updateIterableNested<PlayersHomesConfig, Player, Home, MutableList<Home>>(Player::homes, playersHomesConfig.players.first().homes) {
            for (i in 0..10) {
                it.add(Home(100, 100, 100, 0.0f, 0.0f, "secret base"))
            }
        }

        // Here we update maxHome property to 100 for first player in the list
        val playerToModify = playersHomesConfig.players.first()
        configData.updateNested<PlayersHomesConfig, Player, Int>(Player::maxHomes, playerToModify, 100)

        // Let's now sleep a short time, so we can edit manually players-homes.json file
        Thread.sleep(10_000L)
        // Now if we want to be sure that the things we have edited are loaded
        ConfigManager.reloadConfig(Configs.PLAYERS_HOMES)

        println(Configs.PLAYERS_HOMES.serializableData.players)
    }

}