package com.raizunne.redstonic.Item.Drill;

import com.raizunne.redstonic.Redstonic;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * Created by Raizunne as a part of Redstonic
 * on 03/02/2015, 09:52 PM.
 */
public class DrillBody extends Item{

    int material;
    IIcon[] body;

    public DrillBody(int material){
        this.material = material;
        setCreativeTab(Redstonic.redTab);
        setMaxStackSize(1);
        setUnlocalizedName(getUnlocalizedName());
    }

    @Override
    public String getUnlocalizedName() {
        switch(material){
            case 0: return "UnknownDrillBody";
            case 1: return "IronDrillBody";
            case 2: return "ElectrumDrillBody";
            case 3: return "EnderiumDrillBody";
            default: return "UnknownDrillBody";
        }
    }

    @Override
    public void registerIcons(IIconRegister i) {
        body = new IIcon[7];
        body[0] = i.registerIcon("redstonic:Drill/Bodies/Icon/Unknown");
        body[1] = i.registerIcon("redstonic:Drill/Bodies/Icon/Iron");
        body[2] = i.registerIcon("redstonic:Drill/Bodies/Icon/Electrum");
        body[3] = i.registerIcon("redstonic:Drill/Bodies/Icon/Enderium");

    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        switch(material){
            case 0: return body[0];
            case 1: return body[1];
            case 2: return body[2];
            case 3: return body[3];
            default: return body[0];
        }
    }

    @Override
    public IIcon getIconIndex(ItemStack p_77650_1_) {
        switch(material){
            case 0: return body[0];
            case 1: return body[1];
            case 2: return body[2];
            case 3: return body[3];
            default: return body[0];
        }
    }
}
