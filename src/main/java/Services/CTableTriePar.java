/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BDD.CBDD;
import BDD.CParametresStockageBDD;
import Entities.CCategorie;
import Entities.CEditeur;
import Entities.CLangue;
import Entities.CLivre;
import Entities.CTriePar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BtsSio9
 */
public class CTableTriePar {  //on ajoute les objets
  
  protected CBDD bdd;

//On créer les Getter et Setter de CTableTriePar
  public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableTriePar(CBDD bdd) {
        this.setBdd(bdd);
    }

    
    public int insererTriePar (CTriePar triePar) {
        
     String req = "INSERT INTO `trie_par`(`Id_Livre`, `Id_Categorie`)"
                + "VALUES ('"
                + triePar.getIdLivre() + "', '"
                + triePar.getIdCategorie() + "');";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }
    
     public int modifierTriePar(CTriePar triePar) {
        //Rqt a executer
            String req = "UPDATE `trie_par` SET Id_Livre ='"
                + triePar.getIdLivre()
                + "' , Id_Categorie = '"
                + triePar.getIdCategorie()
                + "' WHERE Id_Livre = '" + triePar.getIdLivre()
                + "';";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res modifierTriePar = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO modifierTriePar");
        }
        return res;
    }
    
     public int supprimerTriePar(CTriePar triePar){
        
        //Rqt a executer
         String req = "DELETE FROM `trie_par` WHERE `Id_Livre` = '"+triePar.getIdLivre()
                 +"';";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res supprimer triePar = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO suppression triePar impossible");
        }
        return res;
    }     
     
    CTriePar convertirRsTriePar(ResultSet rs) {
        try {
            int idLivre = rs.getInt("Id_Livre");
            int idCategorie = rs.getInt("Id_Categorie");
            return new CTriePar(idLivre, idCategorie);
        } catch (SQLException ex) {
            Logger.getLogger(CTableTriePar.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<CTriePar> lireTriePar() {//Permet de lire toutes les trié par de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CTriePar> listeDesTriePars = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`trie_par`");
            try {
                while (rs.next()) {//Ajout des données a une liste
                    CTriePar unTripar = convertirRsTriePar(rs);
                    listeDesTriePars.add(unTripar);
                }
                if (listeDesTriePars.isEmpty()) {//Si bbd vide renvoi un msg d'erreur
                    System.out.println("La liste des trié par est vide.");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeDesTriePars;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion impossible bdd lire tous les trié par");
        }
        return null;
    }

     public ArrayList<CTriePar> lireUnTriPar(int id) {      
        if (bdd.connecter() == true) {
            ArrayList<CTriePar> listeDesTriePars = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`trie_par` WHERE `Id_Livre` = '"+id+"';");
            try {
                while (rs.next()) {       //Ajout des données a la liste
                    CTriePar unTriePar = convertirRsTriePar(rs);
                    listeDesTriePars.add(unTriePar);
                }
                if(listeDesTriePars.isEmpty()){//Si données introuvables renvoi un msg d'erreur
                    System.out.println("404 DesTriePars not found");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeDesTriePars;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO");
        }
        return null;
    }
     
    public void printTriePar(ArrayList<CTriePar> liste){      //Permet d'afficher les lectures de la BDD
         liste.forEach((triePar)->{
             System.out.println("ID du livre : " + triePar.getIdLivre());
             System.out.println("ID de la catégorie : " + triePar.getIdCategorie());
         });
     }
   
    
    public static void main(String[] args) {
        CTableTriePar tableTriePar = new CTableTriePar(new CBDD(new CParametresStockageBDD("parametresBdd.properties"))); 
        CTriePar triePar = new CTriePar(20,4);

        tableTriePar.insererTriePar(triePar);
       // tableTriePar.modifierTriePar(triePar);
       // tableTriePar.supprimerTriePar(triePar);
       // tableTriePar.lireLesTriePars();
       // tableTriePar.printTriePar(tableTriePar.lireTriePar());
       // tableTriePar.printTriePar(tableTriePar.lireUnTriPar(19));
    }
}
