package Entities;


import java.time.LocalDateTime;


public class CRegarde {
    
    //attribut de CRegarde
    
    protected CAdministrateur admin;
    protected CLivre livre;
    protected LocalDateTime chronoTagRegarde;
    protected int idAdmin;
    protected int idLivre;
    
    //constructeur
    
    public CRegarde(CAdministrateur admin, CLivre livre, LocalDateTime chronoTagRegarde){
    setAdmin(admin);
    setLivre(livre);
    setChronoTagRegarde(chronoTagRegarde);
    }
    
    public CRegarde(CAdministrateur admin, CLivre livre) {
        setAdmin(admin);
        setLivre(livre);
    }

    public CRegarde(int idAdmin, int idLivre, LocalDateTime chronoTagEdition) {
        setIdLivre(idLivre);
        setIdAdmin(idAdmin);
        setChronoTagRegarde(chronoTagEdition);
    }
    
    //getter et setter CRegarde

    public int getIdAdmin() {
        return idAdmin;
    }

    public final void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public final void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
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

    public LocalDateTime getChronoTagRegarde() {
        return chronoTagRegarde;
    }

    public final void setChronoTagRegarde(LocalDateTime chronoTagRegarde) {
        this.chronoTagRegarde = chronoTagRegarde;
    }
    
    
    
}
