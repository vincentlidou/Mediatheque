/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BDD.CBDD;
import BDD.CParametresStockageBDD;
import Entities.CCategories;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BTS sio
 */
public class CTableCategories {
    
    protected ArrayList<CCategories> listcategories;
    protected CBDD bdd;

    public ArrayList<CCategories> getListcategories() {
        return listcategories;
    }

    public void setListcategories(ArrayList<CCategories> listcategories) {
        this.listcategories = listcategories;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }
    
    public CTableCategories(){
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBDD.properties"));
    }
    public int insererCategories(CCategories categories){
        //Req a executer
        String req = "INSERT INTO `categories`(`Id_Categorie`,"
                + " `Nom_Categorie`)"
                + "  VALUES ('"
                +categories.getIdCategorie()+"', '"
                +categories.getNomCategorie()+"');";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la Rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res inserercategories = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("connexion impossible bdd insererCategories");
        }
        return res;
        
    }
    
    public int supprimerCategories(String id){
        //Rqt a executer
         String req = "DELETE FROM `categories` WHERE `Id_Categorie` = '"+id+"';";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res supprimer categories = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO suppression categories impossible");
        }
        return res;
    }
    
    public int modifierCategories(CCategories categories){
      //Rqt a executer
    String req = "UPDATE `categories` SET Nom_Categorie ='"
                + categories.getNomCategorie()
                + "' WHERE Id_Categorie = '"+categories.getIdCategorie()+"';";
    
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res modifierCategories = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO modifierCategories");
        }
        return res;
    }   
    
    CCategories convertirRSCategories(ResultSet rs) {//Converti les données SQL en java
        try {
            int idCategories = rs.getInt("Id_Categorie");
            String nomCategories = rs.getString("Nom_Categorie");
            
            
            return new CCategories(idCategories, nomCategories);
        } catch (SQLException ex) {
            Logger.getLogger(CTableCategories.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<CCategories> lireCategories() {//Permet de lire tous les admins de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CCategories> listeCategories = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`categories`");
            try {
                while (rs.next()) {//Ajout des données a une liste
                    CCategories categories = convertirRSCategories(rs);
                    listeCategories.add(categories);
                }
                if(listeCategories.isEmpty()){//Si bbd vide renvoi un msg d'erreur
                    System.out.println("404 tout categories not found");
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
    
     public ArrayList<CCategories> lireUneCategories(int id) {//Permet de lire un admin de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CCategories> listeCategories = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`categories` WHERE `Id_Categorie` = '"+id+"';");
            try {
                while (rs.next()) {//Ajout des données a la liste
                    CCategories categories = convertirRSCategories(rs);
                    listeCategories.add(categories);
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
     
    public void printCategories(ArrayList<CCategories> liste){//Permet d'afficher les lectures de la BDD
         liste.forEach((categories)->{
             System.out.println("Nom de la catégorie : " + categories.getNomCategorie()); 
         });
     }
    
    public static void main(String[] args) {
        CTableCategories tableCategories = new CTableCategories();
        CCategories categories = new CCategories (2, "Roman");
        //tableCategories.insererCategories(categories);
        //tableCategories.supprimerCategories("1");
        //tableCategories.modifierCategories(categories);
        //tableCategories.printCategories(tableCategories.lireCategories());
        tableCategories.printCategories(tableCategories.lireUneCategories(1));
}
}
