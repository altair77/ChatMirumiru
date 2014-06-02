package com.altair.chatMirumiru;

import com.altair.chatMirumiru.gui.ChatMirumiruGui;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ChatMirumiruKeyEvent {

	private ChatMirumiruGui window;

	public ChatMirumiruKeyEvent(ChatMirumiruGui window) {
		this.window = window;
	}

	/*
	* KeyInputEventの実装
	*/
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void inputKey(InputEvent.KeyInputEvent event) {
		if (ChatMirumiruCore.openGuiKey.isPressed())
		{
			window.setVisible(true);
		}
	}

}
