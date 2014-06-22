package com.altair.chatMirumiru.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import net.minecraft.client.Minecraft;

import com.altair.chatMirumiru.ChatMirumiruCore;

public class ChatMirumiruGui implements ActionListener {

	private ArrayList<String> allChatLog = new ArrayList<String>();
	private ArrayList<Long> allChatTime = new ArrayList<Long>();
	private final String userMatchStr = "^(\\[.+\\])+(<.+>)+.+:\\x20";
	private final String systemMatchStr = "";

	private JFrame frame;
	private JTextPane textPane;
	private JTextField textField;
	private JCheckBox autoScrollCheckBox;
	private JCheckBox userCheckBox;
	private JCheckBox systemCheckBox;
	private JCheckBox dateCheckBox;
	private JTextField searchField;
	private JToggleButton tglbtnHighlight;
	private JToggleButton tglbtnPickup;

	/**
	 * Create the application.
	 */
	public ChatMirumiruGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 334);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		StyleContext sc = new StyleContext();
		DefaultStyledDocument doc = new DefaultStyledDocument(sc);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

				userCheckBox = new JCheckBox("ユーザー");
				panel.add(userCheckBox);
				userCheckBox.addActionListener(this);
				userCheckBox.setActionCommand("userCheck");
				userCheckBox.setSelected(true);

						systemCheckBox = new JCheckBox("システム");
						panel.add(systemCheckBox);
						systemCheckBox.addActionListener(this);
						systemCheckBox.setActionCommand("systemCheck");
						systemCheckBox.setSelected(true);

								dateCheckBox = new JCheckBox("日時");
								panel.add(dateCheckBox);
								dateCheckBox.addActionListener(this);
								dateCheckBox.setActionCommand("dateCheck");

								Component horizontalStrut = Box.createHorizontalStrut(20);
								horizontalStrut.setMaximumSize(new Dimension(10, 1));
								panel.add(horizontalStrut);

								searchField = new JTextField();
								searchField.addActionListener(this);
								searchField.setActionCommand("search");
								searchField.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));
								panel.add(searchField);
								searchField.setColumns(10);

								tglbtnHighlight = new JToggleButton("強");
								tglbtnHighlight.addActionListener(this);
								tglbtnHighlight.setActionCommand("highlight");
								tglbtnHighlight.setMargin(new Insets(2, 2, 2, 2));
								tglbtnHighlight.setMaximumSize(new Dimension(41, 20));
								panel.add(tglbtnHighlight);

								tglbtnPickup = new JToggleButton("限");
								tglbtnPickup.addActionListener(this);
								tglbtnPickup.setActionCommand("pickup");
								tglbtnPickup.setMargin(new Insets(2, 2, 2, 2));
								tglbtnPickup.setMaximumSize(new Dimension(41, 20));
								panel.add(tglbtnPickup);

								Component horizontalStrut_1 = Box.createHorizontalStrut(20);
								horizontalStrut_1.setMaximumSize(new Dimension(10, 1));
								panel.add(horizontalStrut_1);

								autoScrollCheckBox = new JCheckBox("自動スクロール");
								panel.add(autoScrollCheckBox);
								autoScrollCheckBox.setSelected(true);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.LINE_AXIS));

				JScrollPane scrollPane = new JScrollPane();
				panel_1.add(scrollPane);

						textPane = new JTextPane();
						textPane.setBackground(Color.LIGHT_GRAY);
						textPane.setDocument(doc);
						scrollPane.setViewportView(textPane);

		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.LINE_AXIS));

				textField = new JTextField();
				panel_2.add(textField);
				textField.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));
				textField.addActionListener(this);
				textField.setActionCommand("chat");
				textField.setColumns(10);

						JButton button = new JButton("送信");
						button.setMargin(new Insets(2, 2, 2, 2));
						button.setMaximumSize(new Dimension(57, 20));
						panel_2.add(button);
						button.addActionListener(this);
						button.setActionCommand("send");

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menu = new JMenu("メニュー");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("検索");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		menu.add(menuItem);

		JMenuItem saveMenuItem = new JMenuItem("保存");
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menu.add(saveMenuItem);

		JMenuItem exitMenuItem = new JMenuItem("閉じる");
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		exitMenuItem.setActionCommand("exit");
		exitMenuItem.addActionListener(this);
		menu.add(exitMenuItem);
	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
		textField.requestFocus();
	}

	public void addList(String text) {
		allChatLog.add(text);
		allChatTime.add(new Date().getTime());
		Document doc = textPane.getDocument();
		SimpleAttributeSet attr = new SimpleAttributeSet();
		try {
			String date = "";
			if(dateCheckBox.isSelected())
				date = "§9" + getDateText() + "§r  ";
			if(userCheckBox.isSelected() && isUserMessage(text))
				insertFormatedString(doc, date+text+"\n");
			else if(systemCheckBox.isSelected() && isSystemMessage(text))
				insertFormatedString(doc, date+text+"\n");
		} catch (BadLocationException e) {
			ChatMirumiruCore.log.error("[ChatMirumiru/error] Failed to read the document.");
		}
		if(autoScrollCheckBox.isSelected())
			textPane.setCaretPosition(doc.getLength());
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("send") || e.getActionCommand().equals("chat")){
			Minecraft.getMinecraft().thePlayer.sendChatMessage(textField.getText());
			textField.setText("");
		}
		if(e.getActionCommand().equals("exit")){
			setVisible(false);
		}
		if(e.getActionCommand().equals("userCheck") || e.getActionCommand().equals("systemCheck") || e.getActionCommand().equals("dateCheck")){
			ChatMirumiruCore.log.info("[ChatMirumiru/info] reView");
			reView();
		}
		if(e.getActionCommand().equals("search") || e.getActionCommand().equals("highlight") || e.getActionCommand().equals("pickup")){
			ChatMirumiruCore.log.info("[ChatMirumiru/info] reView");
			reView();
		}
	}

	private void reView() {
		StyleContext sc = new StyleContext();
		DefaultStyledDocument doc = new DefaultStyledDocument(sc);
		textPane.setDocument(doc);
		try{
			int cnt = 0;
			for(String message : allChatLog) {
				if(searchField.getText().length() > 0){
					if(tglbtnHighlight.isSelected())
						message = markMessage(message, searchField.getText());
					if(tglbtnPickup.isSelected() && !hitChatLog(cnt, searchField.getText()))
						continue;
				}
				String date = "";
				if(dateCheckBox.isSelected())
					date = "§9" + getDateText(allChatTime.get(cnt)) + "§r  ";
				if(userCheckBox.isSelected() && isUserMessage(message))
					insertFormatedString(doc, date+message+"\n");
				else if(systemCheckBox.isSelected() && isSystemMessage(message))
					insertFormatedString(doc, date+message+"\n");
				cnt++;
			}
		}catch(BadLocationException e){
			ChatMirumiruCore.log.error("[ChatMirumiru/error] Failed to read the document.");
		}

	}

	private boolean isUserMessage(String text) {
		text = text.replaceAll("§.", "");
		Pattern p = Pattern.compile(userMatchStr);
		Matcher m = p.matcher(text);
		return m.find();

	}

	private boolean isSystemMessage(String text) {
		return !isUserMessage(text);
	}

	public String getDateText() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPANESE);
		cal.setTime(new Date());
		return cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DATE)+" "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
	}

	public String getDateText(long time) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPANESE);
		cal.setTimeInMillis(time);
		return cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DATE)+" "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
	}

	public void insertFormatedString(Document doc, String text) throws BadLocationException {
		SimpleAttributeSet attr = new SimpleAttributeSet();
		int start = -1;
		String rest = text;
		while((start = rest.indexOf("§")) >= 0){
			doc.insertString(doc.getLength(), rest.substring(0, start), attr);
			switch(rest.charAt(start+1)){
			case '0':  // BLACK
				StyleConstants.setForeground(attr, Color.BLACK);
			case '1':  // DARK_BLUE
				StyleConstants.setForeground(attr, new Color(0, 0, 139));
				break;
			case '2':  // DARK_GREEN
				StyleConstants.setForeground(attr, new Color(0, 100, 0));
				break;
			case '3':  // DARK_AQUA
				StyleConstants.setForeground(attr, new Color(0, 139, 139));
				break;
			case '4':  // DARK_RED
				StyleConstants.setForeground(attr, new Color(139, 0, 0));
				break;
			case '5':  // DARK_PURPLE
				StyleConstants.setForeground(attr, new Color(148, 0, 211));
				break;
			case '6':  // GOLD
				StyleConstants.setForeground(attr, new Color(255, 215, 0));
				break;
			case '7':  // GRAY
				StyleConstants.setForeground(attr, Color.GRAY);
				break;
			case '8':  // DARK_GRAY
				StyleConstants.setForeground(attr, Color.DARK_GRAY);
				break;
			case '9':  // BLUE
				StyleConstants.setForeground(attr, Color.BLUE);
				break;
			case 'a':  // GREEN
				StyleConstants.setForeground(attr, Color.GREEN);
				break;
			case 'b':  // AQUA
				StyleConstants.setForeground(attr, new Color(0, 255, 255));
				break;
			case 'c':  // RED
				StyleConstants.setForeground(attr, Color.RED);
				break;
			case 'd':  // LIGHT_PURPLE
				StyleConstants.setForeground(attr, new Color(238, 130, 238));
				break;
			case 'e':  // YELLOW
				StyleConstants.setForeground(attr, Color.YELLOW);
				break;
			case 'f':  // WHITE
				StyleConstants.setForeground(attr, Color.WHITE);
				break;
			case 'g':  // HIGHLIGHT
				StyleConstants.setBackground(attr, new Color(255, 165, 0));
				break;
			case 'G':  // UNHIGHLIGHT
				StyleConstants.setBackground(attr, new Color(255, 255, 255, 0));
				break;
			case 'l':  // BOLD
				StyleConstants.setBold(attr, true);
				break;
			case 'm':  // STRIKETHROUGH
				StyleConstants.setStrikeThrough(attr, true);
				break;
			case 'n':  // UNDERLINE
				StyleConstants.setUnderline(attr, true);
				break;
			case 'o':  // ITALIC
				StyleConstants.setItalic(attr, true);
				break;
			case 'r':  // RESET
				if(StyleConstants.getBackground(attr) == new Color(255, 165, 0)){
					attr = new SimpleAttributeSet();
					StyleConstants.setBackground(attr, new Color(255, 165, 0));
				}else{
					attr = new SimpleAttributeSet();
				}
				break;
			}
			rest = rest.substring(start+2);
		}
		doc.insertString(doc.getLength(), rest, attr);
	}

	public boolean hitChatLog(int index, String word) {
		if(allChatLog.get(index).indexOf(word) >= 0)
			return true;
		return false;
	}

	public String markMessage(String target, String word) {
		return target.replaceAll(Pattern.quote(word), Matcher.quoteReplacement("§g"+word+"§G"));
	}
}
