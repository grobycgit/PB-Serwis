package PBServiceProgram;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class LoginDialog extends JDialog {

	private static final long serialVersionUID = 3151091724807225509L;
	
	private final JPanel contentPanel = new JPanel();
	private UsersDAO usersDAO;
	private JComboBox<UsersList> userComboBox;
	private JPasswordField passwordField;
	private int login;

	/**
	 * Launch the application.
	 */
	
	public void setUserDAO(UsersDAO theUsersDAO) {
		usersDAO = theUsersDAO;
	}
	
	public static void main(String[] args) {
		try {
			LoginDialog dialog = new LoginDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public void populateUsers(ArrayList<UsersList> usersList){
		
		
		try{
		Properties props = new Properties();
		props.load(new FileInputStream("C:/PBSerwis/login.properties"));
		
		login = Integer.parseInt(props.getProperty("login"));
		
		
		} catch(Exception s){
			s.printStackTrace();
		}
		
		
		userComboBox.setModel(new DefaultComboBoxModel(usersList.toArray(new UsersList[0])));
		if(login != 999){
		userComboBox.setSelectedIndex(login);
		}
	}
	
	public LoginDialog() {
		setAlwaysOnTop(true);
		setTitle("Logowanie");
		setSize(281, 147);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);
		
		
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblUytkownik = new JLabel("U\u017Cytkownik");
			contentPanel.add(lblUytkownik, "2, 2, right, default");
		}
		{

			userComboBox = new JComboBox();
			contentPanel.add(userComboBox, "4, 2, fill, default");
		}
		{
			JLabel lblHaso = new JLabel("Has\u0142o");
			contentPanel.add(lblHaso, "2, 4, right, default");
		}
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField, "4, 4, fill, default");
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Zaloguj");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						performUserLogin();
					}
				});
				okButton.setActionCommand("OK");
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
	private void performUserLogin(){
		try{
			if (userComboBox.getSelectedIndex() == -1){
				JOptionPane.showMessageDialog(LoginDialog.this, "Musisz wybraæ u¿ytkownika", "B³¹d logowania", JOptionPane.ERROR_MESSAGE);
				return;
			}
			UsersList theUser = (UsersList) userComboBox.getSelectedItem();
			int userId = (userComboBox.getSelectedIndex())-1;
			
			Properties props = new Properties();
			OutputStream output = null;
			
			try{
				output = new FileOutputStream("C:/PBSerwis/login.properties");
				
				props.setProperty("login", String.valueOf(userId + 1));
				
				props.store(output, null);
			
			} catch(Exception s){
				s.printStackTrace();
			}
			
			String plainTextPassword = new String(passwordField.getPassword());
			theUser.setPassword(plainTextPassword);

			boolean isValidPassword = usersDAO.authenticate(theUser);
			
			if(isValidPassword){
				setVisible(false);
				
				MainProgram frame = new MainProgram(theUser);
				frame.setLoggedInUserName();
				
				frame.setVisible(true);
			} else{
				JOptionPane.showMessageDialog(this, "Nieprawid³owe has³o", "Nieprawid³owe has³o", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		} catch (Exception ico){
			JOptionPane.showMessageDialog(LoginDialog.this, "B³¹d logowania: " + ico, "B³¹d logwania", JOptionPane.ERROR_MESSAGE);
		}
	}
}
