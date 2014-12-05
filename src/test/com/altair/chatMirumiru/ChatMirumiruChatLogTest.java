package com.altair.chatMirumiru;

import static org.junit.Assert.*;

import org.junit.Test;

import com.altair.chatMirumiru.ChatMirumiruChatLog;

public class ChatMirumiruChatLogTest {

	@Test
	public void test() {
		ChatMirumiruChatLog chatlog = new ChatMirumiruChatLog();
		assertSame(chatlog.size(), 0);
	}

}
