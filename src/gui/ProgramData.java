package gui;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import Main.DCache;
import Main.ICache;
import Main.Main;
import Main.MainMemory;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class ProgramData {

	private JFrame frame;
	
	private static int pipelineWidth;
	private static int InstructionBufferSize;
	private static int RScount;
	private static int ROBcount;
	private static int FUcycles;
	private static int mmcycleTime;
	private static MainMemory memory;
	public static ArrayList<DCache> DCaches;
	public static ArrayList<ICache> ICaches;
	private static String [] assembly;
	private static ArrayList<String> Data;
	private JTextField textField;
	private JTextField textField_1;
	

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgramData window = new ProgramData(DCaches, ICaches, mmcycleTime, pipelineWidth, InstructionBufferSize,
							RScount, ROBcount, FUcycles, assembly);
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
	public ProgramData(ArrayList<DCache> d, ArrayList<ICache> i, int m, int p, int ibs, int rsc, int rob, int fuc, String[] ass) {
		DCaches = d;
		ICaches = i;
		mmcycleTime = m;
		pipelineWidth = p;
		InstructionBufferSize = ibs;
		RScount = rsc;
		ROBcount = rob;
		FUcycles = fuc;
		assembly = ass;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblLast = new JLabel("Last Step");
		lblLast.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		springLayout.putConstraint(SpringLayout.NORTH, lblLast, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblLast, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblLast);
		
		JLabel lblEnterInitialProgram = new JLabel("Enter initial program data if any:");
		lblEnterInitialProgram.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		springLayout.putConstraint(SpringLayout.NORTH, lblEnterInitialProgram, 6, SpringLayout.SOUTH, lblLast);
		springLayout.putConstraint(SpringLayout.WEST, lblEnterInitialProgram, 0, SpringLayout.WEST, lblLast);
		frame.getContentPane().add(lblEnterInitialProgram);
		
		JLabel lblData = new JLabel("Data:");
		springLayout.putConstraint(SpringLayout.NORTH, lblData, 22, SpringLayout.SOUTH, lblEnterInitialProgram);
		springLayout.putConstraint(SpringLayout.WEST, lblData, 69, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblData);
		
		JLabel lblAddress = new JLabel("Address:");
		springLayout.putConstraint(SpringLayout.NORTH, lblAddress, 24, SpringLayout.SOUTH, lblData);
		springLayout.putConstraint(SpringLayout.WEST, lblAddress, 0, SpringLayout.WEST, lblData);
		frame.getContentPane().add(lblAddress);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 22, SpringLayout.SOUTH, lblEnterInitialProgram);
		springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblData);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, 38, SpringLayout.SOUTH, lblEnterInitialProgram);
		springLayout.putConstraint(SpringLayout.EAST, textField, 140, SpringLayout.EAST, lblData);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, -2, SpringLayout.NORTH, lblAddress);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 6, SpringLayout.EAST, lblAddress);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_1, 17, SpringLayout.NORTH, lblAddress);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, lblEnterInitialProgram);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Data.add(Integer.parseInt(textField.getText()), textField_1.getText());
				MainMemory m = new MainMemory(mmcycleTime);
				Main main = new Main(DCaches, ICaches, m, pipelineWidth, InstructionBufferSize, RScount, ROBcount, FUcycles, assembly, Data);
			}
		});
		springLayout.putConstraint(SpringLayout.EAST, btnDone, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnDone);
		
		JButton btnMoreData = new JButton("More data");
		btnMoreData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Data.add(Integer.parseInt(textField.getText()), textField_1.getText());
				NewScreen();
				frame.setVisible(false);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnDone, 0, SpringLayout.NORTH, btnMoreData);
		springLayout.putConstraint(SpringLayout.NORTH, btnMoreData, 6, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, btnMoreData, -108, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnMoreData);
	}

}
