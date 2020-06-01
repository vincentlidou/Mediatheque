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
public class CCategorie {

    protected int idCategorie;
    protected String nomCategorie;
    
    
    //constructeur
    public CCategorie(int idCategorie, String nomCategorie) {
        setIdCategorie(idCategorie);
        setNomCategorie(nomCategorie);
    }

    
    public CCategorie(String nomCategorie) {
        setNomCategorie(nomCategorie);
    }
    
    public CCategorie(int idCategorie){
        setIdCategorie(idCategorie);
    }
    
    //Getter et Setter
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