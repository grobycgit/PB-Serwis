package PBServiceProgram;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import javax.swing.JScrollPane;

public class NewNotif extends JFrame {

	private static final long serialVersionUID = -7044426323723968449L;
	
	private JPanel contentPane;
	private JTextField txtOwner;
	private JTextField txtContact;
	private JTextField txtAddress;
	private JLabel lblKontakt;
	private JLabel lblAddress;
	private JSeparator separator;
	private JLabel lblProduct;
	private JTextField txtProduct;
	private JTextField txtSerial;
	private JLabel lblSerial;
	private JLabel lblAccessories;
	private JLabel lblSellData;
	private JTextField txtSellDate;
	private JSeparator separator_1;
	private JLabel lblDescription;
	private JTextField txtPrice;
	private JSeparator separator_2;
	private JLabel lblWarranty;
	private JComboBox<UsersList> cmbEmployee;
	private JComboBox<NotificationsList> cmbStatus;
	private JLabel lblEmployee;
	private JLabel lvlStatus;
	private JButton btnSend;
	private JSeparator separator_3;
	private JTextField txtAccessories;
	private JButton btnSave;
	private JComboBox cmbSend;
	private JButton btnCancel;
	private JTextArea txtDescription;
	private JTextField txtFinishDate;
	
	private MainProgram main;
	private UsersDAO usersDAO;
	private UsersList choosenUser;
	private NotificationsList tempNotificationsList = null;
	private NotificationsList previousNotificationList = null;
	private boolean updateMode = false;
	private JComboBox cmbWarranty;
	private JTextField txtStartDate;
	private JTextArea txtService;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTextArea txtDamages;
	private JTextField txtRecommendations;
	

	/**
	 * Launch the application.
	 */
	public NewNotif(MainProgram theMainProgram, UsersDAO theUsersDAO, NotificationsList thePreciousNotif, boolean theUpdateMode, UsersList theChoosenUser){
		this();
		usersDAO = theUsersDAO;
		main = theMainProgram;
		previousNotificationList = thePreciousNotif;
		updateMode = theUpdateMode;
		choosenUser = theChoosenUser;
		populateUsers();
		if (updateMode){
			setTitle("Edytowanie zg³oszenia");
			
			populateGui(previousNotificationList);
		}
		
	}
	
	public NewNotif(MainProgram theMainProgram, UsersDAO theUsersDAO, UsersList theChoosenUser){
		this();
		main = theMainProgram;
		usersDAO = theUsersDAO;
		choosenUser = theChoosenUser;
		populateUsers();
	} 
	
	private void populateUsers(){
		try{
			UsersDAO usersSelect = new UsersDAO();
			ArrayList<UsersList> users = usersSelect.getAllUsers();
			cmbEmployee.setModel(new DefaultComboBoxModel(users.toArray(new UsersList[0])));
		} catch(Exception ew){
			ew.printStackTrace();
		}
	}
	
