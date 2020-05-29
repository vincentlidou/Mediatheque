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
public class CUpload {

    protected CVisiteur visiteur;
    protected CLivre livre;
    protected LocalDateTime chronoTagUpload;
    protected int idVisiteur;
    protected int idLivre;

    //constructeur de l'objet
    public CUpload(CVisiteur visiteur, CLivre livre, LocalDateTime chronoTagUpload) {
        setVisiteur(visiteur);
        setLivre(livre);
        setChronoTagUpload(chronoTagUpload);
    }
    
    public CUpload(CVisiteur visiteur, CLivre livre) {
        setVisiteur(visiteur);
        setLivre(livre);
    }

    public CUpload(int idVisiteur, int idLivre, LocalDateTime chronoTagConsulte) {
        setIdVisiteur(idVisiteur);
        setIdLivre(idLivre);
        setChronoTagUpload(chronoTagConsulte);
    }

    //getter et setter CVisiteur

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

    public LocalDateTime getChronoTagUpload() {
        return chronoTagUpload;
    }

    public final void setChronoTagUpload(LocalDateTime chronoTagUpload) {
        this.chronoTagUpload = chronoTagUpload;
    }

}
