package com.raizunne.redstonic.Item.Sword;

import com.raizunne.redstonic.Util.Util;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * Created by Raizunne as a part of Redstonic
 * on 02/05/2015, 11:14 AM.
 */
public class SwordAugment extends Item {

    int type;

    public SwordAugment(int type){
        this.type = type;
        setMaxStackSize(1);
        setUnlocalizedName(getUnlocalizedName());
        setTextureName("redstonic:AugmentTemplate");
    }

    @Override
    public String getUnlocalizedName() {
        switch(type){
            case 1: return "BlazerSwordAugment";
            case 2: return "FortuitousSwordAugment";
            case 3: return "BerserkSwordAugment";
            case 4: return "BerserkIISwordAugment";
        }
        return "UnknownAugment";
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        String[] info = Util.getSwordAugmentInfo(type, stack);
        if (info != null) {
            for (int i = 0; i < info.length; i++) {
                list.add(info[i]);
            }
        }
    }

    IIcon[] icon;

    @Override
    public void registerIcons(IIconRegister i) {
        super.registerIcons(i);
        icon = new IIcon[4];
        icon[0] = i.registerIcon("redstonic:Sword/Augments/Icon/Blazer");
        icon[1] = i.registerIcon("redstonic:Sword/Augments/Icon/Fortuitous");
        icon[2] = i.registerIcon("redstonic:Sword/Augments/Icon/Berserk");
        icon[3] = i.registerIcon("redstonic:Sword/Augments/Icon/BerserkII");
    }

    @Override
    public IIcon getIconIndex(ItemStack p_77650_1_) {
        return icon[type-1];
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return icon[type-1];
    }
}
