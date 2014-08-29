package com.altair.chatMirumiru.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.altair.chatMirumiru.ChatMirumiruConfig;
import com.altair.chatMirumiru.ChatMirumiruCore;

public class ChatMirumiruConfigGui implements ActionListener {

	private JFrame frame;
	private JDialog dialog;
	private JTextField userRegExpTxt;
	private JTextField systemRegExpTxt;

	private ChatMirumiruConfig config = ChatMirumiruCore.config;

	public ChatMirumiruConfigGui(JFrame frame) {
		this.frame = frame;
		initialize();
	}

	public void initialize() {
		dialog = new JDialog(frame, "設定" , true);
		dialog.setBounds(100, 100, 500, 130);
		dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		dialog.getContentPane().setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(32767, 20));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(3);
		flowLayout.setAlignment(FlowLayout.LEFT);
		dialog.getContentPane().add(panel);

		Component horizontalStrut = Box.createHorizontalStrut(5);
		horizontalStrut.setMaximumSize(new Dimension(5, 1));
		panel.add(horizontalStrut);

		JLabel label = new JLabel("ユーザー正規表現");
		label.setMaximumSize(new Dimension(2147483647, 20));
		panel.add(label);

		Component horizontalStrut_3 = Box.createHorizontalStrut(5);
		horizontalStrut_3.setMaximumSize(new Dimension(5, 1));
		panel.add(horizontalStrut_3);

		userRegExpTxt = new JTextField();
		userRegExpTxt.setText(config.getUserRegExp());
		userRegExpTxt.setMaximumSize(new Dimension(2147483647, 20));
		panel.add(userRegExpTxt);
		userRegExpTxt.setColumns(30);

		JPanel panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(32767, 20));
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setVgap(3);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		dialog.getContentPane().add(panel_1);

		Component horizontalStrut_1 = Box.createHorizontalStrut(5);
		horizontalStrut_1.setMaximumSize(new Dimension(5, 1));
		panel_1.add(horizontalStrut_1);

		JLabel label_1 = new JLabel("システム正規表現");
		label_1.setMaximumSize(new Dimension(2147483647, 20));
		panel_1.add(label_1);

		Component horizontalStrut_2 = Box.createHorizontalStrut(5);
		horizontalStrut_2.setMaximumSize(new Dimension(5, 1));
		panel_1.add(horizontalStrut_2);

		systemRegExpTxt = new JTextField();
		systemRegExpTxt.setText(config.getSystemRegExp());
		systemRegExpTxt.setMaximumSize(new Dimension(2147483647, 20));
		systemRegExpTxt.setColumns(30);
		panel_1.add(systemRegExpTxt);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		flowLayout_2.setVgap(3);
		dialog.getContentPane().add(panel_2);

		JButton submitBtn = new JButton("決定");
		submitBtn.setPreferredSize(new Dimension(60, 20));
		submitBtn.setMinimumSize(new Dimension(60, 20));
		submitBtn.setMaximumSize(new Dimension(60, 20));
		submitBtn.setMargin(new Insets(2, 2, 2, 2));
		submitBtn.addActionListener(this);
		submitBtn.setActionCommand("config");
		panel_2.add(submitBtn);

		JButton cancelBtn = new JButton("キャンセル");
		cancelBtn.setPreferredSize(new Dimension(99, 20));
		cancelBtn.setMinimumSize(new Dimension(99, 20));
		cancelBtn.setMaximumSize(new Dimension(99, 20));
		cancelBtn.setMargin(new Insets(2, 2, 2, 2));
		cancelBtn.addActionListener(this);
		cancelBtn.setActionCommand("cancel");
		panel_2.add(cancelBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("config")) {
			try{
				Pattern.compile(userRegExpTxt.getText());
			}catch(PatternSyntaxException exp){
				JOptionPane.showMessageDialog(dialog,
						"ユーザー正規表現が正しくありません。", "エラー",
						JOptionPane.ERROR_MESSAGE);
				ChatMirumiruCore.log.error("Failed to setting user regular expression");
				return;
			}
			if(!systemRegExpTxt.getText().equals("")){
				try{
					Pattern.compile(systemRegExpTxt.getText());
				}catch(PatternSyntaxException exp){
					JOptionPane.showMessageDialog(dialog,
							"システム正規表現が正しくありません。", "エラー",
							JOptionPane.ERROR_MESSAGE);
					ChatMirumiruCore.log.error("Failed to setting system regular expression");
					return;
				}
			}
			config.setUserRegExp(userRegExpTxt.getText());
			config.setSystemRegExp(systemRegExpTxt.getText());
			config.resetConfigration();
			setVisible(false);
		}
		if(e.getActionCommand().equals("cancel")) {
			setVisible(false);
		}
	}

	public void setVisible(boolean b) {
		userRegExpTxt.setText(config.getUserRegExp());
		systemRegExpTxt.setText(config.getSystemRegExp());
		dialog.setVisible(b);
	}

}
