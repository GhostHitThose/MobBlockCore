package me.ghostplugins.core;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

/**
 *  MobBlockCore - GhostPlugins
 *
 *  Each player gets an Arena
 *  They have mobs they can grind and they can upgrade their grinders
 *  TODO : think of a way to prevent play time inflation
 *
 *
 *  -----
 *  ArenaHandler
 *  --
 *  ArrayList<Arena> arenas
 *  getArena(int id)
 *  -----
 *  Region
 *  --
 *  CornerLocation 1
 *  CornerLocation 2
 *  -----
 *  Arena
 *  --
 *  Player player
 *  Region region
 *  -----
 *  ArenaCmd
 *  --
 *  Access Arena handler and create an arena for this player
 *
 * TODO
 * Everything is done through the inventory: damage upgrades, mob spawn upgrades, island size upgrades
 *
 * A minigame version of this game and a map version with payout
 *
 * Custom tournaments and minigames that can be held by players
 *
 *
 * Player move event which checks if they are in THEIR region and loads the entire region if so
 */

public class MobBlockCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("arena").setExecutor(new ArenaCmd());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
