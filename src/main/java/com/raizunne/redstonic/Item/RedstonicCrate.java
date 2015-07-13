package com.raizunne.redstonic.Item;

import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.RedstonicItems;
import com.raizunne.redstonic.Util.Lang;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Raizunne as a part of Redstonic
 * on 08/07/2015, 07:46 PM.
 */
public class RedstonicCrate extends Item{

    public RedstonicCrate(){
        this.setMaxStackSize(1);
        this.setUnlocalizedName("RedstonicCrate");
        setCreativeTab(Redstonic.redTab);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        if(stack.stackTagCompound==null){
            stack.stackTagCompound = new NBTTagCompound();
            NBTTagCompound tag = stack.stackTagCompound;
//            ItemStack[] contentes = new ItemStack[]{new ItemStack(Items.apple, 65), new ItemStack(Blocks.beacon, 100)};
//            setContents(stack, contentes);
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        NBTTagCompound tag = stack.stackTagCompound;
        if(tag!=null){
            if(stack.stackTagCompound.getTagList("Contents", 10).tagCount()>0) {
                list.add("Sent by: " + stack.stackTagCompound.getString("sender"));
                if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                    List<ItemStack> contents = getContents(stack);
                    for (ItemStack item : contents) {
                        list.add(item.getDisplayName() + " x" + item.stackSize);
                    }
                    list.add(stack.stackTagCompound.getString("UUID"));
                } else {
                    list.add(Lang.translate("drill.hold") + " " + EnumChatFormatting.ITALIC + EnumChatFormatting.RED + Lang.translate("util.Shift") + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " to list contents.");
                }
            }else{
                list.add("Empty");
            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if(stack.stackTagCompound.getTagList("Contents", 10).tagCount()!=0){
            for(ItemStack cont : getContents(stack)){
                if(!player.inventory.addItemStackToInventory(cont)){
                    EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, cont);
                    if(!world.isRemote)world.spawnEntityInWorld(item);
                }
            }
            stack.stackSize = 0;
        }
        return stack;
    }

    public static void setContents(ItemStack stack, ItemStack[] contents){
        if(stack.stackTagCompound==null){
            stack.stackTagCompound = new NBTTagCompound();
        }
        if(contents !=null) {
            NBTTagList items = new NBTTagList();
            for (ItemStack cont : contents) {
                NBTTagCompound item = new NBTTagCompound();
                cont.writeToNBT(item);
                items.appendTag(item);
            }
            stack.stackTagCompound.setTag("Contents", items);
        }
    }

    public List<ItemStack> getContents(ItemStack stack){
        if(stack.stackTagCompound==null){
            stack.stackTagCompound = new NBTTagCompound();
        }
        List<ItemStack> contents = new ArrayList<ItemStack>();
        NBTTagList items = stack.stackTagCompound.getTagList("Contents", 10);
        for (int i = 0; i < items.tagCount(); i++) {
            NBTTagCompound item = items.getCompoundTagAt(i);
            contents.add(ItemStack.loadItemStackFromNBT(item));
        }
        return contents;
    }

    public static void setSender(ItemStack stack, String sender){
        if(stack.stackTagCompound==null){
            stack.stackTagCompound = new NBTTagCompound();
        }
        stack.stackTagCompound.setString("sender", sender);
    }

    public static void setUUID(ItemStack stack, String UUID){
        if(stack.stackTagCompound==null){
            stack.stackTagCompound = new NBTTagCompound();
        }
        stack.stackTagCompound.setString("UUID", UUID);
    }

    public static void setDate(ItemStack stack, Date date){
        if(stack.stackTagCompound==null){
            stack.stackTagCompound = new NBTTagCompound();
        }
        NBTTagCompound tag = new NBTTagCompound();
//        tag.setString("date", date.);
    }
}
