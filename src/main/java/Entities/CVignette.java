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
public class CVignette {
    
    protected int idVignette;
    protected String nomImageVignette;
    protected String imageVignette;
    protected int idLivre;

    //Constructeur
    
    public CVignette(int idVignette, String nomImageVignette, String imageVignette) {
        setIdVignette(idVignette);
        setNomImageVignette(nomImageVignette);
        setImageVignette(imageVignette);
    }
    
    public CVignette(String nomImageVignette) {
        setNomImageVignette(nomImageVignette);
    }

    //Getter et Setter
    
    public int getIdVignette() {
        return idVignette;
    }

    public final void setIdVignette(int idVignette) {
        this.idVignette = idVignette;
    }
    
    public final void setImageVignette(String imageVignette) {
        this.imageVignette = imageVignette;
    }
    
    public String getNomImageVignette() {
        return nomImageVignette;
    }
    
    public String getImageVignette(){
        return imageVignette;
    }
    
    public int getIdLivre() {
        return idLivre;
    }
   
    
    public final void setNomImageVignette(String nomImageVignette) {
        this.nomImageVignette = nomImageVignette;
    }
}
