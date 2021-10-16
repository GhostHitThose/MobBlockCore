package me.ghostplugins.core;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Objects;

public class ArenaCmd implements CommandExecutor {

    enum Arg {
        CREATE("create"), //
        DELETE("delete"), //
        ADD("add"), //
        RESET("reset"), //
        TOP("top"), //
        UPGRADES("upgrades"), //
        JOIN("join"), //
        HOME("home"); //

        @Getter
        private final String name;

        Arg(String name) {
            this.name = name;
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only online players can run this command.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            return false;
        }

        System.out.println();

        if (args[0].trim().toUpperCase().equals(Arg.TOP.toString())) {
            player.sendMessage("Top arenas:"); // might make this a gui
            return true;
        }

        if (ArenaHandler.getArenas().containsKey(player.getUniqueId())) {
            if (args[0].trim().toUpperCase().equals(Arg.CREATE.toString())) {
                player.sendMessage("You already have an Arena under your name.");
                return true;
            } else if (args[0].trim().toUpperCase().equals(Arg.DELETE.toString())) {
                player.sendMessage("Deleted Arena"); // TODO : check twice
                return true;
            } else if (args[0].trim().toUpperCase().equals(Arg.HOME.toString())) {
                player.sendMessage("Starting teleport to home");
                player.teleport(ArenaHandler.getArena(player).getRegion().getCenter().add(0, 1, 0));
                return true;
            } else if (args[0].trim().toUpperCase().equals(Arg.ADD.toString())) {
                player.sendMessage("Added player"); // TODO : need to check if they have 2nd args
                return true;
            } else if (args[0].trim().toUpperCase().equals(Arg.RESET.toString())) {
                player.sendMessage("Resetting your Arena");
                return true;
            } else if (args[0].trim().toUpperCase().equals(Arg.UPGRADES.toString())) {
                player.sendMessage("Upgrades: "); // open gui
                return true;
            }
        } else {
            if (args[0].trim().toUpperCase().equals(Arg.JOIN.toString())) {
                player.sendMessage("Joined Arena"); // TODO : need to check if they have 2nd args
                return true;
            } else if (args[0].trim().toUpperCase().equals(Arg.CREATE.toString())) {
                player.sendMessage("Creating Arena");
                ArenaHandler.createArena(player);
                player.teleport(ArenaHandler.getArena(player).getRegion().getCenter().add(0, 1, 0));
                return true;
            }
        }

        if(args[0].equals("spawn")){
            player.teleport(new Location(Bukkit.getWorld("world"), 0,0,0,0,0));
        }

        return false;
    }
}