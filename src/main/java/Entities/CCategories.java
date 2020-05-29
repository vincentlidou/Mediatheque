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
public class CCategories {

    protected int idCategorie;
    protected String nomCategorie;

    public CCategories(int idCategorie, String nomCategorie) {
        setIdCategorie(idCategorie);
        setNomCategorie(nomCategorie);
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public final void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public final void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

}