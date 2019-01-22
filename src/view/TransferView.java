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
public class TransferView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JButton confirmButton;
	private JButton cancelButton;
	private JTextField transferField;
	private JTextField destAcctField;
	private JLabel errorMessageLabel;		// label for potential error messages

	
	public void updateErrorMessage(String errorMessage) {
		errorMessageLabel.setText(errorMessage);
	}
	
	public void clear() {
		transferField.setText("");
		errorMessageLabel.setText("");
	}
	
	public TransferView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	private void initialize() {
		this.setLayout(null);
		
		initConfirmButton();
		initCancelButton();
		initTransferField();
		initDestAcctField();
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
	
	private void initTransferField() {
		JLabel tranAmt = new JLabel("Transfer Amount: ", SwingConstants.RIGHT);
		tranAmt.setBounds(100, 150, 150, 40);
		tranAmt.setLabelFor(transferField);
		tranAmt.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		transferField = new JTextField(20);
		transferField.setBounds(255, 150, 100, 40);
		
		this.add(tranAmt);
		this.add(transferField);
	}
	
	private void initDestAcctField() {
		JLabel destAcct = new JLabel("Receiver Account Number: ", SwingConstants.RIGHT);
		destAcct.setBounds(50, 220, 200, 40);
		destAcct.setLabelFor(destAcctField);
		destAcct.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		destAcctField = new JTextField(20);
		destAcctField.setBounds(255, 220, 100, 40);
		
		this.add(destAcct);
		this.add(destAcctField);
	}
	
	private void initQuestion() {
		JLabel much = new JLabel("How much would you like to transfer and to what account?");
		much.setBounds(95, 85, 350, 55); 
		much.setLabelFor(transferField);
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
		throw new IOException("ERROR: The TransferView class is not serializable.");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();

		if(source.equals(confirmButton)) {
			long tranAcct = Long.parseLong(destAcctField.getText());
			double tranBal = Double.parseDouble(transferField.getText());
			manager.transfer(tranAcct, tranBal);
		}
		if(source.equals(cancelButton)) {
			manager.switchTo(ATM.HOME_VIEW);
		}
	}
}