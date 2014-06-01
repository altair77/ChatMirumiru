package com.altair.chatMirumiru;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = "ChatMirumiru", version = "0.0.1")
public class ChatMirumiruEvent {

	/*
	 * クライアントチャット更新時イベント
	 */
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onChat(ClientChatReceivedEvent event) {
		MinecraftServer.getServer().logInfo("[Chat] " + event.message.getFormattedText());
	}

}
