/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CAdministrateur;
import BDD.CBDD;
import BDD.CParametresStockageBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BTS sio
 */
public class CTableAdministrateur {
    //Ajout des objets
    protected ArrayList<CAdministrateur> listadmin;
    protected CBDD bdd;
    
    //Getter et setter CTableAdmin

    public ArrayList<CAdministrateur> getListadmin() {
        return listadmin;
    }

    public void setListadmin(ArrayList<CAdministrateur> listadmin) {
        this.listadmin = listadmin;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }
    
    public CTableAdministrateur(){
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBDD.properties"));
    }
    
    public int insererAdministrateur(CAdministrateur admin){
        //Req a executer
        String req = "INSERT INTO `administrateur`(`Id_Admin`,"
                + " `Identifiant_Admin`, `Mot_De_Passe_Admin`,"
                + "`Nom_Admin`, `Prenom_Admin`)"
                + "  VALUES ('"
                +admin.getIdAdmin()+"', '"
                +admin.getIdentifiantAdmin()+"',"
                + " '"+admin.getMdpAdmin()+"', '"+admin.getNomAdmin()+"', '"+admin.getPrenomAdmin()+"');";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la Rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res insereradmin = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("connexion impossible bdd insererAdmin");
        }
        return res;
        
    }
    
    public int supprimerAdministrateur(String id){
        //Rqt a executer
         String req = "DELETE FROM `administrateur` WHERE `Id_Admin` = '"+id+"';";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res supprimer admin = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO suppression admin impossible");
        }
        return res;
    }
    
    public int modifierAdministrateur(CAdministrateur admin){
      //Rqt a executer
    String req = "UPDATE `administrateur` SET Identifiant_Admin ='"
                + admin.getIdentifiantAdmin()
                + "' , Mot_De_Passe_Admin = '"
                + admin.getMdpAdmin()
                + "' , Nom_Admin = '"
                + admin.getNomAdmin()
                + "' , Prenom_Admin = '"
                + admin.getPrenomAdmin()
                + "' WHERE Id_Admin = '"+admin.getIdAdmin()+"';";
    
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res modifierAdmin = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO modifierAdmin");
        }
        return res;
    }   
    
    CAdministrateur convertirRSAdministrateur(ResultSet rs) {//Converti les données SQL en java
        try {
            int idAdmin = rs.getInt("Id_Admin");
            String identifiantAdmin = rs.getString("Identifiant_Admin");
            String mdpAdmin = rs.getString("Mot_De_Passe_Admin");
            String nomAdmin = rs.getString("Nom_Admin");
            String prenomAdmin = rs.getString("Prenom_Admin");
            
            return new CAdministrateur(idAdmin, identifiantAdmin, mdpAdmin, nomAdmin, prenomAdmin);
        } catch (SQLException ex) {
            Logger.getLogger(CTableAdministrateur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<CAdministrateur> lireAdministrateur() {//Permet de lire tous les admins de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CAdministrateur> listeDeAdmins = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`administrateur`");
            try {
                while (rs.next()) {//Ajout des données a une liste
                    CAdministrateur admin = convertirRSAdministrateur(rs);
                    listeDeAdmins.add(admin);
                }
                if(listeDeAdmins.isEmpty()){//Si bbd vide renvoi un msg d'erreur
                    System.out.println("404 tout admins not found");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeDeAdmins;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion impossible bdd lire tous les admins");
        }
        return null;
    }
    
     public ArrayList<CAdministrateur> lireUnAdministrateur(int id) {//Permet de lire un admin de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CAdministrateur> listeDeAdmins = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`administrateur` WHERE `Id_Admin` = '"+id+"';");
            try {
                while (rs.next()) {//Ajout des données a la liste
                    CAdministrateur admin = convertirRSAdministrateur(rs);
                    listeDeAdmins.add(admin);
                }
                if(listeDeAdmins.isEmpty()){//Si données introuvables renvoi un msg d'erreur
                    System.out.println("404 visiteur not found");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeDeAdmins;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO");
        }
        return null;
    }
     
    public void printAdministrateur(ArrayList<CAdministrateur> liste){//Permet d'afficher les lectures de la BDD
         liste.forEach((admin)->{
             System.out.println("Nom de l'administrateur : " + admin.getNomAdmin()); 
         });
     }
    
    
    //test methode
    public static void main(String[] args) {
        CTableAdministrateur tableAdmin = new CTableAdministrateur();
        CAdministrateur admin = new CAdministrateur(2, "jacques", "test", "test", "test");
        //tableAdmin.insererAdministrateur(admin);
        //tableAdmin.supprimerAdministrateur("1");
        //tableAdmin.modifierAdministrateur(admin);
        tableAdmin.printAdministrateur(tableAdmin.lireAdministrateur());
        //tableAdmin.printAdministrateur(tableAdmin.lireUnAdministrateur(1));
        
    }
}
