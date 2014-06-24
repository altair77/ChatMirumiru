package com.altair.chatMirumiru.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
import javax.swing.text.rtf.RTFEditorKit;

import net.minecraft.client.Minecraft;

import com.altair.chatMirumiru.ChatMirumiruCore;

public class ChatMirumiruGui implements ActionListener {

	private ArrayList<String> allChatLog = new ArrayList<String>();
	private ArrayList<Long> allChatTime = new ArrayList<Long>();
	private final String userMatchStr = "^((\\[.+\\])+(<.+>)+.+:\\x20|<.+>\\x20)";
	private final String systemMatchStr = "";
	private final int maxLogNum = 10000;
	private final int reloadLogNum = 100;
	private int reloadCnt = 0;
	private boolean saving = false;

	private JFrame frame;
	private JTextPane textPane;
	private JTextField textField;
	private JCheckBox autoScrollCheckBox;
	private JCheckBox userCheckBox;
	private JCheckBox systemCheckBox;
	private JCheckBox dateCheckBox;
	private JToggleButton tglbtnUser;
	private JToggleButton tglbtnSystem;
	private JToggleButton tglbtnDate;
	private JToggleButton tglbtnAutoScroll;
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
		frame = new JFrame(ChatMirumiruCore.modid+" v"+ChatMirumiruCore.version);
		frame.setBounds(100, 100, 500, 334);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		StyleContext sc = new StyleContext();
		DefaultStyledDocument doc = new DefaultStyledDocument(sc);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

				tglbtnUser = new JToggleButton("ユーザー");
				tglbtnUser.setSelected(true);
				tglbtnUser.setMaximumSize(new Dimension(41, 20));
				tglbtnUser.setMargin(new Insets(2, 2, 2, 2));
				tglbtnUser.addActionListener(this);
				tglbtnUser.setActionCommand("userCheck");
				panel.add(tglbtnUser);

				tglbtnSystem = new JToggleButton("システム");
				tglbtnSystem.setSelected(true);
				tglbtnSystem.setMaximumSize(new Dimension(41, 20));
				tglbtnSystem.setMargin(new Insets(2, 2, 2, 2));
				tglbtnSystem.addActionListener(this);
				tglbtnSystem.setActionCommand("systemCheck");
				panel.add(tglbtnSystem);

				tglbtnDate = new JToggleButton("日時");
				tglbtnDate.setMaximumSize(new Dimension(41, 20));
				tglbtnDate.setMargin(new Insets(2, 2, 2, 2));
				tglbtnDate.addActionListener(this);
				tglbtnDate.setActionCommand("dateCheck");
				panel.add(tglbtnDate);

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
				tglbtnHighlight.setSelected(true);
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

				tglbtnAutoScroll = new JToggleButton("自動");
				tglbtnAutoScroll.setSelected(true);
				tglbtnAutoScroll.setMaximumSize(new Dimension(41, 20));
				tglbtnAutoScroll.setMargin(new Insets(2, 2, 2, 2));
				panel.add(tglbtnAutoScroll);

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

		JMenuItem saveMenuItem = new JMenuItem("保存");
		saveMenuItem.addActionListener(this);
		saveMenuItem.setActionCommand("save");
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

		while(allChatLog.size() > maxLogNum){
			allChatLog.remove(0);
			allChatTime.remove(0);
		}

		if(saving)
			return;


		if(++reloadCnt >= reloadLogNum){
			ChatMirumiruCore.log.info("Periodic review.");
			reView();
			return;
		}

