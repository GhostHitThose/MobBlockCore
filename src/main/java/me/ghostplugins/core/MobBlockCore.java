package me.ghostplugins.core;

import lombok.Getter;
import me.ghostplugins.core.commands.ArenaCmd;
import me.ghostplugins.core.handlers.ArenaHandler;
import org.bukkit.plugin.java.JavaPlugin;

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
 *  Cuboid
 *  --
 *  CornerLocation 1
 *  CornerLocation 2
 *  -----
 *  Arena
 *  --
 *  Player player
 *  Cuboid region
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

    @Getter
    private static MobBlockCore instance;
    @Getter
    private ArenaHandler arenaHandler;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        arenaHandler = new ArenaHandler();

        getCommand("arena").setExecutor(new ArenaCmd());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
