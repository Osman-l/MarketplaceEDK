package com.example.marketplace.user.database;

import java.sql.*;
import java.util.Map;

import static com.example.marketplace.user.database.Select.*;
import static com.example.marketplace.user.database.UpData.UpdateNotification;

public class Insert {
  //  public static String UrlDB = "jdbc:mysql://localhost:3306/marketplace";
    private static final String SQL_INSERT = "INSERT INTO User ( name, surname, email, password, phone, location, facebook, vk, category, companyname, telegram) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_INSERT_DIALOG = "INSERT INTO dialog ( sender, recipient, write_, notification) VALUES (?,?,?,?)";
    private static final String SQL_INSERT_SMS = "INSERT INTO sms (text, read_, id_dialog) VALUES (?,?,?)";
    //private static final String SQL_SELECT_katalog = "INSERT INTO Katalog(id,categoria,title,description,Time,praice,Image,favorites,email) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_INSERT_katalog = "INSERT INTO katalog_kurs(categoria,type,duration,kol,title,description,time,price,favorites,sertificat, programm,people,info,img,email) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_favorites = "INSERT INTO Favorite (id, email) VALUES (?,?)";
    public static int InsertDialog(String sender, String recipient) {
        int row=0;
        try (Connection conn = DriverManager.getConnection(UrlDB, logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_DIALOG)) {
            preparedStatement.setString(1,sender);
            preparedStatement.setString(2, recipient);
            preparedStatement.setString(3, "0");
            preparedStatement.setString(4, "0");
            row = preparedStatement.executeUpdate();
            System.out.println(row);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
    public static int InsertSms(Map<String, String> sms) {
        int row=0;
        try (Connection conn = DriverManager.getConnection(
                UrlDB, logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_SMS)) {
            preparedStatement.setString(1, sms.get("text"));
            preparedStatement.setString(2,"0");
            preparedStatement.setString(3, sms.get("id_dialog"));
            row = preparedStatement.executeUpdate();
            System.out.println(row);
        } catch (SQLException e) {
            row =0;
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            row =0;
        }
        UpdateNotification("0",sms.get("id_dialog"));
        return row;
    }
    public static int InsertDate(Map<String, String> user) {
        int row=0;
        try (Connection conn = DriverManager.getConnection(
                UrlDB, logindb, password);
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT)) {
            preparedStatement.setString(1, user.get("name"));
            preparedStatement.setString(2, user.get("surname"));
            preparedStatement.setString(3, user.get("email"));
            preparedStatement.setString(4, user.get("password"));
            preparedStatement.setString(5,"");
            preparedStatement.setString(6,"");
            preparedStatement.setString(7,"");
            preparedStatement.setString(8,"");
            preparedStatement.setString(9,"");
            preparedStatement.setString(10,"");
            preparedStatement.setString(11,"");
            row = preparedStatement.executeUpdate();
            System.out.println(row);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public static int InsFavorites(Map<String, String> katalog)  {
        int row=0;
        try (Connection conn = DriverManager.getConnection(
                UrlDB, logindb, password);
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_favorites)) {
            preparedStatement.setString(1, katalog.get("id"));
            preparedStatement.setString(2, katalog.get("email"));
            row = preparedStatement.executeUpdate();
            System.out.println(row);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
    public static int InsKatalog(Map<String, String> katalog) {
        int row=0;
        try (Connection conn = DriverManager.getConnection(
                UrlDB, logindb, password);

                //email,title, categoria, description, info, programm, people, sertificat, price, image
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_katalog)) {
           // preparedStatement.setString(1, String.valueOf(getindex()));
            preparedStatement.setString(1, katalog.get("categoria"));
            preparedStatement.setString(2, katalog.get("type"));
            preparedStatement.setString(3, katalog.get("duration"));
            preparedStatement.setString(4, katalog.get("kol"));
            preparedStatement.setString(5, katalog.get("title"));
            preparedStatement.setString(6, katalog.get("description"));
            preparedStatement.setString(7, katalog.get("time"));
            preparedStatement.setString(8, katalog.get("price"));
            preparedStatement.setString(9, "0");
            preparedStatement.setString(10, katalog.get("sertificat"));
            preparedStatement.setString(11, katalog.get("programm"));
            preparedStatement.setString(12, katalog.get("people"));
            preparedStatement.setString(13, katalog.get("info"));
            preparedStatement.setString(14, katalog.get("img"));
            preparedStatement.setString(15, katalog.get("email"));
            row = preparedStatement.executeUpdate();
            System.out.println(row);
            // rows affected
            // 1

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
}
// 1 главная окно listview в нижней части окна поиск
//2 каталог  мои курсы  созданные курсы  и создать курс
// 3 чат по темам (форум)
// 4сообщение
// личный кабинет информация о пользователе