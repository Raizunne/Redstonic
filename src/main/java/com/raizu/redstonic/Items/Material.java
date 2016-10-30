package com.raizu.redstonic.Items;

import com.raizu.redstonic.Handler.Config;
import com.raizu.redstonic.Redstonic;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Raizu on 22/10/2016.
 * as a part of Redstonic
 **/
public class Material extends Item {

    public Material(){
        setRegistryName("RedstonicMaterial");
        setHasSubtypes(true);
        setCreativeTab(Redstonic.redTab);
        addPropertyOverride(new ResourceLocation("metadata"), new IItemPropertyGetter() {
            @Override
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return stack.getMetadata();
            }
        });
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return (Materials.values()[stack.getMetadata()].glow);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return Materials.values()[stack.getMetadata()].name;
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < Materials.values().length; i++) {
            if(Config.disabledMaterials.contains(i)) {
                subItems.add(new ItemStack(itemIn, 1, i));
                if (Materials.values()[i].ore)OreDictionary.registerOre(Materials.values()[i].oreDic, new ItemStack(itemIn, 1, i));
            }
        }
    }

    public static boolean isType(ItemStack stack, Materials mat){
        return stack.getMetadata() == Arrays.asList(Materials.values()).indexOf(mat);
    }

    public static ItemStack getMaterial(Materials mat){
        return new ItemStack(RedItems.material, 1, Arrays.asList(Materials.values()).indexOf(mat));
    }

}

