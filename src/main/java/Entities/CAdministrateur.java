package Entities;


public class CAdministrateur {

//attribut CAdmin    
 
protected int idAdmin;
protected String identifiantAdmin;
protected String mdpAdmin;
protected String nomAdmin;
protected String prenomAdmin;


//getter et setter attribut

    public int getIdAdmin() {
        return idAdmin;
    }

    public final void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getIdentifiantAdmin() {
        return identifiantAdmin;
    }

    public final void setIdentifiantAdmin(String identifiantAdmin) {
        this.identifiantAdmin = identifiantAdmin;
    }

    public String getMdpAdmin() {
        return mdpAdmin;
    }

    public final void setMdpAdmin(String mdpAdmin) {
        this.mdpAdmin = mdpAdmin;
    }

    public String getNomAdmin() {
        return nomAdmin;
    }

    public final void setNomAdmin(String nomAdmin) {
        this.nomAdmin = nomAdmin;
    }

    public String getPrenomAdmin() {
        return prenomAdmin;
    }

    public final void setPrenomAdmin(String prenomAdmin) {
        this.prenomAdmin = prenomAdmin;
    }

    
    //constructeur
    
    public CAdministrateur(int idAdmin, String identifiantAdmin, String mdpAdmin, String nomAdmin, String prenomAdmin){
       setIdAdmin(idAdmin); 
       setIdentifiantAdmin(identifiantAdmin);
       setMdpAdmin(mdpAdmin);
       setNomAdmin(nomAdmin);
       setPrenomAdmin(prenomAdmin);
    }
    
    public CAdministrateur(String identifiantAdmin){
       setIdentifiantAdmin(identifiantAdmin);
    }
}
