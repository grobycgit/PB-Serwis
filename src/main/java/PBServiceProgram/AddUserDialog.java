package PBServiceProgram;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

public class AddUserDialog extends JDialog {

	private static final long serialVersionUID = 3382381232874948990L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JComboBox<UsersList> cmbPermission;
	private JTextField emailTextField;
	

	private UsersDAO usersDAO;
	private Users users;
	private UsersList previousUserList = null;
	private boolean updateMode = false;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;
	private JLabel lblPowt;
	private JLabel lblHaso;

	
	public AddUserDialog(Users theUsers, UsersDAO theUsersDAO, UsersList thePreviousUser, boolean theUpdateMode){
		this();
		usersDAO = theUsersDAO;
		users = theUsers;
		previousUserList = thePreviousUser;
		updateMode = theUpdateMode;
		
		if (updateMode){
			setTitle("Edytowanie u¿ytkownika");
			
			populateGui(previousUserList);
			hidePassword();
		}
	}
	
	public AddUserDialog(Users theUsers, UsersDAO theUsersDAO) {
		this(theUsers, theUsersDAO, null, false);

	}
	private void populateGui(UsersList theUserList){
		firstNameTextField.setText(theUserList.getFirstName());
		lastNameTextField.setText(theUserList.getLastName());
		cmbPermission.setSelectedItem(theUserList.getPermission());
		emailTextField.setText(theUserList.getEmail());
	}
	
	public static void main(String[] args) {
		try {
			AddUserDialog dialog = new AddUserDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddUserDialog() {
		setTitle("Dodaj U\u017Cytkownika");
		setBounds(100, 100, 450, 258);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblImi = new JLabel("Imi\u0119");
			contentPanel.add(lblImi, "2, 2, right, default");
		}
		{
			firstNameTextField = new JTextField();
			contentPanel.add(firstNameTextField, "4, 2, fill, default");
			firstNameTextField.setColumns(10);
		}
		{
			JLabel lblNazwisko = new JLabel("Nazwisko");
			contentPanel.add(lblNazwisko, "2, 4, right, default");
		}
		{
			lastNameTextField = new JTextField();
			contentPanel.add(lastNameTextField, "4, 4, fill, default");
			lastNameTextField.setColumns(10);
		}
		{
			JLabel lblUprawnienia = new JLabel("Uprawnienia");
			contentPanel.add(lblUprawnienia, "2, 6, right, default");
		}
		{	String[] choicePermission = {"Administrator", "U¿ytkownik"};
			cmbPermission = new JComboBox(choicePermission);
			contentPanel.add(cmbPermission, "4, 6, fill, default");
		}
		{
			JLabel lblEmail = new JLabel("E-mail");
			contentPanel.add(lblEmail, "2, 8, right, default");
		}
		{
			emailTextField = new JTextField();
			contentPanel.add(emailTextField, "4, 8, fill, default");
			emailTextField.setColumns(10);
		}
		{
			lblHaso = new JLabel("Has\u0142o");
			contentPanel.add(lblHaso, "2, 10, right, default");
		}
		{
			txtPassword = new JPasswordField();
			contentPanel.add(txtPassword, "4, 10, fill, default");
		}
		{
			lblPowt = new JLabel("Powt\u00F3rz Has\u0142o");
			contentPanel.add(lblPowt, "2, 12, right, default");
		}
		{
			txtConfirmPassword = new JPasswordField();
			contentPanel.add(txtConfirmPassword, "4, 12, fill, default");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Zapisz");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						saveUser();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Anuluj");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void saveUser() {
		String firstName = firstNameTextField.getText();
		String lastName = lastNameTextField.getText();
		String permission=cmbPermission.getSelectedItem().toString();
		String email = emailTextField.getText();
		
		UsersList tempUserList = null;
		
		if(updateMode){
			
			tempUserList = previousUserList;
			
			tempUserList.setFirstName(firstName);
			tempUserList.setLastName(lastName);
			tempUserList.setPermission(permission);
			tempUserList.setEmail(email);
		} else{
			String password = new String(txtPassword.getPassword());
			String confirmPassword = new String(txtConfirmPassword.getPassword());
			
			if(!password.equals(confirmPassword)){
				JOptionPane.showMessageDialog(this, "Has³a nie pasuj¹ do siebie.", "B³¹d", JOptionPane.ERROR_MESSAGE);
				return;
			}
			tempUserList = new UsersList(firstName, lastName, permission, email);
			System.out.println(tempUserList);
		}
		
		try{
			if(updateMode){
				usersDAO.editUser(tempUserList);
			}else{
				usersDAO.addUser(tempUserList);
			}
			setVisible(false);
			dispose();
			users.refreshUsersView();
			if(updateMode){
				JOptionPane.showMessageDialog(users,"U¿ytkownik zedytowany popranie", "U¿ytkownik zedytowany",JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(users,"U¿ytkownik dodany popranie", "U¿ytkownik Dodany",JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (Exception oups) {
			JOptionPane.showMessageDialog(users, "B³¹d w dodawaniu u¿ytkownika: " + oups.getMessage(), "B³¹d", JOptionPane.ERROR_MESSAGE);
			System.out.println(oups.getMessage());
		}
	}
	
	private void hidePassword(){
		
		lblHaso.setVisible(false);;
		txtPassword.setVisible(false);
		
		lblPowt.setVisible(false);
		txtConfirmPassword.setVisible(false);
	}
}
