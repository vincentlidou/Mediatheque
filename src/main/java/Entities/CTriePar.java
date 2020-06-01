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
public final class CTriePar {

    protected CLivre livre;
    protected CCategorie categorie;
    protected int idLivre;
    protected int idCategorie;

    //constructeur
    public CTriePar(int idLivre, int idCategorie) {
        setIdLivre(idLivre);
        setIdCategorie(idCategorie);
    }

    public CTriePar(CLivre livre, CCategorie categorie) {
        setLivre(livre);
        setCategorie(categorie);
    }

    // Getter et Setter
    public int getIdLivre() {
        return idLivre;
    }

    public final void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public final void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public CLivre getLivre() {
        return livre;
    }

    public void setLivre(CLivre livre) {
        this.livre = livre;
    }

    public CCategorie getCategorie() {
        return categorie;
    }

    public void setCategorie(CCategorie categorie) {
        this.categorie = categorie;
    }
    
}