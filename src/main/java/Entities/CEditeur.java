/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author BTS sio
 */
public class CEditeur {

    protected int idEditeur;
    protected String NomEditeur;

    public CEditeur(int IdEditeur, String NomEditeur) {
        setIdEditeur(IdEditeur);
        setNomEditeur(NomEditeur);
    }
    
    public CEditeur(int idEditeur) {
        setIdEditeur(idEditeur);
    }
    

    public CEditeur(String NomEditeur) {
        setNomEditeur(NomEditeur);
    }

    public int getIdEditeur() {
        return idEditeur;
    }

    public final void setIdEditeur(int IdEditeur) {
        this.idEditeur = IdEditeur;
    }

    public String getNomEditeur() {
        return NomEditeur;
    }

    public final void setNomEditeur(String NomEditeur) {
        this.NomEditeur = NomEditeur;
    }

}
