package com.altair.chatMirumiru;

import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.altair.chatMirumiru.gui.ChatMirumiruGui;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = "ChatMirumiru", version = "0.0.1")
public class ChatMirumiruCore {

	public static final Logger log = LogManager.getLogger("ChatMirumiru");

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ChatMirumiruCore.log.info("[ChatMirumiru] Init");

		ChatMirumiruGui window = new ChatMirumiruGui();
		window.setVisible(true);

		/*
		 * Event登録
		 */
		MinecraftForge.EVENT_BUS.register(new ChatMirumiruEvent(window));


	}

}
