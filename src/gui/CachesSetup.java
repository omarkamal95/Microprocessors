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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
	
		JLabel lblLevelCache = new JLabel("Level " + currentLevel + " Cache:");
		lblLevelCache.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblLevelCache, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblLevelCache, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblLevelCache);
		
		JLabel lblEnterS = new JLabel("Enter S:");
		springLayout.putConstraint(SpringLayout.NORTH, lblEnterS, 6, SpringLayout.SOUTH, lblLevelCache);
		springLayout.putConstraint(SpringLayout.WEST, lblEnterS, 43, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblEnterS);
		
		JLabel lblNewLabel = new JLabel("Enter L:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 6, SpringLayout.SOUTH, lblEnterS);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, lblEnterS);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEnterM = new JLabel("Enter m:");
		springLayout.putConstraint(SpringLayout.NORTH, lblEnterM, 6, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblEnterM, 0, SpringLayout.WEST, lblEnterS);
		frame.getContentPane().add(lblEnterM);
		
		JLabel lblWriteHitPolicy = new JLabel("Write Hit policy:");
		springLayout.putConstraint(SpringLayout.NORTH, lblWriteHitPolicy, 23, SpringLayout.SOUTH, lblEnterM);
		springLayout.putConstraint(SpringLayout.WEST, lblWriteHitPolicy, 0, SpringLayout.WEST, lblEnterS);
		frame.getContentPane().add(lblWriteHitPolicy);
		
		JLabel lblNumberOfCycles = new JLabel("Number of cycles required to access data:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNumberOfCycles, 226, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNumberOfCycles, 0, SpringLayout.WEST, lblEnterS);
		frame.getContentPane().add(lblNumberOfCycles);
		
		textFieldS = new JTextField();
		textFieldS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				S = Integer.parseInt(textFieldS.getText());
				System.out.println(S);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, textFieldS, 32, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textFieldS, 21, SpringLayout.EAST, lblEnterS);
		springLayout.putConstraint(SpringLayout.EAST, textFieldS, -258, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(textFieldS);
		textFieldS.setColumns(10);
		
		textFieldL = new JTextField();
		textFieldL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L = Integer.parseInt(textFieldL.getText());
				System.out.println(L);
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldS, -6, SpringLayout.NORTH, textFieldL);
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldL, 0, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, textFieldL, 0, SpringLayout.EAST, textFieldS);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldL, 0, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, textFieldL, 0, SpringLayout.WEST, textFieldS);
		textFieldL.setColumns(10);
		frame.getContentPane().add(textFieldL);
		
		textFieldm = new JTextField();
		textFieldm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m = Integer.parseInt(textFieldm.getText());
				System.out.println(m);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, textFieldm, 0, SpringLayout.NORTH, lblEnterM);
		springLayout.putConstraint(SpringLayout.WEST, textFieldm, 0, SpringLayout.WEST, textFieldS);
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldm, -186, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textFieldm, -258, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(textFieldm);
		textFieldm.setColumns(10);
		
		final JRadioButton rdbtnNewRadioButton = new JRadioButton("Write Through");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnNewRadioButton, 3, SpringLayout.SOUTH, lblWriteHitPolicy);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnNewRadioButton, 139, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, rdbtnNewRadioButton, -126, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, rdbtnNewRadioButton, -202, SpringLayout.EAST, frame.getContentPane());
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNewRadioButton.getText().equals("Write Through")) whp = 2;
				
				System.out.println(whp);
			}
		});
		rdbtnNewRadioButton.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		final JRadioButton rdbtnWriteBack = new JRadioButton("Write Back");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnWriteBack, -3, SpringLayout.NORTH, rdbtnNewRadioButton);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnWriteBack, 52, SpringLayout.EAST, rdbtnNewRadioButton);
		rdbtnWriteBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(rdbtnWriteBack.getText().equals("Write Back")) whp = 1;
				System.out.println(whp);
			}
		});
		rdbtnWriteBack.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		frame.getContentPane().add(rdbtnWriteBack);
		
		textFieldAccessTime = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldAccessTime, -6, SpringLayout.NORTH, lblNumberOfCycles);
		springLayout.putConstraint(SpringLayout.WEST, textFieldAccessTime, 6, SpringLayout.EAST, lblNumberOfCycles);
		springLayout.putConstraint(SpringLayout.EAST, textFieldAccessTime, -74, SpringLayout.EAST, frame.getContentPane());
		textFieldAccessTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accessTime = Integer.parseInt(textFieldAccessTime.getText());
				System.out.println(accessTime);
			}
		});
		frame.getContentPane().add(textFieldAccessTime);
		textFieldAccessTime.setColumns(10);
		
		JLabel lblCycles = new JLabel("Cycles");
		springLayout.putConstraint(SpringLayout.NORTH, lblCycles, 3, SpringLayout.NORTH, lblNumberOfCycles);
		springLayout.putConstraint(SpringLayout.WEST, lblCycles, 6, SpringLayout.EAST, textFieldAccessTime);
		lblCycles.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		frame.getContentPane().add(lblCycles);
		
		JLabel lblReplacementPolicy = new JLabel("Replacement Policy:");
		springLayout.putConstraint(SpringLayout.WEST, lblReplacementPolicy, 0, SpringLayout.WEST, lblEnterS);
		frame.getContentPane().add(lblReplacementPolicy);
		
		final JRadioButton rdbtnNewRadioButtonLRU = new JRadioButton("LRU");
		rdbtnNewRadioButtonLRU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(rdbtnNewRadioButtonLRU.getText().equals("LRU")) replacementPolicy = 1;
				System.out.println(replacementPolicy);
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, lblReplacementPolicy, -6, SpringLayout.NORTH, rdbtnNewRadioButtonLRU);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnNewRadioButtonLRU, 139, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnNewRadioButtonLRU, 202, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, rdbtnNewRadioButtonLRU, -6, SpringLayout.NORTH, lblNumberOfCycles);
		rdbtnNewRadioButtonLRU.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		frame.getContentPane().add(rdbtnNewRadioButtonLRU);
		
		final JRadioButton rdbtnRandom = new JRadioButton("Random");
		rdbtnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnRandom.getText().equals("Random")) replacementPolicy = 2;
				System.out.println(replacementPolicy);
			}
		});
		springLayout.putConstraint(SpringLayout.EAST, rdbtnNewRadioButtonLRU, -96, SpringLayout.WEST, rdbtnRandom);
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnRandom, 48, SpringLayout.SOUTH, rdbtnWriteBack);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnRandom, 310, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, rdbtnRandom, -6, SpringLayout.NORTH, lblNumberOfCycles);
		springLayout.putConstraint(SpringLayout.EAST, rdbtnRandom, 0, SpringLayout.EAST, rdbtnWriteBack);
		rdbtnRandom.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		frame.getContentPane().add(rdbtnRandom);
		
		JLabel lblIfWriteBack = new JLabel("If Write Back, enter Buffer Size:");
		springLayout.putConstraint(SpringLayout.WEST, lblIfWriteBack, 0, SpringLayout.WEST, lblEnterS);
		springLayout.putConstraint(SpringLayout.SOUTH, lblIfWriteBack, -6, SpringLayout.NORTH, lblReplacementPolicy);
		frame.getContentPane().add(lblIfWriteBack);
		
		BufferSizet = new JTextField();
		BufferSizet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bufferSize = Integer.parseInt(BufferSizet.getText());
				System.out.println(bufferSize);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, BufferSizet, 6, SpringLayout.SOUTH, rdbtnNewRadioButton);
		springLayout.putConstraint(SpringLayout.WEST, BufferSizet, 6, SpringLayout.EAST, lblIfWriteBack);
		springLayout.putConstraint(SpringLayout.SOUTH, BufferSizet, 175, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, BufferSizet, -14, SpringLayout.EAST, lblNumberOfCycles);
		frame.getContentPane().add(BufferSizet);
		BufferSizet.setColumns(10);
		
		JButton btnSubmit = new JButton("Next");
		springLayout.putConstraint(SpringLayout.SOUTH, btnSubmit, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSubmit, -10, SpringLayout.EAST, frame.getContentPane());
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				S = Integer.parseInt(textFieldS.getText());
				L = Integer.parseInt(textFieldL.getText());
				m = Integer.parseInt(textFieldm.getText());
				if(rdbtnNewRadioButton.getText().equals("Write Through")) whp = 2;
				if(rdbtnWriteBack.getText().equals("Write Back")) whp = 1;
				accessTime = Integer.parseInt(textFieldAccessTime.getText());
				if(rdbtnNewRadioButtonLRU.getText().equals("LRU")) replacementPolicy = 1;
				if(rdbtnRandom.getText().equals("Random")) replacementPolicy = 2;
				bufferSize = Integer.parseInt(BufferSizet.getText());
				
				DCache d = new DCache(S, L, m, accessTime, whp, replacementPolicy, bufferSize);
				ICache i = new ICache(S, L, m, accessTime);
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
	}
}
