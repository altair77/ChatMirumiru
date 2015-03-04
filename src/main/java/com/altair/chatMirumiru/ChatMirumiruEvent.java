package com.altair.chatMirumiru;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.altair.chatMirumiru.gui.ChatMirumiruGui;

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

		//ChatMirumiruCore.log.info(event.message.getFormattedText());  // デバッグ用

		window.addList(event.message.getFormattedText());

	}

}
