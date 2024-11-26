package src;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/JDBC";
        String user = "postgres";
        String password = "135343";

        DAOAcces dao = new DAOAcces(url, user, password);

        System.out.println("Liste initiale :");
        ArrayList<Acces> accesList = dao.listerDAO();
        for (Acces a : accesList) {
            System.out.println(a);
        }

        System.out.println("\nAjout d'un nouvel accès :");
        Acces nouvelAcces = new Acces(7, "Sophie", "sophie123", "mypassword", "Professeur", 35);
        dao.ajouterDAO(nouvelAcces);

        System.out.println("\nListe après ajout :");
        accesList = dao.listerDAO();
        for (Acces a : accesList) {
            System.out.println(a);
        }
        //

        System.out.println("\nSuppression d'un accès :");
        dao.supprimerDAO(nouvelAcces);

        System.out.println("\nListe après suppression :");
        accesList = dao.listerDAO();
        for (Acces a : accesList) {
            System.out.println(a);
        }

        dao.fermerConnexion();
    }
}