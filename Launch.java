/**
 * La classe <code>Launch</code> est utilis&eacute;e pour lancer
 * tout ce qui est indispensable au jeu
 *  
 * @version 1.0
 * @author Titouan Wattelet
 */
public class Launch {
	/**
	* Lance ce qui est indispensable au jeu, &agrave; savoir:
	* La fenetre et ce qu'elle comprend ainsi que la position et les clics de souris.
	*/
	public Launch() {
		JFrameMieux frame = new JFrameMieux();
		Fenetre fenetre = new Fenetre();
		Mouse souris = new Mouse(fenetre);
		fenetre.addMouseListener(souris);
		frame.add(fenetre);
		frame.setVisible(true);
	}
}