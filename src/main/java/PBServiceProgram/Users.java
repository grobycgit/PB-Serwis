package PBServiceProgram;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class Users extends JFrame {


	private static final long serialVersionUID = -1886358019107175850L;
	
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tblUsers;
	private MainProgram main;
	private UsersDAO usersDAO;
	private JButton btnDodjaUytkownika;
	private JButton btnAnuluj;
	private JButton btnEdytujUytkownika;
	private JButton btnKasowanieUytkownika;
	private JButton btnZmianaHasa;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Users frame = new Users();
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
	public Users() {
		
		try{
			usersDAO = new UsersDAO();
		} catch (Exception exc){
			JOptionPane.showMessageDialog(this, "B³¹d: "+ exc, "B³¹d", JOptionPane.ERROR_MESSAGE);
		}
		
		setTitle("U\u017Cytkownicy");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setSize(572,300);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnDodjaUytkownika = new JButton("Dodaj");
		btnDodjaUytkownika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddUserDialog dialog= new AddUserDialog(Users.this, usersDAO);
				dialog.setVisible(true);
			}
		});
		panel.add(btnDodjaUytkownika);
		
		btnEdytujUytkownika = new JButton("Edytuj");
		btnEdytujUytkownika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tblUsers.getSelectedRow();
				if(row<0){
					JOptionPane.showMessageDialog(Users.this, "Musisz zaznaczyæ u¿ytkownika", "B³¹d", JOptionPane.ERROR_MESSAGE);
					return;
				}
				UsersList tempUserList = (UsersList) tblUsers.getValueAt(row, UsersTableModel.OBJECT_COL);
				AddUserDialog dialog = new AddUserDialog(Users.this, usersDAO, tempUserList, true);
				dialog.setVisible(true);
			}
		});
		panel.add(btnEdytujUytkownika);
		
		btnAnuluj = new JButton("Anuluj");
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnKasowanieUytkownika = new JButton("Kasowanie");
		btnKasowanieUytkownika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int row = tblUsers.getSelectedRow();
					if (row<0){
						JOptionPane.showMessageDialog(Users.this, "Musisz zaznaczyæ u¿ytkownika", "B³¹d", JOptionPane.ERROR_MESSAGE);
						return;
					} 
					
					UsersList tempUserList = (UsersList) tblUsers.getValueAt(row, UsersTableModel.OBJECT_COL);
					if(tempUserList.getUserId()==1){
						JOptionPane.showMessageDialog(Users.this, "Nie mo¿na usun¹æ konta szefa :).", "B³¹d", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					int response = JOptionPane.showConfirmDialog(Users.this, "Usun¹æ tego u¿ytkownika?", "Usuwanie u¿ytownika",  JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if(response !=JOptionPane.YES_OPTION){
						return;
					}
					
					usersDAO.deleteUser(tempUserList.getUserId());
					refreshUsersView();
					JOptionPane.showMessageDialog(Users.this, "U¿ytkownik usuniêty.", "U¿ytkownik usuniêty", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception daf){
					JOptionPane.showMessageDialog(Users.this, "B³¹d usuwania u¿ytkownika: " + daf.getMessage(), "B³¹d", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnKasowanieUytkownika);
		
		btnZmianaHasa = new JButton("Zmiana Has\u0142a");
		btnZmianaHasa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = tblUsers.getSelectedRow();
				if(row<0){
					JOptionPane.showMessageDialog(Users.this, "Musisz zaznaczyæ u¿ytkownika", "B³¹d", JOptionPane.ERROR_MESSAGE);
					return;
				}
				changePassword();
			}
		});
		panel.add(btnZmianaHasa);
		panel.add(btnAnuluj);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tblUsers = new JTable();
		scrollPane.setViewportView(tblUsers);
		
		try{
			ArrayList<UsersList> disUsers = null;
				disUsers = usersDAO.getAllUsers();
					
				UsersTableModel model = new UsersTableModel(disUsers);
				tblUsers.setModel(model);
			} catch (Exception wert){
				JOptionPane.showMessageDialog(Users.this, "B³¹d: " + wert, "B³¹d", JOptionPane.ERROR_MESSAGE);
			}
		
	}

	public void refreshUsersView() {
		try{
			ArrayList<UsersList> disUsers = null;
				disUsers = usersDAO.getAllUsers();
					
				UsersTableModel model = new UsersTableModel(disUsers);
				tblUsers.setModel(model);
			} catch (Exception wert){
				JOptionPane.showMessageDialog(Users.this, "B³¹d: " + wert, "B³¹d", JOptionPane.ERROR_MESSAGE);
			}
	}
	
	public void changePassword(){
		int row = tblUsers.getSelectedRow();
		if (row < 0){
			JOptionPane.showMessageDialog(main, "Musisz zaznaczyæ u¿ytkownika", "B³¹d", JOptionPane.ERROR_MESSAGE);
			return;
		}
		UsersList theUsersList = (UsersList) tblUsers.getValueAt(row, UsersTableModel.OBJECT_COL);
		PasswordChange passwordChange = new PasswordChange(theUsersList, usersDAO);
		passwordChange.setVisible(true);
	}

}
