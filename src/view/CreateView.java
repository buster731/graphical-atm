package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;

@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JButton confirmButton;
	private JButton cancelButton;
	private JPasswordField pinField;
	private JTextField FNameField;
	private JTextField LNameField;
	private JComboBox<?> MonthField;
	private JComboBox<?> DateField;
	private JComboBox<?> YearField;
	private JTextField PhoneNumField;
	private JTextField PhoneNumField1;
	private JTextField PhoneNumField2;
	private JTextField AddressField;
	private JTextField CityField;
	private JComboBox<?> StateField;
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
		initCancelButton();
		
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
		String[] dates = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		String[] years = {"2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961",  "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900"};
		JComboBox<?> MonthField = new JComboBox<Object>(months);
		MonthField.setBounds(205, 145, 50, 35);
		JLabel DOBM = new JLabel("Date of Birth: M: ", SwingConstants.RIGHT);
		DOBM.setLabelFor(MonthField);
		DOBM.setBounds(55, 145, 150, 35);
		DOBM.setFont(new Font("DialogInput", Font.BOLD, 14));
		JComboBox<?> DateField = new JComboBox<Object>(dates);
		DateField.setBounds(280, 145, 50, 35);
		JLabel DOBD = new JLabel(" D: ", SwingConstants.RIGHT);
		DOBD.setLabelFor(DateField);
		DOBD.setBounds(250, 145, 35, 35);
		DOBD.setFont(new Font("DialogInput", Font.BOLD, 14));
		JComboBox<?> YearField = new JComboBox<Object>(years);
		YearField.setBounds(365, 145, 65, 35);
		JLabel DOBY = new JLabel(" Y: ", SwingConstants.RIGHT);
		DOBY.setBounds(330, 145, 35, 35);
		DOBY.setLabelFor(YearField);
		DOBY.setFont(new Font("DialogInput", Font.BOLD, 14));
		this.add(DOBM);
		this.add(MonthField);
		this.add(DOBD);
		this.add(DateField);
		this.add(DOBY);
		this.add(YearField);
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
		String[] states = {"AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
		
		JComboBox<?> StateField = new JComboBox<Object>(states);
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
		//	String acctStr = ( pinField.getText() + FNameField.getText() + LNameField.getText() + )
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