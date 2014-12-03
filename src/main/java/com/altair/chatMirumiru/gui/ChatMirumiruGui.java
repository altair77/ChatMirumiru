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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
import javax.swing.text.StyleContext;
import javax.swing.text.rtf.RTFEditorKit;

import net.minecraft.client.Minecraft;

import com.altair.chatMirumiru.ChatMirumiruChatLog;
import com.altair.chatMirumiru.ChatMirumiruConfig;
import com.altair.chatMirumiru.ChatMirumiruCore;

public class ChatMirumiruGui implements ActionListener {

	private final ChatMirumiruConfig config = ChatMirumiruCore.config;

	private ChatMirumiruChatLog chatLog = null;
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

	private ChatMirumiruConfigGui configGui = null;

	/**
	 * Create the application.
	 */
	public ChatMirumiruGui() {
		chatLog = new ChatMirumiruChatLog(config.isOnSaveLog());
		initialize();
		reView();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(ChatMirumiruCore.modid + " v"
				+ ChatMirumiruCore.version);
		frame.setBounds(100, 100, 500, 334);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		StyleContext sc = new StyleContext();
		DefaultStyledDocument doc = new DefaultStyledDocument(sc);
		frame.getContentPane().setLayout(
				new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

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
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		menu.add(saveMenuItem);

		JMenuItem settingMenuItem = new JMenuItem("設定");
		settingMenuItem.setActionCommand("setting");
		settingMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, InputEvent.CTRL_MASK));
		settingMenuItem.addActionListener(this);
		menu.add(settingMenuItem);

		JMenuItem exitMenuItem = new JMenuItem("閉じる");
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,
				0));
		exitMenuItem.setActionCommand("exit");
		exitMenuItem.addActionListener(this);
		menu.add(exitMenuItem);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("send")
				|| e.getActionCommand().equals("chat")) {
			Minecraft.getMinecraft().thePlayer.sendChatMessage(textField
					.getText());
			textField.setText("");
		}
		if (e.getActionCommand().equals("exit")) {
			setVisible(false);
		}
		if (e.getActionCommand().equals("userCheck")
				|| e.getActionCommand().equals("systemCheck")
				|| e.getActionCommand().equals("dateCheck")) {
			reView();
		}
		if (e.getActionCommand().equals("search")
				|| e.getActionCommand().equals("highlight")
				|| e.getActionCommand().equals("pickup")) {
			reView();
		}
		if (e.getActionCommand().equals("save")) {
			saveFile();
			reView();
		}
		if (e.getActionCommand().equals("setting")) {
			if(configGui == null)
				configGui = new ChatMirumiruConfigGui(this);
			configGui.setVisible(true);
		}
	}

	public JFrame getFrame() {
		return frame;
	}

	public ChatMirumiruChatLog getChatLog(){
		return chatLog;
	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
		textField.requestFocus();
	}

	public void addList(String text) {
		chatLog.add(text);

		if (saving)
			return;

		if (++reloadCnt >= config.getReloadLogInterval()) {
			ChatMirumiruCore.log.info("Periodic review.");
			reView();
			return;
		}

		Document doc = textPane.getDocument();
		try {
			chatLog.formatDocument(doc, text, searchField.getText(),
					tglbtnUser.isSelected(), tglbtnSystem.isSelected(), tglbtnDate.isSelected(), tglbtnPickup.isSelected(), tglbtnHighlight.isSelected());
		} catch (BadLocationException e) {
			ChatMirumiruCore.log.error("Failed to read the document.");
		}
		if (tglbtnAutoScroll.isSelected())
			textPane.setCaretPosition(doc.getLength());
	}

	public void reView() {
		StyleContext sc = new StyleContext();
		DefaultStyledDocument doc = new DefaultStyledDocument(sc);
		textPane.setDocument(doc);
		textPane.setForeground(new Color(config.getColorDefault()));
		textPane.setBackground(new Color(config.getColorBackground()));
		try {
			chatLog.formatDocument(doc, searchField.getText(),
					tglbtnUser.isSelected(), tglbtnSystem.isSelected(), tglbtnDate.isSelected(), tglbtnPickup.isSelected(), tglbtnHighlight.isSelected());
		} catch (BadLocationException e) {
			ChatMirumiruCore.log.error("Failed to read the document.");
		}
		reloadCnt = 0;
	}

	private void saveFile() {
		saving = true;

		JFileChooser chooser = new JFileChooser() {
			@Override
			public void approveSelection() {
				File f = getSelectedFile();
				if (f.exists() && getDialogType() == SAVE_DIALOG) {
					String m = String.format(
							"<html>%s はすでに存在しています。<br>上書きしてもよろしいですか？",
							f.getAbsolutePath());
					int rv = JOptionPane.showConfirmDialog(this, m, "上書きの確認",
							JOptionPane.YES_NO_OPTION);
					if (rv != JOptionPane.YES_OPTION) {
						return;
					}
				}
				super.approveSelection();
			}
		};
		ChatMirumiruRtfFilter filter = new ChatMirumiruRtfFilter();
		chooser.setFileFilter(filter);
		chooser.setSelectedFile(new File(getFileNameText()));

		if (chooser.showSaveDialog(frame) != JFileChooser.APPROVE_OPTION) {
			saving = false;
			return;
		}

		File fChoosen = chooser.getSelectedFile();
		try {
			OutputStream out;
			if (fChoosen.toString().substring(fChoosen.toString().length() - 4)
					.equals(".rtf"))
				out = new FileOutputStream(fChoosen);
			else
				out = new FileOutputStream(fChoosen + ".rtf");
			RTFEditorKit rtfEditor = new RTFEditorKit();
			rtfEditor.write(out, textPane.getDocument(), 0, textPane
					.getDocument().getLength());
			out.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(frame,
					"ファイル名が正しくないため、\nファイルの保存に失敗しました。", "エラー",
					JOptionPane.ERROR_MESSAGE);
			ChatMirumiruCore.log
					.error("Failed to save the file, because of invalid file name.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "ファイルの保存に失敗しました。", "エラー",
					JOptionPane.ERROR_MESSAGE);
			ChatMirumiruCore.log
					.error("Failed to save the file, for IO error.");
		} catch (BadLocationException e) {
			JOptionPane.showMessageDialog(frame, "ファイルの保存に失敗しました。", "エラー",
					JOptionPane.ERROR_MESSAGE);
			ChatMirumiruCore.log
					.error("Failed to save the file, for RTF error.");
		}

		saving = false;
	}

	public String getFileNameText() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"),
				Locale.JAPANESE);
		cal.setTime(new Date());
		String rtn = "";
		rtn += String.valueOf(cal.get(Calendar.YEAR) % 100);
		if (cal.get(Calendar.MONTH) + 1 < 10)
			rtn += "0";
		rtn += String.valueOf(cal.get(Calendar.MONTH) + 1);
		if (cal.get(Calendar.DATE) < 10)
			rtn += "0";
		rtn += String.valueOf(cal.get(Calendar.DATE));
		return rtn + "_chatlog.rtf";
	}
}
