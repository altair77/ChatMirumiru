package com.altair.chatMirumiru;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ChatMirumiruConfig {

	/** ユーザチャット正規表現 */
	private String userRegExp = "";
	/** システムチャット正規表現 */
	private String systemRegExp = "";

	public ChatMirumiruConfig(FMLPreInitializationEvent event) {
		initConfiguration(event);
	}
	/**
	 * コンフィグのロードと変数設定
	 * @param event
	 */
	public void initConfiguration(FMLPreInitializationEvent event) {

		/*
		 * ChatMirumiru.cfgの自動生成
		 */
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		try{
			config.load();

			config.get("general", "USER_REGULAR_EXPRESSION", "^((\\[.+\\])+.+:\\x20|<.+>\\x20)", "This is regular expression for user message");
			config.removeCategory(config.getCategory("general"));
		}catch(Exception e){
			ChatMirumiruCore.log.error("Failed to open config file");
		}finally{
			config.save();
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
