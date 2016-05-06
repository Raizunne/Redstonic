package com.raizunne.redstonic.Blocks.Shearer;

import com.raizunne.redstonic.Util.SlotAugment;
import com.raizunne.redstonic.Util.SlotWool;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Raizu on 04/05/2016.
 */
public class ContainerShearer extends Container {

    public TEShearer te;
    int lastTimer;
    int lastRadius;
    int lastSheeps;
    int lastMaxTime;

    public ContainerShearer(InventoryPlayer invplayer, TEShearer te){
        this.te = te;
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(invplayer, x, 8 + 18 * x, 147));
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(invplayer, x + y * 9 + 9, 8 + 18 * x, 89 + y * 18));
            }
        }

        for(int x=0;x<3; x++){
            for (int y = 0; y < 3; y++) {
                addSlotToContainer(new SlotWool(te, x+y*3, 45+18*x, 19+y*18));
            }
        }
        for (int x = 0; x < 3; x++) {
            addSlotToContainer(new SlotAugment(te, x+9, 101+x*18, 19));
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
            if(this.lastSheeps!=this.te.getSheepCount()){
                crafting.sendProgressBarUpdate(this, 2, te.getSheepCount());
            }
            if(this.lastMaxTime!=this.te.getMaxTime()){
                crafting.sendProgressBarUpdate(this, 3, te.getMaxTime());
            }
        }
        this.lastTimer = this.te.getTimer();
        this.lastRadius = this.te.getRadius();
        this.lastSheeps = this.te.getSheepCount();
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
            this.te.setSheepCount(data);
        }
        if(id==3){
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
