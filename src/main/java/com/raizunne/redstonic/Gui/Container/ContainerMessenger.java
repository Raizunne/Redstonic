package com.raizunne.redstonic.Gui.Container;

import com.raizunne.redstonic.Gui.GuiMessanger;
import com.raizunne.redstonic.Item.IInventories.IInvMessenger;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

import java.util.List;


/**
 * Created by Raizunne as a part of Redstonic
 * on 09/07/2015, 04:18 PM.
 */
public class ContainerMessenger extends Container {

    ItemStack stack;
    IInventory inventory;
    GuiMessanger.Page page;

    public ContainerMessenger(InventoryPlayer invplayer, ItemStack stack){
        this.stack = stack;
        this.page = GuiMessanger.Page.valueOf(stack.stackTagCompound.getString("page"));
        inventory = new IInvMessenger(stack);
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(invplayer, x, 8 + 18 * x, 147));
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(invplayer, x + y * 9 + 9, 8 + 18 * x, 89 + y * 18));
            }
        }

        for(int y=0; y<3; y++){
            for(int x=0; x<3; x++){
                int xStart;
                if(stack.stackTagCompound.getString("page").equals(GuiMessanger.Page.SEND_PACKAGE.toString())){
                    xStart = 10;
                }else{
                    xStart = -1;
                }
                addSlotToContainer(new Slot(inventory, x+(y*3), xStart+8+18*x, 1+(y*18)));
            }
        }
    }

    public void pageChanged(GuiMessanger.Page page){
        if(page.equals(GuiMessanger.Page.SEND_PACKAGE)){
            int x=0; int y=0;
            for (Slot slot : (List<Slot>)inventorySlots) {
                y = x==3 ? y+1 : 0;
                if ((slot.inventory instanceof IInvMessenger)) {
                    slot.xDisplayPosition = 8+18*x;
                    slot.yDisplayPosition = y*18;
                    slot.putStack(new ItemStack(Items.nether_star));
//                    System.out.println(slot.xDisplayPosition + " / " + slot.yDisplayPosition);
//                    System.out.println(this.page + "   -   " + x + " / " + y);
                    x++;
                }
            }
            x=0; y=0;
        }else{
            for (Slot slot : (List<Slot>)inventorySlots) {
                if ((slot.inventory instanceof IInvMessenger)) {
                    slot.xDisplayPosition = 999999;
                }
            }
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        this.stack = Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem();
        if(stack==null){
            Minecraft.getMinecraft().thePlayer.closeScreen();
            return;
        }
//        System.out.println(this.stack.stackTagCompound.getString("page") + " / " + this.page.toString());
        if(!this.stack.stackTagCompound.getString("page").equals(page.toString())){
            this.page = GuiMessanger.Page.valueOf(this.stack.stackTagCompound.getString("page"));
            pageChanged(GuiMessanger.Page.valueOf(this.stack.stackTagCompound.getString("page")));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }
}
