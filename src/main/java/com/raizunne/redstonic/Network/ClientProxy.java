package com.raizunne.redstonic.Network;

import com.raizunne.redstonic.Blocks.RedBlocks;
import com.raizunne.redstonic.Items.RedItems;

/**
 * Created by Raizu on 04/05/2016.
 */
public class ClientProxy extends CommonProxy {

    public void registerRenderers(){
        RedItems.registerRenderers();
        RedBlocks.registerRenderers();
    }

}
