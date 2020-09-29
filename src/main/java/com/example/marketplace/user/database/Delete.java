package com.example.marketplace.user.database;

import com.example.marketplace.user.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import static com.example.marketplace.user.database.Select.*;

public class Delete {
   // public static String UrlDB = "jdbc:mysql://localhost:3306/marketplace";

    public static void DeleteFavorite( Map<String, String> info) {
        String SQL_DELETE = "DELETE FROM Favorite  WHERE id='"+info.get("id")+"'";

        System.out.println(SQL_DELETE);
        try (Connection conn = DriverManager.getConnection(
                UrlDB, logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE)) {

            int row = preparedStatement.executeUpdate();

            // rows affected
            System.out.println(row);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void DeleteKatalog( Map<String, String> info) {
        String SQL_DELETE = "DELETE FROM katalog_kurs  WHERE id='"+info.get("id")+"'";

        System.out.println(SQL_DELETE);
        try (Connection conn = DriverManager.getConnection(
                UrlDB, logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE)) {

            int row = preparedStatement.executeUpdate();

            // rows affected
            System.out.println(row);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
