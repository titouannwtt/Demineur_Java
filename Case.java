/**
 * La classe <code>Case</code> est utilis&eacute;e pour repr&eacute;senter
 * les cases du jeu.
 *  
 * @version 1.0
 * @author Titouan Wattelet
 */
public class Case {
	/**
	* Constante indiquant la valeur de la case,
	* (c'est-&agrave;-dire la valeur &agrave; lorsque la case est d&eacute;voil&eacute;e).
	* La valeur correspond au nombre de mine adjacente à la case.
	*/
	private int value=0;
	/**
	* Constante indiquant par true/false si la case est min&eacute;e ou non.
	*/
	private boolean mine=false;
	/**
	* Constante indiquant par true/false si la case a &eacute;t&eacute; d&eacute;voiler par l'utilisateur.
	*/
	private boolean devoile=false;
	/**
	* Constante indiquant par true/false si la case a reçu un drapeau de l'utilisateur.
	*/
	private boolean drapeau=false;
	/**
	* Constante indiquant par true/false si la case a reçu un point d'interrogation de l'utilisateur.
	*/
	private boolean interrogation=false;
	/**
	* Constante indiquant par true/false si la case a explos&eacute;e.
	*/
	private boolean explosive=false;
	
	/**
	* Constructeur destin&eacute; &agrave; la cr&eacute;ation des constantes
	* publiques.
	*
	* @param mine true/false selon si la case est min&eacute;e ou non
	* @param devoile true/false selon si la case a &eacute;t&eacute; d&eacute;voil&eacute;e par l'utilisateur.
	*/
	public Case(boolean mine, boolean devoile) {
		this.mine=mine;
		this.devoile=devoile;
	}
	/**
	* Renvoie le nombre de mine autour de la case
	*
	* @return la valeur de la variable value pour la case.
	*/
	public int getValue() {return this.value;}
	/**
	* Renvoie true si la case a explos&eacute;e
	*
	* @return la valeur de la variable explosive pour la case.
	*/
	public boolean getExplosive() {return this.explosive;}
	/**
	* Renvoie true si la case est min&eacute;e
	*
	* @return la valeur de la variable mine pour la case.
	*/
	public boolean getMine() {return this.mine;}
	/**
	* Renvoie true si la case est d&eacute;voil&eacute;e
	*
	* @return la valeur de la variable devoile pour la case.
	*/
	public boolean getDevoile() {return this.devoile;}
	/**
	* Renvoie true si la case a un drapeau.
	*
	* @return la valeur de la variable drapeau pour la case.
	*/
	public boolean getDrapeau() {return this.drapeau;}
	/**
	* Renvoie true si la case a un point d'interrogation.
	*
	* @return la valeur de la variable drapeau pour la case.
	*/
	public boolean getInterrogation() {return this.interrogation;}

	/**
	* Défini le nombre de mines se trouvant autour de la case
	*
	* @param valeur correspond &agrave; au nombre &agrave; afficher lorsque la case est d&eacute;voil&eacute;e.
	*/
	public void setValue(int valeur) {this.value=valeur;}
	/**
	* Défini si la case a explos&eacute;e
	*
	* @param explosive permet de d&eacute;finir si la case a explos&eacute;e ou non
	*/
	public void setExplosive(boolean explosive) {this.explosive=explosive;}
	/**
	* Défini si la case est min&eacute;e
	*
	* @param mine permet de d&eacute;finir si la case est min&eacute;e ou non
	*/
	public void setMine(boolean mine) {this.mine=mine;}
	/**
	* Défini si la case est devoil&eacute;e
	*
	* @param devoile permet de d&eacute;finir si la case est devoil&eacute;e ou non
	*/
	public void setDevoile(boolean devoile) {this.devoile=devoile;}
	/**
	* Défini si la case a un drapeau
	*
	* @param drapeau permet de savoir si la case poss&egrave;de un drapeau ou non.
	*/
	public void setDrapeau(boolean drapeau) {this.drapeau=drapeau;}
	/**
	* Défini si la case a un point d'int&eacute;rrogation
	*
	* @param interrogation permet de savoir si la case poss&egrave;de un point d'int&eacute;rrogation ou non.
	*/
	public void setInterrogation(boolean interrogation) {this.interrogation=interrogation;}
}