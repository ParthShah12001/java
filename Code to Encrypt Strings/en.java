import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.crypto.Cipher;
import javax.crypto.*;
class Encrypt
{
	public static void main(String[]args)
	{
		String s,z=" ",f=" ";
		s = JOptionPane.showInputDialog(null,"Enter Text");
		char [] c = s.toCharArray();
		for(char i : c)
		{
			i =+ 5;
			z = z+i;
		}
		JOptionPane.showMessageDialog(null,z);
		char [] a = z.toCharArray();
		for (char j: a )
		{
			j = j - 5;
			f = f+j;
		}
		JOptionPane.showMessageDialog(null,f);
	}
}