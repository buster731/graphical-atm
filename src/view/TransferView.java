package view;

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
	
	
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The TransferView class is not serializable.");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();

		if(source.equals(confirmButton)) {
			//account balance -= value of transfer field, dest account balance += value
		}
		if(source.equals(cancelButton)) {
			manager.switchTo(ATM.HOME_VIEW);
		}
	}
}