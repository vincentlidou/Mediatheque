/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CLangue;
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
public class CTableLangue {
    //Ajout des objets
    protected ArrayList<CLangue> listlangue;
    protected CBDD bdd;
    
    //Getter et setter CTableLangue

    public ArrayList<CLangue> getListlangue() {
        return listlangue;
    }

    public void setListlangue(ArrayList<CLangue> listlangue) {
        this.listlangue = listlangue;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }
    
    public CTableLangue(){
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBDD.properties"));
    }
    
    public int insererLangue(CLangue langue){
        //Req a executer
        String req = "INSERT INTO `langue`(`Id_Langue`, `Nom_Langue`)"
                + "  VALUES ('"
                +langue.getIdLangue()+"', '"
                +langue.getNomLangue()+"');";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la Rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res insererLangue = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("connexion impossible bdd insererLangue");
        }
        return res;
        
    }
    
    public int supprimerLangue(String id){
        //Rqt a executer
         String req = "DELETE FROM `langue` WHERE `Id_Langue` = '"+id+"';";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res supprimer langue = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO suppression langue impossible");
        }
        return res;
    }
    
    public int modifierLangue(CLangue langue){
      //Rqt a executer
    String req = "UPDATE `langue` SET Nom_Langue ='"
                + langue.getNomLangue()
                + "' , Nom_Langue = '"+"';";
    
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res modifierLangue = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO modifierLangue");
        }
        return res;
    }   
    
    CLangue convertirRSLangue(ResultSet rs) {//Converti les données SQL en java
        try {
            int idLangue = rs.getInt("Id_Langue");
            String NomLangue = rs.getString("Nom_Langue");          
            return new CLangue(idLangue,NomLangue);
        } catch (SQLException ex) {
            Logger.getLogger(CTableLangue.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<CLangue> lireLangue() {//Permet de lire toutes les langues de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CLangue> listeLangues = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`langue`");
            try {
                while (rs.next()) {//Ajout des données a une liste
                    CLangue langue = convertirRSLangue(rs);
                    listeLangues.add(langue);
                }
                if(listeLangues.isEmpty()){//Si bbd vide renvoi un msg d'erreur
                    System.out.println("404 toutes langues not found");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeLangues;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion impossible bdd lire toutes les langues");
        }
        return null;
    }
    
     public ArrayList<CLangue> lireUneLangue(int id) {//Permet de lire une langue de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CLangue> listeLangues = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`langue` WHERE `Id_Langue` = '"+id+"';");
            try {
                while (rs.next()) {//Ajout des données a la liste
                    CLangue langue = convertirRSLangue(rs);
                    listeLangues.add(langue);
                }
                if(listeLangues.isEmpty()){//Si données introuvables renvoi un msg d'erreur
                    System.out.println("404 visiteur not found");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeLangues;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO");
        }
        return null;
    }
     
    public void printLangue(ArrayList<CLangue> liste){//Permet d'afficher les lectures de la BDD
         liste.forEach((langue)->{
             System.out.println("Nom de la langue : " + langue.getNomLangue()); 
         });
     }
    
    
    //test methode
    public static void main(String[] args) {
        CTableLangue tableLangue = new CTableLangue();
        CLangue langue = new CLangue(1, "frenchy");
        //tableLangue.insererLangue(langue);
        //tableLangue.supprimerLangue("1");
        //tableLangue.modifierLangue(langue);
        //tableLangue.printLangue(tableLangue.lireLangue());
        tableLangue.printLangue(tableLangue.lireUneLangue(1));
        
    }
}
