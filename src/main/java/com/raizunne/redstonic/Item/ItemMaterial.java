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
        icon = new IIcon[9];
        icon[0] = i.registerIcon("redstonic:Material/EnergizerCapsuleEMPTY");
        icon[1] = i.registerIcon("redstonic:Material/EnergizerCapsuleFULL");
        icon[2] = i.registerIcon("redstonic:Material/ingotVibrantium");
        icon[3] = i.registerIcon("redstonic:Material/gearIron");
        icon[4] = i.registerIcon("redstonic:Material/gearEnergized");
        icon[5] = i.registerIcon("redstonic:Material/gearVibrant");
        icon[6] = i.registerIcon("redstonic:Material/ingotGlowSteel");
        icon[7] = i.registerIcon("redstonic:Material/capacitor");
        icon[8] = i.registerIcon("redstonic:Material/Redstone Stick");
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
        String[] info = Util.getMaterialInfo(type);
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
            if(info!=null) {
                for (int i = 0; i < info.length; i++) {
                    list.add(info[i]);
                }
            }
        }else{
            if(info!=null){
                list.add(Util.ItemShiftInfo);
            }
        }
    }

    @Override
    public String getUnlocalizedName() {
        switch(type){
            case 0: return "EnergizerCapsule";
            case 1: return "EnergizerCapsuleFULL";
            case 2: return "Vibrantium";
            case 3: return "gearIron";
            case 4: return "gearEnergized";
            case 5: return "gearVibrant";
            case 6: return "ingotGlowSteel";
            case 7: return "capacitor";
            case 8: return "redstoneStick";
            default: return "RedstonicUnknownItem";
        }
    }

    public int getMaxSize(){
        switch(type){
            case 0: return 64;
            case 1: return 1;
            case 2: return 64;
            default: return 64;
        }
    }

    @Override
    public boolean hasEffect(ItemStack p_77636_1_, int pass) {
        switch(type){
            case 2: return true;
            default: return false;
        }
    }
}
