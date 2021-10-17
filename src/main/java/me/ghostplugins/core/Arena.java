package me.ghostplugins.core;

import lombok.Getter;
import me.ghostplugins.core.utils.Cuboid;
import org.bukkit.Axis;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Arena {
    @Getter
    private final Player player;
    @Getter
    private final Cuboid cuboid;

    public Arena(Player player, Cuboid cuboid){
        this.player = player;
        this.cuboid = cuboid;

        // TODO : spawn NPCs, create a section designed for a mob arena and put spawners there
        cuboid.setMinWall(Axis.X, Material.STONE);
        cuboid.setMinWall(Axis.Y, Material.STONE);
        cuboid.setMinWall(Axis.Z, Material.STONE);
    }

    public void buildTemplate(int id){

    }
}