package Entities;


import java.time.LocalDateTime;

public class CEdition {

    //attribut de CEdition
    protected CAdministrateur admin;
    protected CLivre livre;
    protected LocalDateTime chronoTagEdition;
    protected int idAdmin;
    protected int idLivre;

    //constructeurs
    public CEdition(CAdministrateur admin, CLivre livre, LocalDateTime chronoTagEdition) {
        setAdmin(admin);
        setLivre(livre);
        setChronoTagEdition(chronoTagEdition);
    }

    public CEdition(CAdministrateur admin, CLivre livre) {
        setAdmin(admin);
        setLivre(livre);
    }

    public CEdition(int idAdmin, int idLivre, LocalDateTime chronoTagEdition) {
        setIdLivre(idLivre);
        setIdAdmin(idAdmin);
        setChronoTagEdition(chronoTagEdition);
    }

    //getter et setter CEdition
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

    public LocalDateTime getChronoTagEdition() {
        return chronoTagEdition;
    }

    public final void setChronoTagEdition(LocalDateTime chronoTagEdition) {
        this.chronoTagEdition = chronoTagEdition;
    }

}
