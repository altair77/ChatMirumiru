package com.altair.chatMirumiru;

import java.awt.Color;
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
	/** チャット標準文字色 */
	private int colorDefault = Color.BLACK.getRGB();
	/** チャット黒文字色 */
	private int colorBlack = Color.BLACK.getRGB();
	/** チャット暗い青文字色 */
	private int colorDrakBlue = new Color(0, 0, 139).getRGB();
	/** チャット暗い緑文字色 */
	private int colorDarkGreen = new Color(0, 100, 0).getRGB();
	/** チャット暗いアクア文字色 */
	private int colorDarkAqua = new Color(0, 139, 139).getRGB();
	/** チャット暗い赤文字色 */
	private int colorDarkRed = new Color(139, 0, 0).getRGB();
	/** チャット暗い紫文字色 */
	private int colorDarkPurple = new Color(148, 0, 211).getRGB();
	/** チャット金文字色 */
	private int colorGold = new Color(255, 215, 0).getRGB();
	/** チャット灰文字色 */
	private int colorGlay = Color.GRAY.getRGB();
	/** チャット暗い灰文字色 */
	private int colorDarkGlay = Color.DARK_GRAY.getRGB();
	/** チャット青文字色 */
	private int colorBlue = Color.BLUE.getRGB();
	/** チャット緑文字色 */
	private int colorGreen = Color.GREEN.getRGB();
	/** チャットアクア文字色 */
	private int colorAqua = new Color(0, 255, 255).getRGB();
	/** チャット赤文字色 */
	private int colorRed = Color.RED.getRGB();
	/** チャット明るい紫文字色 */
	private int colorLightPurple = new Color(238, 130, 238).getRGB();
	/** チャット黄文字色 */
	private int colorYellow = Color.YELLOW.getRGB();
	/** チャット白文字色 */
	private int colorWhite = Color.WHITE.getRGB();
	/** チャットハイライト色 */
	private int colorHighlight = new Color(255, 165, 0).getRGB();
	/** チャット背景色 */
	private int colorBackground = Color.LIGHT_GRAY.getRGB();

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
		colorDefault  = config.get("general", "ColorDefault", colorDefault, "This is the int value of the RGB color to view default text").getInt();
		colorBlack  = config.get("general", "ColorBlack", colorBlack, "This is the int value of the RGB color to view \"BLACK\" text").getInt();
		colorDrakBlue  = config.get("general", "ColorDrakBlue", colorDrakBlue, "This is the int value of the RGB color to view \"DARK_BULE\" text").getInt();
		colorDarkGreen  = config.get("general", "ColorDarkGreen", colorDarkGreen, "This is the int value of the RGB color to view \"DARK_GREEN\" text").getInt();
		colorDarkAqua  = config.get("general", "ColorDarkAqua", colorDarkAqua, "This is the int value of the RGB color to view \"DARK_AQUA\" text").getInt();
		colorDarkRed  = config.get("general", "ColorDarkRed", colorDarkRed, "This is the int value of the RGB color to view \"DARK_RED\" text").getInt();
		colorDarkPurple  = config.get("general", "ColorDarkPurple", colorDarkPurple, "This is the int value of the RGB color to view \"DARK_PURPLE\" text").getInt();
		colorGold  = config.get("general", "ColorGold", colorGold, "This is the int value of the RGB color to view \"GOLD\" text").getInt();
		colorGlay  = config.get("general", "ColorGlay", colorGlay, "This is the int value of the RGB color to view \"GLAY\" text").getInt();
		colorDarkGlay  = config.get("general", "ColorDarkGlay", colorDarkGlay, "This is the int value of the RGB color to view \"DARK_GLAY\" text").getInt();
		colorBlue  = config.get("general", "ColorBlue", colorBlue, "This is the int value of the RGB color to view \"BLUE\" text").getInt();
		colorGreen  = config.get("general", "ColorGreen", colorGreen, "This is the int value of the RGB color to view \"GREEN\" text").getInt();
		colorAqua  = config.get("general", "ColorAqua", colorAqua, "This is the int value of the RGB color to view \"AQUA\" text").getInt();
		colorRed  = config.get("general", "ColorRed", colorRed, "This is the int value of the RGB color to view \"RED\" text").getInt();
		colorLightPurple  = config.get("general", "ColorLightPurple", colorLightPurple, "This is the int value of the RGB color to view \"LIGHT_PURPLE\" text").getInt();
		colorYellow  = config.get("general", "ColorYellow", colorYellow, "This is the int value of the RGB color to view \"YELLOW\" text").getInt();
		colorWhite  = config.get("general", "ColorWhite", colorWhite, "This is the int value of the RGB color to view \"WHITE\" text").getInt();
		colorHighlight  = config.get("general", "ColorHighlight", colorHighlight, "This is the int value of the RGB color to view highlight").getInt();
		colorBackground  = config.get("general", "ColorBackground", colorBackground, "This is the int value of the RGB color to view background").getInt();
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

	public int getColorBlack() {
		return colorBlack;
	}

	public void setColorBlack(int colorBlack) {
		this.colorBlack = colorBlack;
	}

	public int getColorDrakBlue() {
		return colorDrakBlue;
	}

	public void setColorDrakBlue(int colorDrakBlue) {
		this.colorDrakBlue = colorDrakBlue;
	}

	public int getColorDarkGreen() {
		return colorDarkGreen;
	}

	public void setColorDarkGreen(int colorDarkGreen) {
		this.colorDarkGreen = colorDarkGreen;
	}

	public int getColorDarkAqua() {
		return colorDarkAqua;
	}

	public void setColorDarkAqua(int colorDarkAqua) {
		this.colorDarkAqua = colorDarkAqua;
	}

	public int getColorDarkRed() {
		return colorDarkRed;
	}

	public void setColorDarkRed(int colorDarkRed) {
		this.colorDarkRed = colorDarkRed;
	}

	public int getColorDarkPurple() {
		return colorDarkPurple;
	}

	public void setColorDarkPurple(int colorDarkPurple) {
		this.colorDarkPurple = colorDarkPurple;
	}

	public int getColorGold() {
		return colorGold;
	}

	public void setColorGold(int colorGold) {
		this.colorGold = colorGold;
	}

	public int getColorGlay() {
		return colorGlay;
	}

	public void setColorGlay(int colorGlay) {
		this.colorGlay = colorGlay;
	}

	public int getColorDarkGlay() {
		return colorDarkGlay;
	}

	public void setColorDarkGlay(int colorDarkGlay) {
		this.colorDarkGlay = colorDarkGlay;
	}

	public int getColorBlue() {
		return colorBlue;
	}

	public void setColorBlue(int colorBlue) {
		this.colorBlue = colorBlue;
	}

	public int getColorGreen() {
		return colorGreen;
	}

	public void setColorGreen(int colorGreen) {
		this.colorGreen = colorGreen;
	}

	public int getColorAqua() {
		return colorAqua;
	}

	public void setColorAqua(int colorAqua) {
		this.colorAqua = colorAqua;
	}

	public int getColorRed() {
		return colorRed;
	}

	public void setColorRed(int colorRed) {
		this.colorRed = colorRed;
	}

	public int getColorLightPurple() {
		return colorLightPurple;
	}

	public void setColorLightPurple(int colorLightPurple) {
		this.colorLightPurple = colorLightPurple;
	}

	public int getColorYellow() {
		return colorYellow;
	}

	public void setColorYellow(int colorYellow) {
		this.colorYellow = colorYellow;
	}

	public int getColorWhite() {
		return colorWhite;
	}

	public void setColorWhite(int colorWhite) {
		this.colorWhite = colorWhite;
	}

	public int getColorDefault() {
		return colorDefault;
	}

	public void setColorDefault(int colorDefault) {
		this.colorDefault = colorDefault;
	}

	public int getColorHighlight() {
		return colorHighlight;
	}

	public void setColorHighlight(int colorHighlight) {
		this.colorHighlight = colorHighlight;
	}

	public int getColorBackground() {
		return colorBackground;
	}

	public void setColorBackground(int colorBackground) {
		this.colorBackground = colorBackground;
	}
}
