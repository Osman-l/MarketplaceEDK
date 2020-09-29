package com.example.marketplace.user.createtable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TableCreateUser {

    private static final String SQL_CREATE_User = "CREATE TABLE User"
            + "("
            + " id integer NOT NULL ,"
            + " name VARCHAR(30) NOT NULL,"
            + "surname VARCHAR(30) NOT NULL,"
            + "email VARCHAR (50) NOT NULL,"
            + "password VARCHAR (30) NOT NULL,"
            + "phone VARCHAR (12) ,"
            + "location VARCHAR (100),"
            + "facebook VARCHAR (100),"
            + "vk VARCHAR (100),"
            + "category VARCHAR (100),"
            + "companyname VARCHAR (100),"
            + "telegram VARCHAR (100)"
            + ")";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://mysql-164293.srv.hoster.ru/srv164293_marketplace?useUnicode=true&useJDBCCo" +
                        "mpliantTimezoneShift=true&useLegacyDatetimeCod" +
                        "e=false&serverTimezone=UTC", "srv164293_Osman", "ghewr123");
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_CREATE_User)) {

            preparedStatement.execute();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
