package ui;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioButton {

	private JRadioButton male;
	private JRadioButton female;

	private ButtonGroup group;

	private JLabel label;

	private Color lightBlue = new Color(83, 192, 191);

	private String name1, name2;

	public RadioButton(String name, String name1, String name2, int x, int y, int width, int height, JPanel container) {
		this.name1 = name1;
		this.name2 = name2;
		label = new JLabel(name);
		label.setForeground(lightBlue);
		label.setBounds(x, y - 18, width + 25, height);

		male = new JRadioButton(name1);
		male.setForeground(lightBlue);
		male.setBounds(x, y, width, height);

		female = new JRadioButton(name2);
		female.setForeground(lightBlue);
		female.setBounds(x + 60, y, width, height);

		group = new ButtonGroup();
		group.add(male);
		group.add(female);
		container.add(male);
		container.add(female);
	}

	public String getSelection() {
		if (male.isSelected()) {
			return name1;
		}
		if (female.isSelected()) {
			return name2;
		}
		return "NULL";
	}

	public void setSelection(String name) {
		if(name.equals("")){
			male.setSelected(false);
			female.setSelected(false);
			return;
		}
		if(male.getText().equals(name)){
			male.setSelected(true);
		}else{
			female.setSelected(true);
		}
	}
}
