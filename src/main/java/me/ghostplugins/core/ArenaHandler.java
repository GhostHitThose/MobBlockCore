package me.ghostplugins.core;

import lombok.Getter;
import java.util.UUID;

import org.bukkit.*;
import org.bukkit.entity.Player;
import java.util.Map;
import java.util.HashMap;

public class ArenaHandler {
    @Getter
    private static Map<UUID, Arena> arenas = new HashMap<>();

    public static Arena getArena(Player player){
        return arenas.get(player.getUniqueId());
    }

    public static void createArena(Player player){
        arenas.put(player.getUniqueId(), new Arena(player, new Region(
                new Location(
                        makeWorld(player.getUniqueId()),
                        0,
                        0,
                        0
                ),
                new Location(
                        makeWorld(player.getUniqueId()),
                        16*2,
                        0,
                        16*2
                )
        )));
    }

    private static World makeWorld(UUID uuid){
        if(Bukkit.getWorld(uuid + "") == null){
            WorldCreator c = new WorldCreator(uuid + "");
            c.type(WorldType.FLAT);
            return Bukkit.createWorld(c);
        }

        return Bukkit.getWorld(uuid + "");
    }
}