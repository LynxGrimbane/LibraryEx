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

package logictechcorp.libraryex.block;

import logictechcorp.libraryex.block.property.BlockProperties;
import net.minecraft.block.BlockStairs;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

public class BlockModStairs extends BlockStairs
{
    public BlockModStairs(ResourceLocation registryName, BlockProperties properties)
    {
        super(Blocks.STONE.getDefaultState());
        this.setRegistryName(registryName);
        this.setSoundType(properties.getSoundType());
        this.setLightLevel(properties.getLightLevel());
        this.setHarvestLevel(properties.getHarvestTool(), properties.getHarvestLevel());
        this.setHardness(properties.getHardness());
        this.setResistance(properties.getResistance());
        this.setTickRandomly(properties.needsRandomTick());
        this.setTranslationKey(registryName.toString());
        this.useNeighborBrightness = true;
    }
}
