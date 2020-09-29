package com.example.marketplace.user.createtable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableCreateFavorite {
    private static final String SQL_CREATE = "CREATE TABLE Favorite"
            + "("
            + " id integer NOT NULL ,"  // + " id integer NOT NULL AUTO_INCREMENT  PRIMARY KEY,"
            +  "email VARCHAR(100) NOT NULL"
            + ")";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://mysql-164293.srv.hoster.ru/srv164293_marketplace?useUnicode=true&useJDBCCo" +
                        "mpliantTimezoneShift=true&useLegacyDatetimeCod" +
                        "e=false&serverTimezone=UTC", "srv164293_Osman", "ghewr123");
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_CREATE)) {

            preparedStatement.execute();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
