package com.altair.chatMirumiru;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import com.altair.chatMirumiru.gui.ChatMirumiruGui;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = ChatMirumiruCore.modid, version = ChatMirumiruCore.version)
public class ChatMirumiruCore {
	public static final String modid = "ChatMirumiru";
	public static final String version = "1.0.2";

	public static final Logger log = LogManager.getLogger(modid);
	@SideOnly(Side.CLIENT)
	public static final KeyBinding openGuiKey = new KeyBinding("key.openGuiKey.name", Keyboard.KEY_I, "ChatMirumiru.inputEvent.name");


	@EventHandler
	public void init(FMLInitializationEvent event) {
		ChatMirumiruCore.log.info("Init.");

		/*
		 * GUI作成
		 */
		ChatMirumiruGui window = new ChatMirumiruGui();

		/*
		 * KeyBinding登録
		 */
		if (event.getSide() == Side.CLIENT)
		{
			ClientRegistry.registerKeyBinding(openGuiKey);
		}

		FMLCommonHandler.instance().bus().register(new ChatMirumiruKeyEvent(window));

		/*
		 * ChatEvent登録
		 */
		MinecraftForge.EVENT_BUS.register(new ChatMirumiruEvent(window));


	}

}
