package com.masai.bean;

public class TransactionHistory {

	private int cid;
	private int recievers_acc;
	private String dot;
	private int senders_acc;
	private int transaction_amount;
	private int balance;

	public TransactionHistory() {
		// TODO Auto-generated constructor stub
	}

	public TransactionHistory(int cid, int recievers_acc, String dot, int senders_acc, int transaction_amount,
			int balance) {
		super();
		this.cid = cid;
		this.recievers_acc = recievers_acc;
		this.dot = dot;
		this.senders_acc = senders_acc;
		this.transaction_amount = transaction_amount;
		this.balance = balance;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getRecievers_acc() {
		return recievers_acc;
	}

	public void setRecievers_acc(int recievers_acc) {
		this.recievers_acc = recievers_acc;
	}

	public String getDot() {
		return dot;
	}

	public void setDot(String dot) {
		this.dot = dot;
	}

	public int getSenders_acc() {
		return senders_acc;
	}

	public void setSenders_acc(int senders_acc) {
		this.senders_acc = senders_acc;
	}

	public int getTransaction_amount() {
		return transaction_amount;
	}

	public void setTransaction_amount(int transaction_amount) {
		this.transaction_amount = transaction_amount;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "TransactionHistory [cid=" + cid + ", recievers_acc=" + recievers_acc + ", dot=" + dot + ", senders_acc="
				+ senders_acc + ", transaction_amount=" + transaction_amount + ", balance=" + balance + "]";
	}

}
