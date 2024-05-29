package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;


public class WelcomePage extends JFrame implements Runnable {
	
	private JMenuBar jMenuBar;
	private JMenu jMenu_setting;
	private JMenuItem jMenuItem_logIn;
	private JMenuItem jMenuItem_exit;
	private JLabel jLabel_medicalFile;
	private JLabel jLabel_image;


	public WelcomePage() {
		this.setTitle("Medical file management");
		this.setSize(600, 450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage(WelcomePage.class.getResource("IconMedical.png")));
		
		Font font = new Font("Arial", Font.BOLD, (15));
		
		
		jMenuBar = new JMenuBar();
		
		jMenu_setting = new JMenu("Setting");
		jMenu_setting.setFont(font);
		jMenu_setting.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(WelcomePage.class.getResource("Setting.png"))));
		
		jMenuItem_logIn = new JMenuItem("Log in");
		jMenuItem_logIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String src = e.getActionCommand();
				if (src.equals("Log in")) {
					new LogInAdmin();
				}
			}
		});
		jMenuItem_logIn.setFont(font);
		jMenuItem_logIn.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(WelcomePage.class.getResource("LogIn.png"))));
		jMenuItem_exit = new JMenuItem("Exit");
		jMenuItem_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String src = e.getActionCommand();
				if (src.equals("Exit")) {
					System.exit(0);
				}
				
			}
		});
		
		jMenuItem_exit.setFont(font);
		jMenuItem_exit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(WelcomePage.class.getResource(("Exit.png")))));
		// Create key combination for jMenuItem "Exit":
		jMenuItem_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		
		jMenu_setting.add(jMenuItem_logIn);
		jMenu_setting.add(jMenuItem_exit);
		
		jMenuBar.add(jMenu_setting);
		
		jLabel_medicalFile = new JLabel("Medical file", jLabel_medicalFile.CENTER);
		jLabel_medicalFile.setFont(new Font("Times New Roman", Font.BOLD, 70));
		jLabel_medicalFile.setForeground(Color.RED);
		
		JPanel jPanel_text = new JPanel();
		jPanel_text.setLayout(new BorderLayout());
		jPanel_text.add(jLabel_medicalFile);

		jLabel_image = new JLabel();
		jLabel_image.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(WelcomePage.class.getResource("Profile.png"))));
		
		JPanel jPanel_image = new JPanel();
		jPanel_image.add(jLabel_image);
		
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(2,1));
		jPanel.add(jPanel_text);
		jPanel.add(jPanel_image);
		
		this.setJMenuBar(jMenuBar);
		this.setLayout(new BorderLayout());
		this.add(jPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}


	@Override
	public void run() {
		new WelcomePage();
	}
}
