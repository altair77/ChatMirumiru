package com.altair.chatMirumiru;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ChatMirumiruConfig {

	private FMLPreInitializationEvent event = null;
	/** ユーザチャット正規表現 */
	private String userRegExp = "^((\\[.+\\])+.+:\\x20|<.+>\\x20)";
	/** システムチャット正規表現 */
	private String systemRegExp = "";

	public ChatMirumiruConfig(FMLPreInitializationEvent event) {
		this.event = event;
		initConfiguration();
	}
	/**
	 * コンフィグのロードと変数設定
	 * @param event
	 */
	public void initConfiguration() {
		/*
		 * ChatMirumiru.cfgの自動生成
		 */
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		try{
			config.load();
			loadConfigration(config);
		}catch(Exception e){
			ChatMirumiruCore.log.error("Failed to open config file");
		}finally{
			config.save();
		}
	}

	public void resetConfigration() {
		/*
		 * ChatMirumiru.cfgの自動生成
		 */
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		try{
			config.load();
			config.removeCategory(config.getCategory("general"));
			loadConfigration(config);
		}catch(Exception e){
			ChatMirumiruCore.log.error("Failed to open config file");
		}finally{
			config.save();
		}

	}

	private void loadConfigration(Configuration config) {
		userRegExp = config.get("general", "USER_REGULAR_EXPRESSION", userRegExp, "This is regular expression for user message").getString();
		try{
			Pattern.compile(userRegExp);
		}catch(PatternSyntaxException e){
			ChatMirumiruCore.log.error("Failed to compile user regular expression");
			userRegExp = "^((\\[.+\\])+.+:\\x20|<.+>\\x20)";
		}
		systemRegExp = config.get("general", "SYSTEM_REGULAR_EXPRESSION", systemRegExp, "This is regular expression for system message").getString();
		try{
			Pattern.compile(systemRegExp);
		}catch(PatternSyntaxException e){
			ChatMirumiruCore.log.error("Failed to compile system regular expression");
			systemRegExp = "";
		}
	}

	public String getUserRegExp() {
		return userRegExp;
	}


	public void setUserRegExp(String userRegExp) {
		this.userRegExp = userRegExp;
	}


	public String getSystemRegExp() {
		return systemRegExp;
	}


	public void setSystemRegExp(String systemRegExp) {
		this.systemRegExp = systemRegExp;
	}

}
