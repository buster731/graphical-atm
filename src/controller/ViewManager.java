package controller;

import java.awt.CardLayout;
import java.awt.Container;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import data.Database;
import model.BankAccount;
import model.User;
import view.ATM;
import view.HomeView;
import view.LoginView;
import view.DepositView;
import view.WithdrawView;
import view.TransferView;

public class ViewManager {
	
	private Container views;				// the collection of all views in the application
	private Database db;					// a reference to the database
	private BankAccount account;			// the user's bank account
	private BankAccount destination;		// an account to which the user can transfer funds
	/**
	 * Constructs an instance (or object) of the ViewManager class.
	 * 
	 * @param layout
	 * @param container
	 */
	
	public ViewManager(Container views) {
		this.views = views;
		this.db = new Database();
	}
	
	///////////////////// INSTANCE METHODS ////////////////////////////////////////////
	
	/**
	 * Routes a login request from the LoginView to the Database.
	 * 
	 * @param accountNumber
	 * @param pin
	 * @throws SQLException 
	 */
	public void createAccount(int pin, int dob, long phone, String firstName, String lastName, String streetAddress, String city, String state, String zip) throws SQLException {
		double balance = 0.00;
		User newUser = new User(pin, dob, phone, firstName, lastName, streetAddress, city, state, zip);
		long acctNum = db.getMaxAccountNumber() + 1;
		BankAccount account = new BankAccount('Y', acctNum, balance, newUser);
		db.insertAccount(account);
	}
	
	public double showBal() {
		long accountNumber = account.getAccountNumber();
		double bal = db.getAccount(accountNumber).getBalance();
		return bal;
	}
	
	public String showName() {
		String fName = account.getUser().getFirstName();
		String lName = account.getUser().getLastName();
		return(fName + " " + lName);
	}
	
	public long showAcctNum() {
		long accountNumber = account.getAccountNumber();
		return accountNumber;
	}
	
	public String showPin() {
		int pin = account.getUser().getPin();
		String pin1 = "" + pin;
		return pin1;
	}
	public String showDOB() {
		String DOB = account.getUser().getFormattedDob();
		return DOB;
	}
	
	public String showAddress() {
		String address = account.getUser().getStreetAddress();
		return address;
	}
	
	public String showCity() {
		String City = account.getUser().getCity();
		return City;
	}
	
	public String showPhoneNum() {
		String phoneNum = account.getUser().getFormattedPhone();
		return phoneNum;
	}
	
	public String showState() {
		String state = account.getUser().getState();
		return state;
	}
	
	public String showZip() {
		String zip = account.getUser().getZip();
		return zip;
	}
	public void deposit(double amount) {		
		int worked = account.deposit(amount);
		if(worked == 3) {
			DepositView dv = (DepositView) views.getComponents()[ATM.DEPOSIT_VIEW_INDEX];
			dv.clear();
			db.updateAccount(account);
			HomeView hv = (HomeView) views.getComponents()[ATM.HOME_VIEW_INDEX];
			hv.updateUserInfo();
			switchTo(ATM.HOME_VIEW);
			
		}
		else if(worked == 0){
			DepositView dv = (DepositView) views.getComponents()[ATM.DEPOSIT_VIEW_INDEX];
			dv.updateErrorMessage("Invalid deposit amount");
			dv.clear();
		}

		else {
			DepositView dv = (DepositView) views.getComponents()[ATM.DEPOSIT_VIEW_INDEX];
			dv.updateErrorMessage("Invalid deposit amount");
			dv.clear();
		}
	}
	
	public void withdraw(double amount) {		
		int worked = account.withdraw(amount);
		if(worked == 1){
			WithdrawView wv = (WithdrawView) views.getComponents()[ATM.WITHDRAW_VIEW_INDEX];
			wv.updateErrorMessage("Insufficient Funds");
			wv.clear();
		}
		else if(worked == 0){
			WithdrawView wv = (WithdrawView) views.getComponents()[ATM.WITHDRAW_VIEW_INDEX];
			wv.updateErrorMessage("Invalid withdraw amount");
			wv.clear();
		}
		else if(worked == 3) {
			WithdrawView wv = (WithdrawView) views.getComponents()[ATM.WITHDRAW_VIEW_INDEX];
			wv.clear();
			db.updateAccount(account);
			HomeView hv = (HomeView) views.getComponents()[ATM.HOME_VIEW_INDEX];
			hv.updateUserInfo();
			switchTo(ATM.HOME_VIEW);
		}
		else {
			WithdrawView wv = (WithdrawView) views.getComponents()[ATM.WITHDRAW_VIEW_INDEX];
			wv.updateErrorMessage("Invalid Withdraw Amount");
			wv.clear();
		}
	}
	
	public void transfer(long tranAcct, double amount) {
		int worked = account.transfer(db.getAccount(tranAcct), amount);
		TransferView tv = (TransferView) views.getComponents()[ATM.TRANSFER_VIEW_INDEX];
		switch(worked) {
		case 0:
			tv.updateErrorMessage("Invalid Transfer Amount");
			tv.clear();
		case 1:
			tv.updateErrorMessage("Insufficient Funds");
			tv.clear();
		case 2:
			tv.updateErrorMessage("Account Not Found");
			tv.clear();
		case 3:
			db.updateAccount(account);
			db.updateAccount(db.getAccount(tranAcct));
			HomeView hv = (HomeView) views.getComponents()[ATM.HOME_VIEW_INDEX];
			hv.updateUserInfo();
			switchTo(ATM.HOME_VIEW);
		}
	}
	
	public void changeInfo(String state, String City, String Address, String Zip, int pin, long PhoneNum) {
		account.getUser().setCity(City);
		account.getUser().setState(state);
		account.getUser().setStreetAddress(Address);
		account.getUser().setZip(Zip);
		account.getUser().setPin(account.getUser().getPin(), pin);
		account.getUser().setPhone(PhoneNum);
		db.updateAccount(account);
	}
	public void login(String accountNumber, char[] pin) {
		LoginView lv = (LoginView) views.getComponents()[ATM.LOGIN_VIEW_INDEX];
		
		try {
			account = db.getAccount(Long.valueOf(accountNumber), Integer.valueOf(new String(pin)));
			
			if (account == null) {
				lv.updateErrorMessage("Invalid account number and/or PIN.");
			} else {
				HomeView hv = (HomeView) views.getComponents()[ATM.HOME_VIEW_INDEX];
				hv.initUserInfo();
				switchTo(ATM.HOME_VIEW);
				lv.clear();
			}
		} catch (NumberFormatException e) {
			lv.updateErrorMessage("Account numbers and PINs don't have letters.");
		}
	}
	
	public void logout() {
		try {			
			int choice = JOptionPane.showConfirmDialog(
				views,
				"Are you sure?",
				"Logging Out",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
			);
			
			if (choice == 0) {
				db.shutdown();
				account = null;
				switchTo(ATM.LOGIN_VIEW);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Switches the active (or visible) view upon request.
	 * 
	 * @param view
	 */
	
	public void switchTo(String view) {
//		if(view == "HOME_VIEW") {
//		}
		((CardLayout) views.getLayout()).show(views, view);
		
	}
	
	/**
	 * Routes a shutdown request to the database before exiting the application. This
	 * allows the database to clean up any open resources it used.
	 */
	
	public void shutdown() {
		try {			
			int choice = JOptionPane.showConfirmDialog(
				views,
				"Are you sure?",
				"Shutdown ATM",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
			);
			
			if (choice == 0) {
				db.shutdown();
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
