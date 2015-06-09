package com.raizunne.redstonic.Item.Sword;

import com.raizunne.redstonic.Redstonic;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by Raizunne as a part of Redstonic
 * on 01/05/2015, 03:55 PM.
 */
public class SwordHandle extends Item {

    int type;

    public SwordHandle(int type){
        this.type = type;
        setCreativeTab(Redstonic.redTab);
        setUnlocalizedName(getUnlocalizedName());
        setMaxStackSize(1);
        setTextureName("redstonic:Sword/Handles/Iron");
    }

    @Override
    public String getUnlocalizedName() {
        switch(this.type){
            case 0: return "WoodHandle";
            case 1: return "IronHandle";
            case 2: return "ElectrumHandle";
            case 3: return "EnderiumHandle";
            case 4: return "EnergizedHandle";
            case 5: return "VibrantHandle";
            case 6: return "EndHandle";
        }
        return "UnknownHandle";
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
        switch(this.type){
            case 0: list.add("Max Augments: 0"); break;
            case 1: list.add("Max Augments: 1"); break;
            case 2: list.add("Max Augments: 2"); break;
            case 3: list.add("Max Augments: 3"); break;
            case 4: list.add("Max Augments: 2"); break;
            case 5: list.add("Max Augments: 3"); break;
            case 6: list.add("Max Augments: 0"); break;
        }
    }

    IIcon[] icon;

    @Override
    public void registerIcons(IIconRegister i) {
        super.registerIcons(i);
        icon = new IIcon[6];

        icon[0] = i.registerIcon("redstonic:Sword/Handles/Icon/Wood");
        icon[1] = i.registerIcon("redstonic:Sword/Handles/Icon/Iron");
        icon[2] = i.registerIcon("redstonic:Sword/Handles/Icon/Electrum");
        icon[3] = i.registerIcon("redstonic:Sword/Handles/Icon/Enderium");
        icon[4] = i.registerIcon("redstonic:Sword/Handles/Icon/Energized");
        icon[5] = i.registerIcon("redstonic:Sword/Handles/Icon/Vibrant");
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return icon[type];
    }

    @Override
    public IIcon getIconIndex(ItemStack p_77650_1_) {
        return icon[type];
    }
}
