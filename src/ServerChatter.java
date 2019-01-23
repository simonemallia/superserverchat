import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServerChatter extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JRadioButton rdbtnUser;
	private JRadioButton rdbtnUser_1;
	private static JTextPane textPane;
	private JMenu mnFile;
	private JMenuItem mntmClear;
	private static JButton btnInvia;
	private JMenu mnInfo;
	private JMenuItem mntmCredits;
	private JMenuItem mntmGoIntoWebsite;
	private static JScrollPane scrollPane;
	private JPopupMenu popupMenu;
	private JMenuItem mntmClear_1;
	private JMenuItem mntmSave;
	private JMenuItem mntmOpen;
	private JMenuItem mntmExit;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Image superserver = Toolkit.getDefaultToolkit().getImage(getClass().getResource("superserver.png"));
					Image welcome = Toolkit.getDefaultToolkit().getImage(getClass().getResource("welcome.png"));
					ImageIcon meeting = new ImageIcon (getClass().getResource("meeting.png"));
					ServerChatter frame = new ServerChatter();
					frame.setVisible(true);
					frame.getRootPane().setDefaultButton(btnInvia);
					frame.setIconImage(superserver);
					frame.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {	
							int returnvalue = JOptionPane.showConfirmDialog(frame, "Before closing, do you want to save conversation?", "Save", JOptionPane.YES_NO_CANCEL_OPTION);
						    if (returnvalue == JOptionPane.YES_OPTION) {
						    	JFileChooser c = new JFileChooser();
								c.addChoosableFileFilter(new FileNameExtensionFilter("Text File","txt"));
								c.setAcceptAllFileFilterUsed(false);
								c.showSaveDialog(scrollPane);
								
								try {
									BufferedWriter bw = new BufferedWriter(new FileWriter(c.getSelectedFile() + ".txt"));
									bw.append(textPane.getText());
									bw.close();
									JOptionPane.showMessageDialog(scrollPane, "Saved correctly!");
								} catch (IOException e) {
									e.printStackTrace();
								}
								
								System.exit(0);
						    	
						    }
						    else if (returnvalue == JOptionPane.NO_OPTION) {
						    	System.exit(0);
						    }
						    
						}
					});
					JDialog popup = new JDialog();
					popup.setTitle("Welcome");
					popup.setModal(true);	
					popup.setResizable(false);
					popup.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					popup.setSize(500, 140);
					popup.setAlwaysOnTop(true);
					popup.setLocationRelativeTo(frame);
					popup.setIconImage(welcome);
					Container c = popup.getContentPane();
					JPanel panelc = new JPanel();
					c.add(panelc, BorderLayout.CENTER);
					JLabel icon = new JLabel("");
					icon.setIcon(meeting);	
					panelc.add(icon);			
					panelc.add(new JLabel("Welcome in Super Server Chat"));
					panelc.add(new JLabel("This application was designed to replace notepad"));
					panelc.add(new JLabel("in a remote server session."));
					panelc.add(new JLabel("Let's start your conversation and enjoy it!"));
					popup.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerChatter() {
		setTitle("Super Server Chat");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 624, 726);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmClear = new JMenuItem("Clear");
		mntmClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnValue = 0;
				returnValue = JOptionPane.showConfirmDialog(scrollPane, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
				
				if (returnValue == JOptionPane.YES_OPTION) {
				textPane.setText("");
				}
			}
		});
		mnFile.add(mntmClear);
		
		mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser c = new JFileChooser();
				c.addChoosableFileFilter(new FileNameExtensionFilter("Text File","txt"));
				c.setAcceptAllFileFilterUsed(false);
				c.showSaveDialog(scrollPane);
				
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(c.getSelectedFile() + ".txt"));
					bw.append(textPane.getText());
					bw.close();
					JOptionPane.showMessageDialog(scrollPane, "Saved correctly!");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser c = new JFileChooser();
				c.addChoosableFileFilter(new FileNameExtensionFilter("Text File","txt"));
				c.showOpenDialog(scrollPane);
				try {
					StyledDocument sdoc = textPane.getStyledDocument();
					SimpleAttributeSet red = new SimpleAttributeSet();
					StyleConstants.setFontFamily(red, "Dialog");
					StyleConstants.setForeground(red, Color.RED);
					StyleConstants.setBold(red, true);
					
					SimpleAttributeSet blue = new SimpleAttributeSet();
					StyleConstants.setFontFamily(blue, "Dialog");
					StyleConstants.setForeground(blue, Color.BLUE);
					StyleConstants.setBold(blue, true);
					BufferedReader br = new BufferedReader(new FileReader(c.getSelectedFile()));
					for (String line = br.readLine(); line != null; line = br.readLine()){
						if (line.contains("User 1")) {
							sdoc.insertString(sdoc.getLength(), line + "\n", red);
						}
						else if (line.contains("User 2")) {
							sdoc.insertString(sdoc.getLength(), line + "\n", blue);
						}
					}
					br.close();
					
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
				
			}
		});
		mnFile.add(mntmOpen);
		mnFile.add(mntmSave);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnValue = 0;
				returnValue = JOptionPane.showConfirmDialog(scrollPane, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
				
				if (returnValue == JOptionPane.YES_OPTION) {
				System.exit(0);
				}
			}
		});
		mnFile.add(mntmExit);
		
		mnInfo = new JMenu("Info");
		menuBar.add(mnInfo);
		
		mntmCredits = new JMenuItem("Credits");
		mntmCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(scrollPane, "Super Server Chat v0.04a\nDeveloped By Simone Mallia", "Credits", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		mnInfo.add(mntmCredits);
		
		mntmGoIntoWebsite = new JMenuItem("Go into website");
		mntmGoIntoWebsite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					URI url = new URI ("https://github.com/simonemallia");
					if (Desktop.isDesktopSupported()) {
						Desktop.getDesktop().browse(url);
					}
				} catch (URISyntaxException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mnInfo.add(mntmGoIntoWebsite);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textPane = new JTextPane();
		textPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textPane.setFont(new Font("Dialog", Font.PLAIN, 13));
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		
		popupMenu = new JPopupMenu();
		addPopup(textPane, popupMenu);
		
		mntmClear_1 = new JMenuItem("Clear");
		mntmClear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnValue = 0;
				returnValue = JOptionPane.showConfirmDialog(scrollPane, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
				
				if (returnValue == JOptionPane.YES_OPTION) {
				textPane.setText("");
				}
			}
		});
		popupMenu.add(mntmClear_1);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(8);
		panel.setPreferredSize(new Dimension(70, 70));
		panel.setMinimumSize(new Dimension(50, 50));
		contentPane.add(panel, BorderLayout.SOUTH);
			
		rdbtnUser = new JRadioButton("User1");
		rdbtnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnUser.isSelected()) {
					textField.setText("");
					textField.setForeground(Color.RED);				
				}
			}
		});
		rdbtnUser.setForeground(Color.RED);
		panel.add(rdbtnUser);
		
		rdbtnUser_1 = new JRadioButton("User2");
		rdbtnUser_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnUser_1.isSelected()) {
					textField.setText("");
					textField.setForeground(Color.BLUE);
				}
			}
		});
		rdbtnUser_1.setForeground(Color.BLUE);
		panel.add(rdbtnUser_1);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnUser);
		group.add(rdbtnUser_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.BOLD, 12));
		textField.setToolTipText("Insert your message here");
		textField.setPreferredSize(new Dimension(10, 26));
		panel.add(textField);
		textField.setColumns(25);
		
		btnInvia = new JButton("Send");
		btnInvia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				StyledDocument sdoc = textPane.getStyledDocument();
				SimpleAttributeSet red = new SimpleAttributeSet();
				StyleConstants.setFontFamily(red, "Dialog");
				StyleConstants.setForeground(red, Color.RED);
				StyleConstants.setBold(red, true);
				
				SimpleAttributeSet blue = new SimpleAttributeSet();
				StyleConstants.setFontFamily(blue, "Dialog");
				StyleConstants.setForeground(blue, Color.BLUE);
				StyleConstants.setBold(blue, true);
				if (rdbtnUser.isSelected()) {
					try {
						ZonedDateTime time = ZonedDateTime.now();
						String format = DateTimeFormatter.ofPattern("HH:mm:ss").format(time);
						sdoc.insertString(sdoc.getLength(), format + " - User 1: " + textField.getText() + "\n", red);
						textField.setText("");
					} catch (BadLocationException e) {
						e.printStackTrace();
					}			
				}
				else if (rdbtnUser_1.isSelected()) {
					try {
						ZonedDateTime time = ZonedDateTime.now();
						String format = DateTimeFormatter.ofPattern("HH:mm:ss").format(time);
						sdoc.insertString(sdoc.getLength(), format +  " - User 2: " + textField.getText() + "\n", blue);
						textField.setText("");
						
					} catch (BadLocationException e) {
						e.printStackTrace();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(scrollPane, "Remember to set User 1 or User 2", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		panel.add(btnInvia);
		
		
		
		

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
