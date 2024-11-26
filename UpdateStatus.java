package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateStatus {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/JDBC";
        String user = "postgres";
        String password = "135343";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez l'identifiant de la personne (login) dont vous voulez modifier le statut : ");
        String loginToUpdate = scanner.nextLine();

        System.out.print("Entrez le nouveau statut : ");
        String newStatus = scanner.nextLine();

        String sql = "UPDATE Acces SET statut = ? WHERE login = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newStatus);
            statement.setString(2, loginToUpdate);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Le statut de l'utilisateur avec le login '" + loginToUpdate + "' a été mis à jour avec succès.");
            } else {
                System.out.println("Aucun utilisateur trouvé avec le login '" + loginToUpdate + "'.");
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour : " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
