
package Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class CVisiteur {

    //attributs CVisiteur
    
    protected int idVisiteur;
    protected String nomVisiteur;
    protected String prenomVisiteur;
    protected LocalDate dateNaissanceVisiteur;
    protected String emailVisiteur;
    protected String mdpVisiteur;
    protected String adresseVisiteur;
    protected int codePostalVisiteur;
    protected String villeVisiteur;
    protected ArrayList<CLivre> livres;
    
    
    
    //Constructeur
    
    public CVisiteur(int idVisiteur) {
        setIdVisiteur(idVisiteur);
    }

    public CVisiteur(int idVisiteur, String nomVisiteur, String prenomVisiteur, LocalDate dateNaissanceVisiteur, 
            String emailVisiteur, String mdpVisiteur, String adresseVisiteur, int codePostalVisiteur, String villeVisiteur) {
        this.idVisiteur = idVisiteur;
        this.nomVisiteur = nomVisiteur;
        this.prenomVisiteur = prenomVisiteur;
        this.dateNaissanceVisiteur = dateNaissanceVisiteur;
        this.emailVisiteur = emailVisiteur;
        this.mdpVisiteur = mdpVisiteur;
        this.adresseVisiteur = adresseVisiteur;
        this.codePostalVisiteur = codePostalVisiteur;
        this.villeVisiteur = villeVisiteur;
    }
  
    
    //getter et setter CVisiteur

    public int getIdVisiteur() {
        return idVisiteur;
    }

    public final void setIdVisiteur(int idVisiteur) {
        this.idVisiteur = idVisiteur;
    }

    public String getNomVisiteur() {
        return nomVisiteur;
    }

    public final void setNomVisiteur(String nomVisiteur) {
        this.nomVisiteur = nomVisiteur;
    }

    public String getPrenomVisiteur() {
        return prenomVisiteur;
    }

    public final void setPrenomVisiteur(String prenomVisiteur) {
        this.prenomVisiteur = prenomVisiteur;
    }

    public LocalDate getDateNaissanceVisiteur() {
        return dateNaissanceVisiteur;
    }

    public final void setDateNaissanceVisiteur(LocalDate dateNaissanceVisiteur) {
        this.dateNaissanceVisiteur = dateNaissanceVisiteur;
    }

    public String getEmailVisiteur() {
        return emailVisiteur;
    }

    public final void setEmailVisiteur(String emailVisiteur) {
        this.emailVisiteur = emailVisiteur;
    }

    public String getMdpVisiteur() {
        return mdpVisiteur;
    }

    public final void setMdpVisiteur(String mdp_Visiteur) {
        this.mdpVisiteur = mdp_Visiteur;
    }

    public String getAdresseVisiteur() {
        return adresseVisiteur;
    }

    public final void setAdresseVisiteur(String adresseVisiteur) {
        this.adresseVisiteur = adresseVisiteur;
    }

    public int getCodePostalVisiteur() {
        return codePostalVisiteur;
    }

    public final void setCodePostalVisiteur(int codePostalVisiteur) {
        this.codePostalVisiteur = codePostalVisiteur;
    }

    public String getVilleVisiteur() {
        return villeVisiteur;
    }

    public final void setVilleVisiteur(String villeVisiteur) {
        this.villeVisiteur = villeVisiteur;
    }
    
    
}
