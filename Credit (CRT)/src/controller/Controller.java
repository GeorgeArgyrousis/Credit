package controller;

import model.BlockChain;
import model.wallet.CRTWallet;
import net.Net;
import view.MainFrame;
import view.MainPanel;

public class Controller {

	private BlockChain blockChain;
	private Net netController;
	private MainPanel mainPanel;
	private CRTWallet CRTwallet;
	
	public Controller(){
		this.blockChain = new BlockChain();
		this.netController = new Net("localhost", 3333);
		new Thread(this.netController).start();
		this.CRTwallet = new CRTWallet(this);
		this.mainPanel = new MainPanel(this);
		new MainFrame(mainPanel);
		
	}
	
	public BlockChain getBlockChain(){
		return this.blockChain;
	}
	
	public Net getNetController(){
		return this.netController;
	}
	
	public CRTWallet getCRTWallet(){
		return this.CRTwallet;
	}
	
	public MainPanel getMainPanel(){
		return this.mainPanel;
	}
}
