package nfa035.exo3;

/**
 * Tri d'un tableau en utilisant le tri sélection. Idée :
 * On prend le plus petit élément du tableau, et on le met en premier.
 * Ensuite, on prend le plus petit élément de ce qui reste, et on le met en second...
 * etc...
 */
public class TriBugge {
	
	/**
	 * Trie le tableau t.
	 * @param t
	 */
	public static void trier(int t[]) {
		for (int i= 0; i < t.length -1 ; i++ ) {
			int pos= posPlusPetit(t, i);
			echanger(t, i, pos);
		}
	}
	
	private static void echanger(int[] t, int i, int pos) {
		int tmp= t[i];
		t[i]= t[pos];
		t[pos]= t[i];		
	}

	private static int posPlusPetit(int[] t, int i) {
		int resultat= 0;
		for (int j= i; j < t.length; j++) {
			if (t[j] < t[resultat])
				resultat= j;
		}
		return resultat;
	}

	public static void afficher(int t[]) {
		System.out.print("[");
		for (int i= 0; i < t.length; i++) {
			if (i!= 0) System.out.print(", ");
			System.out.print(t[i]);
		}
		System.out.println("]");
	}
	
	public static void main(String args[]) {
		int t[]= {6, 9, 1, 20, 3, 7, 10, 10, 2, 58, 20};
		trier(t);
		afficher(t);		
	}
	
}
