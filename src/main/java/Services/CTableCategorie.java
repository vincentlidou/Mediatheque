/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.CCategorie;
import BDD.CBDD;
import BDD.CParametresStockageBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BtsSio9
 */
public class CTableCategorie {
    
    //ajout des objets
    protected ArrayList<CCategorie> listcategorie;
    protected CBDD bdd;
    
    
    
    //Getter et Setter
    public ArrayList<CCategorie> getListcategorie() {
        return listcategorie;
    }

    public void setListcategorie(ArrayList<CCategorie> listcategorie) {
        this.listcategorie = listcategorie;
    }

    public CBDD getBdd() {
        return bdd;
    }
    
    public void setBdd(CBDD bdd) {  
        this.bdd = bdd;
    }
    
    public CTableCategorie(){ 
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBDD.properties"));
    }
    
    
    // méthodes
    public int insererCategorie(CCategorie categorie){
        
        //Req a executer
        String req = "INSERT INTO `categories`(`Id_Categorie`, `Nom_Categorie`)"
                + "  VALUES ('"
                +categorie.getIdCategorie()+"', '"
                +categorie.getNomCategorie()+"');";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la Rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res insererCategorie = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("connexion impossible bdd insererCategorie");
        }
        return res;
        
    }
    
    public int supprimerCategorie(String id){
        
        //Rqt a executer
         String req = "DELETE FROM `categories` WHERE `Id_Categorie` = '"+id+"';";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res supprimer categorie = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO suppression categorie impossible");
        }
        return res;
    }
    
    public int modifierCategorie(CCategorie categorie){
        
    //Requête à executer
    String req = "UPDATE `categories` SET Nom_Categorie ='"
                + categorie.getNomCategorie()
                + "' WHERE Id_Categorie = '" + categorie.getIdCategorie()+"';";
        int res = -1;
        if (bdd.connecter() == true) {    //Execution de la Requête
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res modifierCategorie = " + res);
            bdd.deconnecter();
        } else {              //Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO modifierCategorie");
        }
        return res;
    }
    
    //Converti les données SQL en java
    CCategorie convertirRSLangue(ResultSet rs) {    
        try {
            int idCategorie = rs.getInt("Id_Categorie");
            String NomCategorie = rs.getString("Nom_Categorie");          
            return new CCategorie(idCategorie,NomCategorie);
        } catch (SQLException ex) {
            Logger.getLogger(CTableCategorie.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }  
    
  //Permet de lire toutes les categories de la BDD
    public ArrayList<CCategorie> lireCategorie() {            
        if (bdd.connecter() == true) {
            ArrayList<CCategorie> listeCategories = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`categories`");
            try {
                while (rs.next()) {//Ajout des données a une liste
                    CCategorie categorie = convertirRSLangue(rs);
                    listeCategories.add(categorie);
                }
                if(listeCategories.isEmpty()){//Si bbd vide renvoi un msg d'erreur
                    System.out.println("404 toutes categories not found");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeCategories;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion impossible bdd lire toutes les categories");
        }
        return null;
    }
    
    
    //Permet de lire une langue de la BDD
     public ArrayList<CCategorie> lireUneCategorie(int id) {      
        if (bdd.connecter() == true) {
            ArrayList<CCategorie> listeCategories = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`categories` WHERE `Id_Categorie` = '"+id+"';");
            try {
                while (rs.next()) {       //Ajout des données a la liste
                    CCategorie categorie = convertirRSLangue(rs);
                    listeCategories.add(categorie);
                }
                if(listeCategories.isEmpty()){//Si données introuvables renvoi un msg d'erreur
                    System.out.println("404 categories not found");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeCategories;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO");
        }
        return null;
    }
     
    public void printCategorie(ArrayList<CCategorie> liste){      //Permet d'afficher les lectures de la BDD
         liste.forEach((langue)->{
             System.out.println("Nom de la categorie : " + langue.getNomCategorie()); 
         });
     }
    
    //test des méthodes
    public static void main(String[] args) {
        CTableCategorie tableCategorie = new CTableCategorie(); 
        CCategorie categorie = new CCategorie(9, "Test");
        
      // tableCategorie.insererCategorie(categorie);
      // tableCategorie.modifierCategorie(categorie);
      // tableCategorie.supprimerCategorie("9");
      // tableCategorie.printCategorie(tableCategorie.lireCategorie());
       tableCategorie.printCategorie(tableCategorie.lireUneCategorie(1));
        
    }
    
    
}
