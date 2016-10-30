package com.raizu.redstonic.Handler;

import com.raizu.redstonic.Items.Drill.Drill;
import com.raizu.redstonic.Items.Sword.Sword;
import com.raizu.redstonic.Proxy.ClientProxy;
import com.raizu.redstonic.Redstonic;
import net.minecraft.block.BlockLog;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raizu on 15/10/2016.
 * as a part of Redstonic
 **/
public class EventHandler {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void TooltipEvent(ItemTooltipEvent e){
        List<String> tip = e.getToolTip();
        if(e.getItemStack().getItem() instanceof Drill){
            for (int i = 0; i < tip.size(); i++) {
//                System.out.println(tip.get(i));
                if(tip.get(i).startsWith("When in main hand:")){
                    tip.remove(i);
                    tip.remove(i);
                    tip.remove(i);
                    tip.remove(i-1);
                }
            }
        }else if(e.getItemStack().getItem() instanceof Sword){
            List<String> newTip = new ArrayList<String>();
            for (int i = 0; i < tip.size(); i++) {

                if((tip.get(i).startsWith("When on") || tip.get(i).startsWith("When in"))){
                    i+=2;
                }else{
                    if(!tip.get(i).equals(""))newTip.add(tip.get(i));
                }
            }
            tip.clear();
            tip.addAll(newTip);
        }
    }

    @SubscribeEvent
    public void AttackEvent(AttackEntityEvent e){
        if(e.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND)!=null && e.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof Sword && e.getTarget() instanceof EntityLivingBase){
            EntityPlayer player = e.getEntityPlayer();
            float strength = player.getCooledAttackStrength(0.5F);
            ItemStack sword = player.getHeldItem(EnumHand.MAIN_HAND);
            float damage = (Sword.getBasicSwordDamage(sword)*2)*strength;
            DamageSource source = DamageSource.causePlayerDamage(player);
            NBTTagCompound tag = sword.getTagCompound();
            boolean canHit = (tag.getInteger("Energy")>=Sword.getEnergyCost(sword) || (tag.getInteger("maxEnergy")==-1));
            if(canHit){
                tag.setInteger("Energy", tag.getInteger("Energy")-Sword.getEnergyCost(sword));
                e.getTarget().attackEntityFrom(source, damage);
                if(Sword.hasAugment(2, sword))e.getTarget().setFire(8);
                double walked = (double)(player.distanceWalkedModified - player.prevDistanceWalkedModified);
                boolean canSweep = player.getCooledAttackStrength(0.5F)>0.9F;
                boolean isMovingV = player.fallDistance>0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPotionActive(MobEffects.BLINDNESS) && !player.isRiding();
                if(canSweep && !player.isSprinting() && !isMovingV && player.onGround &&walked<(double)player.getAIMoveSpeed()){
                    for(EntityLivingBase entitylivingbase : player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, e.getTarget().getEntityBoundingBox().expand(1.0D, 0.25D, 1.0D))) {
                        if (entitylivingbase != player && entitylivingbase != e.getTarget() && !player.isOnSameTeam(entitylivingbase) && player.getDistanceSqToEntity(entitylivingbase) < 9.0D) {
                            entitylivingbase.knockBack(player, 0.4F, (double) MathHelper.sin(player.rotationYaw * 0.017453292F), (double) (-MathHelper.cos(player.rotationYaw * 0.017453292F)));
                            entitylivingbase.attackEntityFrom(source, 1F);
                        }
                    }
                    player.worldObj.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
                }else{
                    player.worldObj.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, player.getSoundCategory(), 1.0F, 1.0F);
                }
                if(canSweep)player.spawnSweepParticles();
            }else{
                tag.setInteger("Energy", tag.getInteger("Energy"));
                player.getEntityWorld().playSound(player, player.getPosition(), new SoundEvent(new ResourceLocation("random.break")), SoundCategory.BLOCKS, 0.5F, 2F);
            }
            if(((EntityLivingBase)e.getTarget()).getHealth()<=0){
                tag.setInteger("kills", tag.getInteger("kills")+1);
            }
            sword.setTagCompound(tag);
        }
    }

    @SubscribeEvent
    public void OnJoinWorld(EntityJoinWorldEvent e){
        if(e.getEntity() instanceof EntityPlayer && !ClientProxy.newVersion.equals("0.0") && !ClientProxy.gotTold){
            if (!ClientProxy.newVersion.equals(Redstonic.VERSION)){
                EntityPlayer player = (EntityPlayer)e.getEntity();
                player.addChatMessage(new TextComponentString("["+TextFormatting.RED+"REDSTONIC"+TextFormatting.WHITE+"]: Redstonic is out of date!"));
                player.addChatMessage(new TextComponentString("   - What's new? - "+ ClientProxy.newChangelog));
                ClientProxy.gotTold=true;
            }
        }
    }

    @SubscribeEvent
    public void BlazerBlaze(BlockEvent.HarvestDropsEvent event){
        if (event.getHarvester() != null) {
            ItemStack itemHand = event.getHarvester().getHeldItem(EnumHand.MAIN_HAND);
            if (itemHand != null && itemHand.getItem() instanceof Drill) {
                if (itemHand.getTagCompound().getInteger("head") == 6 && !(event.getState().getBlock() instanceof BlockLog)) {
                    ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(event.getState().getBlock(), 1, event.getState().getBlock().getMetaFromState(event.getState())));
                    if(event.getState().getBlock() == Blocks.STONE){
                        stack = new ItemStack(Blocks.STONE, 1, event.getState().getBlock().getMetaFromState(event.getState()));
                    }
                    if (stack != null) {
                        event.getDrops().clear();
                        event.getDrops().add(stack.copy());
                    }
                }
            }
        }else{

        }
    }
}
