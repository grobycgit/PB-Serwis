package PBServiceProgram;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PasswordChange extends JDialog {

	private static final long serialVersionUID = -5026087272367301688L;
	
	private final JPanel contentPanel = new JPanel();
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;
	
	private UsersList usersList;
	private UsersDAO usersDAO;

	public PasswordChange(UsersList theUserList, UsersDAO theUsersDAO){
		this();
		usersList = theUserList;
		usersDAO = theUsersDAO;
		
	}
	
	public PasswordChange(UsersDAO theUsersDAO, UsersList theChoosenUser){
		this();
		usersDAO = theUsersDAO;
		usersList = theChoosenUser;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PasswordChange dialog = new PasswordChange();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PasswordChange() {
		setTitle("Zmiana has\u0142a");
		setBounds(100, 100, 450, 180);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblNewLabel = new JLabel("Has\u0142o");
			contentPanel.add(lblNewLabel, "2, 4, right, default");
		}
		{
			txtPassword = new JPasswordField();
			contentPanel.add(txtPassword, "4, 4, 3, 1, fill, default");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Powt\u00F3rz Has\u0142o");
			contentPanel.add(lblNewLabel_1, "2, 6, right, default");
		}
		{
			txtConfirmPassword = new JPasswordField();
			contentPanel.add(txtConfirmPassword, "4, 6, 3, 1, fill, default");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						changePassword();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void changePassword(){
		String password = new String(txtPassword.getPassword());
		String confirmPassword = new String(txtConfirmPassword.getPassword());
		
		
		if(!password.equals(confirmPassword)){
			JOptionPane.showMessageDialog(PasswordChange.this, "Has³a nie pasuj¹ do siebie.", "B³¹d", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			usersList.setPassword(password);
			usersDAO.changePassword(usersList);
			JOptionPane.showMessageDialog(PasswordChange.this, "Has³o zmienione poprawnie.", "Zmiana has³a", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
		}catch(Exception ewq){
			JOptionPane.showMessageDialog(PasswordChange.this, "B³¹d podczas zmiany has³a.", "B³¹d", JOptionPane.ERROR_MESSAGE);
			ewq.printStackTrace();
		}
	}
}
