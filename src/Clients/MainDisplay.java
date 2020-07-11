package question5;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import databaseSetup.DBoperations;
import databaseSetup.csvdata;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Font;

public class MainDisplay {

	private JFrame frame;
	private JTextField id;
	private JTextField name;
	private JTextField model;
	private JTextField area;
	private JTable table = new JTable();
	clientImpl cl = new clientImpl();
	DBoperations dbop = new DBoperations();
	csvdata csv = new csvdata();
	DefaultTableModel  model1 = new DefaultTableModel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainDisplay window = new MainDisplay();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainDisplay() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 734, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Vehicle Registration Data");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(55, 11, 264, 21);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CarID");
		lblNewLabel_1.setBounds(33, 56, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Car's Name");
		lblNewLabel_2.setBounds(33, 81, 59, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		id = new JTextField();
		id.setBounds(163, 47, 86, 20);
		frame.getContentPane().add(id);
		id.setColumns(10);
		
		name = new JTextField();
		name.setBounds(163, 78, 86, 20);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		model = new JTextField();
		model.setBounds(163, 109, 86, 20);
		frame.getContentPane().add(model);
		model.setColumns(10);
		
		area = new JTextField();
		area.setBounds(163, 141, 86, 20);
		frame.getContentPane().add(area);
		area.setColumns(10);
		
		JButton btnNewButton = new JButton("GET");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				     if(id.getText().isEmpty()) {
				
						 try {
							model1 = cl.Clientget();
							table.setModel(model1);
						} catch (URISyntaxException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} 
				     else{
						 try {
							 int data = Integer.parseInt(id.getText());
							model1 = cl.Clientgetbyid(data);
							table.setModel(model1);
						} catch (URISyntaxException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
				
					
				}
				
			
			
		});
		btnNewButton.setBounds(38, 198, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("POST");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id.getText().isEmpty()||name.getText().isEmpty()||model.getText().isEmpty()||area.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "FILL DATA");
				}
				else {
				int data = Integer.parseInt(id.getText());
				String name1 = name.getText();
				String model2 = model.getText();
				String area1 = area.getText();
				try {
					cl.clientPost(data,name1,model2,area1);
					model1 = cl.Clientget();
					table.setModel(model1);
					JOptionPane.showMessageDialog(null, "DATA INSERTED");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		btnNewButton_1.setBounds(160, 198, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("DELETE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "fill id please");
				}
				else {
				int data = Integer.parseInt(id.getText());
				try {
				   boolean check = cl.clientDelete(data);
				   model1 = cl.Clientget();
					table.setModel(model1);
				   JOptionPane.showMessageDialog(null, "DELETED");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		btnNewButton_2.setBounds(38, 243, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("PUT");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "fill id please");
				}
				else {
				int data = Integer.parseInt(id.getText());
				String name1 = name.getText();
				String Model1 = model.getText();
				String Area1 = area.getText();
				try {
					boolean check = cl.ClientPut(data,name1,Model1,Area1);
					model1 = cl.Clientget();
					table.setModel(model1);
					JOptionPane.showMessageDialog(null, "DATA UPDATED");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		btnNewButton_3.setBounds(160, 243, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("DELETE ALL");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int res;
				try {
					res = dbop.deldata();
					model1 = cl.Clientget();
					table.setModel(model1);
					if(res==1) {JOptionPane.showMessageDialog(null, "DELETE ALL DATA");
					
					}
						else
						{
							JOptionPane.showMessageDialog(null, "NOT DONE");
						}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(38, 294, 89, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("EXPORT CSV");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res;
				try {
					res = csv.csvprint();
					if(res==1) {JOptionPane.showMessageDialog(null, "EXPORT dONE");
					
					}
						else
						{
							JOptionPane.showMessageDialog(null, "NOT DONE");
						}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_5.setBounds(160, 294, 112, 23);
		frame.getContentPane().add(btnNewButton_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(333, 59, 323, 258);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		
		
		JLabel lblNewLabel_3 = new JLabel("Car's Model");
		lblNewLabel_3.setBounds(33, 109, 72, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Regrested Area");
		lblNewLabel_4.setBounds(33, 144, 88, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("ReportView");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(545, 34, 72, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("CreateTable");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res;
				try {
					res = dbop.createdata();
					if(res==1) {JOptionPane.showMessageDialog(null, "TABLE CREATED");
					
					}
						else
						{
							JOptionPane.showMessageDialog(null, "NOT DONE");
						}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Fill Tables");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res;
				try {
					res = dbop.filldata();
					if(res==1) {JOptionPane.showMessageDialog(null, "FILLING DONE");
					
					}
						else
						{
							JOptionPane.showMessageDialog(null, "NOT DONE");
						}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Project Info");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "NAME :SIKANDER NISHAD ROLLNO:A00268805");
					
					
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
	}
}
