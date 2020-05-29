/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BDD.CBDD;
import BDD.CParametresStockageBDD;
import Entities.CModifie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coco
 */
public class CTableModifie {

    //Ajout des objets
    protected CBDD bdd;

    //Getter et Setter CTableVisiteur
    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableModifie(CBDD bdd) {
        this.setBdd(bdd);
    }

    public int insererModifier(CModifie modifie) {
        LocalDateTime localDate = LocalDateTime.now();
        String req = "INSERT INTO `modifie`(`Id_Visiteur`, `Id_Livre`, `Chrono_Tag_Modification_Modifie`)"
                + "VALUES ('"
                + modifie.getIdVisiteur() + "', '"
                + modifie.getIdLivre() + "', '"
                + formateLocalDateTime(localDate) + "');";
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

    public int supprimerModifie(int id) {
        //Rqt a executer
        String req = "DELETE FROM `modifie` WHERE `Id_Livre` = '" + id + "';";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res supprimer modifie = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO suppression admin impossible");
        }
        return res;
    }

    /**
     * Méthode qui permet de modifier un enregistrement livre dans la BDD.
     *
     * @param modifie modifierLivre prend en paramètre un objet Livre
     * préalablement crée. Elle modifie cet élément au sein de la BDD.
     * @return int , retourne "-1" s'il y a une erreur.
     */
    public int updateModifie(CModifie modifie) {
        //Rqt a executer
        LocalDateTime localDate = LocalDateTime.now();
        String req = "UPDATE `modifie` SET Id_Visiteur ='"
                + modifie.getIdVisiteur()
                + "' , Id_Livre = '"
                + modifie.getIdLivre()
                + "' , Chrono_Tag_Modification_Modifie = '"
                + localDate
                + "' WHERE Id_Livre = '" + modifie.getIdLivre() + "';";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res modifierLivre = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO modifierLivre");
        }
        return res;
    }

    CModifie convertirRsModifie(ResultSet rs) {
        try {
            int idVisiteur = rs.getInt("Id_Visiteur");
            int idLivre = rs.getInt("Id_Livre");
            LocalDateTime dateFromDatabase = rs.getTimestamp("Chrono_Tag_Modification_Modifie").toLocalDateTime();
            return new CModifie(idVisiteur, idLivre, dateFromDatabase);
        } catch (SQLException ex) {
            Logger.getLogger(CModifie.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<CModifie> lireLesModifications() {//Permet de lire tous les livres de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CModifie> listeDesModifications = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `modifie`");
            try {
                while (rs.next()) {//Ajout des données a une liste
                    CModifie uneModifie = convertirRsModifie(rs);
                    listeDesModifications.add(uneModifie);
                }
                if (listeDesModifications.isEmpty()) {//Si bbd vide renvoi un msg d'erreur
                    System.out.println("La liste des modification est vide.");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeDesModifications;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion impossible bdd lire tous les livres");
        }
        return null;
    }

    public void printModifier(ArrayList<CModifie> liste) {//Permet d'afficher les lectures de la BDD
        liste.forEach((modifie) -> {
            System.out.println("ID du livre : " + modifie.getIdLivre());
            System.out.println("ID du visiteur : " + modifie.getIdVisiteur());
            System.out.println("Date : " + formateLocalDateTime(modifie.getChronoTagModifie()));
        });
    }
    
    public static String formateLocalDateTime(LocalDateTime localDate) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timeFormat.format(localDate);
    }

    public static void main(String[] args) {
        CTableModifie table = new CTableModifie(new CBDD(new CParametresStockageBDD("parametresBdd.properties")));
        CModifie test = new CModifie(1, 4);
       // table.insererModifier(test);
        //table.insererModifier(test);
        //table.modifierModifie(test);
        table.printModifier(table.lireLesModifications());
    }

}
