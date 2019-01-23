package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JPanel;
import java.awt.font.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.ViewManager;
import view.DepositView;
import view.WithdrawView;
import view.TransferView;

@SuppressWarnings("serial")
public class HomeView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JButton logoutButton;
	private JButton depositButton;
	private JButton withdrawButton;
	private JButton transferButton;
	private JButton informationButton;
	public JLabel bal;
	DepositView dv;
	WithdrawView wv;
	UserView uv;
	
	/**
	 * Constructs an instance (or objects) of the HomeView class.
	 * 
	 * @param manager
	 */
	
	public HomeView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the HomeView components.
	 */
	
	private void initialize() {
		
		// TODO
		//
		// this is a placeholder for this view and should be removed once you start
		// building the HomeView.
		
		this.setLayout(null);
		
		initLogoutButton();
		initDepositButton();
		initWithdrawButton();
		initTransferButton();
		initInformationButton();
		// TODO
		//
		// this is where you should build the HomeView (i.e., all the components that
		// allow the user to interact with the ATM - deposit, withdraw, transfer, etc.).
		//
		// feel free to use my layout in LoginView as an example for laying out and
		// positioning your components.
	}
	
	/*
	 * HomeView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The HomeView class is not serializable.");
	}
	
	private void initLogoutButton() {
		logoutButton = new JButton("Logout");
		logoutButton.setBounds(225, 400, 100, 40);
		logoutButton.addActionListener(this);
		
		this.add(logoutButton);
	}
	
	private void initDepositButton() {
		depositButton = new JButton("Deposit");
		depositButton.setBounds(225, 150, 100, 40);
		depositButton.addActionListener(this);
		
		this.add(depositButton);
	}
	
	private void initWithdrawButton() {
		withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds(225, 200, 100, 40);
		withdrawButton.addActionListener(this);
		
		this.add(withdrawButton);
	}
	
	private void initTransferButton() {
		transferButton = new JButton("Transfer");
		transferButton.setBounds(225, 250, 100, 40);
		transferButton.addActionListener(this);
		
		this.add(transferButton);
	}
	
	public void initInformationButton() {
		informationButton = new JButton("Personal Information");
		informationButton.setBounds(225, 300, 150, 40);
		informationButton.addActionListener(this);
		
		this.add(informationButton);
	}
	public void initUserInfo() {
		JLabel name = new JLabel("User Name: " + manager.showName(), SwingConstants.RIGHT);
		name.setBounds(63, 60, 300, 45);
		name.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		JLabel acctNum = new JLabel("Account Number: " + manager.showAcctNum(), SwingConstants.RIGHT);
		acctNum.setBounds(40, 10, 300, 45);
		acctNum.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		bal = new JLabel("Current Balance: $" + manager.showBal(), SwingConstants.RIGHT);
		bal.setBounds(138, 95, 200, 45);
		bal.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(name);
		this.add(acctNum);
		this.add(bal);		
	}
	
	public void updateUserInfo() {
		bal.setText("");
		bal.setText("Current Balance: $" + manager.showBal());
	}
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the HomeView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if(source.equals(logoutButton)) {
			manager.logout();
		}
		if(source.equals(depositButton)) {
			try {
				dv.clear();
				manager.switchTo(ATM.DEPOSIT_VIEW);			
			} 
			catch (NullPointerException e2) {
				manager.switchTo(ATM.DEPOSIT_VIEW);
			}

		}
		if(source.equals(withdrawButton)) {
			try {
				wv.clear();
				manager.switchTo(ATM.WITHDRAW_VIEW);			
			}
			catch (NullPointerException e2) {
				manager.switchTo(ATM.WITHDRAW_VIEW);
			}
		}
		if(source.equals(transferButton)) {
			manager.switchTo(ATM.TRANSFER_VIEW);
		}
		if(source.equals(informationButton)) {
			uv.update();
			manager.switchTo(ATM.USER_VIEW);
		}
		
	}
}
