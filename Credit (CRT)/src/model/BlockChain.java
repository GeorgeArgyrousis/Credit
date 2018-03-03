package model;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import utility.Content;

public class BlockChain {
	
	/** The class manipulating the content we want to read/write */
	private Content creator;

	/** The list of transactions happening in the current iteration*/
	private ArrayList<String> transactions;
	
	/** The path to the block-chain .txt file */
	private final String blockChainPath = "res/blockChain.txt";

	public BlockChain() {
		transactions = new ArrayList<String>();
		creator = new Content(blockChainPath);
		if(creator.read(1).equals("0")) genesisBlock();
	}

	/** The first block in the chain */
	private void genesisBlock() {
		long index = 0;
		String previousHash = "0";
		long date = new Date().getTime();
		String currentHash = getHash(index + previousHash + date + getTransactionHash());
		saveBlock(new Block(index, previousHash, date, currentHash, transactions));
	}

	/** Generate the next block in the chain */
	public void nextBlock() {
		Block b = getLastBlock();
		long index = b.getIndex() + 1;
		String previousHash = b.getCurrentHash();
		long date = new Date().getTime();
		String currentHash = getHash(index + previousHash + date + getTransactionHash());
		saveBlock(new Block(index, previousHash, date, currentHash, transactions));
	}

	/**
	 * Read the blockList and extract all the 
	 * information neccessary to construct the
	 * last block on the list.
	 * @return The last block on the chain.
	 */
	private Block getLastBlock() {
		List<String> block = this.blockList();
		ArrayList<String> trans = new ArrayList<String>();
		int blockSize = block.size() - 1;
		String bits[] = block.get(blockSize).split("/");
		long index = Long.parseLong(bits[0]);
		int transactionSize = Integer.parseInt(bits[1]);
		if(transactionSize > 0){
			transactionSize--;
			while(transactionSize != -1){
				trans.add(block.get(transactionSize));
				transactionSize--;
				blockSize--;
			}
		}
		blockSize--;
		bits = block.get(blockSize).split("/");
		String previousHash = bits[0];
		long date = Long.parseLong(bits[1]);
		String currentHash = bits[2];
		return new Block(index, previousHash, date, currentHash, trans);
	}

	/**
	 * Write the current block to a file
	 * and clean the list of transactions
	 * for the next block iteration.
	 */
	private void saveBlock(Block b) {
		List<String> block = this.blockList();
		int blockNumber = Integer.parseInt(block.get(0));
		block.set(0, Integer.toString(blockNumber + 1));
		block.add(b.getHeader());
		block.addAll(b.getTransactions());
		block.add(b.getFooter());
		creator.write(block);
		transactions = new ArrayList<String>();
	}

	/**
	 * Add a transaction record to the list of records.
	 * @param The receipient address.
	 * @param The transaction amount.
	 * @param The sending address.
	 */
	public void addTransaction(String from, Double amount, String to) {
		transactions.add(new String(from + "/" + amount + "/" + to));
	}
	
	/**
	 * Print all the transactions that
	 * we have in the list currently.
	 */
	public void printTransactions(){
		transactions.stream()
		.forEach(t -> {
			String bits[] = t.split("/");
			System.out.println("Receipient Address :" + bits[0]);
			System.out.println("Amount transfered :" + bits[1]);
			System.out.println("Sender Address :" + bits[2]);
		});
	}

	/**
	 * Print a block in the chain;
	 * @param The block.
	 */
	public void printBlock(Block b) {
		System.out.println("INDEX: " + b.getIndex());
		System.out.println("PREVIOUS HASH: " + b.getPreviousHash());
		System.out.println("DATE: " + new Date(b.getDate()).toString());
		b.printTransactions();
		System.out.println("CURRENT HASH: " + b.getCurrentHash());
		System.out.println();
	}

	/**
	 * Get the transaction hash in a String with no spaces.
	 * Intented to be digested by the SHA-256 algorithm.
	 * @return The String.
	 */
	private String getTransactionHash() {
		String hash = "";
		for (String s : transactions) {
			hash += s;
		}
		return hash;
	}
	
	/**
	 * Returns the read list of the whole
	 * block-chain.
	 * @return The list of blocks
	 */
	private List<String> blockList(){
		return creator.read(-1, -1);
	}

	/**
	 * Generates a Hash based on a string of combined elements;
	 * 
	 * @param The String.
	 * @return The Hash.
	 */
	private String getHash(String hashable) {
		String hash = "";
		String algorithm = "SHA-256";
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance(algorithm);
			m.update(hashable.getBytes());
			byte[] digested = m.digest();
			hash = DatatypeConverter.printHexBinary(digested).toLowerCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash;
	}
}