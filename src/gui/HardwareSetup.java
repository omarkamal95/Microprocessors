package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import Main.*;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;

public class HardwareSetup {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	
	private int pipelineWidth;
	private int InstructionBufferSize;
	private int loadCount,uncondCount,condCount,callCount,arithmeticCount;
	private int uncondCycle,coCycle,clCycle,arithmeticCycle;
	private int ROBcount;
	private static MainMemory memory;
	public static ArrayList<DCache> DCaches;
	public static ArrayList<ICache> ICaches;
	private static int mmaccessTime;
	private JTextField loadField;
	private JTextField uncondField;
	private JTextField condField;
	private JTextField callbackField;
	private JTextField arithmeticField;
	private JTextField callCycle;
	private JTextField unCycle;
	private JTextField condCycle;
	private JTextField arthCycle;
	
	/**
	 * Create the application.
	 */
	public HardwareSetup(int m, ArrayList<DCache> d, ArrayList<ICache> i) {
		DCaches = d;
		ICaches = i;
		mmaccessTime = m;
		initialize();
	}
	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HardwareSetup window = new HardwareSetup(mmaccessTime, DCaches, ICaches);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSecondStep = new JLabel("Second step:");
		lblSecondStep.setBounds(10, 10, 107, 18);
		lblSecondStep.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		frame.getContentPane().add(lblSecondStep);
		
		JLabel lblHardwareOrganizationSetup = new JLabel("Hardware Organization Setup:");
		lblHardwareOrganizationSetup.setBounds(10, 34, 239, 17);
		lblHardwareOrganizationSetup.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		frame.getContentPane().add(lblHardwareOrganizationSetup);
		
		JLabel lblPipelineWidth = new JLabel("Pipeline width:");
		lblPipelineWidth.setBounds(39, 73, 106, 15);
		frame.getContentPane().add(lblPipelineWidth);
		
		JLabel lblSizeOfInstruction = new JLabel("Size of instruction buffer:");
		lblSizeOfInstruction.setBounds(39, 106, 181, 15);
		frame.getContentPane().add(lblSizeOfInstruction);
		
		JLabel lblNumberOfRob = new JLabel("Number of ROB entries:");
		lblNumberOfRob.setBounds(39, 133, 166, 15);
		frame.getContentPane().add(lblNumberOfRob);
		
		textField = new JTextField();
		textField.setBounds(163, 71, 69, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(226, 99, 63, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(236, 131, 63, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(374, 561, 66, 25);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pipelineWidth = Integer.parseInt(textField.getText());
				InstructionBufferSize = Integer.parseInt(textField_1.getText());
				loadCount = Integer.parseInt(loadField.getText());
				condCount = Integer.parseInt(condField.getText());
				uncondCount = Integer.parseInt(uncondField.getText());
				callCount = Integer.parseInt(callbackField.getText());
				arithmeticCount = Integer.parseInt(arithmeticField.getText());
				ROBcount = Integer.parseInt(textField_3.getText());
				uncondCycle = Integer.parseInt(unCycle.getText());
				coCycle = Integer.parseInt(condCycle.getText());
				clCycle = Integer.parseInt(callCycle.getText());
				arithmeticCycle = Integer.parseInt(arthCycle.getText());;
				
				Assembly a = new Assembly(DCaches, ICaches, mmaccessTime, pipelineWidth, InstructionBufferSize, ROBcount, loadCount, uncondCount, condCount,callCount, arithmeticCount,uncondCycle,coCycle,clCycle,arithmeticCycle);
				a.newScreen();
				frame.setVisible(false);

			}
		});
		frame.getContentPane().add(btnNext);
		
		JLabel lblFunctionalUnits = new JLabel("Functional units:");
		lblFunctionalUnits.setBounds(26, 206, 166, 15);
		frame.getContentPane().add(lblFunctionalUnits);
		
		JLabel lblLoadstrore = new JLabel("Load/Strore");
		lblLoadstrore.setBounds(26, 269, 107, 15);
		frame.getContentPane().add(lblLoadstrore);
		
		JLabel lblUnc = new JLabel("Unconditional branch");
		lblUnc.setBounds(26, 313, 151, 15);
		frame.getContentPane().add(lblUnc);
		
		JLabel lblConditionalBranch = new JLabel("Conditional branch");
		lblConditionalBranch.setBounds(26, 364, 151, 15);
		frame.getContentPane().add(lblConditionalBranch);
		
		JLabel lblNewLabel = new JLabel("Callback/Return");
		lblNewLabel.setBounds(26, 419, 119, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblArithmetic = new JLabel("Arithmetic");
		lblArithmetic.setBounds(37, 464, 140, 15);
		frame.getContentPane().add(lblArithmetic);
		
		JLabel lblNoOfRes = new JLabel("No of Res Stations");
		lblNoOfRes.setBounds(185, 206, 151, 15);
		frame.getContentPane().add(lblNoOfRes);
		
		loadField = new JTextField();
		loadField.setBounds(219, 267, 63, 19);
		frame.getContentPane().add(loadField);
		loadField.setColumns(10);
		
		uncondField = new JTextField();
		uncondField.setBounds(219, 311, 63, 19);
		frame.getContentPane().add(uncondField);
		uncondField.setColumns(10);
		
		condField = new JTextField();
		condField.setBounds(226, 362, 63, 19);
		frame.getContentPane().add(condField);
		condField.setColumns(10);
		
		callbackField = new JTextField();
		callbackField.setBounds(226, 417, 63, 19);
		frame.getContentPane().add(callbackField);
		callbackField.setColumns(10);
		
		arithmeticField = new JTextField();
		arithmeticField.setBounds(226, 462, 63, 19);
		frame.getContentPane().add(arithmeticField);
		arithmeticField.setColumns(10);
		
		JLabel lblCycleTime = new JLabel("cycle Time");
		lblCycleTime.setBounds(348, 206, 92, 15);
		frame.getContentPane().add(lblCycleTime);
		
		callCycle = new JTextField();
		callCycle.setColumns(10);
		callCycle.setBounds(348, 417, 69, 19);
		frame.getContentPane().add(callCycle);
		
		unCycle = new JTextField();
		unCycle.setColumns(10);
		unCycle.setBounds(348, 323, 69, 19);
		frame.getContentPane().add(unCycle);
		
		condCycle = new JTextField();
		condCycle.setColumns(10);
		condCycle.setBounds(348, 362, 69, 19);
		frame.getContentPane().add(condCycle);
		
		arthCycle = new JTextField();
		arthCycle.setColumns(10);
		arthCycle.setBounds(348, 462, 69, 19);
		frame.getContentPane().add(arthCycle);
	}
}
