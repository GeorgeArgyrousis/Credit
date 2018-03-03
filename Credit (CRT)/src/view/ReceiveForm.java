package view;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import ui.Button;
import ui.Frame;
import ui.Label;

public class ReceiveForm extends Frame {

	private static final long serialVersionUID = 1L;

	public Label receiveLabel;
	public Label addressLabel;

	private Button copyButton;
	private String address;

	public ReceiveForm(String title, JPanel panel, String address) {
		super(title, panel);
		this.address = address;
		this.receiveLabel = new Label("Your CRT address", 217, 30, 140, 15, panel);
		this.addressLabel = new Label(address, 2, 51, 570, 25, panel);
		this.copyButton = new Button("Copy Address!", 212, 100, 150, 57, panel, 1);
		copyButtonFunctionality();
	}

	private void copyButtonFunctionality() {
		this.copyButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				StringSelection stringSelection = new StringSelection(getAddress());
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(stringSelection, null);
			}
		});
	}
	
	public String getAddress(){
		return this.address;
	}
}
