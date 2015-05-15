package com.raizunne.redstonic.Util;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Raizunne as a part of Redstonic
 * on 23/02/2015, 02:24 PM.
 */
public class TEHelper {

    private TEHelper() {

    }

    public static ItemStack capacitorLead = new ItemStack(GameRegistry.findItem("ThermalExpansion", "capacitor"), 1, 2);
    public static ItemStack capacitorHardened = new ItemStack(GameRegistry.findItem("ThermalExpansion", "capacitor"), 1, 3);
    public static ItemStack capacitorRedstone = new ItemStack(GameRegistry.findItem("ThermalExpansion", "capacitor"), 1, 4);
    public static ItemStack capacitorResonant = new ItemStack(GameRegistry.findItem("ThermalExpansion", "capacitor"), 1, 5);
    public static ItemStack capacitorCreative = GameRegistry.findItemStack("ThermalExpansion", "capacitorCreative", 1);
    public static ItemStack coilGold = new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), 1, 1);
    public static ItemStack coilSilver = new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), 1, 2);
    public static ItemStack coilElectrum = new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), 1, 3);
    public static ItemStack gearIron = new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"), 1, 12);
    public static ItemStack gearInvar = new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"), 1, 136);
    public static ItemStack gearElectrum = new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"), 1, 135);
    public static ItemStack gearEnderium = new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"), 1, 140);
    public static ItemStack gearShiny = new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"), 1, 140);
    public static ItemStack hardGlass = new ItemStack(GameRegistry.findBlock("ThermalExpansion", "blockGlass"));
    public static ItemStack ingotInvar = OreDictionary.getOres("ingotInvar").get(0);
    public static ItemStack ingotElectrum = OreDictionary.getOres("ingotElectrum").get(0);
    public static ItemStack ingotEnderium = OreDictionary.getOres("ingotEnderium").get(0);
    public static ItemStack ingotLumium = OreDictionary.getOres("ingotLumium").get(0);
    public static ItemStack blockEnderium = OreDictionary.getOres("blockEnderium").get(0);
    public static ItemStack blockElectrum = OreDictionary.getOres("blockElectrum").get(0);

	/* Furnace */
    public static void addFurnaceRecipe(int energy, ItemStack input, ItemStack output) {

        if (input == null || output == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setInteger("energy", energy);
        toSend.setTag("input", new NBTTagCompound());
        toSend.setTag("output", new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag("input"));
        output.writeToNBT(toSend.getCompoundTag("output"));
        FMLInterModComms.sendMessage("ThermalExpansion", "FurnaceRecipe", toSend);
    }

    public static void removeFurnaceRecipe(ItemStack input) {

        if (input == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setTag("input", new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag("input"));
        FMLInterModComms.sendMessage("ThermalExpansion", "RemoveFurnaceRecipe", toSend);
    }

    /* Pulverizer */
    public static void addPulverizerRecipe(int energy, ItemStack input, ItemStack primaryOutput) {

        addPulverizerRecipe(energy, input, primaryOutput, null, 0);
    }

    public static void addPulverizerRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput) {

        addPulverizerRecipe(energy, input, primaryOutput, secondaryOutput, 100);
    }

    public static void addPulverizerRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance) {

        if (input == null || primaryOutput == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setInteger("energy", energy);
        toSend.setTag("input", new NBTTagCompound());
        toSend.setTag("primaryOutput", new NBTTagCompound());

        if (secondaryOutput != null) {
            toSend.setTag("secondaryOutput", new NBTTagCompound());
        }

        input.writeToNBT(toSend.getCompoundTag("input"));
        primaryOutput.writeToNBT(toSend.getCompoundTag("primaryOutput"));

        if (secondaryOutput != null) {
            secondaryOutput.writeToNBT(toSend.getCompoundTag("secondaryOutput"));
            toSend.setInteger("secondaryChance", secondaryChance);
        }

        FMLInterModComms.sendMessage("ThermalExpansion", "PulverizerRecipe", toSend);
    }

    public static void removePulverizerRecipe(ItemStack input) {

        if (input == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setTag("input", new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag("input"));
        FMLInterModComms.sendMessage("ThermalExpansion", "RemovePulverizerRecipe", toSend);
    }

    /* Sawmill */
    public static void addSawmillRecipe(int energy, ItemStack input, ItemStack primaryOutput) {

        addSawmillRecipe(energy, input, primaryOutput, null, 0);
    }

    public static void addSawmillRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput) {

        addSawmillRecipe(energy, input, primaryOutput, secondaryOutput, 100);
    }

    public static void addSawmillRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance) {

        if (input == null || primaryOutput == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setInteger("energy", energy);
        toSend.setTag("input", new NBTTagCompound());
        toSend.setTag("primaryOutput", new NBTTagCompound());

        if (secondaryOutput != null) {
            toSend.setTag("secondaryOutput", new NBTTagCompound());
        }

        input.writeToNBT(toSend.getCompoundTag("input"));
        primaryOutput.writeToNBT(toSend.getCompoundTag("primaryOutput"));

        if (secondaryOutput != null) {
            secondaryOutput.writeToNBT(toSend.getCompoundTag("secondaryOutput"));
            toSend.setInteger("secondaryChance", secondaryChance);
        }

        FMLInterModComms.sendMessage("ThermalExpansion", "SawmillRecipe", toSend);
    }

    public static void removeSawmillRecipe(ItemStack input) {

        if (input == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setTag("input", new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag("input"));
        FMLInterModComms.sendMessage("ThermalExpansion", "RemoveSawmillRecipe", toSend);
    }

    /* Smelter */
    public static void addSmelterRecipe(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput) {

        addSmelterRecipe(energy, primaryInput, secondaryInput, primaryOutput, null, 0);
    }

    public static void addSmelterRecipe(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput, ItemStack secondaryOutput) {

        addSmelterRecipe(energy, primaryInput, secondaryInput, primaryOutput, secondaryOutput, 100);
    }

    public static void addSmelterRecipe(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput, ItemStack secondaryOutput,
                                        int secondaryChance) {

        if (primaryInput == null || secondaryInput == null || primaryOutput == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setInteger("energy", energy);
        toSend.setTag("primaryInput", new NBTTagCompound());
        toSend.setTag("secondaryInput", new NBTTagCompound());
        toSend.setTag("primaryOutput", new NBTTagCompound());

        if (secondaryOutput != null) {
            toSend.setTag("secondaryOutput", new NBTTagCompound());
        }

        primaryInput.writeToNBT(toSend.getCompoundTag("primaryInput"));
        secondaryInput.writeToNBT(toSend.getCompoundTag("secondaryInput"));
        primaryOutput.writeToNBT(toSend.getCompoundTag("primaryOutput"));

        if (secondaryOutput != null) {
            secondaryOutput.writeToNBT(toSend.getCompoundTag("secondaryOutput"));
            toSend.setInteger("secondaryChance", secondaryChance);
        }
        FMLInterModComms.sendMessage("ThermalExpansion", "SmelterRecipe", toSend);
    }

    public static void removeSmelterRecipe(ItemStack primaryInput, ItemStack secondaryInput) {

        if (primaryInput == null || secondaryInput == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setTag("primaryInput", new NBTTagCompound());
        toSend.setTag("secondaryInput", new NBTTagCompound());

        primaryInput.writeToNBT(toSend.getCompoundTag("primaryInput"));
        secondaryInput.writeToNBT(toSend.getCompoundTag("secondaryInput"));
        FMLInterModComms.sendMessage("ThermalExpansion", "RemoveSmelterRecipe", toSend);
    }

    /**
     * Use this to register an Ore TYPE as a "Blast" recipe - it will require Pyrotheum Dust to smelt. Do not add the prefix. This is an opt-in for ores which
     * do NOT have vanilla furnace recipes.
     * <p/>
     * Ex: "Steel" or "ElectrumFlux", not "dustSteel" or "dustElectrumFlux"
     *
     * @param oreType
     */
    public static void addSmelterBlastOre(String oreType) {

        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setString("oreType", oreType);

        FMLInterModComms.sendMessage("ThermalExpansion", "SmelterBlastOreType", toSend);
    }

    /* Crucible */
    public static void addCrucibleRecipe(int energy, ItemStack input, FluidStack output) {

        if (input == null || output == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setInteger("energy", energy);
        toSend.setTag("input", new NBTTagCompound());
        toSend.setTag("output", new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag("input"));
        output.writeToNBT(toSend.getCompoundTag("output"));

        FMLInterModComms.sendMessage("ThermalExpansion", "CrucibleRecipe", toSend);
    }

    public static void removeCrucibleRecipe(ItemStack input) {

        if (input == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setTag("input", new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag("input"));
        FMLInterModComms.sendMessage("ThermalExpansion", "RemoveCrucibleRecipe", toSend);
    }

    /* Transposer */
    public static void addTransposerFill(int energy, ItemStack input, ItemStack output, FluidStack fluid, boolean reversible) {

        if (input == null || output == null || fluid == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setInteger("energy", energy);
        toSend.setTag("input", new NBTTagCompound());
        toSend.setTag("output", new NBTTagCompound());
        toSend.setTag("fluid", new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag("input"));
        output.writeToNBT(toSend.getCompoundTag("output"));
        toSend.setBoolean("reversible", reversible);
        fluid.writeToNBT(toSend.getCompoundTag("fluid"));

        FMLInterModComms.sendMessage("ThermalExpansion", "TransposerFillRecipe", toSend);
    }

    public static void addTransposerExtract(int energy, ItemStack input, ItemStack output, FluidStack fluid, int chance, boolean reversible) {

        if (input == null || output == null || fluid == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setInteger("energy", energy);
        toSend.setTag("input", new NBTTagCompound());
        toSend.setTag("output", new NBTTagCompound());
        toSend.setTag("fluid", new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag("input"));
        output.writeToNBT(toSend.getCompoundTag("output"));
        toSend.setBoolean("reversible", reversible);
        toSend.setInteger("chance", chance);
        fluid.writeToNBT(toSend.getCompoundTag("fluid"));

        FMLInterModComms.sendMessage("ThermalExpansion", "TransposerExtractRecipe", toSend);
    }

    public static void removeTransposerFill(ItemStack input, FluidStack fluid) {

        if (input == null || fluid == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setTag("input", new NBTTagCompound());
        toSend.setTag("fluid", new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag("input"));
        fluid.writeToNBT(toSend.getCompoundTag("fluid"));
        FMLInterModComms.sendMessage("ThermalExpansion", "RemoveTransposerFillRecipe", toSend);
    }

    public static void removeTransposerExtract(ItemStack input) {

        if (input == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setTag("input", new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag("input"));
        FMLInterModComms.sendMessage("ThermalExpansion", "RemoveTransposerExtractRecipe", toSend);
    }

    /* Charger */
    public static void addChargerRecipe(int energy, ItemStack input, ItemStack output) {

        if (input == null || output == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setInteger("energy", energy);
        toSend.setTag("input", new NBTTagCompound());
        toSend.setTag("output", new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag("input"));
        output.writeToNBT(toSend.getCompoundTag("output"));
        FMLInterModComms.sendMessage("ThermalExpansion", "ChargerRecipe", toSend);
    }

    public static void removeChargerRecipe(ItemStack input) {

        if (input == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setTag("input", new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag("input"));
        FMLInterModComms.sendMessage("ThermalExpansion", "RemoveChargerRecipe", toSend);
    }

    /* Insolator */
    public static void addInsolatorRecipe(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput) {

        addInsolatorRecipe(energy, primaryInput, secondaryInput, primaryOutput, null, 0);
    }

    public static void addInsolatorRecipe(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput, ItemStack secondaryOutput) {

        addInsolatorRecipe(energy, primaryInput, secondaryInput, primaryOutput, secondaryOutput, 100);
    }

    public static void addInsolatorRecipe(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput, ItemStack secondaryOutput,
                                          int secondaryChance) {

        if (primaryInput == null || secondaryInput == null || primaryOutput == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setInteger("energy", energy);
        toSend.setTag("primaryInput", new NBTTagCompound());
        toSend.setTag("secondaryInput", new NBTTagCompound());
        toSend.setTag("primaryOutput", new NBTTagCompound());

        if (secondaryOutput != null) {
            toSend.setTag("secondaryOutput", new NBTTagCompound());
        }

        primaryInput.writeToNBT(toSend.getCompoundTag("primaryInput"));
        secondaryInput.writeToNBT(toSend.getCompoundTag("secondaryInput"));
        primaryOutput.writeToNBT(toSend.getCompoundTag("primaryOutput"));

        if (secondaryOutput != null) {
            secondaryOutput.writeToNBT(toSend.getCompoundTag("secondaryOutput"));
            toSend.setInteger("secondaryChance", secondaryChance);
        }
        FMLInterModComms.sendMessage("ThermalExpansion", "InsolatorRecipe", toSend);
    }

    public static void removeInsolatorRecipe(ItemStack primaryInput, ItemStack secondaryInput) {

        if (primaryInput == null || secondaryInput == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setTag("primaryInput", new NBTTagCompound());
        toSend.setTag("secondaryInput", new NBTTagCompound());

        primaryInput.writeToNBT(toSend.getCompoundTag("primaryInput"));
        secondaryInput.writeToNBT(toSend.getCompoundTag("secondaryInput"));
        FMLInterModComms.sendMessage("ThermalExpansion", "RemoveInsolatorRecipe", toSend);
    }

    /**
     * DYNAMOS
     */

	/* Magmatic */
    public static void addMagmaticFuel(String fluidName, int energy) {

        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setString("fluidName", fluidName);
        toSend.setInteger("energy", energy);

        FMLInterModComms.sendMessage("ThermalExpansion", "MagmaticFuel", toSend);
    }

    /* Compression */
    public static void addCompressionFuel(String fluidName, int energy) {

        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setString("fluidName", fluidName);
        toSend.setInteger("energy", energy);

        FMLInterModComms.sendMessage("ThermalExpansion", "CompressionFuel", toSend);
    }

    /* Reactant */
    public static void addReactantFuel(String fluidName, int energy) {

        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setString("fluidName", fluidName);
        toSend.setInteger("energy", energy);

        FMLInterModComms.sendMessage("ThermalExpansion", "ReactantFuel", toSend);
    }

    /* Coolants */
    public static void addCoolant(String fluidName, int energy) {

        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setString("fluidName", fluidName);
        toSend.setInteger("energy", energy);

        FMLInterModComms.sendMessage("ThermalExpansion", "Coolant", toSend);
    }
}
