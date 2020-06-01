/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BDD.CBDD;
import BDD.CParametresStockageBDD;
import Entities.CEcritPar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BtsSio9
 */
public class CTableEcritPar {
    
    protected CBDD bdd;

//On créer les Getter et Setter de CTableTriePar
  public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableEcritPar(CBDD bdd) {
        this.setBdd(bdd);
    }

    
    public int insererEcritPar (CEcritPar ecritPar) {
        
     String req = "INSERT INTO `ecrit_par`(`Id_Auteur`, `Id_Livre`)"
                + "VALUES ('"
                + ecritPar.getIdAuteur() + "', '"
                + ecritPar.getIdLivre() + "');";
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
    
     public int modifierEcritPar(CEcritPar ecritPar) {
        //Rqt a executer
            String req = "UPDATE `ecrit_par` SET Id_Auteur ='"
                + ecritPar.getIdAuteur()
                + "' , Id_Livre = '"
                + ecritPar.getIdLivre()
                + "' WHERE Id_Auteur = '" + ecritPar.getIdAuteur()
                + "';";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res modifierEcritPar = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO modifierEcritPar");
        }
        return res;
    }
    
     public int supprimerEcritPar(CEcritPar ecritPar){
        
        //Rqt a executer
         String req = "DELETE FROM `ecrit_par` WHERE `Id_Auteur` = '"+ecritPar.getIdAuteur()
                 +"';";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res supprimer ecritPar = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO suppression ecritPar impossible");
        }
        return res;
    }     
     
    CEcritPar convertirRsEcritPar(ResultSet rs) {
        try {
            int idAuteur = rs.getInt("Id_Auteur");
            int idLivre = rs.getInt("Id_Livre");
            return new CEcritPar(idAuteur, idLivre);
        } catch (SQLException ex) {
            Logger.getLogger(CTableEcritPar.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<CEcritPar> lireEcritPar() {//Permet de lire tous les ecrit par de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CEcritPar> listeDesEcritPars = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`ecrit_par`");
            try {
                while (rs.next()) {//Ajout des données a une liste
                    CEcritPar unEcritpar = convertirRsEcritPar(rs);
                    listeDesEcritPars.add(unEcritpar);
                }
                if (listeDesEcritPars.isEmpty()) {//Si bbd vide renvoi un msg d'erreur
                    System.out.println("La liste des ecrit par est vide.");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeDesEcritPars;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion impossible bdd lire tous les ecrit par");
        }
        return null;
    }

      public ArrayList<CEcritPar> lireUnEcritPar(int id) {      
        if (bdd.connecter() == true) {
            ArrayList<CEcritPar> listeDesEcritPars = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`ecrit_par` WHERE `Id_Auteur` = '"+id+"';");
            try {
                while (rs.next()) {       //Ajout des données a la liste
                    CEcritPar unEcritPar = convertirRsEcritPar(rs);
                    listeDesEcritPars.add(unEcritPar);
                }
                if(listeDesEcritPars.isEmpty()){//Si données introuvables renvoi un msg d'erreur
                    System.out.println("404 DesTriePars not found");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeDesEcritPars;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO");
        }
        return null;
    }
     
    public void printEcritPar(ArrayList<CEcritPar> liste){      //Permet d'afficher les lectures de la BDD
         liste.forEach((ecritPar)->{
             System.out.println("ID de l'auteur : " + ecritPar.getIdAuteur());
             System.out.println("ID du livre : " + ecritPar.getIdLivre());
             
         });
     }
   
     
     
    public static void main(String[] args) {
        CTableEcritPar tableEcritPar = new CTableEcritPar(new CBDD(new CParametresStockageBDD("parametresBdd.properties"))); 
       CEcritPar ecritPar = new CEcritPar(2,19);

       // tableEcritPar.insererEcritPar(ecritPar);
       // tableEcritPar.modifierEcritPar(ecritPar);
       // tableEcritPar.supprimerEcritPar(ecritPar);
       // tableEcritPar.printEcritPar(tableEcritPar.lireEcritPar());
        tableEcritPar.printEcritPar(tableEcritPar.lireUnEcritPar(11));
    }
        
}
