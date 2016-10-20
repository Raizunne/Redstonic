package com.raizu.redstonic.Proxy;

import com.raizu.redstonic.Blocks.RedBlocks;
import com.raizu.redstonic.Items.RedItems;

/**
 * Created by Raizu on 14/10/2016.
 * as a part of Redstonic
 **/
public class ClientProxy extends CommonProxy{

    public void registerRenderers() {
        RedItems.registerRenderers();
        RedBlocks.registerRenderers();
    }

    public void registerPreInit() {

    }
}
