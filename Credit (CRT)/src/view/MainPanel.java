package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Controller;
import ui.Button;
import ui.Label;
import ui.Panel;

public class MainPanel extends JPanel implements Runnable {

	/* serialVersionUID */
	public static final long serialVersionUID = 4L;

	/* The width and height for our panel */
	public static final int WIDTH = 275, HEIGHT = 588;
	
	/* static constants */
	public static final int MINUTE = 1, SECONDS = MINUTE * 60;
	
	public static final String receiveButtonPath = "res/RECEIVE_BUTTON.png", sendButtonPath = "res/SEND_BUTTON.png";

	private boolean running = false;
	
	private Controller controller;

	private Panel top;
	private JPanel bottom;
	
	private JScrollPane scrollList;

	public Label walletLabel;
	public Label nameLabel;
	private Label balanceLabel;
	private Label refreshLabel;

	private Button sendButton;
	private Button receiveButton;

	public MainPanel(Controller controller) {
		this.controller = controller;
		this.setBackground(new Color(232, 238, 241));
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(true);
		this.requestFocus();
		this.setLayout(null);
		this.initComponents();
	}

	/**
	 * Method called automatically to start the thread and the main loop;
	 */
	public void addNotify() {
		super.addNotify();
		if (!running) {
			new Thread(this).start();
			running = true;
		}
	}

	/** Initialize all the UI components */
	private void initComponents() {
		String NAME = controller.getCRTWallet().getName();
		String BALANCE = Double.toString(controller.getCRTWallet().getBalance());
		top = new Panel(0, 0, 275, 294, this);
		top.addImage("res/BACKGROUND.png");
		refreshLabel = new Label("0:00", 117, 10, 40, 17, top);
		walletLabel = new Label("WALLET", 113, 43, 49, 11, top);
		nameLabel = new Label(NAME, 123, 90, 28, 15, top);
		balanceLabel = new Label(BALANCE, 42, 117, 190, 27, top);

		sendButton = new Button("Send", 15, 208, 110, 57, top, 2);
		sendButton.addImage("res/SEND.png", true);
		receiveButton = new Button("Receive", 150, 208, 110, 57, top, 1);
		receiveButton.addImage("res/RECEIVE.png", true);
		
		bottom = new JPanel();
		bottom.setBounds(0, 295, 275, 294);
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
		addTransactions();
		scrollPane();
		actionComponents();
	}
	
	/* Add a transaction to the ui */
	private void addTransactions(){
		if(controller.getCRTWallet().getTransactions().size() == 0) return;
		bottom.removeAll();
		bottom.revalidate();
		bottom.repaint();
		for(String s : controller.getCRTWallet().getTransactions()){
			String bits[] = s.split("/");
			String path = (bits[0].equals(controller.getCRTWallet().getAddress()) ? sendButtonPath : receiveButtonPath);
			Button b = new Button("<html><center></center></html>", 15, 0, 245, 57, bottom, 3);
			b.setFrom(bits[0], (bits[0].equals(controller.getCRTWallet().getAddress()) ? new Color(0, 221, 219) : new Color(255, 192, 115)));
			b.setAmount(Double.parseDouble(bits[1]));
			b.setTo(bits[2]);
			b.addImage(path, false);
		}
		bottom.revalidate();
		bottom.repaint();
	}
	
	/* Generate the scrollPane */
	private void scrollPane(){
		scrollList = new JScrollPane(bottom, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollList.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		scrollList.getViewport().setBorder(null);
		scrollList.setViewportBorder(null);
		scrollList.setAutoscrolls(true);
		scrollList.setBorder(null);
		scrollList.setBounds(0, 294, 275, 296);
		this.add(scrollList);
	}

	/* Actions that happen when the buttons are being pressed */
	private void actionComponents() {
		sendButton.addActionListener(e -> new SendForm("Send CRT", new Panel(0, 0, 275, 290), controller));
		receiveButton.addActionListener(e -> new ReceiveForm("Receive CRT", new Panel(0, 0, 575, 170), controller.getCRTWallet().getAddress()));
	}
	
	/* Refresh the view */
	public void refresh(){
		balanceLabel.setText(Double.toString(controller.getCRTWallet().getBalance()));
		addTransactions();
	}

	/* Main loop that runs the thread */
	public void run() {
		long delay = 1000;
		int time = SECONDS;
		while (running) {
			time -= 1;
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			refreshLabel.setText("0:" + time);
			if (time == 0) {
				refreshLabel.setText("SAVING BLOCK");
				controller.getBlockChain().nextBlock();
				time = SECONDS;
			}
		}
	}
}
