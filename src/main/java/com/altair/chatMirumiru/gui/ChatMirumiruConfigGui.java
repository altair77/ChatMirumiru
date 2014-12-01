package com.altair.chatMirumiru.gui;

import java.awt.Color;
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
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import com.altair.chatMirumiru.ChatMirumiruConfig;
import com.altair.chatMirumiru.ChatMirumiruCore;

public class ChatMirumiruConfigGui implements ActionListener {

	private ChatMirumiruGui parentGui;
	private ChatMirumiruConfig config = ChatMirumiruCore.config;

	private JDialog dialog;
	private JTextField userRegExpTxt;
	private JTextField systemRegExpTxt;
	private JSpinner saveLogMaxSpn;
	private JSpinner reloadLogSpn;
	private JButton blackBtn;
	private JButton darkBlueBtn;
	private JButton darkGreenBtn;
	private JButton darkAquaBtn;
	private JButton darkRedBtn;
	private JButton darkPurpleBtn;
	private JButton goldBtn;
	private JButton glayBtn;
	private JButton darkGrayBtn;
	private JButton blueBtn;
	private JButton greenBtn;
	private JButton aquaBtn;
	private JButton redBtn;
	private JButton lightPurpleBtn;
	private JButton yellowBtn;
	private JButton whiteBtn;
	private JButton defaultBtn;
	private JButton highlightBtn;
	private JButton backgroundBtn;
	private Component horizontalStrut_8;

	public ChatMirumiruConfigGui(ChatMirumiruGui gui) {
		this.parentGui = gui;
		initialize();
	}

