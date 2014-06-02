package com.altair.chatMirumiru.gui;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

public class ChatMirumiruGui {

	private ChatMirumiruGui window;
	private JFrame frame;
	private JList list;
	private DefaultListModel model;

	/**
	 * Create the application.
	 */
	public ChatMirumiruGui() {
		initialize();
	}

	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatMirumiruGui window = new ChatMirumiruGui();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		model = new DefaultListModel();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 460, 320);
		frame.getContentPane().add(scrollPane);
		list = new JList(model);
		scrollPane.setViewportView(list);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menu = new JMenu("ファイル");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("保存");
		menu.add(menuItem);
	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	public void addList(String text) {
		model.addElement(text);
		list.ensureIndexIsVisible(model.getSize() - 1);
	}
}
