/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BDD.CBDD;
import BDD.CParametresStockageBDD;
import Entities.CConsulte;
import Entities.CEditeur;
import Entities.CLangue;
import Entities.CLivre;
import Entities.CModifie;
import Entities.CUpload;
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
public class CTableUpload {
    //Ajout des objets

    protected CBDD bdd;

    //Getter et Setter CTableUpload
    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableUpload(CBDD bdd) {
        this.setBdd(bdd);
    }

    public int insererUpload(CUpload upload) {
        LocalDateTime localDate = LocalDateTime.now();
        String req = "INSERT INTO `upload`(`Id_Visiteur`, `Id_Livre`, `Chrono_Tag_Upload`)"
                + "VALUES ('"
                + upload.getVisiteur().getIdVisiteur() + "', '"
                + upload.getLivre().getIdLivre() + "', '"
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
    

    public int supprimerUpload(CUpload upload) {
        //Rqt a executer
        String req = "DELETE FROM `upload` WHERE `Id_Livre` = '" + upload.getLivre().getIdLivre() + "';";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res supprimer upload = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO suppression upload impossible");
        }
        return res;
    }

    /**
     * Méthode qui permet de modifier un enregistrement consulte dans la BDD.
     *
     * @param upload updateTelecharge prend en paramètre un objet telecharge
     * préalablement crée. Elle modifie cet élément au sein de la BDD.
     * @return int , retourne "-1" s'il y a une erreur.
     */
    public int updateUpload(CConsulte upload) {
        //Rqt a executer
        LocalDateTime localDate = LocalDateTime.now();
        String req = "UPDATE `upload` SET Id_Admin ='"
                + upload.getIdVisiteur()
                + "' , Id_Livre = '"
                + upload.getIdLivre()
                + "' , Chrono_Tag_Upload = '"
                + formateLocalDateTime(localDate)
                + "' WHERE Id_Livre = '"
                + upload.getIdLivre()
                + "' AND Id_Admin = '"
                + upload.getIdVisiteur()
                + "';";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res methode updateUpload = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO methode updateUpload");
        }
        return res;
    }

    CUpload convertirRsUpload(ResultSet rs) {
        try {
            int idVisiteur = rs.getInt("Id_Visiteur");
            int idLivre = rs.getInt("Id_Livre");
            LocalDateTime dateFromDatabase = rs.getTimestamp("Chrono_Tag_Upload").toLocalDateTime();
            return new CUpload(idVisiteur, idLivre, dateFromDatabase);
        } catch (SQLException ex) {
            Logger.getLogger(CModifie.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<CUpload> lireLesUploads() {//Permet de lire tous les uploads de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CUpload> listeDesUploads = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `upload`");
            try {
                while (rs.next()) {//Ajout des données a une liste
                    CUpload uneUpload = convertirRsUpload(rs);
                    listeDesUploads.add(uneUpload);
                }
                if (listeDesUploads.isEmpty()) {//Si bbd vide renvoi un msg d'erreur
                    System.out.println("La liste des consultations est vide.");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeDesUploads;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion impossible bdd lire tous uploads");
        }
        return null;
    }

    public void printConsulte(ArrayList<CUpload> liste) {//Permet d'afficher les lectures de la BDD
        liste.forEach((upload) -> {
            System.out.println("ID du livre : " + upload.getIdLivre());
            System.out.println("ID visiteur : " + upload.getIdVisiteur());
            System.out.println("Date : " + formateLocalDateTime(upload.getChronoTagUpload()));
        });
    }

    public static String formateLocalDateTime(LocalDateTime localDate) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timeFormat.format(localDate);
    }

    public static void main(String[] args) {
        CTableUpload table = new CTableUpload(new CBDD(new CParametresStockageBDD("parametresBdd.properties")));
        String date = "2020-03-11";
        LocalDate localDate = LocalDate.parse(date);
        CVisiteur visiteur = new CVisiteur(1, "test", "test", localDate, "test@test.com", "test", "5 rue gaubert", 35400, "Saint-Malo");
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
        CUpload consulte = new CUpload(visiteur, livre);
        //table.supprimerRajout(rajout);
        table.insererUpload(consulte);
        //table.updateRajout(rajout);
        table.printConsulte(table.lireLesUploads());
    }

}
