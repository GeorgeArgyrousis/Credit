package ui;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ComboBox extends JComboBox<String>{

	private static final long serialVersionUID = 1L;
	
	private Color lightBlue = new Color(83, 192, 191);
	private Color darkBlue = new Color(53, 64, 72);
	
	private JLabel label;
	
	private String physicalActivityArray[] = { "Choose Type", "No Effort | 1.4 - 1.5", "Very Light Effort | 1.5 - 1.69", "Light Effort | 1.70 - 1.80", "Moderate Effort | 1.8 - 1.99", "Vigorous Effort | 2.0 - 2.20", "Maximum Effort | 2.20 - 2.40" };

	public ComboBox(String name, int x, int y, int width, int height, JPanel container){
		label = new JLabel(name);
		label.setForeground(lightBlue);
		label.setBounds(x, y - 18, width + 25, height);
		setModel(new DefaultComboBoxModel<String>(physicalActivityArray));
		setEditable(true);
		getEditor().getEditorComponent().setBackground(darkBlue);
		getEditor().getEditorComponent().setForeground(lightBlue);
		((JTextField) getEditor().getEditorComponent()).setBackground(darkBlue);
		setBounds(x, y, width + 50, height);
		container.add(label);
		container.add(this);
	}
	
	public String getText(){
		return physicalActivityArray[getSelectedIndex()];
	}
	
	public void setText(String text){
		for(int i = 0; i < physicalActivityArray.length; i++){
			if(physicalActivityArray[i].equals(text)){
				setSelectedIndex(i);
			}
		}
	}
}
