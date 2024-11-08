package net.villagelaw.procedures;

import net.villagelaw.network.VillagelawModVariables;
import net.villagelaw.VillagelawMod;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class StartupTasksProcedure {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		execute();
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
		File config = new File("");
		com.google.gson.JsonObject configInput = new com.google.gson.JsonObject();
		List<Object> vandalBlocks = new ArrayList<>();
		double counter = 0;
		String configpath = "";
		String currentJSON = "";
		com.google.gson.JsonArray configVals = new com.google.gson.JsonArray();
		com.google.gson.JsonArray exemptList = new com.google.gson.JsonArray();
		configpath = FMLPaths.GAMEDIR.get().toString() + "" + File.separator + "config";
		config = new File(configpath, File.separator + "villagelaw.json");
		if (!config.exists()) {
			VillagelawMod.LOGGER.debug(("[DEBUG] CONFIG CREATED AT: " + configpath));
			try {
				config.getParentFile().mkdirs();
				config.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			vandalBlocks.add(Blocks.FIRE);
			vandalBlocks.add(Blocks.SOUL_FIRE);
			vandalBlocks.add(Blocks.DEAD_BUSH);
			vandalBlocks.add(Blocks.DEAD_BUSH);
			vandalBlocks.add(Blocks.BROWN_MUSHROOM);
			vandalBlocks.add(Blocks.RED_MUSHROOM);
			vandalBlocks.add(Blocks.PEONY);
			vandalBlocks.add(Blocks.PINK_PETALS);
			vandalBlocks.add(Blocks.LARGE_FERN);
			vandalBlocks.add(Blocks.WITHER_ROSE);
			vandalBlocks.add(Blocks.LILY_OF_THE_VALLEY);
			vandalBlocks.add(Blocks.PITCHER_PLANT);
			vandalBlocks.add(Blocks.GRASS);
			vandalBlocks.add(Blocks.FERN);
			vandalBlocks.add(Blocks.TALL_GRASS);
			vandalBlocks.add(Blocks.SNOW);
			vandalBlocks.add(Blocks.POWDER_SNOW);
			vandalBlocks.add(Blocks.ICE);
			vandalBlocks.add(Blocks.PACKED_ICE);
			vandalBlocks.add(Blocks.FROSTED_ICE);
			vandalBlocks.add(Blocks.BLUE_ICE);
			vandalBlocks.add(Blocks.WHITE_TULIP);
			vandalBlocks.add(Blocks.LILAC);
			vandalBlocks.add(Blocks.WARPED_FUNGUS);
			vandalBlocks.add(Blocks.CRIMSON_FUNGUS);
			vandalBlocks.add(Blocks.COBWEB);
			vandalBlocks.add(Blocks.PINK_TULIP);
			vandalBlocks.add(Blocks.OXEYE_DAISY);
			vandalBlocks.add(Blocks.CORNFLOWER);
			vandalBlocks.add(Blocks.DANDELION);
			vandalBlocks.add(Blocks.POPPY);
			vandalBlocks.add(Blocks.BLUE_ORCHID);
			vandalBlocks.add(Blocks.ALLIUM);
			vandalBlocks.add(Blocks.AZURE_BLUET);
			vandalBlocks.add(Blocks.RED_TULIP);
			vandalBlocks.add(Blocks.SUNFLOWER);
			vandalBlocks.add(Blocks.ORANGE_TULIP);
			vandalBlocks.add(Blocks.ROSE_BUSH);
			vandalBlocks.add(Blocks.SWEET_BERRY_BUSH);
			try {
				FileWriter configwriter = new FileWriter(config, false);
				BufferedWriter configbw = new BufferedWriter(configwriter);
				{
					configbw.write("Village Crimes: ");
					configbw.newLine();
				}
				{
					configbw.write("{\"vandalism\":\"true\"}");
					configbw.newLine();
				}
				{
					configbw.write("{\"theft\":\"true\"}");
					configbw.newLine();
				}
				{
					configbw.write("{\"items\":\"true\"}");
					configbw.newLine();
				}
				{
					configbw.write("");
					configbw.newLine();
				}
				{
					configbw.write("Block Break Protection: ");
					configbw.newLine();
				}
				{
					configbw.write("{\"block_protection\":\"true\"}");
					configbw.newLine();
				}
				{
					configbw.write("");
					configbw.newLine();
				}
				{
					configbw.write("Build Protection: ");
					configbw.newLine();
				}
				{
					configbw.write("{\"build_protection\":\"true\"}");
					configbw.newLine();
				}
				{
					configbw.write("");
					configbw.newLine();
				}
				{
					configbw.write("Vandalism Exempt Blocks:");
					configbw.newLine();
				}
				counter = 0;
				currentJSON = "";
				{
					configbw.write("{");
					configbw.newLine();
				}
				{
					configbw.write("\"exempt_blocks\":");
					configbw.newLine();
				}
				{
					configbw.write("[");
					configbw.newLine();
				}
				while (counter < vandalBlocks.size()) {
					currentJSON = ("" + vandalBlocks.get((int) counter)).replace("Block", "");
					currentJSON = currentJSON.replace("{", "\"");
					currentJSON = currentJSON.replace("}", "\"");
					if (counter == vandalBlocks.size() - 1) {
						{
							configbw.write(currentJSON);
							configbw.newLine();
						}
					} else {
						{
							configbw.write((currentJSON + ", "));
							configbw.newLine();
						}
					}
					counter = counter + 1;
				}
				{
					configbw.write("]");
					configbw.newLine();
				}
				{
					configbw.write("}");
					configbw.newLine();
				}
				configbw.close();
				configwriter.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		currentJSON = "";
		try {
			BufferedReader configReader = new BufferedReader(new FileReader(config));
			String stringiterator = "";
			while ((stringiterator = configReader.readLine()) != null) {
				if (stringiterator.contains("{") && stringiterator.contains("}")) {
					configInput = new Object() {
						public com.google.gson.JsonObject parse(String rawJson) {
							try {
								return new com.google.gson.Gson().fromJson(rawJson, com.google.gson.JsonObject.class);
							} catch (Exception e) {
								VillagelawMod.LOGGER.error(e);
								return new com.google.gson.Gson().fromJson("{}", com.google.gson.JsonObject.class);
							}
						}
					}.parse(stringiterator);
					if (!(configInput.size() == 0)) {
						configVals.add(configInput);
					}
					currentJSON = "";
				} else if (stringiterator.contains("{") && !stringiterator.contains("}")) {
					currentJSON = "";
					currentJSON = stringiterator;
				} else if (!stringiterator.contains("{") && stringiterator.contains("}")) {
					currentJSON = currentJSON + "" + stringiterator;
					configInput = new Object() {
						public com.google.gson.JsonObject parse(String rawJson) {
							try {
								return new com.google.gson.Gson().fromJson(rawJson, com.google.gson.JsonObject.class);
							} catch (Exception e) {
								VillagelawMod.LOGGER.error(e);
								return new com.google.gson.Gson().fromJson("{}", com.google.gson.JsonObject.class);
							}
						}
					}.parse(currentJSON);
					if (!(configInput.size() == 0)) {
						configVals.add(configInput);
					}
					currentJSON = "";
				} else {
					currentJSON = currentJSON + "" + stringiterator;
				}
			}
			configReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (!configVals.isEmpty()) {
			configInput = configVals.get(0).getAsJsonObject();
			currentJSON = configInput.keySet().stream().toList().get(0);
			if ((currentJSON).equals("vandalism")) {
				if ((configInput.get("vandalism").getAsString()).equals("true")) {
					VillagelawModVariables.isVandalismCrime = true;
				} else {
					VillagelawModVariables.isVandalismCrime = false;
				}
			}
			if ((currentJSON).equals("theft")) {
				if ((configInput.get("theft").getAsString()).equals("true")) {
					VillagelawModVariables.isTheftCrime = true;
				} else {
					VillagelawModVariables.isTheftCrime = false;
				}
			}
			if ((currentJSON).equals("item")) {
				if ((configInput.get("item").getAsString()).equals("true")) {
					VillagelawModVariables.isItemCrime = true;
				} else {
					VillagelawModVariables.isItemCrime = false;
				}
			}
			if ((currentJSON).equals("block_protection")) {
				if ((configInput.get("block_protection").getAsString()).equals("true")) {
					VillagelawModVariables.isBlockProtection = true;
				} else {
					VillagelawModVariables.isBlockProtection = false;
				}
			}
			if ((currentJSON).equals("build_protection")) {
				if ((configInput.get("build_protection").getAsString()).equals("true")) {
					VillagelawModVariables.isBuildProtection = true;
				} else {
					VillagelawModVariables.isBuildProtection = false;
				}
			}
			if ((currentJSON).equals("exempt_blocks")) {
				exemptList = configInput.get("exempt_blocks").getAsJsonArray();
				while (!exemptList.isEmpty()) {
					VillagelawModVariables.ExemptBlocks.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation((exemptList.get(0).getAsString()).toLowerCase(java.util.Locale.ENGLISH))));
					exemptList.remove(0);
				}
			}
			configVals.remove(0);
		}
	}
}
