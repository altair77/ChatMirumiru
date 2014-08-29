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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.altair.chatMirumiru.ChatMirumiruConfig;
import com.altair.chatMirumiru.ChatMirumiruCore;

public class ChatMirumiruConfigGui implements ActionListener {

	private JFrame frame;
	private JDialog dialog;
	private JTextField userRegExpTxt;
	private JTextField systemRegExpTxt;
	private JSpinner saveLogMaxSpn;
	private JSpinner reloadLogSpn;

	private ChatMirumiruConfig config = ChatMirumiruCore.config;

	public ChatMirumiruConfigGui(JFrame frame) {
		this.frame = frame;
		initialize();
	}

	public void initialize() {
		dialog = new JDialog(frame, "設定" , true);
		dialog.setBounds(100, 100, 500, 160);
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

		JPanel panel_3 = new JPanel();
		panel_3.setMaximumSize(new Dimension(32767, 20));
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setVgap(3);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		dialog.getContentPane().add(panel_3);

		Component horizontalStrut_4 = Box.createHorizontalStrut(5);
		horizontalStrut_4.setMaximumSize(new Dimension(5, 1));
		panel_3.add(horizontalStrut_4);

		JLabel label_2 = new JLabel("最大保存数");
		label_2.setMaximumSize(new Dimension(2147483647, 20));
		panel_3.add(label_2);

		Component horizontalStrut_5 = Box.createHorizontalStrut(5);
		horizontalStrut_5.setMaximumSize(new Dimension(5, 1));
		panel_3.add(horizontalStrut_5);

		saveLogMaxSpn = new JSpinner();
		saveLogMaxSpn.setModel(new SpinnerNumberModel(10000, 100, 2147483647, 100));
		saveLogMaxSpn.setPreferredSize(new Dimension(100, 20));
		panel_3.add(saveLogMaxSpn);

		Component horizontalStrut_6 = Box.createHorizontalStrut(5);
		horizontalStrut_6.setPreferredSize(new Dimension(15, 0));
		horizontalStrut_6.setMaximumSize(new Dimension(5, 1));
		panel_3.add(horizontalStrut_6);

		JLabel label_3 = new JLabel("再表示間隔数");
		label_3.setMaximumSize(new Dimension(2147483647, 20));
		panel_3.add(label_3);

		Component horizontalStrut_7 = Box.createHorizontalStrut(5);
		horizontalStrut_7.setMaximumSize(new Dimension(5, 1));
		panel_3.add(horizontalStrut_7);

		reloadLogSpn = new JSpinner();
		reloadLogSpn.setModel(new SpinnerNumberModel(100, 0, 2147483647, 10));
		reloadLogSpn.setPreferredSize(new Dimension(100, 20));
		panel_3.add(reloadLogSpn);

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
				ChatMirumiruCore.log.error("Failed to set user regular expression");
				return;
			}
			if(!systemRegExpTxt.getText().equals("")){
				try{
					Pattern.compile(systemRegExpTxt.getText());
				}catch(PatternSyntaxException exp){
					JOptionPane.showMessageDialog(dialog,
							"システム正規表現が正しくありません。", "エラー",
							JOptionPane.ERROR_MESSAGE);
					ChatMirumiruCore.log.error("Failed to set system regular expression");
					return;
				}
			}
			int saveLogMax = (Integer) saveLogMaxSpn.getValue();
			if(saveLogMax < 100 || saveLogMax > Integer.MAX_VALUE){
				JOptionPane.showMessageDialog(dialog,
						"最大保存数が正しくありません。", "エラー",
						JOptionPane.ERROR_MESSAGE);
				ChatMirumiruCore.log.error("Failed to set max value of save log");
				return;
			}
			int reloadLogInterval = (Integer) reloadLogSpn.getValue();
			if(reloadLogInterval < 0 || reloadLogInterval > Integer.MAX_VALUE){
				JOptionPane.showMessageDialog(dialog,
						"再表示間隔数が正しくありません。", "エラー",
						JOptionPane.ERROR_MESSAGE);
				ChatMirumiruCore.log.error("Failed to set value of reloading Log");
				return;
			}
			config.setUserRegExp(userRegExpTxt.getText());
			config.setSystemRegExp(systemRegExpTxt.getText());
			config.setSavingLogMax(saveLogMax);
			config.setReloadLogInterval(reloadLogInterval);
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
		saveLogMaxSpn.setValue(config.getSavingLogMax());
		reloadLogSpn.setValue(config.getReloadLogInterval());
		dialog.setVisible(b);
	}

}
