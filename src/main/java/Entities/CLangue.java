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
public class CLangue {
    
    protected int idLangue;
    protected String nomLangue;

    //Constructeur
    
    public CLangue(int idLangue, String nomLangue) {
        setIdLangue(idLangue);
        setNomLangue(nomLangue);
    }
    
    public CLangue(String nomLangue) {
        setNomLangue(nomLangue);
    }
    
    public CLangue(int idLangue){
        setIdLangue(idLangue);
    }

    //Getter et Setter
    
    public int getIdLangue() {
        return idLangue;
    }

    public final void setIdLangue(int idLangue) {
        this.idLangue = idLangue;
    }

    public String getNomLangue() {
        return nomLangue;
    }

    public final void setNomLangue(String nomLangue) {
        this.nomLangue = nomLangue;
    }
}
