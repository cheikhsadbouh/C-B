package candb;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CandB extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextArea edit;
	static JTextArea showMsg;
	static JTextArea showOnline;
	JButton send;
	JLabel lab;
	JLabel logoImage;
	JButton btn;
	static Socket con;

	public static void main(String[] args) throws IOException {
		new CandB();
		try{
			con = new Socket("192.168.1.3", 6789);
			showOnline.setText(con.getInetAddress().getHostName());
			while(true){
				try{
					DataInputStream in = new DataInputStream(
							con.getInputStream());
					String msg = in.readUTF();
					showMsg.setText(showMsg.getText() + "\n"+ con.getInetAddress().getHostName() + ": " + msg);
				}catch (Exception e){
					showMsg.setText(showMsg.getText() + "\n" + e.getMessage());
					try{
						Thread.sleep(2000);
						break;
					}catch(InterruptedException e2){
						showMsg.setText(showMsg.getText() + "\n"+ e2.getMessage());
					}
				}
			}
		}catch(IOException e){
			showMsg.setText(showMsg.getText() + "\n" + e.getMessage());
		}finally{
		//	if(con.isConnected())
				con.close();
		}

	}

	public CandB(){
		setSize(new Dimension(800, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("C&B Chat Application");
		setResizable(false);
		setLocationRelativeTo(null);

		getContentPane().setBackground(Color.decode("#4c7e9b"));
		// online
		showOnline = new JTextArea();
		// showOnline.setBackground(Color.decode("#2EBDFF"));
		showOnline.setEditable(false);
		// showOnline.setForeground(Color.WHITE);
		showOnline.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		showOnline.setLineWrap(false);
		showOnline.setVisible(true);

		JScrollPane jsp1 = new JScrollPane(showOnline);
		jsp1.setBounds(550, 10, 230, 450);
		jsp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		// ------End------------
		// Show message
		showMsg = new JTextArea();
		// showMsg.setBackground(Color.decode("#2EBDFF"));
		showMsg.setEditable(false);
		// showMsg.setForeground(Color.WHITE);
		showMsg.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		showMsg.setLineWrap(true);
		showMsg.setVisible(true);
		JScrollPane jsp2 = new JScrollPane(showMsg);
		jsp2.setBounds(10, 100, 500, 200);
		jsp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// ------End------------
		// LOGO
		logoImage = new JLabel();
		logoImage.setIcon(new ImageIcon("logo.png"));
		logoImage.setBounds(150, 10, 200, 80);
		// ------END-------
		// Label
		lab = new JLabel("Type your Msg: ");
		lab.setBounds(10, 310, 200, 25);
		lab.setForeground(Color.WHITE);
		lab.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		// ------END------
		// Text Edit
		edit = new JTextArea();
		edit.setBackground(Color.decode("#2EBDFF"));
		edit.setForeground(Color.WHITE);
		edit.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		edit.setBounds(10, 345, 350, 100);
		edit.setWrapStyleWord(true);
		edit.setVisible(true);
		// --------END---------
		// Bitton
		send = new JButton("Send");
		send.setBackground(Color.decode("#3366FF"));
		send.setForeground(Color.WHITE);
		send.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		send.setBounds(370, 395, 140, 50);
		send.addActionListener(this);
		// ------END--------
		// color chooser
		btn = new JButton("BG color");

		btn.setBounds(10, 20, 130, 30);
		btn.addActionListener(this);

		// -------END-----
		add(logoImage);
		add(btn);
		add(jsp1);
		add(lab);
		add(edit);
		add(send);
		add(jsp2);
		setLayout(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {
			Color color = Color.decode("#4c7e9b");
			Color colC = JColorChooser.showDialog(this, "Choose", color);
			getContentPane().setBackground(colC);
		}
		if (e.getSource() == send && !edit.getText().equals("")) {
			String str = edit.getText();
			try {
				try {
					DataOutputStream out = new DataOutputStream(
							con.getOutputStream());
					out.writeUTF(str);
				} catch (NullPointerException ne) {
					showMsg.setText(showMsg.getText() + "\n  Pas de cnx");
				}
			} catch (IOException e1) {
				try {
					Thread.sleep(2000);

				} catch (Exception e2) {

				}
				showMsg.setText(showMsg.getText() + "\n" + e1.getMessage());
			}
			edit.setText("");
			showMsg.setText(showMsg.getText() + "\nMyC&B :" + str);
		}

	}

}
