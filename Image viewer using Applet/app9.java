import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code="app9" width =500 height=500></applet>*/
class MyPanel extends Panel
{
	Image img;
	MyPanel()
	{
		super();
	}
	void setImage(Image img)
	{
		this.img=img;
		repaint();
	}
	public void paint(Graphics g)
	{
		g.drawImage(img,50,50,250,250,this);
	}
}
public class app9 extends Applet implements ActionListener
{
	Button bp,bn,bf,bl;
	int index;
	Image[]arr;
	MyPanel cp;
	Panel sp;
	public void init()
	{
		arr=new Image[10];
		index=0;
		while(index<10)
		{
			arr[index] = getImage(getCodeBase(),"img"+index+".jpg");
			index++;
		}
		index=0;
		cp = new MyPanel();
		sp = new Panel();
		bf = new Button("|<");
		bp = new Button("<<");
		bn = new Button(">>");
		bl = new Button(">|");
		bf.addActionListener(this);
		bp.addActionListener(this);
		bn.addActionListener(this);
		bl.addActionListener(this);
		sp.add(bf);
		sp.add(bp);
		sp.add(bn);
		sp.add(bl);
		setLayout(new GridLayout());
		add(cp,"center");
		add(sp,"south");
		cp.setImage(arr[0]);
	}
	public void actionPerformed(ActionEvent e)
	{
		Button b = (Button)e.getSource();
		if(b==bf)
			index=0;
		if(b==bl)
			index=9;
		if(b==bp)
		{
			if(--index<0)
				index=0;
		}
		if(b==bn)
		{
			if(++index>9)
				index=9;
		}
		cp.setImage(arr[index]);
	}
	public void paint(Graphics g)
	{}
}