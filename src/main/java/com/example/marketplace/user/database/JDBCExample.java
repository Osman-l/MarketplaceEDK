package com.example.marketplace.user.database;

import com.example.marketplace.user.User;

import java.sql.*;

public class JDBCExample {

    private static final String SQL_INSERT = "INSERT INTO User (token, name, surname, email, password) VALUES (?,?,?,?,?)";

    public static void InsertDate(User user) {



            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://mysql-164137.srv.hoster.ru/srv164137_marketplace?useUnicode=true&useJDBCCo" +
                            "mpliantTimezoneShift=true&useLegacyDatetimeCod" +
                            "e=false&serverTimezone=UTC", "srv164137_osman", "ghewr123");
                 PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT)) {

                preparedStatement.setString(1, user.getId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getSurname());
                preparedStatement.setString(4, user.getEmail());

                int row = preparedStatement.executeUpdate();
                System.out.println(row);


                // rows affected
                // 1

            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }



}