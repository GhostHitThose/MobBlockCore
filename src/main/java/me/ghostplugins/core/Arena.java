package me.ghostplugins.core;

import org.bukkit.entity.Player;
import lombok.Getter;

public class Arena {
    @Getter
    private final Player player;
    @Getter
    private final Region region;

    public Arena(Player player, Region region){
        this.player = player;
        this.region = region;

        // TODO : spawn NPCs, create a section designed for a mob arena and put spawners there
    }
}