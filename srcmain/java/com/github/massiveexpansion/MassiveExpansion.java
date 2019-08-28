package com.github.massiveexpansion;

import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = MassiveExpansion.MODID, name = MassiveExpansion.NAME, version = MassiveExpansion.VERSION)
public class MassiveExpansion {
	public static final String MODID = "massiveexpansion";
	public static final String NAME = "Massive Expansion";
	public static final String VERSION = "1";
	
	private static Logger log;
	
	@EventHandler
	public void FMLPreInit(FMLPreInitializationEvent event) {
		log = event.getModLog();
	}
	
	private static KeyBinding[] keys = new KeyBinding[6];
	@EventHandler
	public void FMLInit(FMLInitializationEvent event) {
		log.info("Massive Expansion v" + VERSION + " for Minecraft 1.12.2. Works best on massivecraft.com ;)");
		
		
		if (event.getSide() == Side.CLIENT) {
			keys[0] = new KeyBinding("key.massiveexpansion.backpack", Keyboard.KEY_B, "key.massiveexpansion.category");
			keys[1] = new KeyBinding("key.massiveexpansion.chat.faction", Keyboard.KEY_V, "key.massiveexpansion.category");
			keys[2] = new KeyBinding("key.massiveexpansion.chat.local", Keyboard.KEY_X, "key.massiveexpansion.category");
			keys[3] = new KeyBinding("key.massiveexpansion.chat.help", Keyboard.KEY_H, "key.massiveexpansion.category");
			keys[4] = new KeyBinding("key.massiveexpansion.chat.general", Keyboard.KEY_G, "key.massiveexpansion.category");
			keys[5] = new KeyBinding("key.massiveexpansion.chat.directmessage", Keyboard.KEY_Y, "key.massiveexpansion.category");
			for (KeyBinding key : keys) ClientRegistry.registerKeyBinding(key);
			
			MinecraftForge.EVENT_BUS.register(KeyListener.class);
		} else {
			log.error("This is a clientside only mod! Mod has been disabled, delete the jar to save approximately 2kb of ram.");
		}
	}
	
	public static KeyBinding[] getKeys() {
		return keys;
	}
}
