package com.altair.chatMirumiru;

import net.minecraftforge.client.event.ClientChatReceivedEvent;

import com.altair.chatMirumiru.gui.ChatMirumiruGui;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ChatMirumiruEvent {

	private ChatMirumiruGui window;

	public ChatMirumiruEvent(ChatMirumiruGui window) {
		this.window = window;
	}

	/*
	 * クライアントチャット更新時イベント
	 */
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void chat(ClientChatReceivedEvent event) {

		ChatMirumiruCore.log.info("[ChatMirumiru] " + event.message.getFormattedText());

		window.addList(event.message.getFormattedText());

	}

}
