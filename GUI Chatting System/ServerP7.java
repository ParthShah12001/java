import java.io.*;
import java.util.*;
import java.lang.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class ServerP7 extends Frame implements ActionListener,Runnable
{
	Label lf;
	TextArea tf,tt;
	Button bs,be;
	ServerSocket ss;
	Socket s;
	String str;
	DataOutputStream dos;
	DataInputStream dis;
	Thread th;

	ServerP7() throws Exception
	{
		super("Server");
		setBackground(new Color(34,139,34));
		lf = new Label("                                  ChatBox");
		lf.setFont(new Font("Bold",Font.PLAIN,20));
		lf.setBackground(new Color(0,0,0));
		lf.setForeground(new Color(34,139,34));
		bs = new Button("Send");
		be = new Button("Encrypt");
		bs.addActionListener(this);
		be.addActionListener(this);
		tf = new TextArea();
		tf.setEditable(false);
		tf.setBackground(new Color(0,0,0));
		tf.setForeground(new Color(34,139,34));
		tt = new TextArea();
		tt.setBackground(new Color(0,0,0));
		tt.setForeground(new Color(34,139,34));
		th = new Thread(this);
		setLayout(null);
		lf.setBounds(20,50,500,40);
		tf.setBounds(20,90,500,600);
		tt.setBounds(20,700,400,80);
		bs.setBounds(430,700,100,40);
		be.setBounds(430,740,100,40);
		setBackground(new Color(34,139,34));
		add(lf);
		add(tf);
		add(tt);
		add(bs);
		add(be);
		setSize(540,800);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e3)
			{
				System.exit(0);
			}
		});
		setVisible(true);
		ss = new ServerSocket(5051);
		s = ss.accept();
		dos = new DataOutputStream(s.getOutputStream());
		dis = new DataInputStream(s.getInputStream());
		th.start();
	}
	public void run()
	{
		try
		{
			while(true)
			{
				String t="";
				str = dis.readUTF();
				int m = str.length();
				m = m-1;
				if (str.charAt(m)=='`')
				{
					String g = str;
					str = str.substring(0,m);
					char []c = str.toCharArray();
					int z = c.length;
					int i=0;
					String en="";
					for(i=0;i<z;i++)
					{
						int ascii = c[i];
						ascii = ascii - 5;
						char q = (char)ascii;
						en = en + q;
					}
					 t = tf.getText()+"\n"+"\nMessage from Client:  "+g+"\n"+"\nDecrypted Message:  "+en;
				}
				else
				{
					 t = tf.getText()+"\n"+"\nMessage from Client:   "+str;
				}
				tf.setText(t);
			}
		}
		catch (Exception e)
		{
		}
	}
	public void actionPerformed(ActionEvent e1)
	{
		Button b = (Button)e1.getSource();
		if(b==bs)
		{
			try
			{
				str = tt.getText();
				dos.writeUTF(str);
				tt.setText("");
				String h = tf.getText()+"\nMessage to Client:  "+str;
				tf.setText(h);
			}
			catch (Exception e)
			{
			}
		}
		else if(b == be)
		{
			String en="";
			str = tt.getText();
			char [] c = str.toCharArray();
			int z = c.length;
			int i=0;
			for (i=0;i<z ;i++ )
			{
				int ascii = c[i];
				ascii = ascii + 5 ;
				char q = (char)ascii;
				en = en + q;
			}
			en = en + "`";
			try
			{
				str = en;
				dos.writeUTF(str);
				tt.setText("");
			}
			catch (Exception e)
			{
			}
		}
	}
}
class LoginPage extends JFrame implements ActionListener
{
	JLabel lid,pw;
	JButton bok,bcl;
	JTextField tid;
	JPasswordField pwd;
	String id,pid;
	LoginPage()throws Exception
	{
		lid = new JLabel("Login ID:");
		pw = new JLabel("Password:");
		tid = new JTextField(30);
		pwd = new JPasswordField(30);
		bok = new JButton("Login");
		bok.addActionListener(this);
		bcl = new JButton("Clear");
		bcl.addActionListener(this);
		setLayout(null);
		lid.setBounds(100,130,100,25);
		tid.setBounds(160,130,150,25);
		pw.setBounds(90,180,150,25);
		pwd.setBounds(160,180,150,25);
		bok.setBounds(120,225,75,30);
		bcl.setBounds(210,225,75,30);
		add(lid);
		add(tid);
		add(pw);
		add(pwd);
		add(bok);
		add(bcl);
		setSize(400,400);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e3)
			{
				System.exit(0);
			}
		});
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton)e.getSource();
		if(b == bok)
		{
			id = tid.getText();
			pid = pwd.getText();
			if(id.equals("Operator"))
			{
				if(pid.equals("Abc#1234"))
				{
					try
					{
						new ServerP7();		
					}
					catch (Exception e1)
					{
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Invalid password");
					pw.setText("");
					pw.requestFocus();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Invalid login ID");
				tid.setText("");
				tid.requestFocus();
			}
		}
		else
			tid.setText("");
			pw.setText("");
			tid.requestFocus();
	}
	public static void main(String[]args)throws Exception
	{
		new LoginPage();
	}
}