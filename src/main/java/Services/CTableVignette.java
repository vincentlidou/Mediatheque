/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CVignette;
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
public class CTableVignette {
    //Ajout des objets
    protected ArrayList<CVignette> listvignette;
    protected CBDD bdd;
    
    //Getter et setter CTableVignette

    public ArrayList<CVignette> getListvignette() {
        return listvignette;
    }

    public void setListvignette(ArrayList<CVignette> listvignette) {
        this.listvignette = listvignette;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }
    
    public CTableVignette(){
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBDD.properties"));
    }
    
    public int insererVignette(CVignette vignette){
        //Req a executer
        String req = "INSERT INTO `vignette`(`Id_Vignette`, `Nom_Image_Vignette`, `Image_Vignette`, `Id_Livre`)"
                + "  VALUES ('"
                +vignette.getIdVignette()+"', '"
                +vignette.getNomImageVignette()+"', '"
                +vignette.getImageVignette()+"', '"
                +vignette.getIdLivre()+"');";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la Rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res insererVignette = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("connexion impossible bdd insererVignette");
        }
        return res;
        
    }
    
    public int supprimerVignette(String id){
        //Rqt a executer
         String req = "DELETE FROM `vignette` WHERE `Id_Vignette` = '"+id+"';";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res supprimer vignette = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO suppression vignette impossible");
        }
        return res;
    }
    
    public int modifierVignette(CVignette vignette){
      //Rqt a executer
    String req = "UPDATE `vignette` SET Nom_Image_Vignette ='"
                + vignette.getNomImageVignette()
                + "' , Nom_Image_Vignette = '"+"';";
    
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res modifierVignette = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO modifierVignette");
        }
        return res;
    }   
    
    CVignette convertirRSVignette(ResultSet rs) {//Converti les données SQL en java
        try {
            int idVignette = rs.getInt("Id_Vignette");
            String NomImageVignette = rs.getString("Nom_Image_Vignette");
            String ImageVignette = rs.getString("Image_Vignette");
            return new CVignette(idVignette,NomImageVignette,ImageVignette);
        } catch (SQLException ex) {
            Logger.getLogger(CTableVignette.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<CVignette> lireVignette() {//Permet de lire toutes les vignettes de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CVignette> listeVignettes = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`vignette`");
            try {
                while (rs.next()) {//Ajout des données a une liste
                    CVignette vignette = convertirRSVignette(rs);
                    listeVignettes.add(vignette);
                }
                if(listeVignettes.isEmpty()){//Si bbd vide renvoi un msg d'erreur
                    System.out.println("404 toutes vignettes not found");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeVignettes;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion impossible bdd lire toutes les vignettes");
        }
        return null;
    }
    
     public ArrayList<CVignette> lireUneVignette(int id) {//Permet de lire une vignette de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CVignette> listeVignettes = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`vignette` WHERE `Id_Vignette` = '"+id+"';");
            try {
                while (rs.next()) {//Ajout des données a la liste
                    CVignette vignette = convertirRSVignette(rs);
                    listeVignettes.add(vignette);
                }
                if(listeVignettes.isEmpty()){//Si données introuvables renvoi un msg d'erreur
                    System.out.println("404 visiteur not found");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeVignettes;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO");
        }
        return null;
    }
     
    public void printVignette(ArrayList<CVignette> liste){//Permet d'afficher les lectures de la BDD
         liste.forEach((vignette)->{
             System.out.println("Nom de la vignette : " + vignette.getNomImageVignette()); 
         });
     }
    
    
    //test methode
    public static void main(String[] args) {
        CTableVignette tableVignette = new CTableVignette();
        CVignette vignette = new CVignette(1, "frenchy", "/images/101.jpg");
        //tableVignette.insererVignette(vignette);
        //tableVignette.supprimerVignette("1");
        //tableVignette.modifierVignette(vignette);
        //tableVignette.printVignette(tableVignette.lireVignette());
        tableVignette.printVignette(tableVignette.lireUneVignette(1));
        
    }
}
