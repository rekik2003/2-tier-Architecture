package interface1;

public class Livres {
	public String ISBN;
	public String titre;
	public String auteur;
	public int annee;
	public double prix;
	public Livres(String iSBN, String titre, String auteur, int annee, double prix) {
		ISBN = iSBN;
		this.titre = titre;
		this.auteur = auteur;
		this.annee = annee;
		this.prix = prix;
	}	
}
