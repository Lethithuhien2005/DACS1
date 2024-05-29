package Interfaces;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReadFile extends JFrame implements Runnable {
	private JTextArea jTextArea_contentFile;
	private FileReader fr;
	private BufferedReader br;
	private String file;

	public ReadFile(String fileName) {
		this.file = fileName;

		this.setTitle("Read file");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font f = new Font("Arial", Font.BOLD, 15);

		jTextArea_contentFile = new JTextArea();
		jTextArea_contentFile.setFont(f);

		JScrollPane jScrollPane = new JScrollPane(jTextArea_contentFile);

		JButton jButton_back = new JButton("Back");
		jButton_back.setFont(f);
		jButton_back.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(ReadFile.class.getResource("Back.png"))));
		jButton_back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new HomePageView();

			}
		});

		this.setLayout(new BorderLayout());
		this.add(jScrollPane, BorderLayout.CENTER);
		this.add(jButton_back, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	@Override
	public void run() {
		readFile(file);
			for (int i=0; i<100; i++) {
				System.out.println("Thread " + file + " is running!");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}

	private void readFile(String fileName) {

		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String s;
			while ((s = br.readLine()) != null) {
				jTextArea_contentFile.append(s + "\n");
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
