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
public class CTrie_par {

    protected int idLivre;
    protected int idCategorie;

    public CTrie_par(int idLivre, int idCategorie) {
        setIdLivre(idLivre);
        setIdCategorie(idCategorie);
    }

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

}