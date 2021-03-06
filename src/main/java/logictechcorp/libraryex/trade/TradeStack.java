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

package logictechcorp.libraryex.trade;

import com.electronwill.nightconfig.core.Config;
import logictechcorp.libraryex.utility.ConfigHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TradeStack
{
    public static final TradeStack EMPTY = new TradeStack(ItemStack.EMPTY, 0, 0);

    protected ItemStack stack;
    protected int minCount;
    protected int maxCount;

    public TradeStack(ItemStack stack, int minCount, int maxCount)
    {
        this.stack = stack;
        this.minCount = minCount;
        this.maxCount = maxCount;

        if(this.minCount > this.maxCount)
        {
            int temp = this.minCount;
            this.minCount = this.maxCount;
            this.maxCount = temp;
        }
    }

    public TradeStack(Item item, int meta, int minCount, int maxCount)
    {
        this(new ItemStack(item, 1, meta), minCount, maxCount);
    }

    public TradeStack(Item item, int minCount, int maxCount)
    {
        this(item, 0, minCount, maxCount);
    }

    public TradeStack(Block block, int meta, int minCount, int maxCount)
    {
        this(new ItemStack(block, 1, meta), minCount, maxCount);
    }

    public TradeStack(Block block, int minCount, int maxCount)
    {
        this(block, block.getMetaFromState(block.getDefaultState()), minCount, maxCount);
    }

    public TradeStack(Config config, String path)
    {
        this.stack = ConfigHelper.getItemStack(config, path);
        this.minCount = config.getOrElse(path + ".minCount", 1);
        this.maxCount = config.getOrElse(path + ".maxCount", this.stack.getMaxStackSize());
    }

    public ItemStack getItemStack()
    {
        return this.stack;
    }

    public int getMinCount()
    {
        return this.minCount;
    }

    public int getMaxCount()
    {
        return this.maxCount;
    }
}
