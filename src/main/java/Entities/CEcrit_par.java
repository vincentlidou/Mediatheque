/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author BtsSio9
 */
public class CEcrit_par {
    protected CEditeur editeur;
    protected CLivre livre;

    public CEcrit_par(CEditeur editeur, CLivre livre) {
        setEditeur(editeur);
        setLivre(livre);
    }

    public CEditeur getEditeur() {
        return editeur;
    }

    public final void setEditeur(CEditeur editeur) {
        this.editeur = editeur;
    }

    public CLivre getLivre() {
        return livre;
    }

    public final void setLivre(CLivre livre) {
        this.livre = livre;
    }
    
    
    
}