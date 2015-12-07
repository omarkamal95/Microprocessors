package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;

import javax.swing.Action;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIapp {

	private JFrame frame;
	private JTextField textField;
	public int levelOfCaches; 

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
		sl_panel.putConstraint(SpringLayout.NORTH, lblEnterLevelOf, 33, SpringLayout.SOUTH, lblToStartCreating);
		sl_panel.putConstraint(SpringLayout.WEST, lblEnterLevelOf, 113, SpringLayout.WEST, panel);
		panel.add(lblEnterLevelOf);
		
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				levelOfCaches = Integer.parseInt(textField.getText());
				CachesSetup c = new CachesSetup(levelOfCaches);
				c.NewScreen();
				frame.setVisible(false);
				System.out.println(levelOfCaches);
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, textField, -6, SpringLayout.NORTH, lblEnterLevelOf);
		sl_panel.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblEnterLevelOf);
		panel.add(textField);
		textField.setColumns(10);
		
		
		
	}
}
