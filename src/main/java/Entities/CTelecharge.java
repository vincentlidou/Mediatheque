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
public class CTelecharge {

    protected CVisiteur visiteur;
    protected CLivre livre;
    protected LocalDateTime chronoTagTelecharge;
    protected int idVisiteur;
    protected int idLivre;

    //constructeur de l'objet
    public CTelecharge(CVisiteur visiteur, CLivre livre, LocalDateTime chronoTagTelecharge) {
        setVisiteur(visiteur);
        setLivre(livre);
        setChronoTagTelecharge(chronoTagTelecharge);
    }

    public CTelecharge(CVisiteur visiteur, CLivre livre) {
        setVisiteur(visiteur);
        setLivre(livre);
    }

    public CTelecharge(int idVisiteur, int idLivre, LocalDateTime chronoTagEdition) {
        setIdLivre(idLivre);
        setIdVisiteur(idVisiteur);
        setChronoTagTelecharge(chronoTagEdition);
    }

    //getter et setter CTelecharge
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

    public LocalDateTime getChronoTagTelecharge() {
        return chronoTagTelecharge;
    }

    public final void setChronoTagTelecharge(LocalDateTime chronoTagTelecharge) {
        this.chronoTagTelecharge = chronoTagTelecharge;
    }

}
