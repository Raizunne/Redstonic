package com.raizu.redstonic.Items.Sword;

import com.raizu.redstonic.Handler.Config;
import com.raizu.redstonic.Items.RedItems;
import com.raizu.redstonic.Redstonic;
import com.raizu.redstonic.Utils.StringUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Raizu on 16/10/2016.
 * as a part of Redstonic
 **/
public class SwordHandle extends Item {

    public String[] handles = {"Wood", "Iron", "Electrum", "Enderium", "Energetic", "Vibrant"};
    public int[] augmentSlots = {0, 1, 2, 3, 2, 3};
    public String[] oreDic = {"handleLowTier", "handleLowMidTier", "handleMidTier", "handleHighTier", "handleMidTier", "handleHighTier"};

    public SwordHandle() {
        setRegistryName("SwordHandle");
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
            case 0: tooltip.add("0 " + StringUtils.localize("redstonic.drill.augmentslot")); break;
            case 1: tooltip.add("1 " + StringUtils.localize("redstonic.drill.augmentslot")); break;
            case 2: tooltip.add("2 " + StringUtils.localize("redstonic.drill.augmentslot")); break;
            case 3: tooltip.add("3 " + StringUtils.localize("redstonic.drill.augmentslot")); break;
            case 4: tooltip.add("2 " + StringUtils.localize("redstonic.drill.augmentslot")); break;
            case 5: tooltip.add("3 " + StringUtils.localize("redstonic.drill.augmentslot")); break;
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return handles[stack.getMetadata()]+"Handle";
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < handles.length; i++) {
            if(handles[i]=="Wood" || OreDictionary.getOres("ingot"+handles[i]).size()>0 || OreDictionary.getOres("ingot"+handles[i]+"Alloy").size()>0) {
                if(Config.disabledHandles.contains(i)) {
                    subItems.add(new ItemStack(itemIn, 1, i));
                    OreDictionary.registerOre(oreDic[i], new ItemStack(itemIn, 1, i));
                }
            }
        }
    }

    public static ItemStack getHandle(SwordPart part){
        return new ItemStack(RedItems.swordHandle, 1, part.part);
    }
}
