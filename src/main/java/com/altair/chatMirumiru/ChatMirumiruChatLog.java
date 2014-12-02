package com.altair.chatMirumiru;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class ChatMirumiruChatLog {

	private final ChatMirumiruConfig config = ChatMirumiruCore.config;

	private ArrayList<String> chatText = new ArrayList<String>();
	private ArrayList<Long> chatTime = new ArrayList<Long>();

	public ChatMirumiruChatLog() {

	}

	public void add(String text){
		chatText.add(text);
		chatTime.add(new Date().getTime());
		if(size() > config.getSavingLogMax())
			remove(0);
	}

	public String getMainText(int index){
		return chatText.get(index);
	}

	public long getTime(int index){
		return chatTime.get(index);
	}

	public String getDateText(long time) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"),
				Locale.JAPANESE);
		cal.setTimeInMillis(time);
		String rtn = "";
		rtn += String.valueOf(cal.get(Calendar.YEAR) % 100) + "/";
		if (cal.get(Calendar.MONTH) + 1 < 10)
			rtn += "0";
		rtn += String.valueOf(cal.get(Calendar.MONTH) + 1) + "/";
		if (cal.get(Calendar.DATE) < 10)
			rtn += "0";
		rtn += String.valueOf(cal.get(Calendar.DATE)) + " ";
		if (cal.get(Calendar.HOUR_OF_DAY) < 10)
			rtn += "0";
		rtn += String.valueOf(cal.get(Calendar.HOUR_OF_DAY)) + ":";
		if (cal.get(Calendar.MINUTE) < 10)
			rtn += "0";
		rtn += String.valueOf(cal.get(Calendar.MINUTE)) + ":";
		if (cal.get(Calendar.SECOND) < 10)
			rtn += "0";
		rtn += String.valueOf(cal.get(Calendar.SECOND));
		return rtn;
	}

	public String getDateText(int index) {
		return getDateText(getTime(index));
	}

	public int size(){
		return chatText.size();
	}

	public void remove(int index){
		chatText.remove(index);
		chatTime.remove(index);
	}

	public void formatDocument(Document doc, String text, long date, String searchText, boolean user, boolean system, boolean addDate, boolean search, boolean highlight)
			throws BadLocationException
	{
		if (searchText.length() > 0) {
			if (search && !hitChatLog(text, searchText))
				return;
			if (highlight)
				text = markMessage(text, searchText);
		}
		String dateText = "";
		if (addDate)
			dateText = "§9" + getDateText(date) + "§r ";
		if (user && isUserMessage(text))
			insertFormatedString(doc, dateText + text + "\n");
		else if (system && isSystemMessage(text))
			insertFormatedString(doc, dateText + text + "\n");
	}

	public void formatDocument(Document doc, String text, String searchText, boolean user, boolean system, boolean addDate, boolean search, boolean highlight)
			throws BadLocationException
	{
		formatDocument(doc, text, new Date().getTime(), searchText, user, system, addDate, search, highlight);
	}

	public void formatDocument(Document doc, String searchText, boolean user, boolean system, boolean addDate, boolean search, boolean highlight)
			throws BadLocationException
	{
		for(int i = 0; i < size(); i++)
			formatDocument(doc, getMainText(i), getTime(i), searchText, user, system, addDate, search, highlight);
	}

	public boolean hitChatLog(String target, String word) {
		target = target.replaceAll("§.", "");
		if (target.indexOf(word) >= 0)
			return true;
		return false;
	}

	public String markMessage(String target, String word) {
		return target.replaceAll(Pattern.quote(word),
				Matcher.quoteReplacement("§g" + word + "§G"));
	}

	private boolean isUserMessage(String text) {
		text = text.replaceAll("§.", "");
		Pattern p = Pattern.compile(config.getUserRegExp());
		Matcher m = p.matcher(text);
		return m.find();
	}

	private boolean isSystemMessage(String text) {
		if(config.getSystemRegExp().equals(""))
			return !isUserMessage(text);
		text = text.replaceAll("§.", "");
		Pattern p = Pattern.compile(config.getSystemRegExp());
		Matcher m = p.matcher(text);
		return m.find();
	}

	public void insertFormatedString(Document doc, String text)
			throws BadLocationException {
		SimpleAttributeSet attr = new SimpleAttributeSet();
		int start = -1;
		String rest = text;
		while ((start = rest.indexOf("§")) >= 0) {
			doc.insertString(doc.getLength(), rest.substring(0, start), attr);
			switch (rest.charAt(start + 1)) {
			case '0': // BLACK
				StyleConstants.setForeground(attr, new Color(config.getColorBlack()));
			case '1': // DARK_BLUE
				StyleConstants.setForeground(attr, new Color(config.getColorDarkBlue()));
				break;
			case '2': // DARK_GREEN
				StyleConstants.setForeground(attr, new Color(config.getColorDarkGreen()));
				break;
			case '3': // DARK_AQUA
				StyleConstants.setForeground(attr, new Color(config.getColorDarkAqua()));
				break;
			case '4': // DARK_RED
				StyleConstants.setForeground(attr, new Color(config.getColorDarkRed()));
				break;
			case '5': // DARK_PURPLE
				StyleConstants.setForeground(attr, new Color(config.getColorDarkPurple()));
				break;
			case '6': // GOLD
				StyleConstants.setForeground(attr, new Color(config.getColorGold()));
				break;
			case '7': // GRAY
				StyleConstants.setForeground(attr, new Color(config.getColorGlay()));
				break;
			case '8': // DARK_GRAY
				StyleConstants.setForeground(attr, new Color(config.getColorDarkGlay()));
				break;
			case '9': // BLUE
				StyleConstants.setForeground(attr, new Color(config.getColorBlue()));
				break;
			case 'a': // GREEN
				StyleConstants.setForeground(attr, new Color(config.getColorGreen()));
				break;
			case 'b': // AQUA
				StyleConstants.setForeground(attr, new Color(config.getColorAqua()));
				break;
			case 'c': // RED
				StyleConstants.setForeground(attr, new Color(config.getColorRed()));
				break;
			case 'd': // LIGHT_PURPLE
				StyleConstants.setForeground(attr, new Color(config.getColorLightPurple()));
				break;
			case 'e': // YELLOW
				StyleConstants.setForeground(attr, new Color(config.getColorYellow()));
				break;
			case 'f': // WHITE
				StyleConstants.setForeground(attr, new Color(config.getColorWhite()));
				break;
			case 'g': // HIGHLIGHT
				StyleConstants.setBackground(attr, new Color(config.getColorHighlight()));
				break;
			case 'G': // UNHIGHLIGHT
				StyleConstants.setBackground(attr, new Color(255, 255, 255, 0));
				break;
			case 'l': // BOLD
				StyleConstants.setBold(attr, true);
				break;
			case 'm': // STRIKETHROUGH
				StyleConstants.setStrikeThrough(attr, true);
				break;
			case 'n': // UNDERLINE
				StyleConstants.setUnderline(attr, true);
				break;
			case 'o': // ITALIC
				StyleConstants.setItalic(attr, true);
				break;
			case 'r': // RESET
				if (StyleConstants.getBackground(attr) == new Color(config.getColorHighlight())) {
					attr = new SimpleAttributeSet();
					StyleConstants.setBackground(attr, new Color(config.getColorHighlight()));
				} else {
					attr = new SimpleAttributeSet();
				}
				StyleConstants.setForeground(attr, new Color(config.getColorDefault()));
				break;
			}
			rest = rest.substring(start + 2);
		}
		doc.insertString(doc.getLength(), rest, attr);
	}

}
