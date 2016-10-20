package com.raizu.redstonic.Blocks.Cone;

import com.raizu.redstonic.Redstonic;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * Created by Raizu on 14/10/2016.
 * as a part of Redstonic
 **/
public class ConeItem extends ItemBlock {

    public ConeItem(Block block) {
        super(block);
        setRegistryName("coneItem");
        setUnlocalizedName("coneItem");
        setCreativeTab(Redstonic.redTab);
    }
}
