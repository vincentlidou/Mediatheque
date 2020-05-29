/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;

/**
 *
 * @author BtsSio9
 */
public class CRajoute {
    
    
    //attribut de CRajoute
    
    protected CAdministrateur admin;
    protected CLivre livre;
    protected LocalDate chronoTagRajoute;
    
    //constructeur
    
    public CRajoute(CAdministrateur admin, CLivre livre, LocalDate chronoTagRajoute){
    setAdmin(admin);
    setLivre(livre);
    setChronoTagRajoute(chronoTagRajoute);
    }
    
    //getter et setter CRajoute

    public CAdministrateur getAdmin() {
        return admin;
    }

    public final void setAdmin(CAdministrateur admin) {
        this.admin = admin;
    }

    public CLivre getLivre() {
        return livre;
    }

    public final void setLivre(CLivre livre) {
        this.livre = livre;
    }

    public LocalDate getChronoTagRajoute() {
        return chronoTagRajoute;
    }

    public final void setChronoTagRajoute(LocalDate chronoTagRajoute) {
        this.chronoTagRajoute = chronoTagRajoute;
    }
    
   
}