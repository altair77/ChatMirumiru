package com.altair.chatMirumiru;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.altair.chatMirumiru.gui.ChatMirumiruGui;

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
