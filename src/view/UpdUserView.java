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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;

@SuppressWarnings("serial")
public class UpdUserView extends JPanel implements ActionListener {
		private ViewManager manager;		// manages interactions between the views, model, and database
		private JButton confirmButton;
		private JButton cancelButton;
		private JPasswordField pinField;
		private JTextField AddressField;
		private JTextField CityField;
		private JComboBox<?> StateField;
		private JTextField ZipField;
		private JLabel address;
		private JLabel errorMessageLabel;		// label for potential error messages
		private JFormattedTextField phoneNumField;
		private JLabel DOB;
		private JLabel name;
		private JLabel acctNum;

		public void updateErrorMessage(String errorMessage) {
			errorMessageLabel.setText(errorMessage);
		}
		
		public void clear() {
			errorMessageLabel.setText("");
			StateField.setSelectedItem(manager.showState());
			pinField.setText(manager.showPin());
			AddressField.setText(manager.showAddress());
			CityField.setText(manager.showCity());
			ZipField.setText(manager.showZip());
			phoneNumField.setText(manager.showPhoneNum());
			name.setText("Name: " + manager.showName());
			acctNum.setText("Account Number: " + manager.showAcctNum());
			
		}
		
		public UpdUserView(ViewManager manager) {
			super();
			
			this.manager = manager;
			initialize();
		}
		
		private void initialize() {
			this.setLayout(null);
			
			initConfirmButton();
			initCancelButton();
			initErrorMessageLabel();
			initPhoneNumField();
			initDOBLabel();
			initPinField();
			initCityField();
			initAddressField();
			initZipField();
			initStateField();
			initAccountNumLabel();
			initNameLabel();
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
		
		private void initPinField() {
			JLabel label = new JLabel("PIN", SwingConstants.RIGHT);
			label.setBounds(100, 110, 95, 35);
			label.setLabelFor(pinField);
			label.setFont(new Font("DialogInput", Font.BOLD, 14));
			
			pinField = new JPasswordField(20);
			pinField.setBounds(205, 105, 200, 35);
			
			this.add(label);
			this.add(pinField);
		}
		
		private void initPhoneNumField() {
			phoneNumField = new JFormattedTextField("(---) ---/----");
			phoneNumField.setBounds(145, 180, 150, 35);
			phoneNumField.setFont(new Font("DialogInput", Font.BOLD, 14));
			
			this.add(phoneNumField);
		}
		
		private void initDOBLabel() {
			//NEED TO FINISH CODING THIS PART!!!! IT NEEDS TO BE LABELS FOR EVERYTHING
				//THE UPDATE THING SHOULD BE SAME AS CREATE VIEW BUT SET TEXT TO OLD VALUES

				DOB = new JLabel("Date of Birth: ", SwingConstants.RIGHT);
				DOB.setBounds(145, 155, 150, 35);
				this.add(DOB);
				
			}
		
		private void initAddressField() {
			address = new JLabel("Street Address: ", SwingConstants.RIGHT);
			address.setBounds(80, 200, 115, 35);
			address.setLabelFor(AddressField);
			address.setFont(new Font("DialogInput", Font.BOLD, 14));
			
			AddressField = new JTextField(20);
			AddressField.setBounds(205, 200, 200, 35);
			
			this.add(address);
			this.add(AddressField);
		}
		
		private void initCityField() {
			JLabel label = new JLabel("City", SwingConstants.RIGHT);
			label.setBounds(100, 245, 95, 35);
			label.setLabelFor(CityField);
			label.setFont(new Font("DialogInput", Font.BOLD, 14));
			
			CityField = new JTextField(20);
			CityField.setBounds(205, 245, 200, 35);
			
			this.add(label);
			this.add(CityField);
		}
		private void initZipField() {
			JLabel label = new JLabel("Zip", SwingConstants.RIGHT);
			label.setBounds(100, 290, 95, 35);
			label.setLabelFor(ZipField);
			label.setFont(new Font("DialogInput", Font.BOLD, 14));
			
			ZipField = new JTextField(20);
			ZipField.setBounds(205, 290, 200, 35);
			
			this.add(label);
			this.add(ZipField);
		}
		
		private void initStateField() {
			String[] states = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
			
			StateField = new JComboBox<Object>(states);
			JLabel label = new JLabel("State", SwingConstants.RIGHT);
			label.setBounds(100, 335, 95, 35);
			label.setLabelFor(StateField);
			label.setFont(new Font("DialogInput", Font.BOLD, 14));
			
			StateField.setBounds(205, 335, 100, 35);
			
			this.add(label);
			this.add(StateField);
		}
		
		private void initConfirmButton() {
			confirmButton = new JButton("Confirm");
			confirmButton.setBounds(275, 400, 100, 40);
			confirmButton.addActionListener(this);
			
			this.add(confirmButton);
		}
		
		private void initCancelButton() {
			cancelButton = new JButton("Cancel");
			cancelButton.setBounds(145, 400, 100, 40);
			cancelButton.addActionListener(this);
			
			this.add(cancelButton);
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

			if(source.equals(confirmButton)) {
					String PhoneStr = phoneNumField.getText();
					long PhoneNum = Long.parseLong(PhoneStr);
					String pinVal =  String.copyValueOf(pinField.getPassword());
					int pinNum = Integer.parseInt(pinVal);
					manager.changeInfo(StateField.getSelectedItem().toString(), CityField.getText(), address.getText(), ZipField.getText(), pinNum, PhoneNum);
					manager.switchTo(ATM.USER_VIEW);
			}
			if(source.equals(cancelButton)) {
				clear();
				manager.switchTo(ATM.USER_VIEW);
			}
			
		}
}