		Document doc = textPane.getDocument();
		SimpleAttributeSet attr = new SimpleAttributeSet();
		try {
			if(searchField.getText().length() > 0){
				if(tglbtnPickup.isSelected() && !hitChatLog(text, searchField.getText()))
					return;
				if(tglbtnHighlight.isSelected())
					text = markMessage(text, searchField.getText());
			}
			String date = "";
			if(tglbtnDate.isSelected())
				date = "§9" + getDateText() + "§r ";
			if(tglbtnUser.isSelected() && isUserMessage(text))
				insertFormatedString(doc, date+text+"\n");
			else if(tglbtnSystem.isSelected() && isSystemMessage(text))
				insertFormatedString(doc, date+text+"\n");
		} catch (BadLocationException e) {
			ChatMirumiruCore.log.error("Failed to read the document.");
		}
		if(tglbtnAutoScroll.isSelected())
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
			reView();
		}
		if(e.getActionCommand().equals("search") || e.getActionCommand().equals("highlight") || e.getActionCommand().equals("pickup")){
			reView();
		}
		if(e.getActionCommand().equals("save")){
			saveFile();
			reView();
		}
	}

	private void saveFile() {
		saving = true;

		JFileChooser chooser = new JFileChooser(){
			@Override
			public void approveSelection() {
				File f = getSelectedFile();
				if(f.exists() && getDialogType() == SAVE_DIALOG) {
					String m = String.format(
							"<html>%s はすでに存在しています。<br>上書きしてもよろしいですか？",
							f.getAbsolutePath());
					int rv = JOptionPane.showConfirmDialog(
							this, m, "上書きの確認", JOptionPane.YES_NO_OPTION);
					if(rv!=JOptionPane.YES_OPTION) {
						return;
					}
				}
				super.approveSelection();
			  }
		};
		ChatMirumiruRtfFilter filter = new ChatMirumiruRtfFilter();
		chooser.setFileFilter(filter);
		chooser.setSelectedFile(new File(getFileNameText()));

		if(chooser.showSaveDialog(frame) != JFileChooser.APPROVE_OPTION){
			saving = false;
			return;
		}

		File fChoosen = chooser.getSelectedFile();
		try {
			OutputStream out;
			if(fChoosen.toString().substring(fChoosen.toString().length()-4).equals(".rtf"))
				out = new FileOutputStream(fChoosen);
			else
				out = new FileOutputStream(fChoosen+".rtf");
			RTFEditorKit rtfEditor =  new RTFEditorKit();
			rtfEditor.write(out, textPane.getDocument(), 0, textPane.getDocument().getLength());
			out.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(frame, "ファイル名が正しくないため、\nファイルの保存に失敗しました。", "エラー", JOptionPane.ERROR_MESSAGE);
			ChatMirumiruCore.log.error("Failed to save the file, because of invalid file name.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "ファイルの保存に失敗しました。", "エラー", JOptionPane.ERROR_MESSAGE);
			ChatMirumiruCore.log.error("Failed to save the file, for IO error.");
		} catch (BadLocationException e) {
			JOptionPane.showMessageDialog(frame, "ファイルの保存に失敗しました。", "エラー", JOptionPane.ERROR_MESSAGE);
			ChatMirumiruCore.log.error("Failed to save the file, for RTF error.");
		}

		saving = false;
	}

	private void reView() {
		StyleContext sc = new StyleContext();
		DefaultStyledDocument doc = new DefaultStyledDocument(sc);
		textPane.setDocument(doc);
		try{
			int cnt = -1;
			for(String message : allChatLog) {
				cnt++;
				if(searchField.getText().length() > 0){
					if(tglbtnPickup.isSelected() && !hitChatLog(cnt, searchField.getText()))
						continue;
					if(tglbtnHighlight.isSelected())
						message = markMessage(message, searchField.getText());
				}
				String date = "";
				if(tglbtnDate.isSelected())
					date = "§9" + getDateText(allChatTime.get(cnt)) + "§r ";
				if(tglbtnUser.isSelected() && isUserMessage(message))
					insertFormatedString(doc, date+message+"\n");
				else if(tglbtnSystem.isSelected() && isSystemMessage(message))
					insertFormatedString(doc, date+message+"\n");
			}
		}catch(BadLocationException e){
			ChatMirumiruCore.log.error("Failed to read the document.");
		}
		reloadCnt = 0;
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
		Date date = new Date();
		return getDateText(date.getTime());
	}

	public String getDateText(long time) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPANESE);
		cal.setTimeInMillis(time);
		String rtn = "";
		rtn += String.valueOf(cal.get(Calendar.YEAR)%100)+"/";
		if(cal.get(Calendar.MONTH)+1 < 10)
			rtn += "0";
		rtn += String.valueOf(cal.get(Calendar.MONTH)+1)+"/";
		if(cal.get(Calendar.DATE) < 10)
			rtn += "0";
		rtn += String.valueOf(cal.get(Calendar.DATE))+" ";
		if(cal.get(Calendar.HOUR_OF_DAY) < 10)
			rtn += "0";
		rtn += String.valueOf(cal.get(Calendar.HOUR_OF_DAY))+":";
		if(cal.get(Calendar.MINUTE) < 10)
			rtn += "0";
		rtn += String.valueOf(cal.get(Calendar.MINUTE))+":";
		if(cal.get(Calendar.SECOND) < 10)
			rtn += "0";
		rtn += String.valueOf(cal.get(Calendar.SECOND));
		return rtn;
	}

	public String getFileNameText() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPANESE);
		cal.setTime(new Date());
		String rtn = "";
		rtn += String.valueOf(cal.get(Calendar.YEAR)%100);
		if(cal.get(Calendar.MONTH)+1 < 10)
			rtn += "0";
		rtn += String.valueOf(cal.get(Calendar.MONTH)+1);
		if(cal.get(Calendar.DATE) < 10)
			rtn += "0";
		rtn += String.valueOf(cal.get(Calendar.DATE));
		return rtn+"_chatlog.rtf";
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
		return hitChatLog(allChatLog.get(index), word);
	}

	public boolean hitChatLog(String target, String word) {
		target = target.replaceAll("§.", "");
		if(target.indexOf(word) >= 0)
			return true;
		return false;
	}

	public String markMessage(String target, String word) {
		return target.replaceAll(Pattern.quote(word), Matcher.quoteReplacement("§g"+word+"§G"));
	}
}
