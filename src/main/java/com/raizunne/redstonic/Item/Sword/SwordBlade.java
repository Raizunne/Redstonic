package com.raizunne.redstonic.Item.Sword;

import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.Util.SwordUtil;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by Raizunne as a part of Redstonic
 * on 30/04/2015, 01:38 PM.
 */
public class SwordBlade extends Item {

    int type;

    public SwordBlade(int type){
        this.type = type;
        setCreativeTab(Redstonic.redTab);
        setMaxStackSize(1);
        setUnlocalizedName(getUnlocalizedName());
        setTextureName("redstonic:Sword/Blades/Iron");
    }

    @Override
    public String getUnlocalizedName() {
        switch(this.type){
            case 0: return "IronBlade";
            case 1: return "DiamondBlade";
            case 2: return "ElectrumBlade";
            case 3: return "EnderiumBlade";
            case 4: return "EnergizedBlade";
            case 5: return "VibrantBlade";
            case 6: return "EndBlade";
        }
        return "UnknownBlade";
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
        list.add(EnumChatFormatting.RED + "" + SwordUtil.getDamage(new ItemStack(this))/2 + " Heart " + EnumChatFormatting.GRAY + "Base Damage.");
        if(stack.stackTagCompound!=null && stack.stackTagCompound.getInteger("totalKills")>0)list.add("Kills: " + stack.stackTagCompound.getInteger("totalKills"));
    }

    IIcon[] icon;

    @Override
    public void registerIcons(IIconRegister i) {
        super.registerIcons(i);
        icon = new IIcon[6];

        icon[0] = i.registerIcon("redstonic:Sword/Blades/Icon/Iron");
        icon[1] = i.registerIcon("redstonic:Sword/Blades/Icon/Diamond");
        icon[2] = i.registerIcon("redstonic:Sword/Blades/Icon/Electrum");
        icon[3] = i.registerIcon("redstonic:Sword/Blades/Icon/Enderium");
        icon[4] = i.registerIcon("redstonic:Sword/Blades/Icon/Energized");
        icon[5] = i.registerIcon("redstonic:Sword/Blades/Icon/Vibrant");
    }

    @Override
    public IIcon getIconIndex(ItemStack p_77650_1_) {
        return icon[type];
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return icon[type];
    }
}
