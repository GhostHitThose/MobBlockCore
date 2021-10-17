package me.ghostplugins.core.handlers;

import lombok.Getter;
import me.ghostplugins.core.Arena;
import me.ghostplugins.core.MobBlockCore;
import me.ghostplugins.core.utils.Cuboid;
import me.ghostplugins.core.utils.FileUtil;
import me.ghostplugins.core.utils.GenerateVoidWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class ArenaHandler {
    @Getter
    private final Map<UUID, Arena> players;

    public ArenaHandler(){
        this.players = new HashMap<>();
        loadPlayers();
    }

    public Arena getArena(Player player){
        return players.get(player.getUniqueId());
    }

    public void deleteArena(Player player) {
        players.get(player.getUniqueId()).getCuboid().getWorld().getPlayers().forEach(p -> p.teleport(new Location(Bukkit.getWorld("world"), 0,0,0,0,0)));
        Bukkit.unloadWorld(player.getUniqueId().toString(), true);
        Bukkit.getScheduler().runTaskAsynchronously(MobBlockCore.getInstance(), () -> {
            File file = new File(MobBlockCore.getInstance().getDataFolder() + "/../../" + player.getUniqueId().toString());
            Path path = Paths.get(file.getPath());

            try (Stream<Path> walk = Files.walk(path)) {
                walk.sorted(Comparator.reverseOrder()).forEach(FileUtil::deleteDirectoryJava8Extract);
            } catch (IOException e) {
                e.printStackTrace();
            }
            players.remove(player.getUniqueId());
        });
    }

    public void createArena(Player player){
        players.put(player.getUniqueId(), new Arena(player, new Cuboid(
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

        players.get(player.getUniqueId()).buildTemplate(0);
    }

    private World makeWorld(UUID uuid){
        if(Bukkit.getWorld(uuid + "") == null){
            WorldCreator wc = new WorldCreator(uuid.toString());
            wc.generator(new GenerateVoidWorld());
            wc.createWorld();
        }

        return Bukkit.getWorld(uuid.toString());
    }

    private void loadPlayers(){
        Bukkit.getWorlds().forEach(world -> {
            UUID uuid;
            try {
                uuid = UUID.fromString(world.getName());
            } catch(IllegalArgumentException e){
                return;
            }
            if(Bukkit.getPlayer(uuid) != null)
                createArena(Bukkit.getPlayer(uuid));
        });
    }
}