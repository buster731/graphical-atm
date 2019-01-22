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

@SuppressWarnings("serial")
public class UserView extends JPanel implements ActionListener {
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JButton editButton;
	private JButton returnButton;
	private JLabel errorMessageLabel;
	private JTextField NameField;
	private JComboBox<?> MonthBox;
	private JComboBox<?> DateBox;
	private JComboBox<?> YearBox;
	private JTextField PhoneNumField;
	private JTextField PhoneNumField1;
	private JTextField PhoneNumField2;
	private JTextField AddressField;
	private JTextField CityField;
	private JComboBox<?> StateField;
	private JTextField ZipField;// label for potential error messages
	
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
		initNameField();
		initDOBField();
		initPhoneNumField();
		initAddressField();
		initCityField();
		initStateField();
		initZipField();
	}
	
	private void initNameField() {
		JLabel label = new JLabel("Name: ", SwingConstants.RIGHT);
		label.setBounds(100, 25, 95, 35);
		label.setLabelFor(NameField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		NameField = new JTextField(20);
		NameField.setBounds(205, 25, 200, 35);
		NameField.setText(manager.showName());
		
		this.add(label);
		this.add(NameField);
	}


	private void initDOBField() {
	//NEED TO FINISH CODING THIS PART!!!! IT NEEDS TO BE LABELS FOR EVERYTHING
		//THE UPDATE THING SHOULD BE SAME AS CREATE VIEW BUT SET TEXT TO OLD VALUES

		JLabel DOB = new JLabel("Date of Birth: " + manager.showDOB(), SwingConstants.RIGHT);
		DOB.setBounds(145, 145, 150, 35);
		this.add(DOB);
		
	}
	
	private void initAddressField() {
		JLabel label = new JLabel("Street Address", SwingConstants.RIGHT);
		label.setBounds(80, 185, 115, 35);
		label.setLabelFor(AddressField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		AddressField = new JTextField(20);
		AddressField.setBounds(205, 185, 200, 35);
		
		this.add(label);
		this.add(AddressField);
	}
	
	private void initPhoneNumField() {
		JLabel label = new JLabel("Phone Number", SwingConstants.RIGHT);
		label.setBounds(72, 225, 125, 35);
		JLabel label1 = new JLabel(" - ", SwingConstants.RIGHT);
		label1.setBounds(260, 225, 50, 35);
		JLabel label2 = new JLabel(" - ", SwingConstants.RIGHT);
		label2.setBounds(320, 225, 50, 35);
		label.setLabelFor(PhoneNumField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		label1.setLabelFor(PhoneNumField1);
		label1.setFont(new Font("DialogInput", Font.BOLD, 14));
		label2.setLabelFor(PhoneNumField2);
		label2.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		PhoneNumField = new JTextField(20);
		PhoneNumField.setBounds(209, 225, 40, 35);
		PhoneNumField1 = new JTextField(20);
		PhoneNumField1.setBounds(265, 225, 40, 35);
		PhoneNumField2 = new JTextField(20);
		PhoneNumField2.setBounds(325, 225, 40, 35);
		
		this.add(label);
		this.add(PhoneNumField);
		this.add(PhoneNumField1);
		this.add(PhoneNumField2);
	}
	
	private void initCityField() {
		JLabel label = new JLabel("City", SwingConstants.RIGHT);
		label.setBounds(100, 265, 95, 35);
		label.setLabelFor(CityField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		CityField = new JTextField(20);
		CityField.setBounds(205, 265, 200, 35);
		
		this.add(label);
		this.add(CityField);
	}
	
	private void initStateField() {
		String[] states = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
		
		StateField = new JComboBox<Object>(states);
		JLabel label = new JLabel("State", SwingConstants.RIGHT);
		label.setBounds(100, 305, 95, 35);
		label.setLabelFor(StateField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		StateField.setBounds(205, 305, 100, 35);
		
		this.add(label);
		this.add(StateField);
	}
	
	private void initZipField() {
		JLabel label = new JLabel("Zip", SwingConstants.RIGHT);
		label.setBounds(100, 345, 95, 35);
		label.setLabelFor(ZipField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		ZipField = new JTextField(20);
		ZipField.setBounds(205, 345, 200, 35);
		
		this.add(label);
		this.add(ZipField);
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
	
	
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The DepositView class is not serializable.");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();

		if(source.equals(editButton)) {
			
		}
		
		if(source.equals(returnButton)) {
			manager.switchTo(ATM.UPDUSER_VIEW);
		}
		
	}
}
