package com.raizunne.redstonic.Items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Raizu on 05/05/2016, 12:44 AM.
 */
public class Augment extends Item {

    String[] tooltip;

    public Augment(String name, String... tooltip){
        this.setUnlocalizedName("augment"+name);
        this.setRegistryName("augment"+name);
        this.tooltip = tooltip;
        this.setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        for (int i = 0; i < this.tooltip.length; i++) {
            tooltip.add(this.tooltip[i]);
        }
    }
}
