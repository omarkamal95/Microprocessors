package gui;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import Main.DCache;
import Main.ICache;
import Main.MainMemory;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Assembly {

	private JFrame frame;
	private String text;
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
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Assembly window = new Assembly(DCaches, ICaches, mmcycleTime, pipelineWidth, InstructionBufferSize, RScount, ROBcount, FUcycles);
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
	public Assembly(ArrayList<DCache> d, ArrayList<ICache> i, int m, int p, int ibs, int rsc, int rob, int fuc) {
		DCaches = d;
		ICaches = i;
		mmcycleTime = m;
		pipelineWidth = p;
		InstructionBufferSize = ibs;
		RScount = rsc;
		ROBcount = rob;
		FUcycles = fuc;
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
		
		JLabel lblWriteYourAssembly = new JLabel("Write your Assembly code");
		springLayout.putConstraint(SpringLayout.NORTH, lblWriteYourAssembly, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblWriteYourAssembly, 121, SpringLayout.WEST, frame.getContentPane());
		lblWriteYourAssembly.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		frame.getContentPane().add(lblWriteYourAssembly);
		
		JLabel lblOrg = new JLabel("ORG:");
		springLayout.putConstraint(SpringLayout.WEST, lblOrg, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblOrg, -222, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(lblOrg);
		
		JLabel lblAssembly = new JLabel("Assembly:");
		springLayout.putConstraint(SpringLayout.NORTH, lblAssembly, 6, SpringLayout.SOUTH, lblOrg);
		springLayout.putConstraint(SpringLayout.WEST, lblAssembly, 0, SpringLayout.WEST, lblOrg);
		frame.getContentPane().add(lblAssembly);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, -6, SpringLayout.NORTH, lblOrg);
		springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblOrg);
		springLayout.putConstraint(SpringLayout.EAST, textField, -346, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		final JTextArea textArea = new JTextArea();
		springLayout.putConstraint(SpringLayout.NORTH, textArea, 6, SpringLayout.SOUTH, lblAssembly);
		springLayout.putConstraint(SpringLayout.WEST, textArea, 10, SpringLayout.WEST, lblAssembly);
		springLayout.putConstraint(SpringLayout.SOUTH, textArea, -5, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textArea, -85, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(textArea);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text = textArea.getText();
				assembly = text.split("//n");
				for(int i  = 0; i< assembly.length; i++)
				{
					System.out.println(assembly[i]);	
				}
				ProgramData p = new ProgramData(DCaches, ICaches, mmcycleTime, pipelineWidth, InstructionBufferSize, RScount, ROBcount, FUcycles, assembly);
				p.NewScreen();
				frame.setVisible(false);
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnNext, 0, SpringLayout.EAST, textArea);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNext, 0, SpringLayout.SOUTH, textArea);
		frame.getContentPane().add(btnNext);
	}
}
