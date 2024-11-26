package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/JDBC";
        String user = "postgres";
        String password = "135343";

        String sql = "INSERT INTO Acces (prenom, login, password, statut, age) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setString(1, "asmae");
            statement.setString(2, "asmae123");
            statement.setString(3, "asmae");
            statement.setString(4, "Etudiant");
            statement.setInt(5, 20);


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Le tuple a été inséré avec succès !");
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion : " + e.getMessage());
        }
    }
}