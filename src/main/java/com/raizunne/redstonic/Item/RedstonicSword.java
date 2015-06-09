package com.raizunne.redstonic.Item;

import cofh.api.energy.IEnergyContainerItem;
import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.Util.DrillUtil;
import com.raizunne.redstonic.Util.Lang;
import com.raizunne.redstonic.Util.SwordUtil;
import com.raizunne.redstonic.Util.Util;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.List;
import java.util.Locale;

/**
 * Created by Raizunne as a part of Redstonic
 * on 30/04/2015, 01:38 PM.
 */
public class RedstonicSword extends Item implements IEnergyContainerItem {

    public int damage = 10;

    public RedstonicSword() {
        super();
        setUnlocalizedName("RedstonicSword");
        setCreativeTab(Redstonic.redTab);
        setMaxStackSize(1);
        setMaxDamage(80);
        setTextureName("redstonic:Sword/Placeholder");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        if(stack.stackTagCompound!=null){
            NBTTagCompound nbt = stack.stackTagCompound;
//            int damage = nbt.getInteger("damage")/2;
//            damage += hasAugment(stack, 3) ? 2 : 0;
//            damage += hasAugment(stack ,4) ? 3 : 0;
            int damage = SwordUtil.getAbsoluteDamage(stack)/2;

            String Energy = Lang.addComas(nbt.getInteger("Energy")) + "";
            String MaxEnergy = Lang.addComas(nbt.getInteger("MaxEnergy")) + "";
            if(stack.stackTagCompound.getInteger("MaxEnergy")==-1 || stack.stackTagCompound.getInteger("MaxEnergy")==-2 || stack.stackTagCompound.getInteger("MaxEnergy")==-5){
                Energy = EnumChatFormatting.OBFUSCATED + "CREATIVE" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY;
                MaxEnergy = "Creative";
            }

            list.add(EnumChatFormatting.GREEN + "Energy: " + EnumChatFormatting.GRAY + Energy + "/" + MaxEnergy + " RF");
            list.add(EnumChatFormatting.GOLD + "Kills: " + EnumChatFormatting.GRAY + stack.stackTagCompound.getInteger("totalKills"));
            if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                String blade = SwordUtil.getBladeName(stack.stackTagCompound.getInteger("blade")) + " Blade";
                String handle = SwordUtil.getHandleName(stack.stackTagCompound.getInteger("handle")) + " Handle";
                String battery = DrillUtil.getBatteryName(stack.stackTagCompound.getInteger("battery"));
                list.add(Lang.translate("sword.Blade") + EnumChatFormatting.RESET + EnumChatFormatting.DARK_GRAY + ": " + blade);
                list.add(Lang.translate("sword.Handle") + EnumChatFormatting.RESET + EnumChatFormatting.DARK_GRAY + ": " + handle);
                list.add(Lang.translate("drill.battery") + EnumChatFormatting.RESET + EnumChatFormatting.DARK_GRAY + ": " + battery);
                list.add(Lang.translate("drill.energyusage") + EnumChatFormatting.RESET + EnumChatFormatting.DARK_GRAY + ": " + EnumChatFormatting.GREEN + Lang.addComas(calcEnergy(stack)));
                list.add(Lang.translate("sword.Damage") + EnumChatFormatting.RESET + EnumChatFormatting.DARK_GRAY + ": " + damage + " Hearts");
            }else if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && !hasNOAugment(stack)){
                if(nbt.getInteger("aug1")!=0){ list.add(EnumChatFormatting.YELLOW + Lang.translate("drill.augment") + ": " + EnumChatFormatting.DARK_GRAY + SwordUtil.getAugName(nbt.getInteger("aug1"))); }
                if(nbt.getInteger("aug2")!=0){ list.add(EnumChatFormatting.YELLOW + Lang.translate("drill.augment") + ": " + EnumChatFormatting.DARK_GRAY + SwordUtil.getAugName(nbt.getInteger("aug2"))); }
                if(nbt.getInteger("aug3")!=0){ list.add(EnumChatFormatting.YELLOW + Lang.translate("drill.augment") + ": " + EnumChatFormatting.DARK_GRAY + SwordUtil.getAugName(nbt.getInteger("aug3"))); }
//            }else if(Keyboard.isKeyDown(56)){
//                list.add("MOTHERCUcKER");
            }else{
                list.add("Hold " + EnumChatFormatting.RED + Lang.translate("util.Shift") + EnumChatFormatting.GRAY + " for more info.");
                if(!hasNOAugment(stack))list.add(Util.ItemCtrlInfo);
//                if(nbt.getInteger("totalKills")>0)list.add(Util.ItemAltInfo);
            }
            list.add("");
            list.add(EnumChatFormatting.BLUE + "+" + damage + EnumChatFormatting.RED + " Heart " + EnumChatFormatting.BLUE + "Damage");
        }else{
            list.add("Crafted in the" + EnumChatFormatting.YELLOW + " Redstonic Modifier");
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                list.add("Needed to craft: " + EnumChatFormatting.YELLOW + "Sword Blade, ");
                list.add(EnumChatFormatting.YELLOW + "Sword Handle, Battery");
                list.add(EnumChatFormatting.YELLOW + "(Flux Capacitor or Battery)");
            } else {
                list.add(Lang.translate("drill.hold") + " " + EnumChatFormatting.RED + Lang.translate("util.Shift") + EnumChatFormatting.RESET + " " + EnumChatFormatting.GRAY + Lang.translate("drill.formoreinfo"));

                list.add("");
                list.add(EnumChatFormatting.BLUE + "Modular Attack Damage");
            }
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {

        if(stack.stackTagCompound==null){
            stack.stackTagCompound = new NBTTagCompound();
        }
//        System.out.println(Util.hasEnchantment(stack, Enchantment.fireAspect));

        stack.stackTagCompound.removeTag("ench");

        if(hasAugment(stack, 1) && !Util.hasEnchantment(stack, Enchantment.fireAspect)){
            stack.addEnchantment(Enchantment.fireAspect, 2);
        }
        if(hasAugment(stack, 2) && !Util.hasEnchantment(stack, Enchantment.looting)){
            stack.addEnchantment(Enchantment.looting, 3);
        }
    }

    IIcon[] blades;
    IIcon[] handles;
    IIcon[] augments;

    @Override
    public float getDigSpeed(ItemStack itemstack, Block block, int metadata) {
        return super.getDigSpeed(itemstack, block, metadata);
    }

    @Override
    public void registerIcons(IIconRegister i) {
        super.registerIcons(i);
        blades = new IIcon[6];
        handles = new IIcon[7];
        augments = new IIcon[5];

        blades[0] = i.registerIcon("redstonic:Sword/Blades/Iron");
        blades[1] = i.registerIcon("redstonic:Sword/Blades/Diamond");
        blades[2] = i.registerIcon("redstonic:Sword/Blades/Electrum");
        blades[3] = i.registerIcon("redstonic:Sword/Blades/Enderium");
        blades[4] = i.registerIcon("redstonic:Sword/Blades/Energized");
        blades[5] = i.registerIcon("redstonic:Sword/Blades/Vibrant");

        handles[0] = i.registerIcon("redstonic:Sword/Handles/Wood");
        handles[1] = i.registerIcon("redstonic:Sword/Handles/Iron");
        handles[2] = i.registerIcon("redstonic:Sword/Handles/Electrum");
        handles[3] = i.registerIcon("redstonic:Sword/Handles/Enderium");
        handles[4] = i.registerIcon("redstonic:Sword/Handles/Energized");
        handles[5] = i.registerIcon("redstonic:Sword/Handles/Vibrant");

        augments[0] = i.registerIcon("redstonic:Drill/Augment/Render/Null");
        augments[1] = i.registerIcon("redstonic:Sword/Augments/Blazer");
        augments[2] = i.registerIcon("redstonic:Sword/Augments/Fortuitous");
        augments[3] = i.registerIcon("redstonic:Sword/Augments/Berserk");
        augments[4] = i.registerIcon("redstonic:Sword/Augments/Berserk II");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World p_77659_2_, EntityPlayer player) {
        player.setItemInUse(stack, getMaxItemUseDuration(stack));
        return stack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.block;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 999999;
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        if(stack.stackTagCompound!=null){
            if(pass==0){
                return blades[stack.stackTagCompound.getInteger("blade")];
            }
            if(pass==1){
                return handles[stack.stackTagCompound.getInteger("handle")];
            }
            if(pass==2){
                return augments[stack.stackTagCompound.getInteger("aug1")];
            }
            if(pass==3){
                return augments[stack.stackTagCompound.getInteger("aug2")];
            }
            if(pass==4){
                return augments[stack.stackTagCompound.getInteger("aug3")];
            }
        }else{
            if(pass==0){
                return blades[0];
            }
            if(pass==1){
                return handles[0];
            }
            if(pass==2){
                return augments[0];
            }
            if(pass==3){
                return augments[0];
            }
            if(pass==4){
                return augments[0];
            }
        }
        return null;
    }

    @Override
    public IIcon getIconIndex(ItemStack stack) {
        return getIconFromDamage(0);
    }

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public int getRenderPasses(int metadata) {
        return 5;
    }

    public int calcEnergy(ItemStack stack){
        NBTTagCompound tag = stack.stackTagCompound;
        float multiplier = 1;
        if(hasAugment(stack, 1)){
            multiplier = multiplier + 0.2F;
        }
        if(hasAugment(stack, 2)){
            multiplier = multiplier + 0.4F;
        }
        if(hasAugment(stack, 3)){
            multiplier = multiplier + 0.2F;
        }
        int head = tag.getInteger("blade");
        switch(head){
            case 0:	return (int)(300 * multiplier);
            case 1: return (int)(1000 * multiplier);
            case 2: return (int)(1500 * multiplier);
            case 3: return (int)(3000 * multiplier);
            case 4: return (int)(1500 * multiplier);
            case 5: return (int)(3000 * multiplier);
            case 6: return (int)(5000 * multiplier);
            default: return 0;
        }
    }

    public boolean hasAugment(ItemStack stack, int i){
        NBTTagCompound nbt = stack.stackTagCompound;
        return nbt.getInteger("aug1")==i || nbt.getInteger("aug2")==i || nbt.getInteger("aug3")==i;
    }

    public void takeEnergy(ItemStack stack, int i){
        stack.stackTagCompound.setInteger("Energy", stack.stackTagCompound.getInteger("Energy")-i);
        double modifier = (double)80/stack.stackTagCompound.getInteger("MaxEnergy");
        stack.setItemDamage((int)(80 - stack.stackTagCompound.getInteger("Energy")*modifier));
    }

    public int getInput(ItemStack stack){
        int body = (stack.stackTagCompound.getInteger("handle"));
        switch(body){
            case 0: return 1000;
            case 1: return 5000;
            case 2:case 4: return 10000;
            case 3:case 5: return 32000;
            case 6: return 50000;
            default: return 1;
        }
    }

    public boolean hasNOAugment(ItemStack stack){
        NBTTagCompound nbt = stack.stackTagCompound;
        return nbt.getInteger("aug1")==0 && nbt.getInteger("aug2")==0 && nbt.getInteger("aug3")==0;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entityLivingTo, EntityLivingBase entityLivingFrom) {
        DamageSource source = DamageSource.causePlayerDamage((EntityPlayer)entityLivingFrom);

        boolean canHit = (stack.stackTagCompound.getInteger("Energy")>=calcEnergy(stack) || (stack.stackTagCompound.getInteger("MaxEnergy")==-2 || stack.stackTagCompound.getInteger("MaxEnergy")==-1 || stack.stackTagCompound.getInteger("MaxEnergy")==-5));

//        int damage = canHit ? stack.stackTagCompound.getInteger("damage") : 0;
//        if(canHit)damage += hasAugment(stack, 3) ? 4 : 0;
//        if(canHit)damage += hasAugment(stack, 4) ? 6 : 0;
        int damage = SwordUtil.getAbsoluteDamage(stack);
        entityLivingTo.attackEntityFrom(source, (float)(damage));

        if(canHit){
            takeEnergy(stack, calcEnergy(stack));
        }else{
            takeEnergy(stack, stack.stackTagCompound.getInteger("Energy"));
            entityLivingFrom.playSound("random.break", 0.5F, 1F);
        }

        if(entityLivingTo.getHealth()<=0){
            stack.stackTagCompound.setInteger("totalKills", stack.stackTagCompound.getInteger("totalKills")+1);
        }

        int energy = stack.stackTagCompound.getInteger("Energy");
        int maxEnergy = stack.stackTagCompound.getInteger("MaxEnergy");
        double modifier = (double) 80 / maxEnergy;
        stack.setItemDamage((int) (80 - energy * modifier));


        return super.hitEntity(stack, entityLivingFrom, entityLivingTo);
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return false;
    }

    @Override
    public boolean isFull3D() {
        return true;
    }


    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        if (container.stackTagCompound == null) {
            container.stackTagCompound = new NBTTagCompound();
        }
        int maxEnergy = container.stackTagCompound.getInteger("MaxEnergy");
        int energy = container.stackTagCompound.getInteger("Energy");
        int energyReceived = Math.min(maxEnergy - energy, Math.min(getInput(container), maxReceive));
        if(energy>=maxEnergy){
        }

        if (!simulate) {
            energy += energyReceived;
            container.stackTagCompound.setInteger("Energy", energy);
        }
        double modifier = (double)80/maxEnergy;
        container.setItemDamage((int)(80 - energy*modifier));
        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        return container.stackTagCompound.getInteger("Energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return container.stackTagCompound.getInteger("MaxEnergy");
    }
}
