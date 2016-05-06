package com.raizunne.redstonic.Blocks.Butcher;

import com.raizunne.redstonic.Blocks.Shearer.TEShearer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Raizu on 05/05/2016, 08:28 PM.
 */
public class ContainerButcher extends Container{

    TEButcher te;
    int lastTimer, lastRadius, lastMaxTime;

    public ContainerButcher(InventoryPlayer invplayer, TEButcher te) {
        this.te = te;
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(invplayer, x, 8 + 18 * x, 147));
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(invplayer, x + y * 9 + 9, 8 + 18 * x, 89 + y * 18));
            }
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for(int i=0; i<this.crafters.size(); i++){
            ICrafting crafting = (ICrafting)this.crafters.get(i);
            if(this.lastTimer!=this.te.getTimer()){
                crafting.sendProgressBarUpdate(this, 0, te.getTimer());
            }
            if(this.lastRadius!=this.te.getRadius()){
                crafting.sendProgressBarUpdate(this, 1, te.getRadius());
            }
            if(this.lastMaxTime!=this.te.getMaxTime()){
                crafting.sendProgressBarUpdate(this, 2, te.getMaxTime());
            }
        }
        this.lastTimer = this.te.getTimer();
        this.lastRadius = this.te.getRadius();
        this.lastMaxTime = this.te.getMaxTime();
    }

    @Override
    public void updateProgressBar(int id, int data) {
        super.updateProgressBar(id, data);
        if(id==0){
            this.te.setTimer(data);
        }
        if(id==1){
            this.te.setRadius(data);
        }
        if(id==2){
            this.te.setMaxTime(data);
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return null;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return te.isUseableByPlayer(playerIn);
    }

}
