import java.awt.*;
import javax.swing.*;
import java.io.*;

public class JFrameMieux extends JFrame {
  	private Config configfile;
	public JFrameMieux() {
		super("Demineur - Projet APL 2.1");
		setResizable(false);
		int x=1080;
		int y=749;
		try{
			x=Integer.parseInt(configfile.getConfig("game.config", "taille-fenetre-x"));
			y=Integer.parseInt(configfile.getConfig("game.config", "taille-fenetre-y"));
		}catch(Exception error){}
		setSize(x, y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}