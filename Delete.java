package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/JDBC";
        String user = "postgres";
        String password = "135343";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'identifiant de la personne à supprimer (login): ");
        String loginToDelete = scanner.nextLine();

        String sql = "DELETE FROM Acces WHERE login = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, loginToDelete);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("L'utilisateur avec le login '" + loginToDelete + "' a été supprimé avec succès.");
            } else {
                System.out.println("Aucun utilisateur trouvé avec le login '" + loginToDelete + "'.");
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression : " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

