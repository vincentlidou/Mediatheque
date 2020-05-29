/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDateTime;

/**
 *
 * @author Coco
 */
public class CConsulte {

    protected CVisiteur visiteur;
    protected CLivre livre;
    protected LocalDateTime chronoTagConsulte;
    protected int idVisiteur;
    protected int idLivre;

    //constructeur de l'objet
    public CConsulte(CVisiteur visiteur, CLivre livre, LocalDateTime chronoTagConsulte) {
        setVisiteur(visiteur);
        setLivre(livre);
        setChronoTagConsulte(chronoTagConsulte);
    }

    public CConsulte(CVisiteur visiteur, CLivre livre) {
        setVisiteur(visiteur);
        setLivre(livre);
    }

    public CConsulte(int idVisiteur, int idLivre, LocalDateTime chronoTagConsulte) {
        setIdVisiteur(idVisiteur);
        setIdLivre(idLivre);
        setChronoTagConsulte(chronoTagConsulte);
    }

    //getter and setter
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

    public CVisiteur getVisiteur() {
        return visiteur;
    }

    public final void setVisiteur(CVisiteur visiteur) {
        this.visiteur = visiteur;
    }

    public CLivre getLivre() {
        return livre;
    }

    public final void setLivre(CLivre livre) {
        this.livre = livre;
    }

    public LocalDateTime getChronoTagConsulte() {
        return chronoTagConsulte;
    }

    public final void setChronoTagConsulte(LocalDateTime chronoTagConsulte) {
        this.chronoTagConsulte = chronoTagConsulte;
    }

}
