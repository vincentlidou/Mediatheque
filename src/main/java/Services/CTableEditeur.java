/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CEditeur;
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
public class CTableEditeur {
    //Ajout des objets
    protected ArrayList<CEditeur> listediteur;
    protected CBDD bdd;
    
    //Getter et setter CTableEditeur

    public ArrayList<CEditeur> getListediteur() {
        return listediteur;
    }

    public void setListediteur(ArrayList<CEditeur> listediteur) {
        this.listediteur = listediteur;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }
    
    public CTableEditeur(){
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBDD.properties"));
    }
    
    public int insererEditeur(CEditeur editeur){
        //Req a executer
        String req = "INSERT INTO `editeur`(`Id_Editeur`,"
                + " `Nom_Editeur`)"
                + "  VALUES ('"
                +editeur.getIdEditeur()+"', '"
                +editeur.getNomEditeur()+"');";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la Rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res insererediteur = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("connexion impossible bdd insererEditeur");
        }
        return res;
        
    }
    
    public int supprimerEditeur(String id){
        //Rqt a executer
         String req = "DELETE FROM `editeur` WHERE `Id_Editeur` = '"+id+"';";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res supprimer editeur = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO suppression editeur impossible");
        }
        return res;
    }
    
    public int modifierEditeur(CEditeur editeur){
      //Rqt a executer
    String req = "UPDATE `editeur` SET Nom_Editeur ='"
                + editeur.getNomEditeur()
                + "' WHERE Id_Editeur = '"+editeur.getIdEditeur()+"';";
    
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res modifierEditeur = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO modifierEditeur");
        }
        return res;
    }   
    
    CEditeur convertirRSEditeur(ResultSet rs) {//Converti les données SQL en java
        try {
            int idEditeur = rs.getInt("Id_Editeur");
            String nomEditeur = rs.getString("Nom_Editeur");
          
            return new CEditeur(idEditeur, nomEditeur);
        } catch (SQLException ex) {
            Logger.getLogger(CTableEditeur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<CEditeur> lireEditeur() {//Permet de lire tous les editeurs de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CEditeur> listeEditeurs = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`editeur`");
            try {
                while (rs.next()) {//Ajout des données a une liste
                    CEditeur editeur = convertirRSEditeur(rs);
                    listeEditeurs.add(editeur);
                }
                if(listeEditeurs.isEmpty()){//Si bbd vide renvoi un msg d'erreur
                    System.out.println("404 ALL auteurs not found");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeEditeurs;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion impossible bdd lire tous les editeurs");
        }
        return null;
    }
    
     public ArrayList<CEditeur> lireUnEditeur(int id) {//Permet de lire un editeur de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CEditeur> listeEditeurs = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`editeur` WHERE `Id_Editeur` = '"+id+"';");
            try {
                while (rs.next()) {//Ajout des données a la liste
                    CEditeur auteur = convertirRSEditeur(rs);
                    listeEditeurs.add(auteur);
                }
                if(listeEditeurs.isEmpty()){//Si données introuvables renvoi un msg d'erreur
                    System.out.println("404 visiteur not found");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeEditeurs;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO");
        }
        return null;
    }
     
    public void printEditeur(ArrayList<CEditeur> liste){//Permet d'afficher les lectures de la BDD
         liste.forEach((editeur)->{
             System.out.println("Nom de l'editeur : " + editeur.getNomEditeur()); 
         });
     }
    
    
    //test methode
    public static void main(String[] args) {
        CTableEditeur tableEditeur = new CTableEditeur();
        CEditeur editeur = new CEditeur(1, "Hachette");
        tableEditeur.insererEditeur(editeur);
        //tableEditeur.supprimerEditeur("1");
        //tableEditeur.modifierEditeur(editeur);
        //tableEditeur.printEditeur(tableEditeur.lireEditeur());
        //tableEditeur.printEditeur(tableEditeur.lireUnEditeur(1));
        
    }
}
