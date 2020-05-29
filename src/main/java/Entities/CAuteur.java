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
public class CAuteur {
    protected int idAuteur;
    protected String nomAuteur;
    protected String prenomAuteur;

    public CAuteur(int idAuteur, String nomAuteur, String prenomAuteur) {
        setIdAuteur(idAuteur);
        setNomAuteur(nomAuteur);
        setPrenomAuteur(prenomAuteur);
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public final void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    public final void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    public String getPrenomAuteur() {
        return prenomAuteur;
    }

    public final void setPrenomAuteur(String prenomAuteur) {
        this.prenomAuteur = prenomAuteur;
    }
    
    
}