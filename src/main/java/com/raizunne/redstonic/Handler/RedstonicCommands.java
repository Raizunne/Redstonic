package com.raizunne.redstonic.Handler;

import com.raizunne.redstonic.Item.RedstonicMessanger;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandServerKick;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListOpsEntry;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Raizunne as a part of Redstonic
 * on 8/12/15, 2:38 PM.
 */
public class RedstonicCommands {

    public class Redstonic implements ICommand{

        public Redstonic(){

        }

        @Override
        public String getCommandName() {
            return "redstonic";
        }

        @Override
        public String getCommandUsage(ICommandSender p_71518_1_) {
            return null;
        }

        @Override
        public List getCommandAliases() {
            return null;
        }

        @Override
        public void processCommand(ICommandSender com, String[] args) {
            if(args.length==0){
                com.addChatMessage(new ChatComponentText("Not enough arguments"));
                return;
            }
            if(args.length>0){
                if(args[0].equals("listmessages")){
                    RedstonicWorldData worldData = RedstonicWorldData.get(com.getEntityWorld());
                    NBTTagCompound worldTag = worldData.getData();
                    if(worldTag.hasNoTags()){
                        com.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "No messages are currently on transit."));
                    }
                    Iterator tagserino = worldTag.func_150296_c().iterator();
                    while(tagserino.hasNext()){
                        String name = tagserino.next().toString();
                        NBTTagList tag = worldTag.getTagList(name, 10);
                        com.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "" + EnumChatFormatting.BOLD + name));
//                        for(int i=0; i<tag.tagCount(); i++){
//                            ItemStack stack = ItemStack.loadItemStackFromNBT(tag.getCompoundTagAt(i));
//                            com.addChatMessage(new ChatComponentText(EnumChatFormatting.RESET + "  - Package with " + stack.stackTagCompound.getTagList("Contents", 10).tagCount()));
//                        }
                        com.addChatMessage(new ChatComponentText(EnumChatFormatting.RESET + "  -" + tag.tagCount() + " packages"));
                    }

                    System.out.println(worldTag);
                }
            }
        }

        @Override
        public boolean canCommandSenderUseCommand(ICommandSender com) {
            return com instanceof EntityPlayer && FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152596_g(((EntityPlayer) com).getGameProfile());
        }

        @Override
        public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
            return null;
        }

        @Override
        public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
            return false;
        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    public class RemoveMessages implements ICommand {

        public RemoveMessages(){

        }

        @Override
        public String getCommandName() {
            return "removemessages";
        }

        @Override
        public String getCommandUsage(ICommandSender p_71518_1_) {
            return "removemessages <Player Name>";
        }

        @Override
        public List getCommandAliases() {
            return null;
        }

        @Override
        public void processCommand(ICommandSender com, String[] args) {
            if(args.length>0){
                RedstonicMessanger.resetMessages(args[0].toLowerCase(), com.getEntityWorld());
                com.addChatMessage(new ChatComponentText("Removed all Redstonic Messages for " + args[0]));
                return;
            }else{
                com.addChatMessage(new ChatComponentText("Invalid arguments: /removemessages <Player Name>"));
            }
        }

        @Override
        public boolean canCommandSenderUseCommand(ICommandSender com) {
            return com instanceof EntityPlayer && FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152596_g(((EntityPlayer) com).getGameProfile());
        }

        @Override
        public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
            return p_71516_2_.length >= 1 ? CommandBase.getListOfStringsMatchingLastWord(p_71516_2_, MinecraftServer.getServer().getAllUsernames()) : null;
        }

        @Override
        public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
            return true;
        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

}
