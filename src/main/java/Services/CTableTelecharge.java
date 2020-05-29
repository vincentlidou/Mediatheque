/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BDD.CBDD;
import BDD.CParametresStockageBDD;
import Entities.CEditeur;
import Entities.CLangue;
import Entities.CLivre;
import Entities.CModifie;
import Entities.CTelecharge;
import Entities.CVisiteur;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coco
 */
public class CTableTelecharge {
    //Ajout des objets

    protected CBDD bdd;

    //Getter et Setter CTableTelecharge
    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableTelecharge(CBDD bdd) {
        this.setBdd(bdd);
    }

    public int insererTelecharge(CTelecharge telecharge) {
        LocalDateTime localDate = LocalDateTime.now();
        String req = "INSERT INTO `telecharge`(`Id_Visiteur`, `Id_Livre`, `Chrono_Tag_Telecharge`)"
                + "VALUES ('"
                + telecharge.getVisiteur().getIdVisiteur() + "', '"
                + telecharge.getLivre().getIdLivre() + "', '"
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

    public int supprimerRegarde(CTelecharge telecharge) {
        //Rqt a executer
        String req = "DELETE FROM `telecharge` WHERE `Id_Livre` = '" + telecharge.getLivre().getIdLivre() + "';";
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
     * @param telecharge updateTelecharge prend en paramètre un objet telecharge
     * préalablement crée. Elle modifie cet élément au sein de la BDD.
     * @return int , retourne "-1" s'il y a une erreur.
     */
    public int updateTelecharge(CTelecharge telecharge) {
        //Rqt a executer
        LocalDateTime localDate = LocalDateTime.now();
        String req = "UPDATE `telecharge` SET Id_Admin ='"
                + telecharge.getIdVisiteur()
                + "' , Id_Livre = '"
                + telecharge.getIdLivre()
                + "' , Chrono_Tag_Edite = '"
                + formateLocalDateTime(localDate)
                + "' WHERE Id_Livre = '"
                + telecharge.getIdLivre()
                + "' AND Id_Admin = '"
                + telecharge.getIdVisiteur()
                + "';";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res methode updateTelecharge = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO methode updateTelecharge");
        }
        return res;
    }

    CTelecharge convertirRsRajout(ResultSet rs) {
        try {
            int idVisiteur = rs.getInt("Id_Visiteur");
            int idLivre = rs.getInt("Id_Livre");
            LocalDateTime dateFromDatabase = rs.getTimestamp("Chrono_Tag_Telecharge").toLocalDateTime();
            return new CTelecharge(idVisiteur, idLivre, dateFromDatabase);
        } catch (SQLException ex) {
            Logger.getLogger(CModifie.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<CTelecharge> lireLesTelechargements() {//Permet de lire toutes les telechargements de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CTelecharge> listeDesTelechargements = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `telecharge`");
            try {
                while (rs.next()) {//Ajout des données a une liste
                    CTelecharge unTelechargement = convertirRsRajout(rs);
                    listeDesTelechargements.add(unTelechargement);
                }
                if (listeDesTelechargements.isEmpty()) {//Si bbd vide renvoi un msg d'erreur
                    System.out.println("La liste des telechargement est vide.");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeDesTelechargements;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion impossible bdd lire tous les telechargements");
        }
        return null;
    }

    public void printRajout(ArrayList<CTelecharge> liste) {//Permet d'afficher les lectures de la BDD
        liste.forEach((telechargement) -> {
            System.out.println("ID du livre : " + telechargement.getIdLivre());
            System.out.println("ID visiteur : " + telechargement.getIdVisiteur());
            System.out.println("Date : " + formateLocalDateTime(telechargement.getChronoTagTelecharge()));
        });
    }

    public static String formateLocalDateTime(LocalDateTime localDate) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timeFormat.format(localDate);
    }

    public static void main(String[] args) {
        CTableTelecharge table = new CTableTelecharge(new CBDD(new CParametresStockageBDD("parametresBdd.properties")));
        String date = "2020-03-11";
        LocalDate localDate = LocalDate.parse(date);
        CVisiteur visiteur = new CVisiteur(1, "test", "test", localDate, "test@test.com","test","5 rue gaubert",35400,"Saint-Malo");
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
        CTelecharge telecharge = new CTelecharge(visiteur,livre);
        //table.supprimerRajout(rajout);
        table.insererTelecharge(telecharge);
        //table.updateRajout(rajout);
        table.printRajout(table.lireLesTelechargements());
    }
}
