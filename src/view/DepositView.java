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
public class DepositView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JButton confirmButton;
	private JButton cancelButton;
	private JTextField depositField;
	private JLabel question;
	private JLabel success;
	private JLabel fail;
	
	public DepositView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	private void initialize() {
		this.setLayout(null);
		
		initConfirmButton();
		initCancelButton();
		initDepositField();
		initQuestion();
	
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
	
	private void initDepositField() {
		JLabel depAmt = new JLabel("Deposit Amount: ", SwingConstants.RIGHT);
		depAmt.setBounds(100, 225, 130, 40);
		depAmt.setLabelFor(depositField);
		depAmt.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		depositField = new JTextField(20);
		depositField.setBounds(235, 225, 150, 40);
		
		this.add(depAmt);
		this.add(depositField);
	}
	
	private void initQuestion() {
		JLabel much = new JLabel("How much would you like to deposit?");
		much.setBounds(95, 85, 350, 55); 
		much.setLabelFor(depositField);
		much.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		this.add(much);
	}
	
	/*private void initSuccess() {
		JLabel success = new JLabel("Deposit successful. Your new balance is: " + manager.showBal());
		success.setBounds(75, 435, 350, 65);
	}
	*/
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The DepositView class is not serializable.");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();

		if(source.equals(confirmButton)) {
			double amount = Double.parseDouble(depositField.getText());
			int worked = manager.deposit(amount);
			if(worked == 3) {
				//initSuccess();
			}
			else{
				//initError();
			}
			//account balance += value of deposit field
		}
		if(source.equals(cancelButton)) {
			manager.switchTo(ATM.HOME_VIEW);
		}
	}
}