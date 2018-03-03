package view;

import javax.swing.JPanel;

import ui.Button;
import ui.Frame;
import ui.Label;

public class ReceiveForm extends Frame {

	private static final long serialVersionUID = 1L;

	private Label receiveLabel;
	private Label addressLabel;
	
	private Button copyButton;

	public ReceiveForm(String title, JPanel panel, String address) {
		super(title, panel);
		this.receiveLabel = new Label("Your CRT address", 217, 30, 140, 15, panel);
		this.addressLabel = new Label(address, 2, 51, 570, 25, panel);
		this.copyButton = new Button("Copy Address!", 212, 100, 150, 57, panel, 1);
		//TODO :: FUNCTIONALITY
	}
}
