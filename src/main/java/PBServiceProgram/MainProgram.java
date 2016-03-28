package PBServiceProgram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class MainProgram extends JFrame {

	private static final long serialVersionUID = 4360433285595200954L;
	
	private JPanel contentPane;
	private UsersDAO usersDAO;
	private JTable tblNotif;
	private NotificationsTableModel model;
	private ConnectionManager manager;
	private JTextField txtSearch;
	private UsersList choosenUser;
	private int withNotif;
	private JPopupMenu popupMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					UsersDAO usersDAO = new UsersDAO();
					ArrayList<UsersList> users = usersDAO.getLoginName();
					

					LoginDialog dialog = new LoginDialog();
					dialog.populateUsers(users);
					dialog.setUserDAO(usersDAO);
					
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainProgram(UsersList theUser) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\PBSerwis\\icons\\pb_new.jpg"));
		
		choosenUser = theUser;
		setMinimumSize(new Dimension(1250,600));
//		setPreferredSize(new Dimension(1550, 600));

//		InputMap im = tblNotif.getInputMap();
//		ActionMap am = tblNotif.getActionMap();
//
//		KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
//
//		im.put(enterKey, "Action.enter");
//		am.put("Action.enter", new AbstractAction() {
//		    public void actionPerformed(ActionEvent evt) {
//		    	edit();
//		    }
//		});
		
		try{
			usersDAO = new UsersDAO();
		} catch (Exception exc){
			JOptionPane.showMessageDialog(this, "B³¹d: "+ exc, "B³¹d", JOptionPane.ERROR_MESSAGE);
		}
		
		setTitle("Program Serwisowy PB Komputer - user");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	archive(1); // set 1 if close set 0 if not
		    }
		});

		setSize(1550, 900);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("Plik");
		menuBar.add(mnFile);
		
		JMenuItem mntmFileNotification = new JMenuItem("Nowe zg\u0142oszenie");
		mntmFileNotification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewNotif newN = new NewNotif();
				newN.setVisible(true);
			}
		});
		mnFile.add(mntmFileNotification);
		
		JMenuItem mntmFileUsers = new JMenuItem("Prze\u0142\u0105cz u\u017Cytkownika");
		mntmFileUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					setVisible(false);
					UsersDAO usersDAO = new UsersDAO();
					ArrayList<UsersList> users = usersDAO.getLoginName();
					

					LoginDialog dialog = new LoginDialog();
					dialog.populateUsers(users);
					dialog.setUserDAO(usersDAO);
					
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnFile.add(mntmFileUsers);
		
		JMenuItem mntmFileExit = new JMenuItem("Zako\u0144cz");
		mntmFileExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmFileExit);
		
		JMenu mnTools = new JMenu("Narz\u0119dzia");
		menuBar.add(mnTools);
		
		JMenuItem mntmToolsPassword = new JMenuItem("Zmiana has\u0142a");
		mntmToolsPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeMyPassword();
			}
		});
		mnTools.add(mntmToolsPassword);
		
		JMenuItem mntmToolsBackup = new JMenuItem("Archiwizacja");
		mntmToolsBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				archive(0); // set 1 if close set 0 if not
			}
		});
		mnTools.add(mntmToolsBackup);
		
		JMenu mnAdministrator = new JMenu("Administrator");
		menuBar.add(mnAdministrator);
		
		try{
			UsersDAO dao = new UsersDAO();
			ArrayList<UsersList> users = dao.getAllUsers();
			for(UsersList w: users){
				if(choosenUser.getFirstName().contains(w.getFirstName())&&choosenUser.getLastName().contains(w.getLastName())){
					String ifAdmin = w.getPermission();
					if(ifAdmin.equals("Administrator")){
						mnAdministrator.setVisible(true);
					}else{
						mnAdministrator.setVisible(false);
					}
				}
			}
		} catch (Exception w){
			w.printStackTrace();
		}
		
		JMenuItem mntmAdminServer = new JMenuItem("Konfiguracja serwera");
		mnAdministrator.add(mntmAdminServer);
		
		JMenuItem mntmAdminUsers = new JMenuItem("U\u017Cytkownicy");
		mntmAdminUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Users change = new Users();
				change.setVisible(true);
				
			}
		});
		mnAdministrator.add(mntmAdminUsers);
		
		JMenuItem mntmDearchiwizacja = new JMenuItem("Dearchiwizacja");
		mntmDearchiwizacja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					File file = new File("c:\\PBSerwis\\backup.sql");
					if(!file.exists()){
						JOptionPane.showMessageDialog(MainProgram.this, "Plik archiwum nie istnieje.", "B³¹d w odczycie pliku archiwum.", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					SimpleDateFormat modifDay = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat modifHour = new SimpleDateFormat("HH:mm");

					String[] options ={"Tak", "Nie"};
					int response = JOptionPane.showOptionDialog(MainProgram.this, "Czy przywróciæ archiwum z dnia " + 
					modifDay.format(file.lastModified()) + " z godziny " + modifHour.format(file.lastModified()) + "?", "Dearchiwizacja",  JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, null);
					if(response ==JOptionPane.YES_OPTION){
						Runtime.getRuntime().exec("cmd /c mysql -uroot -p@n1ch1L@t0r pbserwisdb < C:/PBSerwis/backup.sql");
						JOptionPane.showMessageDialog(MainProgram.this, "Kopia bazy wykonana pomyœlnie.", "Kopia bazy", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception wew){
					wew.printStackTrace();
				}
				showAllNotifications();
			}
		});
		mnAdministrator.add(mntmDearchiwizacja);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tblNotif = new JTable(model);
		tblNotif.setAutoCreateRowSorter(true);
		tblNotif.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if (e.getClickCount() == 2){
					edit();
				}
			}
		});
		
		try{
		ArrayList<NotificationsList> disNotifications = null;
		disNotifications = usersDAO.getAllNotifications();
		NotificationsTableModel model = new NotificationsTableModel(disNotifications);
		tblNotif.setModel(model);
		tblNotif.setAutoResizeMode(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		
		}catch(Exception ew){
			ew.printStackTrace();
		}
		
		scrollPane.setViewportView(tblNotif);
		
		popupMenu = new JPopupMenu();
		addPopup(tblNotif, popupMenu);
		
		JMenuItem mntmDodaj = new JMenuItem("Dodaj");
		mntmDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addNotif();
			}
		});
		popupMenu.add(mntmDodaj);
		
		JMenuItem mntmUsu = new JMenuItem("Usu\u0144");
		mntmUsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteNotif();
			}
		});
		popupMenu.add(mntmUsu);
		
		JMenu mnStatus = new JMenu("Status");
		popupMenu.add(mnStatus);
		
		JMenuItem mntmRealizowany = new JMenuItem("Realizowany");
		mntmRealizowany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = tblNotif.getSelectedRow();
				if(row<0){
					JOptionPane.showMessageDialog(MainProgram.this, "Musisz zaznaczyæ zg³oszenie do edycji", "B³¹d", JOptionPane.ERROR_MESSAGE);
					return;
				}
				NotificationsList tempNotificationsList = (NotificationsList) tblNotif.getValueAt(row, NotificationsTableModel.OBJECT_COL);
				try{
					usersDAO.setUndone(tempNotificationsList.getNotifId());
				} catch (Exception x){
					
				}
				refreshNotificationsView();
			}
		});
		mnStatus.add(mntmRealizowany);
		
		JMenuItem mntmZakoczony = new JMenuItem("Zako\u0144czony");
		mntmZakoczony.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");
				
				int row = tblNotif.getSelectedRow();
				if(row<0){
					JOptionPane.showMessageDialog(MainProgram.this, "Musisz zaznaczyæ zg³oszenie do edycji", "B³¹d", JOptionPane.ERROR_MESSAGE);
					return;
				}
				NotificationsList tempNotificationsList = (NotificationsList) tblNotif.getValueAt(row, NotificationsTableModel.OBJECT_COL);
				try{
				if(!tempNotificationsList.equals("Zakoñczony")){
					usersDAO.setDone(tempNotificationsList.getNotifId(), dateOnly.format(cal.getTime()));
				}	
				String[] options ={"Tak", "Nie"};
				if(!tempNotificationsList.getFinishDate().equals("")){
					int response = JOptionPane.showOptionDialog(MainProgram.this, "Czy zmieniæ datê zakoñczenia zg³oszenia", "Zmiana daty zakoñczenia zg³oszenia",  JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, null);
					if(response ==JOptionPane.YES_OPTION){
						usersDAO.setDone(tempNotificationsList.getNotifId(), dateOnly.format(cal.getTime()));
					}
				}
					
				} catch (Exception x){
					
				}
				refreshNotificationsView();
			}
		});
		mnStatus.add(mntmZakoczony);
		
		tblNotif.addMouseListener(new MouseAdapter() {
			public void mousePressed( MouseEvent e )
			{
				Point point = e.getPoint();
				int row = tblNotif.getSelectedRow();
		        int currentRow = tblNotif.rowAtPoint(point);
		        tblNotif.setRowSelectionInterval(currentRow, currentRow);
			}
		});
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(1);
		flowLayout.setHgap(1);
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnNewNotif = new JButton("");
		btnNewNotif.setToolTipText("Nowe Zg\u0142oszenie");
		btnNewNotif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNotif();
			}
		});
		btnNewNotif.setIcon(new ImageIcon("\\PBSerwis\\icons\\newNotif.png"));
		btnNewNotif.setMargin(new Insets(0, 0, 0, 0));
		panel.add(btnNewNotif);
		
		JButton btnEditNotif = new JButton("");
		btnEditNotif.setToolTipText("Edycja zg\u0142oszenia");
		btnEditNotif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				edit();
			}
		});
		btnEditNotif.setMargin(new Insets(0, 0, 0, 0));
		btnEditNotif.setIcon(new ImageIcon("\\PBSerwis\\icons\\editNotif.png"));
		panel.add(btnEditNotif);
		
		JButton btnDelNotif = new JButton("");
		btnDelNotif.setToolTipText("Usuwanie zg\u0142oszenia");
		btnDelNotif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteNotif();
			}
		});
		
		JButton button = new JButton("");
		button.setToolTipText("Drukowanie (Export do Excela)");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = tblNotif.getSelectedRow();
				if(row<0){
					JOptionPane.showMessageDialog(MainProgram.this, "Musisz zaznaczyæ zg³oszenie do druku", "B³¹d", JOptionPane.ERROR_MESSAGE);
					return;
				}
				NotificationsList tempNotificationsList = (NotificationsList) tblNotif.getValueAt(row, NotificationsTableModel.OBJECT_COL);
				PrintNotification print = new PrintNotification(MainProgram.this, tempNotificationsList);
				print.print();
			}
		});
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setIcon(new ImageIcon("\\PBSerwis\\icons\\print.png"));
		panel.add(button);
		btnDelNotif.setIcon(new ImageIcon("\\PBSerwis\\icons\\delNotif.png"));
		btnDelNotif.setMargin(new Insets(0, 0, 0, 0));
		panel.add(btnDelNotif);
		
		JButton btnRefresh = new JButton("");
		btnRefresh.setToolTipText("Od\u015Bwie\u017Canie listy zg\u0142osze\u0144");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshNotificationsView();
			}
		});
		btnRefresh.setMargin(new Insets(0, 0, 0, 0));
		btnRefresh.setIcon(new ImageIcon("\\PBSerwis\\icons\\refresh.png"));
		panel.add(btnRefresh);
		
		JButton btnShowMy = new JButton("");
		btnShowMy.setToolTipText("Poka\u017C zg\u0142oszenia dla mnie");
		btnShowMy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showMyNotifications();
				withNotif = 1;
			}
		});
		btnShowMy.setMargin(new Insets(0, 0, 0, 0));
		btnShowMy.setIcon(new ImageIcon("\\PBSerwis\\icons\\myNot.png"));
		panel.add(btnShowMy);
		
		JButton btnShowAll = new JButton("");
		btnShowAll.setToolTipText("Poka\u017C wszystkie zg\u0142oszenia");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAllNotifications();
				withNotif = 2;
			}
		});
		btnShowAll.setMargin(new Insets(0, 0, 0, 0));
		btnShowAll.setIcon(new ImageIcon("\\PBSerwis\\icons\\allNot.png"));
		panel.add(btnShowAll);
		
		JButton btnAddUser = new JButton("");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Users change = new Users();
				change.setVisible(true);
			}
		});
		btnAddUser.setIcon(new ImageIcon("\\PBSerwis\\icons\\newUser.png"));
		btnAddUser.setMargin(new Insets(0, 0, 0, 0));
		panel.add(btnAddUser);
		
		try{
			
			UsersDAO dao = new UsersDAO();
			ArrayList<UsersList> users = dao.getAllUsers();
			for(UsersList w: users){
				if(choosenUser.getFirstName().contains(w.getFirstName())&&choosenUser.getLastName().contains(w.getLastName())){
					String ifAdmin = w.getPermission();
					if(ifAdmin.equals("Administrator")){
						btnAddUser.setVisible(true);
					}else{
						btnAddUser.setVisible(false);
					}
				}
			}
		} catch (Exception w){
			w.printStackTrace();
		}
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateOnly = new SimpleDateFormat("dd-MM-yyyy");
		
		String today = dateOnly.format(cal.getTime());
		
		JLabel lblSzukaj = new JLabel("                                                                Szukaj:");
		lblSzukaj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblSzukaj);
		
		txtSearch = new JTextField();
		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchAction();
			}
		});
		panel.add(txtSearch);
		txtSearch.setColumns(15);
		
		JButton btnSzukaj = new JButton("Szukaj");
		btnSzukaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchAction();
			}
		});
		panel.add(btnSzukaj);
		
		JLabel lblDate = new JLabel("                                                              Dzi\u015B: " + today);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblDate);
		
		refreshNotificationsView();
	}
	
	protected void edit(){
		int row = tblNotif.getSelectedRow();
		if(row<0){
			JOptionPane.showMessageDialog(MainProgram.this, "Musisz zaznaczyæ zg³oszenie do edycji", "B³¹d", JOptionPane.ERROR_MESSAGE);
			return;
		}
		NotificationsList tempNotificationsList = (NotificationsList) tblNotif.getValueAt(row, NotificationsTableModel.OBJECT_COL);
		NewNotif window = new NewNotif(MainProgram.this, usersDAO, tempNotificationsList, true, choosenUser);
		
		window.setVisible(true);
	}
	
	public void refreshNotificationsView() {
		if(withNotif==1){
			showMyNotifications();
		}else{
			showAllNotifications();
		}
	}
	
	public void searchAction(){
		String shWord = txtSearch.getText();
		if(shWord.equals("")){
			showAllNotifications();
		}else{
			searchNotifications(shWord);
		}
	}
	
	public void searchNotifications(String word){
		ArrayList<NotificationsList> disNotifications = null;
		try{
			disNotifications = usersDAO.searchNotifications(word);
			NotificationsTableModel model = new NotificationsTableModel(disNotifications);
			tblNotif.setModel(model);
			render();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showMyNotifications(){
		ArrayList<NotificationsList> disNotifications = null;
		try{
			disNotifications = usersDAO.getAllUserNotifications(choosenUser);
			NotificationsTableModel model = new NotificationsTableModel(disNotifications);
			tblNotif.setModel(model);
			render();
		} catch (Exception w){
			w.printStackTrace();
		}
	}
	
	public void showAllNotifications(){
		ArrayList<NotificationsList> disNotifications = null;
		try{
			disNotifications = usersDAO.getAllNotifications();
						
			NotificationsTableModel model = new NotificationsTableModel(disNotifications);
			tblNotif.setModel(model);
			render();
			
			} catch (Exception wert){
				JOptionPane.showMessageDialog(MainProgram.this, "B³¹d: " + wert, "B³¹d", JOptionPane.ERROR_MESSAGE);
			}
	}
	
	public void render(){
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tblNotif.setDefaultRenderer(Integer.class, centerRenderer);
			
		tblNotif.getColumnModel().getColumn(0).setMaxWidth(40);
		tblNotif.getColumnModel().getColumn(1).setMinWidth(90);
		tblNotif.getColumnModel().getColumn(1).setMaxWidth(90);
		tblNotif.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tblNotif.getColumnModel().getColumn(2).setMinWidth(70);
		tblNotif.getColumnModel().getColumn(2).setMaxWidth(70);
		tblNotif.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tblNotif.getColumnModel().getColumn(3).setPreferredWidth(120);
		tblNotif.getColumnModel().getColumn(4).setMinWidth(75);
		tblNotif.getColumnModel().getColumn(4).setMaxWidth(75);
		tblNotif.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		tblNotif.getColumnModel().getColumn(5).setMinWidth(50);
		tblNotif.getColumnModel().getColumn(5).setMaxWidth(50);
		tblNotif.getColumnModel().getColumn(6).setPreferredWidth(100);
//		tblNotif.getColumnModel().getColumn(7).setPreferredWidth(5);
		tblNotif.getColumnModel().getColumn(8).setPreferredWidth(100);
		tblNotif.getColumnModel().getColumn(9).setMinWidth(70);
		tblNotif.getColumnModel().getColumn(9).setMaxWidth(70);
		tblNotif.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
		tblNotif.getColumnModel().getColumn(10).setMinWidth(65);
		tblNotif.getColumnModel().getColumn(10).setMaxWidth(65);
		tblNotif.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);
		tblNotif.getColumnModel().getColumn(11).setPreferredWidth(200);
		tblNotif.getColumnModel().getColumn(12).setMinWidth(120);
		tblNotif.getColumnModel().getColumn(12).setMaxWidth(120);
		tblNotif.getColumnModel().getColumn(12).setCellRenderer(centerRenderer);
		tblNotif.getColumnModel().getColumn(13).setMinWidth(120);
		tblNotif.getColumnModel().getColumn(13).setMaxWidth(120);
		tblNotif.getColumnModel().getColumn(13).setCellRenderer(centerRenderer);
		tblNotif.getColumnModel().getColumn(14).setMinWidth(100);
		tblNotif.getColumnModel().getColumn(14).setMaxWidth(100);
		tblNotif.getColumnModel().getColumn(14).setCellRenderer(centerRenderer);
		tblNotif.getColumnModel().getColumn(15).setMinWidth(75);
		tblNotif.getColumnModel().getColumn(15).setMaxWidth(75);
		tblNotif.getColumnModel().getColumn(15).setCellRenderer(centerRenderer);
		

		
//		if(tblNotif.getColumnModel().getColumn(15).equals("Zrealizowany")){
//		DefaultTableCellRenderer colorRed = new DefaultTableCellRenderer();
//		colorRed.setForeground(Color.RED);
//		colorRed.setHorizontalAlignment( JLabel.CENTER );
//		tblNotif.getColumnModel().getColumn(15).setCellRenderer(colorRed);
//		}
//		else{
//		DefaultTableCellRenderer colorGreen = new DefaultTableCellRenderer();
//		colorGreen.setForeground(Color.GREEN);
//		colorGreen.setHorizontalAlignment( JLabel.CENTER );
//		tblNotif.getColumnModel().getColumn(15).setCellRenderer(colorGreen);
//		}
	}
	
	public void changeMyPassword(){
		PasswordChange passwordChange = new PasswordChange(usersDAO, choosenUser);
		passwordChange.setVisible(true);
	}
	
	public void archive(int ifClose){
		try{
			
			File file = new File("c:\\PBSerwis\\backup.sql");
			SimpleDateFormat modifDay = new SimpleDateFormat("dd/MM/yyyy");
			Date today = new Date();
			
			if(ifClose==1&&!modifDay.format(file.lastModified()).equals(modifDay.format(today))){
				String[] options ={"Tak", "Nie"};
				int response = JOptionPane.showOptionDialog(MainProgram.this, "Wykonaæ archiwizacjê?", "Archiwizacja",  JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, null);
				if(response ==JOptionPane.YES_OPTION){
					Runtime.getRuntime().exec("cmd /c mysqldump -uroot -p@n1ch1L@t0r pbserwisdb > C:/PBSerwis/backup.sql");
					JOptionPane.showMessageDialog(MainProgram.this, "Kopia bazy wykonana pomyœlnie.", "Kopia bazy", JOptionPane.INFORMATION_MESSAGE);
				}
				
				if(response ==JOptionPane.NO_OPTION){
					return;
				}
			}if(ifClose==0){
				Runtime.getRuntime().exec("cmd /c mysqldump -uroot -p@n1ch1L@t0r pbserwisdb > C:/PBSerwis/backup.sql");
				JOptionPane.showMessageDialog(MainProgram.this, "Kopia bazy wykonana pomyœlnie.", "Kopia bazy", JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (Exception wew){
			wew.printStackTrace();
		}
	}
	
	public void addNotif(){
		NewNotif newN = new NewNotif(MainProgram.this, usersDAO, choosenUser);
		newN.setVisible(true);
	}
	
	public void deleteNotif(){
		try{
			int row = tblNotif.getSelectedRow();
			if(row<0){
				JOptionPane.showMessageDialog(MainProgram.this, "Musisz zaznaczyæ zg³oszenie", "B³¹d", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int response = JOptionPane.showConfirmDialog(MainProgram.this, "Czy usun¹æ to zg³oszenie?", "Usuwanie zg³oszenia", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response !=JOptionPane.YES_OPTION){
				return;
			}
			NotificationsList tempNotificationsList = (NotificationsList) tblNotif.getValueAt(row, NotificationsTableModel.OBJECT_COL);
			usersDAO.deleteNotification(tempNotificationsList.getNotifId());
			refreshNotificationsView();
			JOptionPane.showMessageDialog(MainProgram.this, "Zg³oszenie usuniête.", "Usuwanie zg³oszenia", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception gun){
		JOptionPane.showMessageDialog(MainProgram.this, "B³¹d usuwania zg³oszenia: " + gun.getMessage(), "B³¹d", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void setLoggedInUserName() {
		
		try{
		manager = new ConnectionManager();
		setTitle("Program Serwisowy PB Komputer:                                 U¿ytkownik: " + choosenUser.getFirstName() + " " + choosenUser.getLastName() +
					 "                                 Serwer: " + manager.toString());
		}catch(Exception w){
			w.printStackTrace();
		}
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