	private void populateGui (NotificationsList theNotificationsList){
		
		txtStartDate.setText(theNotificationsList.getStartDate());
		txtFinishDate.setText(theNotificationsList.getFinishDate());
		txtOwner.setText(theNotificationsList.getOwner());
		txtContact.setText(theNotificationsList.getContact());
		txtAddress.setText(theNotificationsList.getAddress());
		txtProduct.setText(theNotificationsList.getProduct());
		txtSerial.setText(theNotificationsList.getSerial());
		txtAccessories.setText(theNotificationsList.getAccessories());
		txtSellDate.setText(theNotificationsList.getSellDate());
		txtDescription.setText(theNotificationsList.getDescription());
		txtService.setText(theNotificationsList.getService());
		txtDamages.setText(theNotificationsList.getDamages());
		txtRecommendations.setText(theNotificationsList.getRecommendations());
		txtPrice.setText(theNotificationsList.getPrice());
		cmbEmployee.setSelectedItem(theNotificationsList.getEmployee());
		cmbWarranty.setSelectedItem(theNotificationsList.getWarranty());
		cmbStatus.setSelectedItem(theNotificationsList.getStatus());
		
		try{
			int id = 999;
			String selectedUser = theNotificationsList.getEmployee();
			String splited[] = selectedUser.split(" ");
			
			String first ="";
			String last ="";
			for(int q=0; q<1;q++){
				first = splited[0]; 
				last = splited[1];
			}
			
			UsersList snif = new UsersList(first, last);
			
			UsersDAO dao = new UsersDAO();
			ArrayList<UsersList> users = dao.getAllUsers();

			for(UsersList w: users){
				if(snif.getFirstName().contains((w.getFirstName()))&&snif.getLastName().contains(w.getLastName())){
					id = users.indexOf(w);
					cmbEmployee.setSelectedIndex(id);
				}
			}
		}catch (Exception e){
			
		}
			
//		try{
//			int id = 999;
//			String selectedUser = theNotificationsList.getEmployee();
//			String splited[] = selectedUser.split(" ");
//			
//			String first ="";
//			String last ="";
//			for(int q=0; q<1;q++){
//				first = splited[0]; 
//				last = splited[1];
//			}
//			
//			UsersList snif = new UsersList(first, last);
//			ArrayList<UsersList> usersik = new ArrayList<>();
//			usersik.add(snif);
//			
//			UsersDAO dao = new UsersDAO();
//			ArrayList<UsersList> users = dao.getAllUsers();
//			System.out.println(users);
//			UsersList www = users.get(2);
//			if (users.contains(first)){
//				
//			}
//			
//			for (UsersList w: users){
////				ArrayList<String> kim = addAll(UsersList users);
//			}
//			
//			for (int i=0; i < users.size(); i++){
//				if (users.get(i).equals(selectedUser)){
//					id = users.indexOf(i);
//				}
//			}
//			cmbEmployee.setSelectedItem(www);
//			
//			
//		} catch(Exception ew){
//			
//		}
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewNotif frame = new NewNotif();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewNotif() {
		setTitle("Nowe Zg\u0142oszenie");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(730, 800);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblOwner = new JLabel("W\u0142a\u015Bciciel");
		
		txtOwner = new JTextField();
		txtOwner.setColumns(10);
		
		txtContact = new JTextField();
		txtContact.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		
		lblKontakt = new JLabel("Kontakt");
		
		lblAddress = new JLabel("Adres");
		
		separator = new JSeparator();
		
		lblProduct = new JLabel("Dotyczy");
		
		txtProduct = new JTextField();
		txtProduct.setColumns(10);
		
		txtSerial = new JTextField();
		txtSerial.setColumns(10);
		
		lblSerial = new JLabel("Numer Seryjny");
		
		lblAccessories = new JLabel("Wyposa\u017Cenie");
		
		lblSellData = new JLabel("Data Sprzeda\u017Cy (rok/miesi¹c/dzieñ)");
		
		txtSellDate = new JTextField();
		txtSellDate.setName("");
		txtSellDate.setColumns(10);
		
		separator_1 = new JSeparator();
		
		lblDescription = new JLabel("Opis Usterki");
		
		JLabel lblPrice = new JLabel("\u0141\u0105czna wycena");
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		
		separator_2 = new JSeparator();
		
		lblWarranty = new JLabel("Rodzaj Serwisu");
		
		String[] choiceWarranty = {"Pozagwarancyjny", "Gwarancyjny"};
		cmbWarranty = new JComboBox(choiceWarranty);
		
		
		cmbEmployee = new JComboBox();
		
//		Type safetry: The expression of type JComboBox needs unchecked conversion to confirm to JComboBox
		String[] choiceStatus = {"Realizowany", "Zakoñczony"};
		cmbStatus = new JComboBox(choiceStatus);
		
		lblEmployee = new JLabel("Pracownik");
		
		lvlStatus = new JLabel("Status zg\u0142oszenia");
		
		JButton btnSavePrint = new JButton("Zapisz/Drukuj");
		btnSavePrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveNotifications(choosenUser);
				
				NotificationsList lastNotif = tempNotificationsList;
				
				try {
					lastNotif = usersDAO.getLastNotif();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try{
				PrintNotification print = new PrintNotification(main, lastNotif);
				print.print();
				} catch (Exception w){
					
				}
			}
		});
		
		btnSend = new JButton("Wy\u015Blij");
		btnSend.setVisible(false);
		
		separator_3 = new JSeparator();
		
		txtAccessories = new JTextField();
		txtAccessories.setColumns(10);
		
