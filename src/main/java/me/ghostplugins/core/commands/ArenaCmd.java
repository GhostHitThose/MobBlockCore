package me.ghostplugins.core.commands;

import me.ghostplugins.core.MobBlockCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCmd implements CommandExecutor {

    private final MobBlockCore main;

    public ArenaCmd(){
        this.main = MobBlockCore.getInstance();
    }

    enum Arg {
        CREATE, //
        DELETE, //
        ADD, //
        RESET, //
        TOP, //
        UPGRADES, //
        JOIN, //
        HOME,
        SPAWN; //
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

        if (Arg.valueOf(args[0].trim().toUpperCase()) == Arg.TOP) {
            player.sendMessage("Top arenas:"); // might make this a gui
        }
        if(Arg.valueOf(args[0].trim().toUpperCase()) == Arg.SPAWN){
            player.teleport(new Location(Bukkit.getWorld("world"), 0,0,0,0,0));
        } // DELETE AFTER TESTING

        if(main.getArenaHandler().getPlayers().containsKey(player.getUniqueId())){
            switch (Arg.valueOf(args[0].trim().toUpperCase())){
                case CREATE:
                    player.sendMessage("You already have an Arena under your name.");
                    break;
                case DELETE:
                    player.sendMessage("Deleted Arena"); // TODO : check twice
                    main.getArenaHandler().deleteArena(player);
                    break;
                case HOME:
                    player.sendMessage("Starting teleport to home");
                    player.teleport(main.getArenaHandler().getArena(player).getCuboid().getCenter().add(0, 1-main.getArenaHandler().getArena(player).getCuboid().getCenter().getWorld().getMaxHeight()/2, 0));
                    break;
                case ADD:
                    player.sendMessage("Added player"); // TODO : need to check if they have 2nd args
                    break;
                case RESET:
                    player.sendMessage("Resetting your Arena");
                    break;
                case UPGRADES:
                    player.sendMessage("Upgrades: "); // open gui
                    break;
            }
        } else {
            // if they dont already have an arena
            switch (Arg.valueOf(args[0].trim().toUpperCase())) {
                case CREATE:
                    player.sendMessage("Creating Arena");
                    long last = System.currentTimeMillis();
                    main.getArenaHandler().createArena(player);
                    long time = (System.currentTimeMillis()-last)/1000;
                    player.sendMessage("Created Arena in " + time + " seconds.");
                    break;
                case JOIN:
                    player.sendMessage("Joined Arena"); // TODO : need to check if they have 2nd args
                    break;
            }
        }

        return true;
    }
}