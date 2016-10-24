package com.raizu.redstonic.Items.Drill;

import com.raizu.redstonic.Items.RedItems;
import com.raizu.redstonic.Redstonic;
import com.raizu.redstonic.Utils.StringUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Raizu on 15/10/2016.
 * as a part of Redstonic
 **/
public class DrillBody extends Item {

    public int bodyCount = 6;
    public String bodies[] = {"Iron", "Electrum", "Enderium", "Energetic", "Vibrant", "End"};
    public int[] augmentSlots = {1, 2, 3, 2, 3, 0};
    public String[] oreDic = {"bodyLowTier", "bodyMidTier", "bodyHighTier", "bodyMidTier", "bodyHighTier", "bodyUltimateTier"};


    public DrillBody(){
        setRegistryName("DrillBody");
        setCreativeTab(Redstonic.redTab);
        setHasSubtypes(true);
        setMaxStackSize(1);
        setMaxDamage(0);
        addPropertyOverride(new ResourceLocation("metadata"), new IItemPropertyGetter() {
            @Override
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return stack.getMetadata();
            }
        });
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);
        switch(stack.getMetadata()){
            case 0: tooltip.add("1 " + StringUtils.localize("redstonic.drill.augmentslot")); break;
            case 1: tooltip.add("2 " + StringUtils.localize("redstonic.drill.augmentslot")); break;
            case 2: tooltip.add("3 " + StringUtils.localize("redstonic.drill.augmentslot")); break;
            case 3: tooltip.add("2 " + StringUtils.localize("redstonic.drill.augmentslot")); break;
            case 4: tooltip.add("3 " + StringUtils.localize("redstonic.drill.augmentslot")); break;
            case 5: tooltip.add("0 " + StringUtils.localize("redstonic.drill.augmentslot")); break;
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return bodies[stack.getMetadata()]+"Body";
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < bodyCount; i++) {
            if(OreDictionary.getOres("ingot"+bodies[i]).size()>0 || OreDictionary.getOres("ingot"+bodies[i]+"Alloy").size()>0 || bodies[i].equals("End")) {
                subItems.add(new ItemStack(itemIn, 1, i));
                OreDictionary.registerOre(oreDic[i], new ItemStack(itemIn, 1, i));
            }
        }
    }

    public static ItemStack getBody(DrillPart part){
        return new ItemStack(RedItems.drillBody, 1, part.part);
    }
}
