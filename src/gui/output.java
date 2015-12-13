package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Main.Main;
import Main.ROBentry;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class output extends JFrame {

	private DefaultTableModel tableModel;
	private DefaultTableModel tableModel1;
	private DefaultTableModel tableModel2;
	private DefaultTableModel tableModel3;
	private DefaultTableModel tableModel4;
	private DefaultTableModel tableModel5;






	private JPanel contentPane;
	private ROBentry []ROB;

	/**
	 * Launch the application.
	 */
	public static Main main;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;
	private JTable table_5;
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					output frame = new output(main);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public output(Main m){
		main = m;
		initialize();
	}
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRunCycle = new JButton("Run Cycle");
		
		btnRunCycle.setBounds(657, 523, 117, 25);
		contentPane.add(btnRunCycle);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 55, 290, 168);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel northPanel = new JPanel();
		JLabel lblField1 = new JLabel("Instruction   ");
		JLabel lblField2 = new JLabel("Dest   ");
		JLabel lblField3 = new JLabel("value   ");
		JLabel lblField4 = new JLabel("Ready   ");
		northPanel.add(lblField1);
		northPanel.add(lblField2);
		northPanel.add(lblField3);
		northPanel.add(lblField4);
		getContentPane().add(northPanel, BorderLayout.NORTH);
		getContentPane().add(scrollPane,BorderLayout.CENTER);
		tableModel = new DefaultTableModel(new Object[]{"instruction","dest","value","ready"},0);
		tableModel.addRow(new Object[]{"asddsa","eefefe","ds","sd"});
		table.setModel(tableModel);
		
		JLabel lblRob = new JLabel("ROB");
		lblRob.setBounds(192, 28, 70, 15);
		contentPane.add(lblRob);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(314, 40, 460, 183);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JPanel northPanel1 = new JPanel();
		JLabel lblField5 = new JLabel("type  ");
		JLabel lblField6 = new JLabel("State  ");
		JLabel lblField7 = new JLabel("PcPos   ");
		JLabel lblField8 = new JLabel("RS   ");
		JLabel lblField9 = new JLabel("RT   ");
		JLabel lblField10 = new JLabel("RD  ");
		JLabel lblField11 = new JLabel("Exec ");
		JLabel lblField12 = new JLabel("WB   ");
		JLabel lblField13 = new JLabel("RS index  ");


		northPanel1.add(lblField5);
		northPanel1.add(lblField6);
		northPanel1.add(lblField7);
		northPanel1.add(lblField8);
		northPanel1.add(lblField9);
		northPanel1.add(lblField10);
		northPanel1.add(lblField11);
		northPanel1.add(lblField12);
		northPanel1.add(lblField13);

		getContentPane().add(northPanel1, BorderLayout.NORTH);
		getContentPane().add(scrollPane_1,BorderLayout.CENTER);
		tableModel1 = new DefaultTableModel(new Object[]{"type","state","pcpos","rs","rt","rd","exec","wb","rs index"},0);
		tableModel1.addRow(new Object[]{"asddsa","eefefe","ds","sd","das","asddas","asddsa","sds","saa"});
		table_1.setModel(tableModel1);
		
		JLabel lblInstructionBuffer = new JLabel("Instruction buffer");
		lblInstructionBuffer.setBounds(482, 12, 219, 15);
		contentPane.add(lblInstructionBuffer);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(23, 266, 283, 183);
		contentPane.add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
		JPanel northPanel2 = new JPanel();
		JLabel lblField14 = new JLabel("type  ");
		JLabel lblField15 = new JLabel("Busy  ");
		JLabel lblField16 = new JLabel("Op   ");
		JLabel lblField17 = new JLabel("Vj   ");
		JLabel lblField18 = new JLabel("Vk   ");
		JLabel lblField19 = new JLabel("Qj  ");
		JLabel lblField20 = new JLabel("Qk ");
		JLabel lblField21 = new JLabel("Dest  ");


		northPanel2.add(lblField14);
		northPanel2.add(lblField15);
		northPanel2.add(lblField16);
		northPanel2.add(lblField17);
		northPanel2.add(lblField18);
		northPanel2.add(lblField19);
		northPanel2.add(lblField20);
		northPanel2.add(lblField21);

		getContentPane().add(northPanel2, BorderLayout.NORTH);
		getContentPane().add(scrollPane_2,BorderLayout.CENTER);
		tableModel2 = new DefaultTableModel(new Object[]{"type","busy","op","vj","vk","qj","qk","dest"},0);
		tableModel2.addRow(new Object[]{"asddsa","eefefe","ds","sd","das","asddas","asddsa","sds"});
		table_2.setModel(tableModel2);
		
		JLabel lblReservationStations = new JLabel("Reservation Stations");
		lblReservationStations.setBounds(76, 239, 186, 15);
		contentPane.add(lblReservationStations);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(346, 267, 413, 56);
		contentPane.add(scrollPane_3);
		
		table_3 = new JTable();
		scrollPane_3.setViewportView(table_3);
		
		JPanel northPanel3 = new JPanel();
		JLabel lblField22 = new JLabel("R0  ");
		JLabel lblField23 = new JLabel("R1  ");
		JLabel lblField24 = new JLabel("R2   ");
		JLabel lblField25 = new JLabel("R3  ");
		JLabel lblField26 = new JLabel("R4   ");
		JLabel lblField27 = new JLabel("R5  ");
		JLabel lblField28 = new JLabel("R6 ");
		JLabel lblField29 = new JLabel("R7  ");


		northPanel3.add(lblField22);
		northPanel3.add(lblField23);
		northPanel3.add(lblField24);
		northPanel3.add(lblField25);
		northPanel3.add(lblField26);
		northPanel3.add(lblField27);
		northPanel3.add(lblField28);
		northPanel3.add(lblField29);

		getContentPane().add(northPanel3, BorderLayout.NORTH);
		getContentPane().add(scrollPane_3,BorderLayout.CENTER);
		tableModel3 = new DefaultTableModel(new Object[]{"r0","r1","r2","r3","r4","r5","r6","r7"},0);
		tableModel3.addRow(new Object[]{"asddsa","eefefe","ds","sd","das","asddas","asddsa","sds"});
		table_3.setModel(tableModel3);
		
		
		JLabel lblRegisterStatus = new JLabel("Register Status");
		lblRegisterStatus.setBounds(482, 239, 128, 15);
		contentPane.add(lblRegisterStatus);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(12, 12, 129, 38);
		contentPane.add(scrollPane_4);
		
		table_4 = new JTable();
		scrollPane_4.setViewportView(table_4);
		
		JPanel northPanel4 = new JPanel();
		JLabel lblField30 = new JLabel("Head  ");
		JLabel lblField31 = new JLabel("Tail ");

		northPanel4.add(lblField30);
		northPanel4.add(lblField31);

		getContentPane().add(northPanel4, BorderLayout.NORTH);
		getContentPane().add(scrollPane_4,BorderLayout.CENTER);
		tableModel4 = new DefaultTableModel(new Object[]{"head","tail"},0);
		tableModel4.addRow(new Object[]{"asddsa","eefefe"});
		table_4.setModel(tableModel4);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(346, 354, 299, 206);
		contentPane.add(scrollPane_5);
		
		JPanel northPanel5 = new JPanel();
		JLabel lblField32 = new JLabel("Address  ");
		JLabel lblField33 = new JLabel("Data ");

		northPanel5.add(lblField32);
		northPanel5.add(lblField33);

		getContentPane().add(northPanel5, BorderLayout.NORTH);
		getContentPane().add(scrollPane_5,BorderLayout.CENTER);
		
		table_5 = new JTable();
		scrollPane_5.setViewportView(table_5);
		
		tableModel5 = new DefaultTableModel(new Object[]{"address","data"},0);
		tableModel5.addRow(new Object[]{"asddsa","eefefe"});
		table_5.setModel(tableModel5);
		JLabel lblMemory = new JLabel("Memory");
		lblMemory.setBounds(460, 327, 70, 15);
		contentPane.add(lblMemory);
		
		btnRunCycle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.run();
				ROB = main.getROB();
//				System.out.println(""+ROB[1].getInstruction());
//				System.out.println(Main.getR1());

				for(int i = 1; i<ROB.length; i++){
					if(ROB[i].getInstruction() != 0)
				 tableModel.addRow(new Object[]{""+ROB[i].getInstruction(),ROB[i].getDest()});
				}
			}
		});

	}
}
