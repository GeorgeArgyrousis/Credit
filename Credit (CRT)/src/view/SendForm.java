package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JPanel;

import controller.Controller;
import ui.Button;
import ui.Field;
import ui.Frame;

public class SendForm extends Frame{

	private static final long serialVersionUID = 1L;

	private Controller controller;
	
	private Field addressField;
	private Field amountField;
	
	private Button sendButton;
	
	public SendForm(String title, JPanel panel, Controller controller){
		super(title, panel);
		this.controller = controller;
		this.addressField = new Field("Address", 10, 20, 250, 100, panel);
		this.amountField = new Field("Amount", 10, 120, 250, 100, panel);
		this.sendButton = new Button("Send", 82, 220, 110, 57, panel, 2);
		this.sendButton.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e){
				addTransaction();
			}
		});
	}
	
	private void addTransaction(){
		/* TODO :: field checks */
		if(addressField.getText().isEmpty() || amountField.getText().isEmpty()){
			System.out.println("The to field cannot be empty");
			return;
		}
		String to = this.addressField.getText();
		Double amount = Double.parseDouble(this.amountField.getText());
		if(amount > controller.getCRTWallet().getBalance()){
			System.out.println("You do not have sufficient funds");
			return;
		}
		/* TODO :: confirmation messge */
		/* TODO :: Sound? */
		String transaction = controller.getCRTWallet().sendTransaction(amount, to);
		try {
			controller.getNetController().send(transaction.getBytes(), InetAddress.getByName("localhost"), 3333);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.dispose();
	}
}
