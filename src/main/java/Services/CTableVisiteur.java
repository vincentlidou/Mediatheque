/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BDD.CBDD;
import BDD.CParametresStockageBDD;
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
 * 
 * @author BtsSio9
 */
public class CTableVisiteur {
    
    //Ajout des objets
    protected CBDD bdd;

    //Getter et Setter CTableVisiteur
    
    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableVisiteur (CBDD bdd) {
        this.setBdd(bdd);
    }
    
    
    /**
     * méthode permettant l'insertion d'une nouvelle authentification dans la
     * table en prenant pour parametre un objet
     *
     * 
     * @param nouveauVisiteur
     * @return retourne un int, '-1' si il y a une erreur
     */
    public int insererVisiteur(CVisiteur nouveauVisiteur) {
        //Requête "Insérer un Visiteur" à exécuter 
        String req = "INSERT INTO `visiteur`(`Nom_Visiteur`, `Prenom_Visiteur`, `Date_Naissance`, `Adresse_Mail_Visiteur`, `Mot_De_Passe_Visiteur`, `Adresse`, `Code_Postal`, `Ville`) " 
            + "VALUES ('"
            + nouveauVisiteur.getNomVisiteur() + "','"
            + nouveauVisiteur.getPrenomVisiteur() + "','"
            + nouveauVisiteur.getDateNaissanceVisiteur() + "','"
            + nouveauVisiteur.getEmailVisiteur() + "','"
            + nouveauVisiteur.getMdpVisiteur() + "','"
            + nouveauVisiteur.getAdresseVisiteur() + "','"
            + nouveauVisiteur.getCodePostalVisiteur() + "','"
            + nouveauVisiteur.getVilleVisiteur () + "');";
        System.out.println(req);                   //Affichage dans la console de la requête
        int res = -1;
        if (bdd.connecter() == true) {             //Exécution de la requête d'insertion
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {                                   // Message d'erreur si impossibilité d'accès à la BDD
             System.out.println("Connexion KO - Impossible d'accèder à la BDD");
        }
        return res;
    }
        
    
    public int supprimerVisiteur(int id){
    //Requête "Supprimer un Visiteur" à exécuter
    String req = "DELETE FROM `visiteur` WHERE `Id_Visiteur` = '"+id+"';";
    System.out.println(req);                 //Affichage dans la console de la requête
    int res= -1;
    if (bdd.connecter() == true) {          //Exécution de la requête d'insertion
        res = bdd.executerRequeteUpdate (req);
        System.out.println("Supprimer Visiteur = " + res);
        bdd.deconnecter();        
    }else {                                 // Message d'erreur si impossibilité d'accès
        System.out.println("Connexion KO - Impossible d'accèder à la BDD");
    }
    return res;
    }
    
    
    public int modifierVisiteur(CVisiteur modifierVisiteur){
        //Requête "Modifier un Visiteur" à exécuter
        String req = "UPDATE `visiteur` SET Nom_Visiteur = '" 
                + modifierVisiteur.getNomVisiteur()
                + "' , Prenom_Visiteur = '" 
                + modifierVisiteur.getPrenomVisiteur()
                + "' , Date_Naissance = '" 
                + modifierVisiteur.getDateNaissanceVisiteur()
                + "' , Adresse_Mail_Visiteur = '"
                + modifierVisiteur.getEmailVisiteur()
                + "' , Mot_De_Passe_Visiteur = '" 
                + modifierVisiteur.getMdpVisiteur()
                + "' , Adresse = '" 
                + modifierVisiteur.getAdresseVisiteur()
                + "' , Code_Postal = '" 
                + modifierVisiteur.getCodePostalVisiteur()
                + "' WHERE Id_Visiteur = '" + modifierVisiteur.getIdVisiteur()+"';";
            
        System.out.println(req);              //Affichage dans la console de la requête
        int res= -1;
        if (bdd.connecter() == true){         //Exécution de la requête d'insertion
            res = bdd.executerRequeteUpdate (req);
            System.out.println("Modifier Visiteur = " + res);
            bdd.deconnecter();
        }else {                               // Message d'erreur si impossibilité d'accès
            System.out.println("Connexion KO - Impossible d'accèder à la BDD");
        }
        return res;
    }
    
    
    CVisiteur convertirRsVisiteur(ResultSet rs) {//Converti les données SQL en java
        try {
            int idVisiteur = rs.getInt("Id_Visiteur");
            String nomVisiteur = rs.getString("Nom_Visiteur");
            String prenomVisiteur = rs.getString("Prenom_Visiteur");
            LocalDate dateNaissanceVisiteur = rs.getDate("Date_Naissance").toLocalDate();
            String emailVisiteur = rs.getString("Adresse_Mail_Visiteur");
            String mdpVisiteur = rs.getString("Mot_De_Passe_Visiteur");
            String adresseVisiteur = rs.getString("Adresse");
            int codePostalVisiteur = rs.getInt("Code_Postal");
            String villeVisiteur = rs.getString("Ville");

            
            return new CVisiteur(idVisiteur, nomVisiteur, prenomVisiteur, dateNaissanceVisiteur, emailVisiteur, mdpVisiteur, adresseVisiteur, codePostalVisiteur, villeVisiteur);
        
        } catch (SQLException ex) {
            Logger.getLogger(CTableVisiteur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<CVisiteur> lireVisiteur() {//Permet de lire tous les visiteurs de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CVisiteur> listeDeVisiteurs = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`visiteur`");
            try {
                while (rs.next()) {//Ajout des données a une liste
                    CVisiteur visiteur = convertirRsVisiteur(rs);
                    listeDeVisiteurs.add(visiteur);
                }
                if(listeDeVisiteurs.isEmpty()){//Si bbd vide renvoi un msg d'erreur
                    System.out.println("404 all visiteurs not found");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeDeVisiteurs;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion impossible bdd lire tous les visiteurs");
        }
        return null;
    }
    
     public ArrayList<CVisiteur> lireUnVisiteur(int id) {//Permet de lire un visiteur de la BDD
        if (bdd.connecter() == true) {
            ArrayList<CVisiteur> listeDeVisiteurs = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `mediatheque`.`visiteur` WHERE `Id_Visiteur` = '"+id+"';");
            try {
                while (rs.next()) {//Ajout des données a la liste
                    CVisiteur visiteur = convertirRsVisiteur(rs);
                    listeDeVisiteurs.add(visiteur);
                }
                if(listeDeVisiteurs.isEmpty()){//Si données introuvables renvoi un msg d'erreur
                    System.out.println("404 visiteur not found");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeDeVisiteurs;
        } else {//Si connexion impossible a la BDD renvoi un message d'erreur
            System.out.println("Connexion KO");
        }
        return null;
    }
     
    public void printVisiteur(ArrayList<CVisiteur> liste){//Permet d'afficher les lectures de la BDD
         liste.forEach((visiteur)->{
             System.out.println("Nom du visiteur : " + visiteur.getNomVisiteur()); 
             System.out.println("DoB du visiteur : " + visiteur.getDateNaissanceVisiteur());
         });
     }
    
    public static String formateLocalDate(LocalDate localDate) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return timeFormat.format(localDate);
    }
    
    //Test de l'insertion, la modification et la suppression de données dans la BDD Médiatheque
   /*public static void main(String[] args) {
        CTableVisiteur table = new CTableVisiteur(new CBDD (new CParametresStockageBDD("parametresBdd.properties")));
        CVisiteur test = new CVisiteur(6, "Ayuo", "Etre", LocalDate.parse("1961-09-23"), "ahhhh@gmail.fr", 
        "viveLesChats.01", "1 rue mainville", 35700, "Rennes");
        
       //table.insererVisiteur(test);
       //table.supprimerVisiteur(6);
       //table.modifierVisiteur(test);
       // table.printVisiteur(table.lireVisiteur());
        table.printVisiteur(table.lireUnVisiteur(6));
        
    }
    */
}