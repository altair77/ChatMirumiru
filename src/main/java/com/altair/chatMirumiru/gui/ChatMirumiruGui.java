package com.altair.chatMirumiru.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

	private JFrame frame;
	private JTextPane textPane;
	private JTextField textField;
	private JCheckBox autoScrollCheckBox;

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
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 33, 460, 201);
		frame.getContentPane().add(scrollPane);

		textPane = new JTextPane();
		StyleContext sc = new StyleContext();
		DefaultStyledDocument doc = new DefaultStyledDocument(sc);
		textPane.setDocument(doc);
		scrollPane.setViewportView(textPane);


		autoScrollCheckBox = new JCheckBox("自動スクロール");
		autoScrollCheckBox.setSelected(true);
		autoScrollCheckBox.setBounds(347, 6, 125, 21);
		frame.getContentPane().add(autoScrollCheckBox);

		JCheckBox userCheckBox = new JCheckBox("ユーザー");
		userCheckBox.setSelected(true);
		userCheckBox.setBounds(12, 6, 92, 21);
		frame.getContentPane().add(userCheckBox);

		JCheckBox systemCheckBox = new JCheckBox("システム");
		systemCheckBox.setSelected(true);
		systemCheckBox.setBounds(108, 6, 92, 21);
		frame.getContentPane().add(systemCheckBox);

		textField = new JTextField();
		textField.addActionListener(this);
		textField.setActionCommand("chat");
		textField.setBounds(12, 244, 383, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton button = new JButton("送信");
		button.addActionListener(this);
		button.setActionCommand("send");
		button.setBounds(407, 243, 65, 21);
		frame.getContentPane().add(button);

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
		Document doc = textPane.getDocument();
		SimpleAttributeSet attr = new SimpleAttributeSet();
		try {
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
	}

}
