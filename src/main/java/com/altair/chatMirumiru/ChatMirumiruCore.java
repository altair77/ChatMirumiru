package com.altair.chatMirumiru;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = "ChatMirumiru", version = "0.0.1")
public class ChatMirumiruCore {

	@Mod.EventHandler
	public void init (FMLInitializationEvent event) {
		/*
		 * Event登録
		 */
		FMLCommonHandler.instance().bus().register(new ChatMirumiruEvent());
	}

}
