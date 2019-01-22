package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;

@SuppressWarnings("serial")
public class WithdrawView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JButton confirmButton;
	private JButton cancelButton;
	private JTextField withdrawField;
	private JLabel errorMessageLabel;		// label for potential error messages

	
	public void updateErrorMessage(String errorMessage) {
		errorMessageLabel.setText(errorMessage);
	}
	
	public void clear() {
		withdrawField.setText("");
		errorMessageLabel.setText("");
	}
	
	public WithdrawView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	private void initialize() {
		this.setLayout(null);
		
		initConfirmButton();
		initCancelButton();
		initWithdrawField();
		initQuestion();
		initErrorMessageLabel();
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
	
	private void initWithdrawField() {
		JLabel withAmt = new JLabel("Withdraw Amount: ", SwingConstants.RIGHT);
		withAmt.setBounds(100, 225, 150, 40);
		withAmt.setLabelFor(withdrawField);
		withAmt.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		withdrawField = new JTextField(20);
		withdrawField.setBounds(255, 225, 100, 40);
		
		this.add(withAmt);
		this.add(withdrawField);
	}
	
	
	private void initQuestion() {
		JLabel much = new JLabel("How much would you like to withdraw?");
		much.setBounds(95, 85, 350, 55); 
		much.setLabelFor(withdrawField);
		much.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(much);
	}
	
	private void initErrorMessageLabel() {
		errorMessageLabel = new JLabel("", SwingConstants.CENTER);
		errorMessageLabel.setBounds(0, 350, 500, 35);
		errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 14));
		errorMessageLabel.setForeground(Color.RED);
		
		this.add(errorMessageLabel);
	}
	
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The WithdrawView class is not serializable.");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();

		if(source.equals(confirmButton)) {
			try{
				
				double amount = Double.parseDouble(withdrawField.getText());
				manager.withdraw(amount);
			
			}
			catch(NumberFormatException e1) {
				updateErrorMessage("Invalid withdraw amount");
				e1.printStackTrace();
			}
		}
		if(source.equals(cancelButton)) {
			manager.switchTo(ATM.HOME_VIEW);
		}
	}
}