import java.awt.*;
import java.awt.event.*;
class Ball extends Thread
{
	Panel pobj;
	static int xsize=10;
	static int ysize=10;
	int x,y,dx,dy;
	Color cr;
	Ball(Panel p)
	{
		super();
		pobj=p;
		x=0;
		y=(int)(Math.random()*500);
		dx=dy=2;
		cr = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
		start();
	}
	public void run()
	{
		while(true)
		{
			move();
			try
			{
				Thread.sleep(100);
			}
			catch (Exception e)
			{
			}
		}
	}
	public void move()
	{
		Graphics g = pobj.getGraphics();
		g.setColor(Color.WHITE);
		g.fillOval(x,y,xsize,ysize);
		x+=dx;
		y+=dy;
		g.setColor(cr);
		g.fillOval(x,y,xsize,ysize);
		Dimension d = pobj.getSize();
		if(x<0)
		{
			x=0;
			dx=-dx;
		}
		if(x>d.width)
		{
			x=d.width-xsize;
			dx=-dx;
		}
		if(y<0)
		{
			y=0;
			dy=-dy;
		}
		if(y>d.height)
		{
			y=d.height-ysize;
			dy=-dy;
		}
	}
}
class BouncingBall extends Frame implements ActionListener
{
	Panel p,q;
	Button bn,be;
	BouncingBall()
	{
		p = new Panel();
		q = new Panel();
		bn = new Button("New Ball");
		be = new Button("Exit");
		bn.addActionListener(this);
		be.addActionListener(this);
		q.add(bn);
		q.add(be);
		add(q,"South");
		add(p,"Center");
		setSize(500,500);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		Ball a = null;
		Button b = (Button)e.getSource();
		if(b==be)
			System.exit(0);
		else
			a = new Ball(p);
	}
	public static void main(String[]args)
	{
		BouncingBall a = new BouncingBall();
	}
}