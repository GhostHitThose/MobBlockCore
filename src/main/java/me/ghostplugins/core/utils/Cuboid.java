package me.ghostplugins.core.utils;

import lombok.Getter;
import org.bukkit.Axis;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Objects;

public class Cuboid {

    @Getter
    private final Location minLocation, maxLocation;
    @Getter
    private Location corner1, corner2;
    @Getter
    private World world;

    public Cuboid(Location corner1, Location corner2){
        if(!(Objects.equals(corner1.getWorld(), corner2.getWorld()))){
            minLocation = null;
            maxLocation = null;
            return;
        }

        this.corner1 = corner1;
        this.corner2 = corner2;

        world = corner1.getWorld();
        double x2 = Math.max(corner1.getX(), corner2.getX());
        double y2 = world.getMaxHeight();
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

    public void setMinWall(Axis axis, Material material){
        int loopX;
        int loopY;
        int loopZ;
        switch(axis){
            case X:
                loopX = (int)(maxLocation.getX()-minLocation.getX());
                loopY = (int)(maxLocation.getY()-minLocation.getY());
                for(int i = 0; i < loopX; i++){
                    for(int j = 0; j < loopY; j++){
                        world.getBlockAt((int) (minLocation.getX() + i),(int) (minLocation.getX() + j), (int) minLocation.getZ()).setType(material);
                    }
                }
                break;
            case Y:
                loopX = (int)(maxLocation.getX()-minLocation.getX());
                loopZ = (int)(maxLocation.getZ()-minLocation.getZ());
                for(int i = 0; i < loopX; i++){
                    for(int j = 0; j < loopZ; j++){
                        world.getBlockAt((int) (minLocation.getX() + i),(int) (minLocation.getY()), (int) minLocation.getZ() + j).setType(material);
                    }
                }
                break;
            case Z:
                loopZ = (int)(maxLocation.getZ()-minLocation.getZ());
                loopY = (int)(maxLocation.getY()-minLocation.getY());
                for(int i = 0; i < loopZ; i++){
                    for(int j = 0; j < loopY; j++){
                        world.getBlockAt((int) (minLocation.getX()),(int) (minLocation.getX() + j), (int) minLocation.getZ() + i).setType(material);
                    }
                }
                break;
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