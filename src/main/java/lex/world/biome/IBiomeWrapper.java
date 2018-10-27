package lex.world.biome;

import lex.world.gen.GenerationStage;
import lex.world.gen.feature.Feature;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;

import java.util.List;

public interface IBiomeWrapper
{
    Biome getBiome();

    int getWeight();

    boolean isEnabled();

    boolean genDefaultFeatures();

    IBlockState getBiomeBlock(BiomeBlockType type, IBlockState fallback);

    List<Biome.SpawnListEntry> getEntitySpawnEntries(EnumCreatureType creatureType);

    List<Feature> getFeatures(GenerationStage generationStage);
}
