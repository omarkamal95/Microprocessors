package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import Main.DCache;
import Main.ICache;
import Main.MainMemory;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class CachesSetup {

	private JFrame frame;
	private static int levels;
	private static int currentLevel = 1;
	public ArrayList<DCache> DCaches;
	public ArrayList<ICache> ICaches;
	private JTextField textFieldS;
	private JTextField textFieldL;
	private JTextField textFieldm;
	private JTextField textFieldAccessTime;
	
	private int S;
	private int L;
	private int m;
	private int whp;
	private int accessTime;
	private static int mmaccessTime;
	private int replacementPolicy;
	private int bufferSize;
	private static MainMemory memory;
	private JTextField BufferSizet;
	

	/**
	 * Create the application.
	 * @param levelOfCaches 
	 */
	public CachesSetup(int a, int l) {
		levels = l;
		DCaches = new ArrayList();
		ICaches = new ArrayList();
		mmaccessTime = a;
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						CachesSetup window = new CachesSetup(mmaccessTime, levels);
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
		frame.setBounds(100, 100, 650, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		JLabel lblLevelCache = new JLabel("Level " + currentLevel + " Cache:");
		lblLevelCache.setBounds(10, 10, 117, 17);
		lblLevelCache.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		frame.getContentPane().add(lblLevelCache);
		
		JLabel lblEnterS = new JLabel("Enter S:");
		lblEnterS.setBounds(43, 33, 56, 15);
		frame.getContentPane().add(lblEnterS);
		
		JLabel lblNewLabel = new JLabel("Enter L:");
		lblNewLabel.setBounds(43, 54, 55, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEnterM = new JLabel("Enter m:");
		lblEnterM.setBounds(43, 75, 59, 15);
		frame.getContentPane().add(lblEnterM);
		
		JLabel lblWriteHitPolicy = new JLabel("Write Hit policy:");
		lblWriteHitPolicy.setBounds(43, 113, 113, 15);
		frame.getContentPane().add(lblWriteHitPolicy);
		
		JLabel lblNumberOfCycles = new JLabel("Number of cycles required to access data:");
		lblNumberOfCycles.setBounds(53, 291, 298, 15);
		frame.getContentPane().add(lblNumberOfCycles);
		
		textFieldS = new JTextField();
		textFieldS.setBounds(120, 32, 272, 16);
		textFieldS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				S = Integer.parseInt(textFieldS.getText());
				System.out.println(S);
			}
		});
		frame.getContentPane().add(textFieldS);
		textFieldS.setColumns(10);
		
		textFieldL = new JTextField();
		textFieldL.setBounds(120, 54, 272, 15);
		textFieldL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L = Integer.parseInt(textFieldL.getText());
				System.out.println(L);
			}
		});
		textFieldL.setColumns(10);
		frame.getContentPane().add(textFieldL);
		
		textFieldm = new JTextField();
		textFieldm.setBounds(120, 75, 272, 17);
		textFieldm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m = Integer.parseInt(textFieldm.getText());
				System.out.println(m);
			}
		});
		frame.getContentPane().add(textFieldm);
		textFieldm.setColumns(10);
		
		final JRadioButton rdbtnNewRadioButton = new JRadioButton("Write Through");
		rdbtnNewRadioButton.setBounds(139, 131, 195, 22);
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNewRadioButton.getText().equals("Write Through")) whp = 2;
				
				System.out.println(whp);
			}
		});
		rdbtnNewRadioButton.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		final JRadioButton rdbtnWriteBack = new JRadioButton("Write Back");
		rdbtnWriteBack.setBounds(358, 131, 123, 22);
		rdbtnWriteBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(rdbtnWriteBack.getText().equals("Write Back")) whp = 1;
				System.out.println(whp);
			}
		});
		rdbtnWriteBack.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		frame.getContentPane().add(rdbtnWriteBack);
		
		textFieldAccessTime = new JTextField();
		textFieldAccessTime.setBounds(385, 289, 229, 19);
		textFieldAccessTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accessTime = Integer.parseInt(textFieldAccessTime.getText());
				System.out.println(accessTime);
			}
		});
		frame.getContentPane().add(textFieldAccessTime);
		textFieldAccessTime.setColumns(10);
		
		JLabel lblReplacementPolicy = new JLabel("Replacement Policy:");
		lblReplacementPolicy.setBounds(43, 224, 142, 15);
		frame.getContentPane().add(lblReplacementPolicy);
		
		final JRadioButton rdbtnNewRadioButtonLRU = new JRadioButton("LRU");
		rdbtnNewRadioButtonLRU.setBounds(139, 245, 75, 18);
		rdbtnNewRadioButtonLRU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(rdbtnNewRadioButtonLRU.getText().equals("LRU")) replacementPolicy = 1;
				System.out.println(replacementPolicy);
			}
		});
		rdbtnNewRadioButtonLRU.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		frame.getContentPane().add(rdbtnNewRadioButtonLRU);
		
		final JRadioButton rdbtnRandom = new JRadioButton("Random");
		rdbtnRandom.setBounds(310, 241, 274, 22);
		rdbtnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnRandom.getText().equals("Random")) replacementPolicy = 2;
				System.out.println(replacementPolicy);
			}
		});
		rdbtnRandom.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		frame.getContentPane().add(rdbtnRandom);
		
		JLabel lblIfWriteBack = new JLabel("If Write Back, enter Buffer Size:");
		lblIfWriteBack.setBounds(43, 197, 223, 15);
		frame.getContentPane().add(lblIfWriteBack);
		
		JButton btnSubmit = new JButton("Next");
		btnSubmit.setBounds(574, 347, 66, 25);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				S = Integer.parseInt(textFieldS.getText());
//				L = Integer.parseInt(textFieldL.getText());
//				m = Integer.parseInt(textFieldm.getText());
//				if(rdbtnNewRadioButton.getText().equals("Write Through")) whp = 2;
//				if(rdbtnWriteBack.getText().equals("Write Back")) whp = 1;
//				accessTime = Integer.parseInt(textFieldAccessTime.getText());
//				if(rdbtnNewRadioButtonLRU.getText().equals("LRU")) replacementPolicy = 1;
//				if(rdbtnRandom.getText().equals("Random")) replacementPolicy = 2;
//				bufferSize = Integer.parseInt(BufferSizet.getText());
				
				// For testing
				S = 64;
				L = 4;
				m = 2;
				whp = 2;
				replacementPolicy = 1;
				bufferSize = 2;
				accessTime = 4;
				
				DCache d = new DCache(S, L, m, accessTime, whp, replacementPolicy, bufferSize);
				ICache i = new ICache(S, L, m, accessTime,replacementPolicy);
				DCaches.add(d);
				ICaches.add(i);
				if(levels != 1)
				{
					NewScreen();
					frame.setVisible(false);
					levels--;
					currentLevel++;
				}
				else
				{
					HardwareSetup h = new HardwareSetup(mmaccessTime, DCaches, ICaches);
					h.NewScreen();
					frame.setVisible(false);
				}
				
			}
		});
		frame.getContentPane().add(btnSubmit);
		
		BufferSizet = new JTextField();
		BufferSizet.setBounds(284, 195, 114, 19);
		frame.getContentPane().add(BufferSizet);
		BufferSizet.setColumns(10);
	}
}
