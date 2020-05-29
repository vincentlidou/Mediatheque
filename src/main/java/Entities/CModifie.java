/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author BtsSio9
 */
public class CModifie {

    protected int idVisiteur;
    protected int idLivre;
    protected LocalDateTime chronoTagModifie;

    //constructeur de l'objet
    //public CModifie(CVisiteur visiteur, CLivre livre, LocalDate chronoTagModifie) {
        //setVisiteur(visiteur);
        //setLivre(livre);
        //setChronoTagModifie(chronoTagModifie);
    //}
    
    public CModifie(int idVisiteur, int idLivre) {
        setIdVisiteur(idVisiteur);
        setIdLivre(idLivre);
    }

    public CModifie(int idVisiteur, int idLivre, LocalDateTime chronoTagModifie) {
        setIdVisiteur(idVisiteur);
        setIdLivre(idLivre);
        setChronoTagModifie(chronoTagModifie);
    }

    public int getIdVisiteur() {
        return idVisiteur;
    }

    public final void setIdVisiteur(int idVisiteur) {
        this.idVisiteur = idVisiteur;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public final void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }
    
    public LocalDateTime getChronoTagModifie() {
        //LocalDateTime temporaire =  LocalDateTime.now();
        //DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //timeFormat.format(temporaire);
        return chronoTagModifie;
    }

    public final void setChronoTagModifie(LocalDateTime chronoTagModifie) {
        this.chronoTagModifie = chronoTagModifie;
    }

}
