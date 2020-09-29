package com.example.marketplace.user.database;

import com.example.marketplace.user.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.example.marketplace.user.database.Select.*;

public class UpData {
  //  public static String UrlDB = "jdbc:mysql://localhost:3306/marketplace";
  public static void UpdateRedersSms(Map<String, String> result) {
      String SQL_UPDATE = "UPDATE sms SET " +
              "read_=0 WHERE id_dialog='"+result.get("id_dialog")+"'";
      System.out.println(SQL_UPDATE);
      try (Connection conn = DriverManager.getConnection(
              UrlDB, logindb, password);
           PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE)) {

          int row = preparedStatement.executeUpdate();

          // rows affected
          System.out.println(row);

      } catch (SQLException e) {
          System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
      } catch (Exception e) {
          e.printStackTrace();
      }

  }
    public static void UpdateWriteSms(String result) {
        String SQL_UPDATE = "UPDATE sms SET read_=1 WHERE  id_dialog ='"+result+"'";
        System.out.println(SQL_UPDATE);
        try (Connection conn = DriverManager.getConnection(
                UrlDB, logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE)) {

            int row = preparedStatement.executeUpdate();

            // rows affected
            System.out.println(row);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void UpdateWriteDialogs(Map<String, String> result) {
        String SQL_UPDATE = "UPDATE dialog SET write_= '"+result.get("write")+"' WHERE sender= '"+result.get("sender")+"' AND recipient= '"+result.get("recipient")+"' ";
        System.out.println(SQL_UPDATE);
        try (Connection conn = DriverManager.getConnection(
                UrlDB, logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE)) {
            int row = preparedStatement.executeUpdate();
            // rows affected
            System.out.println(row);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void UpdateNotification(String notification , String result ) {
        String SQL_UPDATE = "";
        if(notification.equals("0")) {
             SQL_UPDATE = "UPDATE dialog SET notification='0' WHERE id='" + result + "'";
        }
        if(notification.equals("1")) {
            SQL_UPDATE = "UPDATE dialog SET notification='1' WHERE id='" + result + "'";
        }
        System.out.println(SQL_UPDATE);
        try (Connection conn = DriverManager.getConnection(
                UrlDB, logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE)) {
            int row = preparedStatement.executeUpdate();

            // rows affected
            System.out.println(row);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
    public static void UpdateDialogsNotification(String result ) {
        String SQL_UPDATE = "UPDATE dialog SET " +
                "notification='1' WHERE recipient='"+result+"'";
        System.out.println(SQL_UPDATE);
        try (Connection conn = DriverManager.getConnection(
                UrlDB, logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE)) {

            int row = preparedStatement.executeUpdate();

            // rows affected
            System.out.println(row);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void UpdateProfile(User user) {
         String SQL_UPDATE = "UPDATE User SET " +
                 "phone='"+user.getPhone()
                 +"', location='"+user.getLocation()
                 +"', facebook='"+user.getFacebook()
                 +"', vk='"+user.getVk()
                 +"', category='"+user.getCategory()
                 +"', companyname='"+user.getCompanyname()
                 +"', telegram='"+user.getTelegram()
                 +"' WHERE email='"+user.getEmail()+"'";
       System.out.println(SQL_UPDATE);
        try (Connection conn = DriverManager.getConnection(
                UrlDB, logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE)) {
            int row = preparedStatement.executeUpdate();
            // rows affected
            System.out.println(row);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void UpdatePassword(User user) {
        String SQL_UPDATE = "UPDATE User SET " +
                "password='"+user.getPassword()
                +"' WHERE email='"+user.getEmail()+"'";
        System.out.println(SQL_UPDATE);
        try (Connection conn = DriverManager.getConnection(
                UrlDB, logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE)) {

            int row = preparedStatement.executeUpdate();

            // rows affected
            System.out.println(row);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void UpdateFavorit(Map<String, String> favor) {

        String SQL_UPDATE = "UPDATE katalog_kurs SET " +
                "favorites='"+favor.get("sumfavorite")
                +"' WHERE id='"+favor.get("id")+"'";
        System.out.println(SQL_UPDATE);
        try (Connection conn = DriverManager.getConnection(
                UrlDB, logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE)) {

            int row = preparedStatement.executeUpdate();

            // rows affected
            System.out.println(row);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void UpdateAdd(Map<String, String> katalog_ad) {//title id price descriptions

        String SQL_UPDATE = "UPDATE katalog_kurs SET " +
                "type='"+katalog_ad.get("type")
                +  "',duration='"+katalog_ad.get("duration")
                +  "',kol='"+katalog_ad.get("kol")
                +  "',title='"+katalog_ad.get("title")
                +  "',description='"+katalog_ad.get("description")
                +  "',price='"+katalog_ad.get("praice")
                +  "',sertificat='"+katalog_ad.get("sertificat")
                +  "',programm='"+katalog_ad.get("programm")
                +  "',people='"+katalog_ad.get("people")
                +  "',info='"+katalog_ad.get("info")
                +"' WHERE id='"+katalog_ad.get("id")+"'";
        System.out.println(SQL_UPDATE);
        try (Connection conn = DriverManager.getConnection(
                UrlDB, logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE)) {

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
