/*
 * LibraryEx
 * Copyright (c) 2017-2019 by LogicTechCorp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package logictechcorp.libraryex.utilities;

import com.mojang.datafixers.Dynamic;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class DynamicHelper
{
    public static <T> Biome.SpawnListEntry deserializeSpawnListEntry(Dynamic<T> dynamic)
    {
        EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(dynamic.get("entity").asString("minecraft:pig")));
        int spawnWeight = dynamic.get("spawnWeight").asInt(10);
        int minimumGroupCount = dynamic.get("minimumGroupCount").asInt(1);
        int maximumGroupCount = dynamic.get("maximumGroupCount").asInt(4);
        return new Biome.SpawnListEntry(entityType, spawnWeight, minimumGroupCount, maximumGroupCount);
    }

    public static <T> ConfiguredFeature<?> deserializeConfiguredFeature(Dynamic<T> dynamic)
    {
        Dynamic featureDynamic = dynamic.get("feature").orElseEmptyMap();
        Dynamic placementDynamic = dynamic.get("placement").orElseEmptyMap();

        Feature feature = ForgeRegistries.FEATURES.getValue(new ResourceLocation(featureDynamic.get("Name").asString("minecraft:random_selector")));
        Placement placement = ForgeRegistries.DECORATORS.getValue(new ResourceLocation(placementDynamic.get("Name").asString("minecraft:nope")));
        return Biome.createDecoratedFeature(feature, feature.createConfig(featureDynamic), placement, placement.createConfig(placementDynamic));
    }

    public static <T> GenerationStage.Decoration deserializeGenerationStage(Dynamic<T> dynamic)
    {
        for(GenerationStage.Decoration stage : GenerationStage.Decoration.values())
        {
            if(dynamic.get("stage").asString().orElse("").equals(stage.getName()))
            {
                return stage;
            }
        }

        return GenerationStage.Decoration.RAW_GENERATION;
    }
}
