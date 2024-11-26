package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Select {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/JDBC";
        String user = "postgres";
        String password = "135343";

        String sql = "SELECT prenom, login, statut, age FROM Acces";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("Nom\t\tLogin\t\tStatut\t\tÂge");

            while (resultSet.next()) {
                String prenom = resultSet.getString("prenom");
                String login = resultSet.getString("login");
                String statut = resultSet.getString("statut");
                int age = resultSet.getInt("age");

                System.out.println(prenom + "\t\t" + login + "\t\t" + statut + "\t\t" + age);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des données : " + e.getMessage());
        }
    }
}
