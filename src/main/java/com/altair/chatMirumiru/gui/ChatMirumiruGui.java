package com.altair.chatMirumiru.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleContext;

import net.minecraft.client.Minecraft;

import com.altair.chatMirumiru.ChatMirumiruCore;

public class ChatMirumiruGui implements ActionListener {

	private ArrayList<String> allChatLog = new ArrayList<String>();
	private ArrayList<String> allChatDate = new ArrayList<String>();
	private final String userMatchStr = "^(\\[.+\\])+(<.+>)+.+:\\x20";
	private final String systemMatchStr = "";
	private Calendar cal;

	private JFrame frame;
	private JTextPane textPane;
	private JTextField textField;
	private JCheckBox autoScrollCheckBox;
	private JCheckBox userCheckBox;
	private JCheckBox systemCheckBox;
	private JCheckBox dateCheckBox;

	/**
	 * Create the application.
	 */
	public ChatMirumiruGui() {
		cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPANESE);
		//cal = Calendar.getInstance();
		//cal.setTimeInMillis(Minecraft.getSystemTime());
		cal.setTime(new Date());

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

								Component horizontalGlue = Box.createHorizontalGlue();
								panel.add(horizontalGlue);


								autoScrollCheckBox = new JCheckBox("自動スクロール");
								panel.add(autoScrollCheckBox);
								autoScrollCheckBox.setSelected(true);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.LINE_AXIS));

				JScrollPane scrollPane = new JScrollPane();
				panel_1.add(scrollPane);

						textPane = new JTextPane();
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
						panel_2.add(button);
						button.addActionListener(this);
						button.setActionCommand("send");

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menu = new JMenu("ファイル");
		menuBar.add(menu);

		JMenuItem saveMenuItem = new JMenuItem("保存");
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
		allChatDate.add(getDateText());
		Document doc = textPane.getDocument();
		SimpleAttributeSet attr = new SimpleAttributeSet();
		try {
			if(userCheckBox.isSelected() && isUserMessage(text))
				doc.insertString(doc.getLength(), text+"\n", attr);
			else if(systemCheckBox.isSelected() && isSystemMessage(text))
				doc.insertString(doc.getLength(), text+"\n", attr);
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
	}

	private void reView() {
		StyleContext sc = new StyleContext();
		DefaultStyledDocument doc = new DefaultStyledDocument(sc);
		textPane.setDocument(doc);
		try{
			int cnt = 0;
			for(String message : allChatLog) {
				String date = "";
				if(dateCheckBox.isSelected())
					date = allChatDate.get(cnt) + "  ";
				if(userCheckBox.isSelected() && isUserMessage(message))
					doc.insertString(doc.getLength(), date+message+"\n", new SimpleAttributeSet());
				else if(systemCheckBox.isSelected() && isSystemMessage(message))
					doc.insertString(doc.getLength(), date+message+"\n", new SimpleAttributeSet());
				cnt++;
			}
		}catch(BadLocationException e){
			ChatMirumiruCore.log.error("[ChatMirumiru/error] Failed to read the document.");
		}

	}

	private boolean isUserMessage(String text) {
		Pattern p = Pattern.compile(userMatchStr);
		Matcher m = p.matcher(text);
		return m.find();

	}

	private boolean isSystemMessage(String text) {
		return !isUserMessage(text);
	}

	public String getDateText() {
		return cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DATE)+" "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
	}

}