		btnSave = new JButton("Zapisz");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveNotifications(choosenUser);
			}
		});
		
		cmbSend = new JComboBox();
		cmbSend.setVisible(false);
		
		btnCancel = new JButton("Anuluj");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		txtFinishDate = new JTextField();
		txtFinishDate.setVisible(false);
		txtFinishDate.setColumns(10);
		
		txtStartDate = new JTextField("");
		txtStartDate.setVisible(false);
		txtStartDate.setColumns(10);
		
		JLabel lblService = new JLabel("Wykonane czynno\u015Bci");
		
		scrollPane = new JScrollPane();
		
		scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JLabel lblDamages = new JLabel("Stwierdzone uszkodzenia");
		
		txtRecommendations = new JTextField();
		txtRecommendations.setColumns(10);
		
		JLabel lblZalecenia = new JLabel("Zalecenia");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtOwner, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblOwner))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtContact, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblKontakt))
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblAddress)
										.addComponent(txtAddress, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)))
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblProduct)
										.addComponent(txtProduct, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE))
									.addGap(28)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSerial)
										.addComponent(txtSerial, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblAccessories)
										.addComponent(txtAccessories, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSellData)
										.addComponent(txtSellDate)))
								.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
								.addComponent(lblDescription)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(91)
							.addComponent(txtFinishDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtStartDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnSavePrint)
							.addGap(18)
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(cmbSend, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 683, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(cmbWarranty, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblWarranty))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(cmbEmployee, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmployee))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lvlStatus)
								.addComponent(cmbStatus, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 683, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblService)
								.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPrice))
							.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblZalecenia)
								.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
								.addComponent(txtRecommendations)
								.addComponent(lblDamages, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOwner)
						.addComponent(lblKontakt)
						.addComponent(lblAddress))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtOwner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtContact, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduct)
						.addComponent(lblSerial))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtProduct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSerial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAccessories)
						.addComponent(lblSellData))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtSellDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAccessories, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDescription)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblService)
						.addComponent(lblDamages))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPrice)
								.addComponent(lblZalecenia)))
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRecommendations, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lvlStatus)
						.addComponent(lblEmployee)
						.addComponent(lblWarranty))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cmbWarranty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbEmployee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSavePrint)
						.addComponent(btnSave)
						.addComponent(cmbSend, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSend)
						.addComponent(btnCancel))
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFinishDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtStartDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		txtDamages = new JTextArea();
		txtDamages.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_2.setViewportView(txtDamages);
		
		txtService = new JTextArea();
		scrollPane_1.setViewportView(txtService);
		txtService.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		txtDescription = new JTextArea();
		scrollPane.setViewportView(txtDescription);
		txtDescription.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.setLayout(gl_contentPane);
	}

	protected void saveNotifications(UsersList choosenUser) {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");
		
		String startDate = dateOnly.format(cal.getTime());
		String finishDate = txtFinishDate.getText();
		String owner = txtOwner.getText();
		String contact = txtContact.getText();
		String address = txtAddress.getText();
		String product = txtProduct.getText();
		String serial = txtSerial.getText();
		String accessories = txtAccessories.getText();
		String sellDate = txtSellDate.getText();
		String price = txtPrice.getText();
		String description = txtDescription.getText();
		String service = txtService.getText();
		String damages = txtDamages.getText();
		String recommendations = txtRecommendations.getText();
		String warranty=cmbWarranty.getSelectedItem().toString();
		String adder=choosenUser.getFirstName() + " " + choosenUser.getLastName();
		String employee=cmbEmployee.getSelectedItem().toString();
		String status=cmbStatus.getSelectedItem().toString();
		
		
		if(updateMode){
			
			tempNotificationsList = previousNotificationList;
			
			tempNotificationsList.setOwner(owner);
			tempNotificationsList.setContact(contact);
			tempNotificationsList.setAddress(address);
			tempNotificationsList.setProduct(product);
			tempNotificationsList.setSerial(serial);
			tempNotificationsList.setAccessories(accessories);
			tempNotificationsList.setSellDate(sellDate);
			tempNotificationsList.setDescription(description);
			tempNotificationsList.setService(service);
			tempNotificationsList.setDamages(damages);
			tempNotificationsList.setRecommendations(recommendations);
			tempNotificationsList.setPrice(price);
			tempNotificationsList.setWarranty(warranty);
			tempNotificationsList.setEmployee(employee);
			tempNotificationsList.setStatus(status);
			tempNotificationsList.setFinishDate(finishDate);
			
			
			
			if(!status.equals("Zakoñczony")){
				tempNotificationsList.setFinishDate("");
			}			
			String[] options ={"Tak", "Nie"};
			if(!finishDate.equals("")&&status.equals("Zakoñczony")){
				int response = JOptionPane.showOptionDialog(NewNotif.this, "Czy zmieniæ datê zakoñczenia zg³oszenia", "Zmiana daty zakoñczenia zg³oszenia",  JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, null);
				if(response ==JOptionPane.YES_OPTION){
					tempNotificationsList.setFinishDate(dateOnly.format(cal.getTime()));
				}
			}
			if(finishDate.equals("")&&status.equals("Zakoñczony")){
				tempNotificationsList.setFinishDate(dateOnly.format(cal.getTime()));
			}
			
			
			
		} else{
			tempNotificationsList = new NotificationsList(startDate, "", owner, contact, address, product,
					serial, accessories, sellDate, price, description, service, damages, 
					recommendations, warranty, adder, employee, status);
		}
			System.out.println(tempNotificationsList);
		try{
			if(updateMode){
				usersDAO.editNotification(tempNotificationsList);
			}else{
				usersDAO.addNotification(tempNotificationsList);
			}
			
			setVisible(false);
			dispose();
			main.refreshNotificationsView();
			
//			if(updateMode){
//				JOptionPane.showMessageDialog(main,"Zg³oszenie zedytowane poprawnie", "Zg³oszenie zedytowane",JOptionPane.INFORMATION_MESSAGE);
//			}else{
//				JOptionPane.showMessageDialog(main,"Zg³oszenie dodane poprawnie", "Zg³oszenie dodane",JOptionPane.INFORMATION_MESSAGE);
//			}
			
		} catch (Exception oups) {
			JOptionPane.showMessageDialog(main, "B³¹d w dodawaniu zg³oszenia: " + oups.getMessage(), "B³¹d", JOptionPane.ERROR_MESSAGE);
			System.out.println(oups.getMessage());
			oups.printStackTrace();
		}
	}
	
