/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;

/**
 *
 * @author BTS sio
 */
public class CLivre {

    protected int idLivre;
    protected String titreLivre;
    protected String synopsisLivre;
    protected int nombrePagesLivre;
    protected int isbn;
    protected int anneePublicationLivre;
    protected String validationLivre;
    protected CVignette vignette;
    protected CEditeur editeur;
    protected CLangue langue;
    protected ArrayList<CAuteur> auteurs;
    protected ArrayList<CVisiteur> visiteurs;

    

    

    public CLivre(int idLivre, String titreLivre, String synopsisLivre, int nombrePagesLivre, int isbn, int anneePublicationLivre, String validationLivre, CVignette vignette, CEditeur editeur, CLangue langue, ArrayList<CAuteur> auteur) {
        setIdLivre(idLivre);
        setTitreLivre(titreLivre);
        setSynopsisLivre(synopsisLivre);
        setNombrePagesLivre(nombrePagesLivre);
        setISBN(isbn);
        setAnneePublicationLivre(anneePublicationLivre);
        setValidationLivre(validationLivre);
        setVignette(vignette);
        setEditeur(editeur);
        setLangue(langue);
        setAuteurs(auteur);
    }
    
    public CLivre(){
        
    }
    
    public CLivre(int idLivre){
        setIdLivre(idLivre);
    }
    
    public CLivre(String titreLivre){
        setTitreLivre(titreLivre);
    }

    public CLivre(String titreLivre, String synopsisLivre, int nombrePagesLivre, int isbn, int anneePublicationLivre, String validationLivre) {
        this.titreLivre = titreLivre;
        this.synopsisLivre = synopsisLivre;
        this.nombrePagesLivre = nombrePagesLivre;
        this.isbn = isbn;
        this.anneePublicationLivre = anneePublicationLivre;
        this.validationLivre = validationLivre;
    }
    
    
    
    

    public int getIdLivre() {
        return idLivre;
    }

    public final void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public String getTitreLivre() {
        return titreLivre;
    }

    public final void setTitreLivre(String titreLivre) {
        this.titreLivre = titreLivre;
    }

    public String getSynopsisLivre() {
        return synopsisLivre;
    }

    public final void setSynopsisLivre(String synopsisLivre) {
        this.synopsisLivre = synopsisLivre;
    }

    public int getNombrePagesLivre() {
        return nombrePagesLivre;
    }

    public final void setNombrePagesLivre(int nombrePagesLivre) {
        this.nombrePagesLivre = nombrePagesLivre;
    }

    public int getISBN() {
        return isbn;
    }

    public final void setISBN(int isbn) {
        this.isbn = isbn;
    }

    public int getAnneePublicationLivre() {
        return anneePublicationLivre;
    }

    public final void setAnneePublicationLivre(int anneePublicationLivre) {
        this.anneePublicationLivre = anneePublicationLivre;
    }

    public String getValidationLivre() {
        return validationLivre;
    }

    public final void setValidationLivre(String validationLivre) {
        this.validationLivre = validationLivre;
    }

    public CVignette getVignette() {
        return vignette;
    }

    public final void setVignette(CVignette vignette) {
        this.vignette = vignette;
    }

    public CEditeur getEditeur() {
        return editeur;
    }

    public final void setEditeur(CEditeur editeur) {
        this.editeur = editeur;
    }

    public CLangue getLangue() {
        return langue;
    }

    public final void setLangue(CLangue langue) {
        this.langue = langue;
    }

    public ArrayList getAuteur() {
        return auteurs;
    }

    public void setAuteurs(ArrayList<CAuteur> auteurs) {
        this.auteurs = auteurs;
    }
    
     public ArrayList<CAuteur> getAuteurs() {
        return auteurs;
    }
     
     public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

}