	public void initialize() {
		dialog = new JDialog(parentGui.getFrame(), "設定" , true);
		dialog.setBounds(100, 100, 500, 270);
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

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "\u8868\u793A\u8272", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout_4 = (FlowLayout) panel_4.getLayout();
		dialog.getContentPane().add(panel_4);

		defaultBtn = new JButton("デフォルト");
		defaultBtn.setMinimumSize(new Dimension(80, 20));
		defaultBtn.setMaximumSize(new Dimension(80, 20));
		defaultBtn.setPreferredSize(new Dimension(80, 20));
		defaultBtn.setMargin(new Insets(2, 2, 2, 2));
		defaultBtn.setForeground(Color.WHITE);
		defaultBtn.setBackground(Color.BLACK);
		defaultBtn.setActionCommand("default");
		defaultBtn.addActionListener(this);
		panel_4.add(defaultBtn);

		blackBtn = new JButton("Black");
		blackBtn.setMinimumSize(new Dimension(80, 20));
		blackBtn.setMaximumSize(new Dimension(80, 20));
		blackBtn.setPreferredSize(new Dimension(80, 20));
		blackBtn.setMargin(new Insets(2, 2, 2, 2));
		blackBtn.setActionCommand("black");
		blackBtn.addActionListener(this);
		blackBtn.setForeground(Color.WHITE);
		blackBtn.setBackground(Color.BLACK);
		panel_4.add(blackBtn);

		darkBlueBtn = new JButton("DarkBlue");
		darkBlueBtn.setMinimumSize(new Dimension(80, 20));
		darkBlueBtn.setMaximumSize(new Dimension(80, 20));
		darkBlueBtn.setPreferredSize(new Dimension(80, 20));
		darkBlueBtn.setMargin(new Insets(2, 2, 2, 2));
		darkBlueBtn.setActionCommand("darkBlue");
		darkBlueBtn.addActionListener(this);
		darkBlueBtn.setForeground(Color.WHITE);
		darkBlueBtn.setBackground(new Color(0, 0, 139));
		panel_4.add(darkBlueBtn);

		darkGreenBtn = new JButton("DarkGreen");
		darkGreenBtn.setMinimumSize(new Dimension(80, 20));
		darkGreenBtn.setMaximumSize(new Dimension(80, 20));
		darkGreenBtn.setPreferredSize(new Dimension(80, 20));
		darkGreenBtn.setMargin(new Insets(2, 2, 2, 2));
		darkGreenBtn.setActionCommand("darkGreen");
		darkGreenBtn.addActionListener(this);
		darkGreenBtn.setForeground(Color.WHITE);
		darkGreenBtn.setBackground(new Color(0, 100, 0));
		panel_4.add(darkGreenBtn);

		darkAquaBtn = new JButton("DarkAqua");
		darkAquaBtn.setMinimumSize(new Dimension(80, 20));
		darkAquaBtn.setMaximumSize(new Dimension(80, 20));
		darkAquaBtn.setPreferredSize(new Dimension(80, 20));
		darkAquaBtn.setMargin(new Insets(2, 2, 2, 2));
		darkAquaBtn.setActionCommand("darkAqua");
		darkAquaBtn.addActionListener(this);
		darkAquaBtn.setForeground(Color.WHITE);
		darkAquaBtn.setBackground(new Color(0, 139, 139));
		panel_4.add(darkAquaBtn);

		darkRedBtn = new JButton("DarkRed");
		darkRedBtn.setMinimumSize(new Dimension(80, 20));
		darkRedBtn.setMaximumSize(new Dimension(80, 20));
		darkRedBtn.setPreferredSize(new Dimension(80, 20));
		darkRedBtn.setMargin(new Insets(2, 2, 2, 2));
		darkRedBtn.setActionCommand("darkRed");
		darkRedBtn.addActionListener(this);
		darkRedBtn.setForeground(Color.WHITE);
		darkRedBtn.setBackground(new Color(139, 0, 0));
		panel_4.add(darkRedBtn);

		darkPurpleBtn = new JButton("DarkPurple");
		darkPurpleBtn.setMinimumSize(new Dimension(80, 20));
		darkPurpleBtn.setMaximumSize(new Dimension(80, 20));
		darkPurpleBtn.setPreferredSize(new Dimension(80, 20));
		darkPurpleBtn.setMargin(new Insets(2, 2, 2, 2));
		darkPurpleBtn.setActionCommand("darkPurple");
		darkPurpleBtn.addActionListener(this);
		darkPurpleBtn.setForeground(Color.WHITE);
		darkPurpleBtn.setBackground(new Color(148, 0, 211));
		panel_4.add(darkPurpleBtn);

		goldBtn = new JButton("Gold");
		goldBtn.setMinimumSize(new Dimension(80, 20));
		goldBtn.setMaximumSize(new Dimension(80, 20));
		goldBtn.setPreferredSize(new Dimension(80, 20));
		goldBtn.setMargin(new Insets(2, 2, 2, 2));
		goldBtn.setActionCommand("gold");
		goldBtn.addActionListener(this);
		goldBtn.setForeground(Color.WHITE);
		goldBtn.setBackground(new Color(255, 215, 0));
		panel_4.add(goldBtn);

		glayBtn = new JButton("Glay");
		glayBtn.setMinimumSize(new Dimension(80, 20));
		glayBtn.setMaximumSize(new Dimension(80, 20));
		glayBtn.setPreferredSize(new Dimension(80, 20));
		glayBtn.setMargin(new Insets(2, 2, 2, 2));
		glayBtn.setActionCommand("glay");
		glayBtn.addActionListener(this);
		glayBtn.setForeground(Color.WHITE);
		glayBtn.setBackground(Color.GRAY);
		panel_4.add(glayBtn);

		darkGrayBtn = new JButton("DrakGray");
		darkGrayBtn.setMinimumSize(new Dimension(80, 20));
		darkGrayBtn.setMaximumSize(new Dimension(80, 20));
		darkGrayBtn.setPreferredSize(new Dimension(80, 20));
		darkGrayBtn.setMargin(new Insets(2, 2, 2, 2));
		darkGrayBtn.setActionCommand("darkGray");
		darkGrayBtn.addActionListener(this);
		darkGrayBtn.setForeground(Color.WHITE);
		darkGrayBtn.setBackground(Color.DARK_GRAY);
		panel_4.add(darkGrayBtn);

		blueBtn = new JButton("Blue");
		blueBtn.setMinimumSize(new Dimension(80, 20));
		blueBtn.setMaximumSize(new Dimension(80, 20));
		blueBtn.setPreferredSize(new Dimension(80, 20));
		blueBtn.setMargin(new Insets(2, 2, 2, 2));
		blueBtn.setActionCommand("blue");
		blueBtn.addActionListener(this);
		blueBtn.setForeground(Color.WHITE);
		blueBtn.setBackground(Color.BLUE);
		panel_4.add(blueBtn);

		greenBtn = new JButton("Green");
		greenBtn.setMinimumSize(new Dimension(80, 20));
		greenBtn.setMaximumSize(new Dimension(80, 20));
		greenBtn.setPreferredSize(new Dimension(80, 20));
		greenBtn.setMargin(new Insets(2, 2, 2, 2));
		greenBtn.setActionCommand("green");
		greenBtn.addActionListener(this);
		greenBtn.setForeground(Color.WHITE);
		greenBtn.setBackground(Color.GREEN);
		panel_4.add(greenBtn);

		aquaBtn = new JButton("Aqua");
		aquaBtn.setMinimumSize(new Dimension(80, 20));
		aquaBtn.setMaximumSize(new Dimension(80, 20));
		aquaBtn.setPreferredSize(new Dimension(80, 20));
		aquaBtn.setMargin(new Insets(2, 2, 2, 2));
		aquaBtn.setActionCommand("aqua");
		aquaBtn.addActionListener(this);
		aquaBtn.setForeground(Color.WHITE);
		aquaBtn.setBackground(new Color(0, 255, 255));
		panel_4.add(aquaBtn);

		redBtn = new JButton("Red");
		redBtn.setMinimumSize(new Dimension(80, 20));
		redBtn.setMaximumSize(new Dimension(80, 20));
		redBtn.setPreferredSize(new Dimension(80, 20));
		redBtn.setMargin(new Insets(2, 2, 2, 2));
		redBtn.setActionCommand("red");
		redBtn.addActionListener(this);
		redBtn.setForeground(Color.WHITE);
		redBtn.setBackground(Color.RED);
		panel_4.add(redBtn);

		lightPurpleBtn = new JButton("LightPurple");
		lightPurpleBtn.setMinimumSize(new Dimension(80, 20));
		lightPurpleBtn.setMaximumSize(new Dimension(80, 20));
		lightPurpleBtn.setPreferredSize(new Dimension(80, 20));
		lightPurpleBtn.setMargin(new Insets(2, 2, 2, 2));
		lightPurpleBtn.setActionCommand("lightPurple");
		lightPurpleBtn.addActionListener(this);
		lightPurpleBtn.setForeground(Color.WHITE);
		lightPurpleBtn.setBackground(new Color(238, 130, 238));
		panel_4.add(lightPurpleBtn);

		yellowBtn = new JButton("Yellow");
		yellowBtn.setMinimumSize(new Dimension(80, 20));
		yellowBtn.setMaximumSize(new Dimension(80, 20));
		yellowBtn.setPreferredSize(new Dimension(80, 20));
		yellowBtn.setMargin(new Insets(2, 2, 2, 2));
		yellowBtn.setActionCommand("yellow");
		yellowBtn.addActionListener(this);
		yellowBtn.setForeground(Color.BLACK);
		yellowBtn.setBackground(Color.YELLOW);
		panel_4.add(yellowBtn);

		whiteBtn = new JButton("White");
		whiteBtn.setMinimumSize(new Dimension(80, 20));
		whiteBtn.setMaximumSize(new Dimension(80, 20));
		whiteBtn.setPreferredSize(new Dimension(80, 20));
		whiteBtn.setMargin(new Insets(2, 2, 2, 2));
		whiteBtn.setActionCommand("white");
		whiteBtn.addActionListener(this);
		whiteBtn.setForeground(Color.BLACK);
		whiteBtn.setBackground(Color.WHITE);
		panel_4.add(whiteBtn);

		highlightBtn = new JButton("強調");
		highlightBtn.setMinimumSize(new Dimension(80, 20));
		highlightBtn.setMaximumSize(new Dimension(80, 20));
		highlightBtn.setPreferredSize(new Dimension(80, 20));
		highlightBtn.setMargin(new Insets(2, 2, 2, 2));
		highlightBtn.setForeground(Color.WHITE);
		highlightBtn.setBackground(new Color(255, 165, 0));
		highlightBtn.setActionCommand("highlight");
		highlightBtn.addActionListener(this);
		panel_4.add(highlightBtn);

		backgroundBtn = new JButton("背景");
		backgroundBtn.setMinimumSize(new Dimension(80, 20));
		backgroundBtn.setMaximumSize(new Dimension(80, 20));
		backgroundBtn.setPreferredSize(new Dimension(80, 20));
		backgroundBtn.setMargin(new Insets(2, 2, 2, 2));
		backgroundBtn.setForeground(Color.WHITE);
		backgroundBtn.setBackground(Color.LIGHT_GRAY);
		backgroundBtn.setActionCommand("background");
		backgroundBtn.addActionListener(this);
		panel_4.add(backgroundBtn);

		horizontalStrut_8 = Box.createHorizontalStrut(80);
		panel_4.add(horizontalStrut_8);

		JPanel panel_2 = new JPanel();
		panel_2.setMaximumSize(new Dimension(32767, 20));
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

		JButton resetBtn = new JButton("初期値に戻す");
		resetBtn.setPreferredSize(new Dimension(100, 20));
		resetBtn.setMinimumSize(new Dimension(100, 20));
		resetBtn.setMaximumSize(new Dimension(100, 20));
		resetBtn.setMargin(new Insets(2, 2, 2, 2));
		resetBtn.addActionListener(this);
		resetBtn.setActionCommand("reset");
		panel_2.add(resetBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("default"))
			choseColorButton(defaultBtn);
		if(e.getActionCommand().equals("black"))
			choseColorButton(blackBtn);
		if(e.getActionCommand().equals("darkBlue"))
			choseColorButton(darkBlueBtn);
		if(e.getActionCommand().equals("darkGreen"))
			choseColorButton(darkGreenBtn);
		if(e.getActionCommand().equals("darkAqua"))
			choseColorButton(darkAquaBtn);
		if(e.getActionCommand().equals("darkRed"))
			choseColorButton(darkRedBtn);
		if(e.getActionCommand().equals("darkPurple"))
			choseColorButton(darkPurpleBtn);
		if(e.getActionCommand().equals("gold"))
			choseColorButton(goldBtn);
		if(e.getActionCommand().equals("glay"))
			choseColorButton(glayBtn);
		if(e.getActionCommand().equals("darkGray"))
			choseColorButton(darkGrayBtn);
		if(e.getActionCommand().equals("blue"))
			choseColorButton(blueBtn);
		if(e.getActionCommand().equals("green"))
			choseColorButton(greenBtn);
		if(e.getActionCommand().equals("aqua"))
			choseColorButton(aquaBtn);
		if(e.getActionCommand().equals("red"))
			choseColorButton(redBtn);
		if(e.getActionCommand().equals("lightPurple"))
			choseColorButton(lightPurpleBtn);
		if(e.getActionCommand().equals("yellow"))
			choseColorButton(yellowBtn);
		if(e.getActionCommand().equals("white"))
			choseColorButton(whiteBtn);
		if(e.getActionCommand().equals("highlight"))
			choseColorButton(highlightBtn);
		if(e.getActionCommand().equals("background"))
			choseColorButton(backgroundBtn);
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
			config.setColorDefault(defaultBtn.getBackground().getRGB());
			config.setColorBlack(blackBtn.getBackground().getRGB());
			config.setColorDrakBlue(darkBlueBtn.getBackground().getRGB());
			config.setColorDarkGreen(darkGreenBtn.getBackground().getRGB());
			config.setColorDarkAqua(darkAquaBtn.getBackground().getRGB());
			config.setColorDarkRed(darkRedBtn.getBackground().getRGB());
			config.setColorDarkPurple(darkPurpleBtn.getBackground().getRGB());
			config.setColorGold(goldBtn.getBackground().getRGB());
			config.setColorGlay(glayBtn.getBackground().getRGB());
			config.setColorDarkGlay(darkGrayBtn.getBackground().getRGB());
			config.setColorBlue(blueBtn.getBackground().getRGB());
			config.setColorGreen(greenBtn.getBackground().getRGB());
			config.setColorAqua(aquaBtn.getBackground().getRGB());
			config.setColorRed(redBtn.getBackground().getRGB());
			config.setColorLightPurple(lightPurpleBtn.getBackground().getRGB());
			config.setColorYellow(yellowBtn.getBackground().getRGB());
			config.setColorWhite(whiteBtn.getBackground().getRGB());
			config.setColorHighlight(highlightBtn.getBackground().getRGB());
			config.setColorBackground(backgroundBtn.getBackground().getRGB());
			config.resetConfigration();
			parentGui.reView();
			setVisible(false);
		}
		if(e.getActionCommand().equals("cancel")) {
			setVisible(false);
		}
		if(e.getActionCommand().equals("reset")) {
			userRegExpTxt.setText(ChatMirumiruConfig.USER_REG_EXP);
			systemRegExpTxt.setText(ChatMirumiruConfig.SYSTEM_REG_EXP);
			saveLogMaxSpn.setValue(ChatMirumiruConfig.SAVING_LOG_MAX);
			reloadLogSpn.setValue(ChatMirumiruConfig.RELOAD_LOG_INTERVAL);
			setButtonColor(defaultBtn, new Color(ChatMirumiruConfig.COLOR_DEFAULT));
			setButtonColor(blackBtn, new Color(ChatMirumiruConfig.COLOR_BLACK));
			setButtonColor(darkBlueBtn, new Color(ChatMirumiruConfig.COLOR_DARKBLUE));
			setButtonColor(darkGreenBtn, new Color(ChatMirumiruConfig.COLOR_DARKGREEN));
			setButtonColor(darkAquaBtn, new Color(ChatMirumiruConfig.COLOR_DARKAQUA));
			setButtonColor(darkRedBtn, new Color(ChatMirumiruConfig.COLOR_DARKRED));
			setButtonColor(darkPurpleBtn, new Color(ChatMirumiruConfig.COLOR_DARKPURPLE));
			setButtonColor(goldBtn, new Color(ChatMirumiruConfig.COLOR_GOLD));
			setButtonColor(glayBtn, new Color(ChatMirumiruConfig.COLOR_GLAY));
			setButtonColor(darkGrayBtn, new Color(ChatMirumiruConfig.COLOR_DARKGLAY));
			setButtonColor(blueBtn, new Color(ChatMirumiruConfig.COLOR_BLUE));
			setButtonColor(greenBtn, new Color(ChatMirumiruConfig.COLOR_GREEN));
			setButtonColor(aquaBtn, new Color(ChatMirumiruConfig.COLOR_AQUA));
			setButtonColor(redBtn, new Color(ChatMirumiruConfig.COLOR_RED));
			setButtonColor(lightPurpleBtn, new Color(ChatMirumiruConfig.COLOR_LIGHTPURPLE));
			setButtonColor(yellowBtn, new Color(ChatMirumiruConfig.COLOR_YELLOW));
			setButtonColor(whiteBtn, new Color(ChatMirumiruConfig.COLOR_WHITE));
			setButtonColor(highlightBtn, new Color(ChatMirumiruConfig.COLOR_HIGHLIGHT));
			setButtonColor(backgroundBtn, new Color(ChatMirumiruConfig.COLOR_BACKGROUND));
		}
	}

	private void choseColorButton(JButton button) {
		JColorChooser colorchooser = new JColorChooser();
		Color color = colorchooser.showDialog(dialog, "色の選択", button.getBackground());
		if(color == null)
			return;
		// 不透明にする
		color = new Color(color.getRGB(),false);
		setButtonColor(button, color);
	}

	public void setVisible(boolean b) {
		userRegExpTxt.setText(config.getUserRegExp());
		systemRegExpTxt.setText(config.getSystemRegExp());
		saveLogMaxSpn.setValue(config.getSavingLogMax());
		reloadLogSpn.setValue(config.getReloadLogInterval());
		setButtonColor(defaultBtn, new Color(config.getColorDefault()));
		setButtonColor(blackBtn, new Color(config.getColorBlack()));
		setButtonColor(darkBlueBtn, new Color(config.getColorDarkBlue()));
		setButtonColor(darkGreenBtn, new Color(config.getColorDarkGreen()));
		setButtonColor(darkAquaBtn, new Color(config.getColorDarkAqua()));
		setButtonColor(darkRedBtn, new Color(config.getColorDarkRed()));
		setButtonColor(darkPurpleBtn, new Color(config.getColorDarkPurple()));
		setButtonColor(goldBtn, new Color(config.getColorGold()));
		setButtonColor(glayBtn, new Color(config.getColorGlay()));
		setButtonColor(darkGrayBtn, new Color(config.getColorDarkGlay()));
		setButtonColor(blueBtn, new Color(config.getColorBlue()));
		setButtonColor(greenBtn, new Color(config.getColorGreen()));
		setButtonColor(aquaBtn, new Color(config.getColorAqua()));
		setButtonColor(redBtn, new Color(config.getColorRed()));
		setButtonColor(lightPurpleBtn, new Color(config.getColorLightPurple()));
		setButtonColor(yellowBtn, new Color(config.getColorYellow()));
		setButtonColor(whiteBtn, new Color(config.getColorWhite()));
		setButtonColor(highlightBtn, new Color(config.getColorHighlight()));
		setButtonColor(backgroundBtn, new Color(config.getColorBackground()));
		dialog.setVisible(b);
	}

	private void setButtonColor(JButton button, Color color) {
		if(color.getGreen() < 200)
			button.setForeground(Color.WHITE);
		else
			button.setForeground(Color.BLACK);
		button.setBackground(color);
	}
}
