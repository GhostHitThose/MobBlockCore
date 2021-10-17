package me.ghostplugins.core.utils;

import lombok.NonNull;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class GenerateVoidWorld extends ChunkGenerator {
    @Override
    @NonNull
    public ChunkData generateChunkData(World world, Random random,int chunkX, int chunkZ, BiomeGrid biomeGrid){
        return createChunkData(world);
    }
}
