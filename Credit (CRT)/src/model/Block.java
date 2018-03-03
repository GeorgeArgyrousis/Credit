package model;
import java.util.ArrayList;

public class Block {

	/* The index of every block */
	private long index;
	
	/* the linked previous hash of the block; */
	private String previousHash;
	
	/* The date that the blocked was generated at; */
	private long date;
	
	/* The hash of the current block; */
	private String currentHash;
	
	/* A list of all the transactions that need to be stored in that block */
	private ArrayList<String> transactions;

	/* Generate a block */
	public Block(long index, String previousHash, long date, String currentHash, ArrayList<String> transactions) {
		this.index = index;
		this.previousHash = previousHash;
		this.date = date;
		this.currentHash = currentHash;
		this.transactions = new ArrayList<String>(transactions);
		/* clearing the transactions coming from the constructor */
		transactions.clear();
	}

	public long getIndex() {
		return index;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public long getDate() {
		return date;
	}

	public String getCurrentHash() {
		return currentHash;
	}
	
	public ArrayList<String> getTransactions(){
		return transactions;
	}
	
	/* return the information stored in the head of the block; */
	public String getHeader(){
		return this.getPreviousHash() + "/" + this.getDate() + "/" + this.getCurrentHash();
	}
	
	/* return the information that is stored in the foot of the block; */
	public String getFooter(){
		return this.getIndex() + "/" + this.getTransactions().size();
	}
	
	/* Print all the transactions present in the block */
	public void printTransactions(){
		System.out.println("TRANSACTIONS :");
		transactions.stream()
		.forEach(t -> {
			String bits[] = t.split(" ");
			System.out.println(" TO: " + bits[0]);
			System.out.println(" AMOUNT: " + bits[1]);
			System.out.println(" FROM: " + bits[2]);
			System.out.println();
		});
		System.out.println("END TRANSACTIONS");
	}
}
