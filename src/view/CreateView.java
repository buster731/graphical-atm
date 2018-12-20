package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;

@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JButton confirmButton;
	private JPasswordField pinField;
	private JTextField FNameField;
	private JTextField LNameField;
	private JTextField DOBField;
	private JTextField PhoneNumField;
	private JTextField AddressField;
	private JTextField CityField;
	private JTextField StateField;
	private JTextField ZipField;
	
	
	/**
	 * Constructs an instance (or object) of the CreateView class.
	 * 
	 * @param manager
	 */
	
	public CreateView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the CreateView components.
	 */
	
	private void initialize() {
		this.setLayout(null);
		
		initFNameField();
		initLNameField();
		initPinField();
		initDOBField();
		initPhoneNumField();
		initAddressField();
		initCityField();
		initStateField();
		initZipField();
		initConfirmButton();
		
	}
	
	private void initFNameField() {
		JLabel label = new JLabel("First Name", SwingConstants.RIGHT);
		label.setBounds(100, 25, 95, 35);
		label.setLabelFor(FNameField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		FNameField = new JTextField(20);
		FNameField.setBounds(205, 25, 200, 35);
		
		this.add(label);
		this.add(FNameField);
	}

	private void initLNameField() {
		JLabel label = new JLabel("Last Name", SwingConstants.RIGHT);
		label.setBounds(100, 65, 95, 35);
		label.setLabelFor(LNameField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		LNameField = new JTextField(20);
		LNameField.setBounds(205, 65, 200, 35);
		
		this.add(label);
		this.add(LNameField);
	}
	
	private void initPinField() {
		JLabel label = new JLabel("PIN", SwingConstants.RIGHT);
		label.setBounds(100, 105, 95, 35);
		label.setLabelFor(pinField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		pinField = new JPasswordField(20);
		pinField.setBounds(205, 105, 200, 35);
		
		this.add(label);
		this.add(pinField);
	}
	
	private void initDOBField() {
		JLabel label = new JLabel("Date of Birth", SwingConstants.RIGHT);
		label.setBounds(85, 145, 110, 35);
		label.setLabelFor(DOBField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		DOBField = new JTextField(20);
		DOBField.setBounds(205, 145, 200, 35);
		
		this.add(label);
		this.add(DOBField);
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
		label.setBounds(85, 225, 110, 35);
		label.setLabelFor(PhoneNumField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		PhoneNumField = new JTextField(20);
		PhoneNumField.setBounds(205, 225, 200, 35);
		
		this.add(label);
		this.add(PhoneNumField);
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
		JLabel label = new JLabel("State", SwingConstants.RIGHT);
		label.setBounds(100, 305, 95, 35);
		label.setLabelFor(StateField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		StateField = new JTextField(20);
		StateField.setBounds(205, 305, 200, 35);
		
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
	
	private void initConfirmButton() {
		confirmButton = new JButton("Confirm");
		confirmButton.setBounds(225, 400, 100, 40);
		confirmButton.addActionListener(this);
		
		this.add(confirmButton);
	}
	
	/*
	 * CreateView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The CreateView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the CreateView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if(source.equals(confirmButton)) {
			//manager.createAccount(FNameField.getText(), LNameField.getText(), pinField.getPassword(), DOBField.getText(), PhoneNumField.getText(), AddressField.getText(), CityField.getText(), StateField.getText(), ZipField.getText());
			//this needs to be reformatted as a string (account) to be passed into view manager to insert into db
		}
		// TODO
		//
		// this is where you'll setup your action listener, which is responsible for
		// responding to actions the user might take in this view (an action can be a
		// user clicking a button, typing in a textfield, etc.).
		//
		// feel free to use my action listener in LoginView.java as an example.
	}
}