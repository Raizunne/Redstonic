package com.raizunne.redstonic.Item;

import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.Util.Util;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.List;

/**
 * Created by Raizunne as a part of Redstonic
 * on 23/02/2015, 02:41 PM.
 */
public class ItemMaterial extends Item {
    int type;
    IIcon[] icon;
    public ItemMaterial(int type){
        this.type = type;
        setCreativeTab(Redstonic.redTab);
        setUnlocalizedName(getUnlocalizedName());
        setMaxStackSize(getMaxSize());
    }

    @Override
    public void registerIcons(IIconRegister i) {
        icon = new IIcon[2];
        icon[0] = i.registerIcon("redstonic:Material/EnergizerCapsuleEMPTY");
        icon[1] = i.registerIcon("redstonic:Material/EnergizerCapsuleFULL");
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        return icon[type];
    }

    @Override
    public IIcon getIconIndex(ItemStack p_77650_1_) {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        return icon[type];
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
            String[] info = Util.getMaterialInfo(type);
            if(info!=null) {
                for (int i = 0; i < info.length; i++) {
                    list.add(info[i]);
                }
            }
        }else{
            list.add(Util.ItemShiftInfo);
        }
    }

    @Override
    public String getUnlocalizedName() {
        switch(type){
            case 0: return "EnergizerCapsule";
            case 1: return "EnergizerCapsuleFULL";
            default: return "RedstonicUnknownItem";
        }
    }

    public int getMaxSize(){
        switch(type){
            case 0: return 64;
            case 1: return 1;
            default: return 64;
        }
    }
}
