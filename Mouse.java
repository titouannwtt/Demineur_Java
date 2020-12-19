import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Mouse implements MouseListener, MouseMotionListener {

	private Fenetre fenetre;
  	private Config configfile;
	
	public Mouse(Fenetre fenetre) {
		this.fenetre=fenetre;
	}
	
	@Override
	public void mouseClicked(MouseEvent evenement) {}
	
	@Override
	public void mouseDragged(MouseEvent evenement) {}
	
	@Override
	public void mouseEntered(MouseEvent evenement) {}
	
	@Override
	public void mouseReleased(MouseEvent evenement) {}
	
	@Override
	public void mouseExited(MouseEvent evenement) {}
	
	@Override
	public void mouseMoved(MouseEvent evenement) {}
	
	@Override
	public void mousePressed(MouseEvent evenement) {
		int clic=evenement.getButton();
		int clicX=evenement.getX();
		int clicY=evenement.getY();
		int tailleCase=20;
		try{
			tailleCase=Integer.parseInt(configfile.getConfig("game.config", "tailleCase"));
		} catch(Exception error){}
		if (fenetre.type==0){
			if( (clicX>=22 && clicY>=262) && (clicX<=277 && clicY<=320) ){
				fenetre.type=1;
			}
			if ( (clicX>=22 && clicY>=335) && (clicX<=277 && clicY<=393) ){
				try{
					File file = new File("lastSession.dat");
					Scanner fichier = new Scanner(file);
					if(fichier.hasNext()==true){
						fenetre.load();
						fenetre.type=2;
					}
				}catch(Exception e){}
			}
			if( (clicX>=22 && clicY>=402) && (clicX<=277 && clicY<=460) ){
				System.exit(0);
			}
		}
		else if (fenetre.type==1){
			if ( (clicX>=11 && clicY>=557) && (clicX<=266 && clicY<=616) ){
				if( fenetre.lignes.getText().equals("")==false || ( (fenetre.colonnes.getText().equals("")==false) && (fenetre.explosives.getText().equals("")==false) ) ){
					int lignes=Integer.parseInt(fenetre.lignes.getText());
                    int colonnes=Integer.parseInt(fenetre.colonnes.getText());
                    int explosives=Integer.parseInt(fenetre.explosives.getText());
					int maxLignesColonnes=30;
					int minLignesColonnes=4;
					try{
						maxLignesColonnes=Integer.parseInt(configfile.getConfig("game.config", "maxLignesColonnes"));
						minLignesColonnes=Integer.parseInt(configfile.getConfig("game.config", "minLignesColonnes"));
					}catch(Exception error){}
                    if( lignes>=minLignesColonnes && lignes<=maxLignesColonnes && colonnes>=minLignesColonnes  && colonnes<=maxLignesColonnes && explosives<lignes*colonnes ){
						fenetre.largeur=lignes;
						fenetre.hauteur=colonnes;
						fenetre.nbMine=explosives;
						fenetre.removeAll();
						fenetre.type=2;
						fenetre.initialiserGrille();
					}
				}
			}
		}
		else if (fenetre.type==2){
			if (fenetre.isEnd==false){
				if( (clicX>7 && clicY>655) && (clicX<262 && clicY<714) ){
					fenetre.save();
					fenetre.type=0;
				}
				clicX=clicX/tailleCase;
				clicY=clicY/tailleCase;
				if ( (clicX>=0 && clicY>=0) && (clicX<fenetre.largeur && clicY<fenetre.hauteur) ){
					if (clic==MouseEvent.BUTTON1){
						if ( fenetre.grille[clicX ][clicY].getDevoile()==false && fenetre.grille[clicX ][clicY].getDrapeau()==false && fenetre.grille[clicX ][clicY].getInterrogation()==false ){
							if ( fenetre.grille[clicX ][clicY].getMine()==true ){
								fenetre.grille[clicX ][clicY].setDevoile(true);
								fenetre.grille[clicX ][clicY].setExplosive(true);
								fenetre.defaite();
							}
							else {
								fenetre.multipleCase(clicX,clicY);
							}
						}
                    }
				}
				if ( clic==MouseEvent.BUTTON3 ){
					if ( fenetre.grille[clicX ][clicY].getDevoile()==false && fenetre.grille[clicX ][clicY].getDrapeau()==false && fenetre.grille[clicX ][clicY].getInterrogation()==false ){
						fenetre.grille[clicX ][clicY].setDrapeau(true);
					}
					else if ( fenetre.grille[clicX ][clicY].getDrapeau()==true && fenetre.grille[clicX ][clicY].getInterrogation()==false ){
						fenetre.grille[clicX ][clicY].setInterrogation(true);
						fenetre.grille[clicX ][clicY].setDrapeau(false);
					}
					else if ( fenetre.grille[clicX ][clicY].getInterrogation()==true ){
						fenetre.grille[clicX ][clicY].setInterrogation(false);
					}
				}
            } else {
				if ( clic==MouseEvent.BUTTON1 ){
					if( (clicX>7 && clicY>655) && (clicX<262 && clicY<714) ){
						fenetre.type=0;
						fenetre.isEnd=false;
						fenetre.isVictoire=false;
					}
				}
            }
		}
		fenetre.repaint();
	}
}