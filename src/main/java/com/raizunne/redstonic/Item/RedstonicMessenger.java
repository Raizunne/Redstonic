package com.raizunne.redstonic.Item;

import com.raizunne.redstonic.Redstonic;
import net.minecraft.item.Item;

/**
 * Created by Raizunne as a part of Redstonic
 * on 09/06/2015, 02:17 PM.
 */
public class RedstonicMessenger extends Item {

   public RedstonicMessenger(){
       setMaxStackSize(1);
       setCreativeTab(Redstonic.redTab);
       setUnlocalizedName("RedstonicMessenger");
       setTextureName("redstonic:RedstonicMessenger");
   }
}
