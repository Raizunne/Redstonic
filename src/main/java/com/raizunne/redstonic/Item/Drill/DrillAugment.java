package com.raizunne.redstonic.Item.Drill;

import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.RedstonicItems;
import com.raizunne.redstonic.Util.DrillUtil;
import com.raizunne.redstonic.Util.Util;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.List;

/**
 * Created by Raizunne as a part of Redstonic
 * on 05/02/2015, 07:19 PM.
 */
public class DrillAugment extends Item {

    int type;
    IIcon[] icons;

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        String[] info = Util.getAugmentInfo(type, stack);
        String hotswapHead = "Empty";
        if(stack.stackTagCompound!=null) {
            hotswapHead = DrillUtil.getDrillHeadName(stack.stackTagCompound.getInteger("hotswapHead"));
        }
        if (info != null) {
            for (int i = 0; i < info.length; i++) {
                list.add(info[i]);
            }
        }
    }

    public DrillAugment(int type){
        this.type = type;
        setCreativeTab(Redstonic.redTab);
        setMaxStackSize(1);
        setUnlocalizedName(getUnlocalizedName());
        setTextureName("redstonic:AugmentTemplate");
    }

    @Override
    public String getUnlocalizedName() {
        switch(type){
            case 0: return "SpeedAugment";
            case 1: return "EnergyAugment";
            case 2: return "HotswapAugment";
            case 3: return "BlockAugment";
            case 4: return "MagnetAugment";
            case 5: return "SpeedIIAugment";
            default: return "UnknownAugment";
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if(stack.stackTagCompound==null && type==2){
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setInteger("hotswapHead", -1);
        }
        if(type==2){
//            System.out.println(stack.stackTagCompound.getInteger("hotswapHead"));
        }
    }

    @Override
    public void registerIcons(IIconRegister i) {
        icons = new IIcon[6];
        icons[0] = i.registerIcon("redstonic:Drill/Augment/Icon/Speed");
        icons[1] = i.registerIcon("redstonic:Drill/Augment/Icon/Energy");
        icons[2] = i.registerIcon("redstonic:Drill/Augment/Icon/Hotswap");
        icons[3] = i.registerIcon("redstonic:Drill/Augment/Icon/Block");
        icons[4] = i.registerIcon("redstonic:Drill/Augment/Icon/Magnet");
        icons[5] = i.registerIcon("redstonic:Drill/Augment/Icon/SpeedII");
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        return icons[type];
    }

    @Override
    public IIcon getIconIndex(ItemStack itemstack) {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        return icons[type];
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if(stack.getItem()==RedstonicItems.HotswapAugment && stack.stackTagCompound!=null) {
            if(stack.stackTagCompound.getInteger("hotswapHead")!=-1)
                player.setItemInUse(stack, getMaxItemUseDuration(stack));
        }
        return stack;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        if(type==2 && stack.stackTagCompound!=null) {
            ItemStack hotswapHead = null;
            hotswapHead = DrillUtil.getDrillHead(stack.stackTagCompound.getInteger("hotswapHead"));
            stack.stackTagCompound.setBoolean("set", false);
            stack.stackTagCompound.setInteger("hotswapHead", -1);
            if(player.inventory.addItemStackToInventory(hotswapHead)){
                player.inventory.addItemStackToInventory(hotswapHead);
            }else{
                EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, hotswapHead);
                if(!world.isRemote){
                    world.spawnEntityInWorld(item);
                }
            }

        }
        return stack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.bow;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 32;
    }
}
