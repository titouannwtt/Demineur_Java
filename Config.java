import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.lang.Exception;
/**
 * La classe <code>Config</code> est utilis&eacute;e pour g&eacute;rer l'acc&egrave;s
 * aux fichiers de configuration (ici "game.config")
 *  
 * @version 1.0
 * @author Titouan Wattelet
 */
public class Config {
	/**
	* Renvoie la valeur correspondante &agrave; la configuration souhait&eacute;e
	*
	* @param fichier correspond au nom du fichier.
	* @param key correspond &agrave; la clef que l'on veut dans le fichier
	* @return la valeur correspondante &agrave; la configuration souhait&eacute;e
	*/
    public String getConfig(String fichier, String key) throws Exception {

        //Ouvre le fichier config en tant que Flux
        String leFichier = System.getProperty("user.dir") + fichier;
    	FileInputStream fileInputStream = new FileInputStream(leFichier);

        //Transforme le fichier ouvert en "pseudo-config"
        Properties config = new Properties();
        config.load(fileInputStream);

        //Recupère les infos dans le fichier config
        String tmp = config.getProperty(key);

        //Manipulations nécessaires lorsqu'on a fini de prendre les infos dans le fichier.
        fileInputStream.close();
        leFichier = null;
        fileInputStream = null;
        config = null;

        //Erreur si la clef n'est pas trouver dans le config
        //(exemple si on veut "x" mais que x n'est pas défini dans le fichier config)
        if (tmp == null) {
        	throw new Exception("Impossible de trouver '" + key + "' dans le fichier: '" + fichier +"'");
        }
        return tmp; 
    }
	
}