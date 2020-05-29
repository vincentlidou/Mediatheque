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
import Entities.CEdition;
import Entities.CLangue;
import Entities.CLivre;
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
public class CTableEdition {
    //Ajout des objets

    protected CBDD bdd;

    //Getter et Setter CTableEdition
    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableEdition(CBDD bdd) {
        this.setBdd(bdd);
    }

    public int insererEdition(CEdition edition) {
        LocalDateTime localDate = LocalDateTime.now();
        String req = "INSERT INTO `edite`(`Id_Admin`, `Id_Livre`, `Chrono_Tag_Edite`)"
                + "VALUES ('"
                + edition.getAdmin().getIdAdmin() + "', '"
                + edition.getLivre().getIdLivre() + "', '"
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

    public int supprimerRajout(CEdition edition) {
        //Rqt a executer
        String req = "DELETE FROM `edite` WHERE `Id_Livre` = '" + edition.getLivre().getIdLivre() + "';";
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
     * @param edition modifierEdition prend en paramètre un objet Edition
     * préalablement crée. Elle modifie cet élément au sein de la BDD.
     * @return int , retourne "-1" s'il y a une erreur.
     */
    public int updateRajout(CEdition edition) {
        //Rqt a executer
        LocalDateTime localDate = LocalDateTime.now();
        String req = "UPDATE `edite` SET Id_Admin ='"
                + edition.getIdAdmin()
                + "' , Id_Livre = '"
                + edition.getIdLivre()
                + "' , Chrono_Tag_Edite = '"
                + formateLocalDateTime(localDate)
                + "' WHERE Id_Livre = '"
                + edition.getIdLivre()
                + "'AND Id_Admin = '"
                + edition.getIdAdmin()
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

    CEdition convertirRsRajout(ResultSet rs) {
        try {
            int idAdmin = rs.getInt("Id_Admin");
            int idLivre = rs.getInt("Id_Livre");
            LocalDateTime dateFromDatabase = rs.getTimestamp("Chrono_Tag_Edite").toLocalDateTime();
            return new CEdition(idAdmin, idLivre, dateFromDatabase);
        } catch (SQLException ex) {
            Logger.getLogger(CModifie.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<CEdition> lireLesEditions() {//Permet de lire toutes les editions de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CEdition> listeDesEditions = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `edite`");
            try {
                while (rs.next()) {//Ajout des données a une liste
                    CEdition uneEdition = convertirRsRajout(rs);
                    listeDesEditions.add(uneEdition);
                }
                if (listeDesEditions.isEmpty()) {//Si bbd vide renvoi un msg d'erreur
                    System.out.println("La liste des modification est vide.");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeDesEditions;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion impossible bdd lire tous les livres");
        }
        return null;
    }

    public void printRajout(ArrayList<CEdition> liste) {//Permet d'afficher les lectures de la BDD
        liste.forEach((edition) -> {
            System.out.println("ID du livre : " + edition.getIdLivre());
            System.out.println("lID admin : " + edition.getIdAdmin());
            System.out.println("Date : " + formateLocalDateTime(edition.getChronoTagEdition()));
        });
    }

    public static String formateLocalDateTime(LocalDateTime localDate) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timeFormat.format(localDate);
    }

    public static void main(String[] args) {
        CTableEdition table = new CTableEdition(new CBDD(new CParametresStockageBDD("parametresBdd.properties")));
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
        CEdition edition = new CEdition(admin,livre);
        //table.supprimerRajout(rajout);
        table.insererEdition(edition);
        //table.updateRajout(rajout);
        table.printRajout(table.lireLesEditions());
    }

}