//	protected void printNotifications(){
//		try{
//		FileInputStream file = new FileInputStream(new File("C:/PBSerwis/serwis.xls"));
//		if (file==null) file.close();
//		
//		HSSFWorkbook workbook = new HSSFWorkbook(file);
//		HSSFSheet sheet = workbook.getSheetAt(0);
//		Cell cell = null;
//		
//		cell = sheet.getRow(7).getCell(4);
//	    cell.setCellValue(txtStartDate.getText());
//		cell = sheet.getRow(12).getCell(1);
//	    cell.setCellValue(txtOwner.getText());
//	    cell = sheet.getRow(13).getCell(1);
//	    cell.setCellValue(txtAddress.getText());
//	    cell = sheet.getRow(14).getCell(1);
//	    cell.setCellValue(txtContact.getText());
//	    cell = sheet.getRow(16).getCell(1);
//	    cell.setCellValue(txtProduct.getText());
//	    cell = sheet.getRow(17).getCell(1);
//	    cell.setCellValue(txtSerial.getText());
//	    cell = sheet.getRow(18).getCell(1);
//	    cell.setCellValue(txtSellDate.getText());
//	    cell = sheet.getRow(19).getCell(1);
//	    cell.setCellValue(txtAccessories.getText());
//	    cell = sheet.getRow(21).getCell(1);
//	    cell.setCellValue(txtDescription.getText());
//	    cell = sheet.getRow(23).getCell(1);
//	    String warranty =(String) cmbWarranty.getSelectedItem();
//	    cell.setCellValue(warranty);
//	    
//	     
//	    file.close();
//
//        FileOutputStream outFile =new FileOutputStream(new File("C:/PBSerwis/serwis.xls"));
//        workbook.write(outFile);
//        outFile.close();
//		workbook.close();
//		
//		Runtime.getRuntime().exec("cmd /c start excel.exe C:\\PBSerwis\\Serwis.xls");
//		
//		}catch(FileNotFoundException e){
//			JOptionPane.showMessageDialog(main, "Nie mo¿na otworzyæ pliku z kilku powodów: \n- Plik jest otworzony.\n- Nie znaleziono pliku serwis.xls w katalogu C:\\PBSerwis.", "B³¹d", JOptionPane.ERROR_MESSAGE);
//		}catch(IOException e){
//			e.printStackTrace();
//		}
//	}
}
