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
public class CTableConsulte {
    //Ajout des objets

    protected CBDD bdd;

    //Getter et Setter CTableConsulte
    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableConsulte(CBDD bdd) {
        this.setBdd(bdd);
    }

    public int insererConsulte(CConsulte consulte) {
        LocalDateTime localDate = LocalDateTime.now();
        String req = "INSERT INTO `consulte`(`Id_Visiteur`, `Id_Livre`, `Chrono_Tag_Consulte`)"
                + "VALUES ('"
                + consulte.getVisiteur().getIdVisiteur() + "', '"
                + consulte.getLivre().getIdLivre() + "', '"
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

    public int supprimerConsulte(CConsulte consulte) {
        //Rqt a executer
        String req = "DELETE FROM `consulte` WHERE `Id_Livre` = '" + consulte.getLivre().getIdLivre() + "';";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res supprimer modifie = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO suppression consulte impossible");
        }
        return res;
    }

    /**
     * Méthode qui permet de modifier un enregistrement consulte dans la BDD.
     *
     * @param consulte updateTelecharge prend en paramètre un objet telecharge
     * préalablement crée. Elle modifie cet élément au sein de la BDD.
     * @return int , retourne "-1" s'il y a une erreur.
     */
    public int updateConsulte(CConsulte consulte) {
        //Rqt a executer
        LocalDateTime localDate = LocalDateTime.now();
        String req = "UPDATE `consulte` SET Id_Admin ='"
                + consulte.getIdVisiteur()
                + "' , Id_Livre = '"
                + consulte.getIdLivre()
                + "' , Chrono_Tag_Consulte = '"
                + formateLocalDateTime(localDate)
                + "' WHERE Id_Livre = '"
                + consulte.getIdLivre()
                + "' AND Id_Admin = '"
                + consulte.getIdVisiteur()
                + "';";
        int res = -1;
        if (bdd.connecter() == true) {//Execution de la rqt
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res methode updateConsulte = " + res);
            bdd.deconnecter();
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO methode updateConsulte");
        }
        return res;
    }

    CConsulte convertirRsRajout(ResultSet rs) {
        try {
            int idVisiteur = rs.getInt("Id_Visiteur");
            int idLivre = rs.getInt("Id_Livre");
            LocalDateTime dateFromDatabase = rs.getTimestamp("Chrono_Tag_Consulte").toLocalDateTime();
            return new CConsulte(idVisiteur, idLivre, dateFromDatabase);
        } catch (SQLException ex) {
            Logger.getLogger(CModifie.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<CConsulte> lireLesConsultations() {//Permet de lire toutes les telechargements de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CConsulte> listeDesConsultations = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `consulte`");
            try {
                while (rs.next()) {//Ajout des données a une liste
                    CConsulte uneConsultation = convertirRsRajout(rs);
                    listeDesConsultations.add(uneConsultation);
                }
                if (listeDesConsultations.isEmpty()) {//Si bbd vide renvoi un msg d'erreur
                    System.out.println("La liste des consultations est vide.");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeDesConsultations;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion impossible bdd lire toutes les consultations");
        }
        return null;
    }

    public void printConsulte(ArrayList<CConsulte> liste) {//Permet d'afficher les lectures de la BDD
        liste.forEach((consultation) -> {
            System.out.println("ID du livre : " + consultation.getIdLivre());
            System.out.println("ID visiteur : " + consultation.getIdVisiteur());
            System.out.println("Date : " + formateLocalDateTime(consultation.getChronoTagConsulte()));
        });
    }

    public static String formateLocalDateTime(LocalDateTime localDate) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timeFormat.format(localDate);
    }

    public static void main(String[] args) {
        CTableConsulte table = new CTableConsulte(new CBDD(new CParametresStockageBDD("parametresBdd.properties")));
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
        CConsulte consulte = new CConsulte(visiteur,livre);
        //table.supprimerRajout(rajout);
        table.insererConsulte(consulte);
        //table.updateRajout(rajout);
        table.printConsulte(table.lireLesConsultations());
    }
    
}
