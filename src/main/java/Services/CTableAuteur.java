/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CAuteur;
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
public class CTableAuteur {
    //Ajout des objets
    protected ArrayList<CAuteur> listauteur;
    protected CBDD bdd;
    
    //Getter et setter CTableAuteur

    public ArrayList<CAuteur> getListauteur() {
        return listauteur;
    }

    public void setListauteur(ArrayList<CAuteur> listauteur) {
        this.listauteur = listauteur;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }
    
    public CTableAuteur(){
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBDD.properties"));
    }
    
    public int insererAuteur(CAuteur auteur){
        //Req a executer
        String req = "INSERT INTO `auteur`(`Id_Auteur`,"
                + " `Nom_Auteur`, `Prenom_Auteur`)"
                + "  VALUES ('"
                +auteur.getIdAuteur()+"', '"
                +auteur.getNomAuteur()+"',"
                + " '"+auteur.getPrenomAuteur()+"');";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la Rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res insererauteur = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("connexion impossible bdd insererAuteur");
        }
        return res;
        
    }
    
    public int supprimerAuteur(String id){
        //Rqt a executer
         String req = "DELETE FROM `auteur` WHERE `Id_Auteur` = '"+id+"';";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res supprimer auteur = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO suppression auteur impossible");
        }
        return res;
    }
    
    public int modifierAuteur(CAuteur auteur){
      //Rqt a executer
    String req = "UPDATE `auteur` SET Nom_Auteur ='"
                + auteur.getNomAuteur()
                + "' , Prenom_Auteur = '"
                + auteur.getPrenomAuteur()
                + "' WHERE Id_Auteur = '"+auteur.getIdAuteur()+"';";
    
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res modifierAuteur = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO modifierAuteur");
        }
        return res;
    }   
    
    CAuteur convertirRSAuteur(ResultSet rs) {//Converti les données SQL en java
        try {
            int idAuteur = rs.getInt("Id_Auteur");
            String nomAuteur = rs.getString("Nom_Auteur");
            String prenomAuteur = rs.getString("Prenom_Auteur");
          
            return new CAuteur(idAuteur, nomAuteur, prenomAuteur);
        } catch (SQLException ex) {
            Logger.getLogger(CTableAuteur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<CAuteur> lireAuteur() {//Permet de lire tous les auteurs de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CAuteur> listeAuteurs = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`auteur`");
            try {
                while (rs.next()) {//Ajout des données a une liste
                    CAuteur auteur = convertirRSAuteur(rs);
                    listeAuteurs.add(auteur);
                }
                if(listeAuteurs.isEmpty()){//Si bbd vide renvoi un msg d'erreur
                    System.out.println("404 ALL auteurs not found");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeAuteurs;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion impossible bdd lire tous les auteurs");
        }
        return null;
    }
    
     public ArrayList<CAuteur> lireUnAuteur(int id) {//Permet de lire un auteur de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CAuteur> listeAuteurs = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`auteur` WHERE `Id_Auteur` = '"+id+"';");
            try {
                while (rs.next()) {//Ajout des données a la liste
                    CAuteur auteur = convertirRSAuteur(rs);
                    listeAuteurs.add(auteur);
                }
                if(listeAuteurs.isEmpty()){//Si données introuvables renvoi un msg d'erreur
                    System.out.println("404 visiteur not found");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeAuteurs;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO");
        }
        return null;
    }
     
    public void printAuteur(ArrayList<CAuteur> liste){//Permet d'afficher les lectures de la BDD
         liste.forEach((auteur)->{
             System.out.println("Nom de l'auteur : " + auteur.getNomAuteur()); 
         });
     }
    
    
    //test methode
    public static void main(String[] args) {
        CTableAuteur tableAuteur = new CTableAuteur();
        CAuteur auteur = new CAuteur(1, "Huxley", "Aldous");
        //tableAuteur.insererAuteur(auteur);
        tableAuteur.supprimerAuteur("1");
        //tableAuteur.modifierAuteur(auteur);
        //tableAuteur.printAuteur(tableAuteur.lireAuteur());
        tableAuteur.printAuteur(tableAuteur.lireUnAuteur(1));
        
    }
}
