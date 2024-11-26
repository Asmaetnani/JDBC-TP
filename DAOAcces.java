package src;

import java.sql.*;
import java.util.ArrayList;

public class DAOAcces {
    private Connection connection;
    private Statement statement;

    public DAOAcces(String url, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
            this.statement = connection.createStatement();
            System.out.println("Connexion établie avec succès.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur lors de la connexion : " + e.getMessage());
        }
    }

    public void fermerConnexion() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            System.out.println("Connexion fermée avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
        }
    }

    public ArrayList<Acces> listerDAO() {
        ArrayList<Acces> listeAcces = new ArrayList<>();
        String sql = "SELECT * FROM Acces";

        try (ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Acces acces = new Acces(
                        rs.getInt("id"),
                        rs.getString("prenom"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("statut"),
                        rs.getInt("age")
                );
                listeAcces.add(acces);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des données : " + e.getMessage());
        }

        return listeAcces;
    }

    public void ajouterDAO(Acces a) {
        String sql = "INSERT INTO Acces (id, prenom, login, password, statut, age) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, a.getId());
            pstmt.setString(2, a.getPrenom());
            pstmt.setString(3, a.getLogin());
            pstmt.setString(4, a.getPassword());
            pstmt.setString(5, a.getStatut());
            pstmt.setInt(6, a.getAge());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Accès ajouté avec succès : " + a);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout : " + e.getMessage());
        }
    }

    public void supprimerDAO(Acces a) {
        String sql = "DELETE FROM Acces WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, a.getId());

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Accès supprimé avec succès : " + a);
            } else {
                System.out.println("Aucun accès trouvé pour l'ID : " + a.getId());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
        }
    }
}