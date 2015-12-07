package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class HardwareSetup {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HardwareSetup window = new HardwareSetup();
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
	public HardwareSetup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblSecondStep = new JLabel("Second step:");
		lblSecondStep.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		springLayout.putConstraint(SpringLayout.NORTH, lblSecondStep, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblSecondStep, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblSecondStep);
		
		JLabel lblHardwareOrganizationSetup = new JLabel("Hardware Organization Setup:");
		lblHardwareOrganizationSetup.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblHardwareOrganizationSetup, 6, SpringLayout.SOUTH, lblSecondStep);
		springLayout.putConstraint(SpringLayout.WEST, lblHardwareOrganizationSetup, 0, SpringLayout.WEST, lblSecondStep);
		frame.getContentPane().add(lblHardwareOrganizationSetup);
		
		JLabel lblPipelineWidth = new JLabel("Pipeline width:");
		springLayout.putConstraint(SpringLayout.NORTH, lblPipelineWidth, 22, SpringLayout.SOUTH, lblHardwareOrganizationSetup);
		springLayout.putConstraint(SpringLayout.WEST, lblPipelineWidth, 39, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblPipelineWidth);
		
		JLabel lblSizeOfInstruction = new JLabel("Size of instruction buffer:");
		springLayout.putConstraint(SpringLayout.NORTH, lblSizeOfInstruction, 18, SpringLayout.SOUTH, lblPipelineWidth);
		springLayout.putConstraint(SpringLayout.WEST, lblSizeOfInstruction, 0, SpringLayout.WEST, lblPipelineWidth);
		frame.getContentPane().add(lblSizeOfInstruction);
		
		JLabel lblNumberOfReservation = new JLabel("Number of Reservation Stations:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNumberOfReservation, 18, SpringLayout.SOUTH, lblSizeOfInstruction);
		springLayout.putConstraint(SpringLayout.WEST, lblNumberOfReservation, 0, SpringLayout.WEST, lblPipelineWidth);
		frame.getContentPane().add(lblNumberOfReservation);
		
		JLabel lblNumberOfRob = new JLabel("Number of ROB entries:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNumberOfRob, 16, SpringLayout.SOUTH, lblNumberOfReservation);
		springLayout.putConstraint(SpringLayout.WEST, lblNumberOfRob, 0, SpringLayout.WEST, lblPipelineWidth);
		frame.getContentPane().add(lblNumberOfRob);
		
		JLabel lblNumberOfCycles = new JLabel("Number of cycles needed by each funtional unit:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNumberOfCycles, 21, SpringLayout.SOUTH, lblNumberOfRob);
		springLayout.putConstraint(SpringLayout.WEST, lblNumberOfCycles, 0, SpringLayout.WEST, lblPipelineWidth);
		frame.getContentPane().add(lblNumberOfCycles);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, -2, SpringLayout.NORTH, lblPipelineWidth);
		springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblPipelineWidth);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, 39, SpringLayout.SOUTH, lblHardwareOrganizationSetup);
		springLayout.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, lblSizeOfInstruction);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, -7, SpringLayout.NORTH, lblSizeOfInstruction);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 6, SpringLayout.EAST, lblSizeOfInstruction);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, 69, SpringLayout.EAST, lblSizeOfInstruction);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_2, -2, SpringLayout.NORTH, lblNumberOfReservation);
		springLayout.putConstraint(SpringLayout.WEST, textField_2, 6, SpringLayout.EAST, lblNumberOfReservation);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_2, 17, SpringLayout.NORTH, lblNumberOfReservation);
		springLayout.putConstraint(SpringLayout.EAST, textField_2, 69, SpringLayout.EAST, lblNumberOfReservation);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_3, 6, SpringLayout.SOUTH, lblNumberOfReservation);
		springLayout.putConstraint(SpringLayout.WEST, textField_3, 6, SpringLayout.EAST, lblNumberOfRob);
		springLayout.putConstraint(SpringLayout.EAST, textField_3, 69, SpringLayout.EAST, lblNumberOfRob);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_4, -6, SpringLayout.NORTH, lblNumberOfCycles);
		springLayout.putConstraint(SpringLayout.WEST, textField_4, 6, SpringLayout.EAST, lblNumberOfCycles);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_4, -45, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField_4, -42, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNext = new JButton("Next");
		springLayout.putConstraint(SpringLayout.NORTH, btnNext, 6, SpringLayout.SOUTH, textField_4);
		springLayout.putConstraint(SpringLayout.EAST, btnNext, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnNext);
	}

}
