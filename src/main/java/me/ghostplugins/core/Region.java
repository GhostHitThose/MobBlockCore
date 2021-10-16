package me.ghostplugins.core;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Objects;

public class Region {
    @Getter
    private final Location minLocation, maxLocation;
    @Getter
    private Location corner1, corner2;

    public Region(Location corner1, Location corner2){
        if(!(Objects.equals(corner1.getWorld(), corner2.getWorld()))){
            minLocation = null;
            maxLocation = null;
            return;
        }

        this.corner1 = corner1;
        this.corner2 = corner2;

        World world = corner1.getWorld();
        double x2 = Math.max(corner1.getX(), corner2.getX());
        double y2 = Math.max(corner1.getY(), corner2.getY());
        double z2 = Math.max(corner1.getZ(), corner2.getZ());
        double x1 = Math.min(corner1.getX(), corner2.getX());
        double y1 = Math.min(corner1.getY(), corner2.getY());
        double z1 = Math.min(corner1.getZ(), corner2.getZ());

        minLocation = new Location(world, x1, y1, z1);
        maxLocation = new Location(world, x2, y2, z2);
    }

    public boolean contains(Location location){
        int loopX = (int)(maxLocation.getX()-minLocation.getX());
        int loopY = (int)(maxLocation.getY()-minLocation.getY());
        int loopZ = (int)(maxLocation.getZ()-minLocation.getZ());

        for(int x = 0; x < loopX; x++){
            for(int y = 0; y < loopY; y++){
                for(int z = 0; z < loopZ; z++){
                    Location currentLoc = minLocation.add(x,y,z);

                    if(currentLoc.equals(location)){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void loadChunks(){
        int loopX = (int)(maxLocation.getX()-minLocation.getX());
        int loopY = (int)(maxLocation.getY()-minLocation.getY());
        int loopZ = (int)(maxLocation.getZ()-minLocation.getZ());

        for(int x = 0; x < loopX; x++){
            for(int y = 0; y < loopY; y++){
                for(int z = 0; z < loopZ; z++){
                    Location currentLoc = minLocation.add(x,y,z);

                    if(currentLoc.getChunk().isLoaded()){
                        continue;
                    }

                    currentLoc.getChunk().load();
                }
            }
        }
    }

    public Location getCenter() {
        return new Location(
                minLocation.getWorld(),
                (minLocation.getX()+maxLocation.getX())/2,
                (minLocation.getY()+maxLocation.getY())/2,
                (minLocation.getZ()+maxLocation.getZ())/2);
    }
}