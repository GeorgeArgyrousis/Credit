package model.wallet;

import java.util.ArrayList;

import controller.Controller;
import utility.Content;

public class CRTWallet {
	
	private Controller controller;
	
	private final String NAME = "CRT";
	
	private String address;
	
	private Double balance;
	
	/* A list of all the transactions that need to be stored in that block */
	private ArrayList<String> transactions;
	
	private Content creator;
	
	private final String CRTWalletPath = "res/CRTWallet.txt";
	
	public CRTWallet(Controller controller){
		test();
		this.controller = controller;
		this.creator = new Content(CRTWalletPath);
		String line = this.creator.read(1);
		if(line.equals("0")){
			generateAddress();
			this.balance = 0.0;
			this.creator.write(1, this.address + "/" + this.balance);
			this.creator.write(2, "0/0");
			this.transactions = new ArrayList<String>();
		}else{
			String bits[] = line.split("/");
			this.address = bits[0];
			this.balance = Double.parseDouble(bits[1]);
			line = this.creator.read(2);
			bits = line.split("/");
			if(!bits[0].equals("0")){
				this.transactions = new ArrayList<String>(this.creator.read(3, Integer.parseInt(bits[1])));
			}else{
				this.transactions = new ArrayList<String>();
			}
		}
	}
	
	private void test(){
		/*Encryption e = new Encryption();
		String encrypt = e.encrypt("24310".getBytes(), "Hello Worlf!".getBytes()).toString();
		System.out.println(encrypt);
		String decrypt = e.decrypt("24310".getBytes(), encrypt.getBytes()).toString();
		System.out.println(decrypt);*/
	}
	
	private void generateAddress(){
		this.address = "0x03785926129457123054712";
	}
	
	public String sendTransaction(Double amount, String to){
		setBalance(this.getBalance() - amount);
		String transaction = getAddress() + "/" + amount + "/" + to;
		addTransaction(transaction);
		controller.getBlockChain().addTransaction(getAddress(), amount, to);
		controller.getMainPanel().refresh();
		return transaction;
	}
	
	private void addTransaction(String transaction){
		this.transactions.add(transaction);
		String bits[] = creator.read(2).split("/");
		int size = Integer.parseInt(bits[0]) + 1;
		int height = size + 2;
		creator.write(2, size + "/" + height);
		creator.write(height, transaction);
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		String bits[] = creator.read(1).split("/");
		this.balance = balance;
		creator.write(1, bits[0] + "/" + this.balance);
	}

	public String getAddress() {
		return this.address;
	}
	
	public String getName() {
		return this.NAME;
	}
	
	public ArrayList<String> getTransactions(){
		return this.transactions;
	}
	
}
