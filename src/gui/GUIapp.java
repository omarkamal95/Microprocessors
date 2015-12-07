package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;

import javax.swing.Action;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Main.MainMemory;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class GUIapp {

	private JFrame frame;
	private JTextField textField;
	private int levelOfCaches; 
	private int accessTime;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIapp window = new GUIapp();
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
	public GUIapp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel lblToStartCreating = new JLabel("To start creating the simulator, start entering the components of the Memory Hierarchy.");
		sl_panel.putConstraint(SpringLayout.NORTH, lblToStartCreating, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblToStartCreating, -21, SpringLayout.EAST, panel);
		panel.add(lblToStartCreating);
		
		JLabel lblEnterLevelOf = new JLabel("Enter level of Caches:");
		sl_panel.putConstraint(SpringLayout.WEST, lblEnterLevelOf, 109, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblEnterLevelOf, -21, SpringLayout.SOUTH, panel);
		panel.add(lblEnterLevelOf);
		
		
		textField = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, textField, -6, SpringLayout.NORTH, lblEnterLevelOf);
		sl_panel.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblEnterLevelOf);
		sl_panel.putConstraint(SpringLayout.SOUTH, textField, 6, SpringLayout.SOUTH, lblEnterLevelOf);
		sl_panel.putConstraint(SpringLayout.EAST, textField, 57, SpringLayout.EAST, lblEnterLevelOf);
		
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblMainMemoryAccess = new JLabel("Main memory access time:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblMainMemoryAccess, 22, SpringLayout.SOUTH, lblToStartCreating);
		sl_panel.putConstraint(SpringLayout.WEST, lblMainMemoryAccess, 0, SpringLayout.WEST, lblEnterLevelOf);
		panel.add(lblMainMemoryAccess);
		
		textField_1 = new JTextField();
		
		sl_panel.putConstraint(SpringLayout.NORTH, textField_1, -6, SpringLayout.NORTH, lblMainMemoryAccess);
		sl_panel.putConstraint(SpringLayout.WEST, textField_1, 6, SpringLayout.EAST, lblMainMemoryAccess);
		sl_panel.putConstraint(SpringLayout.EAST, textField_1, 57, SpringLayout.EAST, lblMainMemoryAccess);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				levelOfCaches = Integer.parseInt(textField.getText());
				accessTime = Integer.parseInt(textField_1.getText());
				MainMemory m = new MainMemory(accessTime);
				CachesSetup c = new CachesSetup(levelOfCaches);
				c.NewScreen();
				frame.setVisible(false);
				System.out.println(levelOfCaches);
			}
		});
		sl_panel.putConstraint(SpringLayout.SOUTH, btnNext, -10, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnNext, 0, SpringLayout.EAST, lblToStartCreating);
		panel.add(btnNext);
		
		
		
	}
}
