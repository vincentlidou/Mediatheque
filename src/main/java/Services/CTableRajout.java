/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BDD.CBDD;
import BDD.CParametresStockageBDD;
import Entities.CAdministrateur;
import Entities.CEditeur;
import Entities.CLangue;
import Entities.CLivre;
import Entities.CModifie;
import Entities.CRajout;
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
public class CTableRajout {

    //Ajout des objets
    protected CBDD bdd;

    //Getter et Setter CTableVisiteur
    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableRajout(CBDD bdd) {
        this.setBdd(bdd);
    }

    public int insererRajout(CRajout rajout) {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String req = "INSERT INTO `rajoute`(`Id_Admin`, `Id_Livre`, `Chrono_Tag_Rajout`)"
                + "VALUES ('"
                + rajout.getAdmin().getIdAdmin() + "', '"
                + rajout.getLivre().getIdLivre() + "', '"
                + timeFormat.format(localDate) + "');";
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

    public int supprimerRajout(CRajout rajout) {
        //Rqt a executer
        String req = "DELETE FROM `rajoute` WHERE `Id_Livre` = '" + rajout.getLivre().getIdLivre() + "';";
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
     * @param rajout modifierLivre prend en paramètre un objet Livre
     * préalablement crée. Elle modifie cet élément au sein de la BDD.
     * @return int , retourne "-1" s'il y a une erreur.
     */
    public int updateRajout(CRajout rajout) {
        //Rqt a executer
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String req = "UPDATE `rajoute` SET Id_Admin ='"
                + rajout.getIdAdmin()
                + "' , Id_Livre = '"
                + rajout.getIdLivre()
                + "' , Chrono_Tag_Rajout = '"
                + timeFormat.format(localDate)
                + "' WHERE Id_Livre = '" 
                + rajout.getIdLivre()
                + "'AND Id_Admin = '" 
                + rajout.getIdAdmin()
                + "';";
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

    CRajout convertirRsRajout(ResultSet rs) {
        try {
            int idAdmin = rs.getInt("Id_Admin");
            int idLivre = rs.getInt("Id_Livre");
            LocalDateTime dateFromDatabase = rs.getTimestamp("Chrono_Tag_Rajout").toLocalDateTime();
            return new CRajout(idAdmin, idLivre, dateFromDatabase);
        } catch (SQLException ex) {
            Logger.getLogger(CModifie.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<CRajout> lireLesRajouts() {//Permet de lire tous les livres de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CRajout> listeDesRajouts = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `rajoute`");
            try {
                while (rs.next()) {//Ajout des données a une liste
                    CRajout unRajout = convertirRsRajout(rs);
                    listeDesRajouts.add(unRajout);
                }
                if (listeDesRajouts.isEmpty()) {//Si bbd vide renvoi un msg d'erreur
                    System.out.println("La liste des modification est vide.");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeDesRajouts;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion impossible bdd lire tous les livres");
        }
        return null;
    }

    public void printRajout(ArrayList<CRajout> liste) {//Permet d'afficher les lectures de la BDD
        liste.forEach((rajout) -> {
            System.out.println("ID du livre : " + rajout.getIdLivre());
            System.out.println("ID admin : " + rajout.getIdAdmin());
            System.out.println("Date : " + formateLocalDateTime(rajout.getChronoTagRajout()));
        });
    }
    
    public static String formateLocalDateTime(LocalDateTime localDate) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timeFormat.format(localDate);
    }

    public static void main(String[] args) {
        CTableRajout table = new CTableRajout(new CBDD(new CParametresStockageBDD("parametresBdd.properties")));
        CAdministrateur admin = new CAdministrateur(2, "jacques", "test", "test", "test");
        CLivre livre = new CLivre();
        livre.setIdLivre(4);
        livre.setTitreLivre("fdddfgfdgdfg");
        livre.setSynopsisLivre("modification");
        livre.setNombrePagesLivre(789);
        livre.setISBN(7890);
        livre.setAnneePublicationLivre(1978);
        livre.setValidationLivre("VALIDE");
        livre.setEditeur(new CEditeur(1));
        livre.setLangue(new CLangue(1));
        CRajout rajout = new CRajout(admin, livre);
        //table.supprimerRajout(rajout);
        //table.insererRajout(rajout);
        //table.updateRajout(rajout);
        table.printRajout(table.lireLesRajouts());
    }

}
