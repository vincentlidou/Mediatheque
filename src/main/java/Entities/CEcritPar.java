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
public class CEcritPar {

    protected CLivre livre;
    protected CAuteur auteur;
    protected int idLivre;
    protected int idAuteur;

    
    //constructeur
    public CEcritPar(CAuteur auteur, CLivre livre) {
        setAuteur(auteur);
        setLivre(livre);
    }
    
    public CEcritPar(int idAuteur, int idLivre) {
        setIdAuteur(idAuteur);
        setIdLivre(idLivre);
    }

    
    // getter et setter

    public CLivre getLivre() {
        return livre;
    }

    public final void setLivre(CLivre livre) {
        this.livre = livre;
    } 

    public CAuteur getAuteur() {
        return auteur;
    }
    
    private void setAuteur(CAuteur auteur) {
        this.auteur = auteur;
    }
    
    public int getIdAuteur() {
        return idAuteur;
    }
    
    private void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }

    public int getIdLivre() {
        return idLivre;
    }
    
    private void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }
    
}