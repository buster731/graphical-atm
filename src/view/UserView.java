package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;
import view.UpdUserView;

@SuppressWarnings("serial")
public class UserView extends JPanel implements ActionListener {
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JButton editButton;
	private JButton returnButton;
	private JLabel errorMessageLabel;// label for potential error messages
	private JLabel name;
	private JLabel DOB;
	private JLabel address;
	private JLabel acctNum;
	private JLabel phoneNum;
	private JLabel city;
	private JLabel state;
	private JLabel zip;
	
	UpdUserView udv;
	
	public void updateErrorMessage(String errorMessage) {
		errorMessageLabel.setText(errorMessage);
	}
	
	public UserView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	private void initialize() {
		this.setLayout(null);
		
		try {
		initEditButton();
		initReturnButton();
		initErrorMessageLabel();
		initNameLabel();
		initDOBLabel();
		initPhoneNumLabel();
		initAddressLabel();
		initCityLabel();
		initStateLabel();
		initZipLabel();
		initAccountNumLabel();
		}
		catch (NullPointerException e1) {
			e1.printStackTrace();
		}
		}
	
	private void initAccountNumLabel() {
		
		acctNum = new JLabel("Account Number: ", SwingConstants.RIGHT);
		acctNum.setBounds(40, 10, 300, 45);
		acctNum.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(acctNum);
	}
	private void initNameLabel() {
		name = new JLabel("Name: ", SwingConstants.RIGHT);
		name.setBounds(100, 65, 95, 35);
		name.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(name);
	}


	private void initDOBLabel() {
	//NEED TO FINISH CODING THIS PART!!!! IT NEEDS TO BE LABELS FOR EVERYTHING
		//THE UPDATE THING SHOULD BE SAME AS CREATE VIEW BUT SET TEXT TO OLD VALUES

		DOB = new JLabel("Date of Birth: ", SwingConstants.RIGHT);
		DOB.setBounds(145, 105, 150, 35);
		this.add(DOB);
		
	}
	
	
	
	private void initPhoneNumLabel() {
		phoneNum = new JLabel("Phone Number: ", SwingConstants.RIGHT);
		phoneNum.setBounds(72, 265, 150, 35);
		this.add(phoneNum);
	}

	private void initAddressLabel() {
		address = new JLabel("Street Address: ", SwingConstants.RIGHT);
		address.setBounds(80, 145, 300, 35);
		address.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(address);
	}
	
	private void initCityLabel() {
		city = new JLabel("City: ", SwingConstants.RIGHT);
		city.setBounds(100, 185, 95, 35);
		city.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(city);
	}
	
	private void initStateLabel() {
		state = new JLabel("State: ", SwingConstants.RIGHT);
		state.setBounds(100, 305, 100, 35);
		state.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(state);
	}
	
	private void initZipLabel() {
		zip = new JLabel("Zip: ", SwingConstants.RIGHT);
		zip.setBounds(100, 225, 95, 35);
		zip.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(zip);
	}
	
	private void initEditButton() {
		editButton = new JButton("Confirm");
		editButton.setBounds(275, 400, 100, 40);
		editButton.addActionListener(this);
		
		this.add(editButton);
	}
	
	private void initReturnButton() {
		returnButton = new JButton("Cancel");
		returnButton.setBounds(145, 400, 100, 40);
		returnButton.addActionListener(this);
		
		this.add(returnButton);
	}
	
	private void initErrorMessageLabel() {
		errorMessageLabel = new JLabel("", SwingConstants.CENTER);
		errorMessageLabel.setBounds(0, 350, 500, 35);
		errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 14));
		errorMessageLabel.setForeground(Color.RED);
		
		this.add(errorMessageLabel);
	}
	
	public void update() {
		
		updateErrorMessage("");
		name.setText("Name: " + manager.showName());
		DOB.setText("Date of Birth: " + manager.showDOB());
		phoneNum.setText("Phone Number: " + manager.showPhoneNum());
		address.setText("Street Address: " + manager.showAddress());
		city.setText("City: " + manager.showCity());
		state.setText("State: " + manager.showState());
		zip.setText("Zip: " + manager.showZip());
		acctNum.setText("Account Number: " + manager.showAcctNum());
	}
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The DepositView class is not serializable.");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();

		if(source.equals(editButton)) {
			udv.clear();
			manager.switchTo(ATM.UPDUSER_VIEW);
		}
		
		if(source.equals(returnButton)) {
			manager.switchTo(ATM.HOME_VIEW);
		}
		
	}
}
