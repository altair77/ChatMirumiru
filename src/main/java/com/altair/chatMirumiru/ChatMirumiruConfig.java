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
	/** 最大ログ保存数 */
	private int savingLogMax = 10000;
	/** ログ更新間隔 */
	private int reloadLogInterval = 100;

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
		userRegExp = config.get("general", "UserRegularExpression", userRegExp, "This is regular expression for user message").getString();
		try{
			Pattern.compile(userRegExp);
		}catch(PatternSyntaxException e){
			ChatMirumiruCore.log.error("Failed to compile user regular expression");
			userRegExp = "^((\\[.+\\])+.+:\\x20|<.+>\\x20)";
		}
		systemRegExp = config.get("general", "SystemRegularExpression", systemRegExp, "This is regular expression for system message").getString();
		try{
			Pattern.compile(systemRegExp);
		}catch(PatternSyntaxException e){
			ChatMirumiruCore.log.error("Failed to compile system regular expression");
			systemRegExp = "";
		}
		savingLogMax = config.get("general", "SaveLogMax", savingLogMax, "This is max value of log to save in the Mod").getInt();
		if(savingLogMax < 100 || savingLogMax > Integer.MAX_VALUE){
			ChatMirumiruCore.log.error("Failed to set the max value of save log");
			savingLogMax = 10000;
		}
		reloadLogInterval = config.get("general", "ReloadLogInterval", reloadLogInterval, "this is interval value of log to reload").getInt();
		if(reloadLogInterval < 0 || reloadLogInterval > Integer.MAX_VALUE){
			ChatMirumiruCore.log.error("Failed to set the value of reloading interval");
			reloadLogInterval = 100;
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

	public int getSavingLogMax() {
		return savingLogMax;
	}

	public void setSavingLogMax(int savingLogMax) {
		this.savingLogMax = savingLogMax;
	}

	public int getReloadLogInterval() {
		return reloadLogInterval;
	}

	public void setReloadLogInterval(int reloadLogInterval) {
		this.reloadLogInterval = reloadLogInterval;
	}
}
