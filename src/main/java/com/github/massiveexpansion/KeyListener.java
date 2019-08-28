package com.github.massiveexpansion;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KeyListener {
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL)
	public static void onEvent(KeyInputEvent event) {
		for (KeyBinding key : MassiveExpansion.getKeys()) {
			if (key.isPressed()) {
				switch (key.getKeyDescription()) {
					case "key.massiveexpansion.backpack": Minecraft.getMinecraft().player.sendChatMessage("/bp"); break;
					case "key.massiveexpansion.chat.faction": Minecraft.getMinecraft().player.sendChatMessage("f:"); break;
					case "key.massiveexpansion.chat.local": Minecraft.getMinecraft().player.sendChatMessage("l:"); break;
					case "key.massiveexpansion.chat.help": Minecraft.getMinecraft().player.sendChatMessage("h:"); break;
					case "key.massiveexpansion.chat.general": Minecraft.getMinecraft().player.sendChatMessage("g:"); break;
					case "key.massiveexpansion.chat.directmessage": openDirectMessage("/msg "); break;
				}
				
			}
		}
	}
	
	private static Robot robot;
	private static void openDirectMessage(String msg) {
		if (robot == null) {
			try {
				robot = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
				return;
			}
		}
		
		KeyBinding.onTick(Minecraft.getMinecraft().gameSettings.keyBindChat.getKeyCode());
		
		for (char c : msg.toCharArray()) {
			if (Character.isUpperCase(c)) {
	            robot.keyPress(KeyEvent.VK_SHIFT);
	        }
	        robot.keyPress(Character.toUpperCase(c));
	        robot.keyRelease(Character.toUpperCase(c));

	        if (Character.isUpperCase(c)) {
	            robot.keyRelease(KeyEvent.VK_SHIFT);
	        }
		}
	}
}
