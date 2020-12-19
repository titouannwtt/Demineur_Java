import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Fenetre extends JComponent {
  	private Config configfile;
  	
	public Case grille[][];
	
	public boolean isEnd=false;

	public boolean isVictoire=false;
	
	public int largeur;
	public int hauteur;
	
	public int type=0;
	
	public int nbMine;
	
	private Image menu;

	private Image mine=Toolkit.getDefaultToolkit().getImage("./Images"+File.separator+"mine.png");

	private Image victoire=Toolkit.getDefaultToolkit().getImage("./Images"+File.separator+"victoire.jpg");

	private Image defaite=Toolkit.getDefaultToolkit().getImage("./Images"+File.separator+"defaite.jpg");

	private Image game=Toolkit.getDefaultToolkit().getImage("./Images"+File.separator+"game.jpg");

	private Image etoile=Toolkit.getDefaultToolkit().getImage("./Images"+File.separator+"etoile.png");

	private Image interrogation=Toolkit.getDefaultToolkit().getImage("./Images"+File.separator+"interrogation.png");
	
	public JTextField lignes = new JTextField();

	public JTextField colonnes = new JTextField();

	public JTextField explosives = new JTextField();

	public Fenetre() {
		this.setLayout(null);
		File file = new File("lastSession.dat");
		if ( file.exists()==false ){
			try{
				file.createNewFile();
			}catch(Exception error){}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics secondPinceau = g.create();
		if(type==0){
			try{
				BufferedReader fichier  = new BufferedReader(new FileReader("lastSession.dat"));
				menu=Toolkit.getDefaultToolkit().getImage("./Images"+File.separator+"menu.jpg");
					if(fichier.readLine()!=null) {
						menu=Toolkit.getDefaultToolkit().getImage("./Images"+File.separator+"menu+reprendre.jpg");
						fichier.close();
					}
					secondPinceau.drawImage(menu, 0, 0, this);
			}catch(Exception error) {}
		}
		else if (type==1){
			try{
				menu=Toolkit.getDefaultToolkit().getImage("./Images"+File.separator+"selection.jpg");
			}catch(Exception error){}
			secondPinceau.drawImage(menu, 0, 0, this);
			selectionMenu();
		}
		else if (type==2){
			int tailleCase=20;
			try{
				tailleCase=Integer.parseInt(configfile.getConfig("game.config", "tailleCase"));
			} catch(Exception error){}
			defaite();
			victoire();
			Font font = secondPinceau.getFont().deriveFont( 20.0f );
			secondPinceau.setFont(font);
			if(this.isOpaque()){
				secondPinceau.setColor(this.getBackground());
				secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
			}
			secondPinceau.drawImage(game, 0, 0, this);
			if(isEnd==true) {
				if(isVictoire==true) {
					secondPinceau.drawImage(victoire, 0, 0, this);
				}
				else {
					secondPinceau.drawImage(defaite, 0, 0, this);
				}
			}
			int nbexplosives=0;
			for(int i=0; i<largeur; i++){
				for(int j=0; j<largeur; j++) {
					if(grille[i][j].getMine()==true) {
						nbexplosives++;
					}
					if(grille[i][j].getDrapeau()==true) {
						nbexplosives--;
					}
				}
			}
			secondPinceau.drawString(Integer.toString(nbexplosives), 1000, 680);

			for(int i=0; i<largeur; i++){
				for(int j=0; j<hauteur; j++) {
					if(grille[i][j].getDevoile()==true) {
						secondPinceau.setColor(Color.WHITE);
						secondPinceau.fillRect(i*tailleCase, j*tailleCase, tailleCase-1, tailleCase-1);
						if(grille[i][j].getMine()==true) {
							if(grille[i][j].getExplosive()==true) {
								secondPinceau.setColor(Color.red);
								secondPinceau.fillRect(i*tailleCase, j*tailleCase, tailleCase-1, tailleCase-1);
							}
							secondPinceau.drawImage(mine, i*tailleCase, j*tailleCase, this);
						}
						else {
							if(grille[i][j].getValue()!=0) {
								if(grille[i][j].getValue()==1){
									secondPinceau.setColor(new Color(255,0,0));
								}
								else {
									secondPinceau.setColor(Color.BLACK);
								}
								secondPinceau.drawString(Integer.toString(grille[i][j].getValue()), i*tailleCase+5, j*tailleCase+16);
							}
						}
					}
					else {
						secondPinceau.setColor(Color.GRAY);
						secondPinceau.fillRect(i*tailleCase, j*tailleCase, tailleCase-1, tailleCase-1);
						if(grille[i][j].getDrapeau()==true){
							secondPinceau.drawImage(etoile, i*tailleCase, j*tailleCase, this);
						}
						if(grille[i][j].getInterrogation()==true){
							secondPinceau.drawImage(interrogation, i*tailleCase, j*tailleCase, this);
						}
					}
				}
			}
		}
	}
	
	public void initialiserGrille() {
		grille = new Case[largeur][hauteur];
		for(int i=0; i<largeur; i++){
			for(int j=0; j<hauteur; j++){
				grille[i][j] = new Case(false, false);
			}
		}
		while(nbMine>0){
			Random random = new Random();
			int x=random.nextInt(largeur);
			int y=random.nextInt(hauteur);
			if( grille[x][y].getMine()==false ){
				grille[x][y].setMine(true);
				nbMine--;
			}
		}

		for(int i=0; i<largeur; i++){
			for(int j=0; j<hauteur; j++){
				int nbexplosives=0;
				for(int x=-1; x<2; x++){
					for(int y=-1; y<2; y++) {
						if((x!=0 || y!=0) && i+x>=0 && i+x<largeur && j+y>=0 && j+y<hauteur) {
							if(grille[i+x][j+y].getMine()){
								nbexplosives++;
							}
						}
					}
				}
				grille[i][j].setValue(nbexplosives);
			}
		}
	}
	
	public void save() {
		File file = new File("lastSession.dat");
		if( file.exists()==true ) {
			file.delete();
		}
		file = new File("lastSession.dat");
		try{
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter fichier  = new BufferedWriter(fileWriter);
			try{
				fichier.write(largeur+" "+ hauteur + " ");
			}catch(Exception error){}
			for(int i=0; i<largeur; i++){
				for(int j=0; j<hauteur; j++){
					try{
						fichier.write(grille[i][j].getValue()+" ");
						if(grille[i][j].getMine()==true) {
							fichier.write(1+" ");
						}
						else {
							fichier.write(0+" ");
						}
						if(grille[i][j].getDevoile()==true) {
							fichier.write(1+" ");
						}
						else {
							fichier.write(0+" ");
						}
						if(grille[i][j].getDrapeau()) {
							fichier.write(1+" ");
						}
						else {
							fichier.write(0+" ");
						}
						if(grille[i][j].getInterrogation()) {
							fichier.write(1+" ");
						}
						else {
							fichier.write(0+" ");
						}
						}catch(Exception error){}
					}
				}
				fichier.close();
			}catch(Exception error){}
	}

	public void load() {
		int temp=0;
		boolean mine;
		boolean devoile;
		boolean drapeau;
		boolean interrogation;
		try{
			File file = new File("lastSession.dat");
			Scanner fichier = new Scanner(file);
			largeur=fichier.nextInt();
			hauteur=fichier.nextInt();
			grille=new Case[largeur][hauteur];
			for(int i=0; i<largeur; i++) {
				for(int j=0; j<hauteur; j++) {
					temp=fichier.nextInt();
					if (fichier.nextInt()==0){
						mine=false; 
					}
					else {
						mine=true;
					}
					if (fichier.nextInt()==0){
						devoile=false;
					}
					else {
						devoile=true;
					}
					if (fichier.nextInt()==0){
						drapeau=false;
					}
					else {
						drapeau=true;
					}
					if (fichier.nextInt()==0){
						interrogation=false;
					}
					else {
						interrogation=true;
					}
					grille[i][j] = new Case(mine, devoile);
					grille[i][j].setValue(temp);
					grille[i][j].setDrapeau(drapeau);
					grille[i][j].setInterrogation(interrogation);
				}
			}
		} catch(Exception error){}
	}

	public void selectionMenu() {
		Font font = new Font("Serif", Font.PLAIN, 36);
		lignes.setHorizontalAlignment(JTextField.CENTER);
		lignes.setFont(font);
		colonnes.setHorizontalAlignment(JTextField.CENTER);
		colonnes.setFont(font);
		explosives.setHorizontalAlignment(JTextField.CENTER);
		explosives.setFont(font);
		lignes.setBounds(15, 220, 300, 60);
		colonnes.setBounds(15, 340, 300, 60);
		explosives.setBounds(15, 460, 300, 60);
		this.add(lignes);
		this.add(colonnes);
		this.add(explosives);
	}

	public void victoire() {
		int d=0;
		int explosives=0;
		for(int i=0; i<largeur; i++){
			for(int j=0; j<hauteur; j++) {
				if( grille[i][j].getMine()==true ){
					explosives++;
				}
				if( grille[i][j].getDevoile()==false ){
					d++;
				}
			}
		}
		if(explosives==d){
			isEnd=true;
			isVictoire=true;
		}
	}

	public void defaite() {
		for(int i=0; i<largeur; i++){
			for(int j=0; j<hauteur; j++) {
				if( grille[i][j].getMine()==true && grille[i][j].getDevoile()==true ){
					isEnd=true;
					File file = new File("lastSession.dat");
					if(file.exists()==true){
						file.delete();
					}
					try{
						FileWriter fileWriter = new FileWriter(file);
						fileWriter.write("");
					}catch (Exception error){}
				}
			}
		}
		if(isEnd==true){
			for(int i=0; i<largeur; i++){
				for(int j=0; j<hauteur; j++) {
					if(grille[i][j].getMine()==true){
						grille[i][j].setDevoile(true);
					}
				}
			}
		}
	}

	public void multipleCase(int i, int j) {
		Case around = grille[i][j];
		if (around.getDevoile()==true || around.getMine()==true){
			return;
		}
		around.setDevoile(true);
		if (around.getValue()>0){
			return;
		}
		for(int r=i-1; r<=i+1; r++) {
			for(int c=j-1; c<=j+1; c++) {
				if (r>=0 && r<largeur && c>=0 && c<hauteur){
					multipleCase(r, c);
				}
			}
		}
	}

}