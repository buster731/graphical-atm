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
import javax.swing.JFormattedTextField;
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
	private JTextField name;
	private JTextField DOB;
	private JTextField address;
	private JTextField acctNum;
	private JFormattedTextField phoneNum;
	private JTextField city;
	private JTextField state;
	private JTextField zip;
	
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
	
	private void initAccountNumLabel() {
		
		JLabel acctNumLabel = new JLabel("Account Number: ", SwingConstants.RIGHT);
		acctNumLabel.setBounds(75, 25, 35, 45);
		acctNumLabel.setFont(new Font("DialogInput", Font.BOLD, 14));
		acctNum = new JTextField(20);
		acctNum.setBounds(125, 25, 300, 35);
		acctNum.setText("");
		this.add(acctNum);
		this.add(acctNumLabel);
		
	}
	private void initNameLabel() {
		JLabel nameLabel = new JLabel("Name: ", SwingConstants.RIGHT);
		nameLabel.setBounds(75, 60, 35, 35);
		nameLabel.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		name = new JTextField(20);
		name.setBounds(125, 60, 100, 35);
		name.setText("");
		this.add(name);
		this.add(nameLabel);
	}


	private void initDOBLabel() {
	//NEED TO FINISH CODING THIS PART!!!! IT NEEDS TO BE LABELS FOR EVERYTHING
		//THE UPDATE THING SHOULD BE SAME AS CREATE VIEW BUT SET TEXT TO OLD VALUES

		JLabel DOBLabel = new JLabel("Date of Birth: ", SwingConstants.RIGHT);
		DOBLabel.setBounds(75, 100, 45, 35);
		DOBLabel.setFont(new Font("DialogInput", Font.BOLD, 14));
		DOB = new JFormattedTextField(20);
		DOB.setBounds(125, 100, 100, 35);
		DOB.setText("");
		this.add(DOBLabel);
		this.add(DOB);
	}
	
	
	
	private void initPhoneNumLabel() {
		JLabel phoneNumLabel = new JLabel("Phone Number: ", SwingConstants.RIGHT);
		phoneNumLabel.setBounds(75, 260, 35, 35);
		phoneNumLabel.setFont(new Font("DialogInput", Font.BOLD, 14));
		phoneNum = new JFormattedTextField(20);
		phoneNum.setText("");
		phoneNum.setBounds(125, 260, 150, 35);
		this.add(phoneNumLabel);
		this.add(phoneNum);
	}

	private void initAddressLabel() {
		JLabel addressLabel = new JLabel("Street Address: ", SwingConstants.RIGHT);
		addressLabel.setBounds(75, 145, 45, 35);
		address = new JTextField(20);
		address.setText("");
		address.setBounds(125, 145, 300, 35);
		addressLabel.setFont(new Font("DialogInput", Font.BOLD, 14));
		this.add(address);
		this.add(addressLabel);
	}
	
	private void initCityLabel() {
		JLabel cityLabel = new JLabel("City: ", SwingConstants.RIGHT);
		cityLabel.setBounds(75, 185, 35, 35);
		city = new JTextField(20);
		city.setText("");
		city.setBounds(125, 185, 95, 35);
		cityLabel.setFont(new Font("DialogInput", Font.BOLD, 14));
		this.add(city);
		this.add(cityLabel);
	}
	
	private void initStateLabel() {
		JLabel stateLabel = new JLabel("State: ", SwingConstants.RIGHT);
		state = new JTextField(20);
		state.setBounds(125, 305, 100, 35);
		stateLabel.setFont(new Font("DialogInput", Font.BOLD, 14));
		stateLabel.setBounds(75, 305, 35, 35);
		state.setText("");
		this.add(state);
		this.add(stateLabel);
	}
	
	private void initZipLabel() {
		JLabel zipLabel = new JLabel("Zip: ", SwingConstants.RIGHT);
		zip = new JTextField(20);
		zip.setBounds(125, 225, 95, 35);
		zipLabel.setBounds(75, 225, 35, 35);
		zipLabel.setFont(new Font("DialogInput", Font.BOLD, 14));
		zip.setText("");
		this.add(zip);
		this.add(zipLabel);
	}
	
	private void initEditButton() {
		editButton = new JButton("Edit");
		editButton.setBounds(275, 400, 100, 40);
		editButton.addActionListener(this);
		
		this.add(editButton);
	}
	
	private void initReturnButton() {
		returnButton = new JButton("Return");
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
		name.setText(manager.showName());
		DOB.setText(manager.showDOB());
		phoneNum.setText(manager.showPhoneNum());
		address.setText(manager.showAddress());
		city.setText(manager.showCity());
		state.setText(manager.showState());
		zip.setText(manager.showZip());
		acctNum.setText(String.valueOf(manager.showAcctNum()));
		name.setEditable(false);
		DOB.setEditable(false);
		phoneNum.setEditable(false);
		address.setEditable(false);
		city.setEditable(false);
		state.setEditable(false);
		zip.setEditable(false);
		acctNum.setEditable(false);
	}
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The DepositView class is not serializable.");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();

		if(source.equals(editButton)) {
			try {
				udv.clear();
			manager.switchTo(ATM.UPDUSER_VIEW);
			}
			catch (NullPointerException e2) {
				manager.switchTo(ATM.UPDUSER_VIEW);
			}
		}
		
		if(source.equals(returnButton)) {
			manager.switchTo(ATM.HOME_VIEW);
		}
		
	}
}
