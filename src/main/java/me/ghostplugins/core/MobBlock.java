package me.ghostplugins.core;

import org.bukkit.plugin.java.JavaPlugin;

/**
 *  MobBlock - GhostPlugins
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
 */

public final class MobBlock extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
