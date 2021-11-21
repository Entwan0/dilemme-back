package local.round;

/*package tplogo;*/
import java.io.*;

public class LectureClavier {

	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * lecture au clavier d'un entier simple precision (int)
	 * 
	 * @return l'entier lu
	 * @param invite une chaine d'invite
	 */
	public static int lireEntier(String invite) {
		int res = 0;
		boolean ok = false;
		System.out.println(invite + " ");
		do {
			try {
				res = Integer.parseInt(stdin.readLine());
				ok = true;
			} catch (NumberFormatException nbfe) {
				System.out.println("entrez un entier");
				System.out.println(invite + " ");
			} catch (Exception e) {
				erreurEntree(e, "entier");
			}
		} while (!ok);
		return res;
	}

	/**
	 * lecture au clavier d'une chaine de caracteres
	 * 
	 * @return la chaine lue
	 */
	public static String lireChaine() {
		try {
			return (stdin.readLine());
		} catch (Exception e) {
			erreurEntree(e, "chaine de caracteres");

			return null;
		}
	}

	/**
	 * lecture au clavier d'un reel simple precision (float)
	 * 
	 * @return le float lu
	 * @param invite une chaine d'invite
	 */
	public static float lireFloat(String invite) {
		System.out.println(invite + " ");
		try {
			return (Float.valueOf(stdin.readLine()).floatValue());
		} catch (Exception e) {
			erreurEntree(e, "reel (float)");

			return (float) 0.0;
		}
	}

	/**
	 * lecture au clavier d'un reel double precision (double) le double lu
	 * 
	 * @param invite une chaine d'invite
	 * @return le double lu
	 */
	public static double lireDouble(String invite) {
		System.out.println(invite + " ");
		try {
			return (Double.valueOf(stdin.readLine()).doubleValue());
		} catch (Exception e) {
			erreurEntree(e, "reel (double)");
			return 0.0;
		}
	}

	/**
	 * lecture au clavier d'une reponse de type oui/non. Si la valeur tapee est "o"
	 * ou "O" cette methode renvoie <code>true</code>, sinon elle renvoie
	 * <code>false</code>.
	 * 
	 * @return <code>true</code> si la chaine lue est "o" ou "O", <code>false</code>
	 *         sinon
	 * @param invite une chaine d'invite
	 */
	public static boolean lireOuiNon(String invite) {
		System.out.println(invite + " ");
		String ch;
		ch = lireChaine();
		return (ch.equals("o") || ch.equals("O"));
	}

	/**
	 * lecture au clavier d'une reponse de type oui/non. Si la valeur tapee est "o"
	 * ou "O" cette methode renvoie <code>true</code>, sinon elle renvoie
	 * <code>false</code>.
	 * 
	 * @return <code>true</code> si la chaine lue est "o" ou "O", <code>false</code>
	 *         sinon
	 */
	public static char lireChar(String invite) {
		System.out.println(invite + " ");
		String ch;
		ch = lireChaine();
		return ch.charAt(0);
	}

	/**
	 * en cas d'erreur lors d'une lecture, affiche sur la sortie standard
	 * (System.out) un message indiquant le type de l'erreur ainsi que la pile des
	 * appels de methodes ayant conduit a cette erreur.
	 * 
	 * @param e       l'exception decrivant l'erreur.
	 * @param message le message d'erreur a afficher.
	 */
	private static void erreurEntree(Exception e, String message) {
		System.out.println("Erreur lecture " + message);
		System.out.println(e);
		e.printStackTrace();
		System.exit(1);
	}

} // LectureClavier
