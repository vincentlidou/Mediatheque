package Entities;

import java.time.LocalDateTime;


public class CRajout {
    
    //attribut de CRajout
    
    protected CAdministrateur admin;
    protected CLivre livre;
    protected LocalDateTime chronoTagRajout;
    protected int idLivre;
    protected int idAdmin;
    
    
    //constructeurs
    
    public CRajout(CAdministrateur admin, CLivre livre, LocalDateTime chronoTagRajout){
    setAdmin(admin);
    setLivre(livre);
    setChronoTagRajout(chronoTagRajout);
    }
    
    public CRajout(CAdministrateur admin, CLivre livre){
    setAdmin(admin);
    setLivre(livre);
    }

    public CRajout(int idAdmin, int idLivre, LocalDateTime chronoTagRajout) {
        setIdLivre(idLivre);
        setIdAdmin(idAdmin);
        setChronoTagRajout(chronoTagRajout);  
    }
    
    //getter et setter CRajout

    public int getIdLivre() {
        return idLivre;
    }

    public final void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public final void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }
    
    

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

    public LocalDateTime getChronoTagRajout() {
        return chronoTagRajout;
    }

    public final void setChronoTagRajout(LocalDateTime chronoTagRajout) {
        this.chronoTagRajout = chronoTagRajout;
    }
    
    
    
}
