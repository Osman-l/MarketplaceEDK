package com.example.marketplace.user.database;

import com.example.marketplace.Dealog;
import com.example.marketplace.user.FTPDownloader;
import com.example.marketplace.user.Sms;
import com.example.marketplace.user.User;

import java.sql.*;
import java.util.*;

import static com.example.marketplace.user.database.Select.Dialogsid;
import static com.example.marketplace.user.database.UpData.*;

public class Select {
 // public static String UrlDB="jdbc:mysql://ovz3.lyumanov-o-i14.n50jp.vps.myjino.ru:49172/marketplace?useUnicode=true&useJDBCCo" +
   //     "mpliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf8";
   // public static String UrlDB="jdbc:mysql://mysql.lyumanov-o-i14.myjino.ru/lyumanov-o-i14_marketplace?useUnicode=true&useJDBCCo" +
     //    "mpliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf8";
 public static String UrlDB="jdbc:mysql://mysql-164293.srv.hoster.ru/srv164293_marketplace?useUnicode=true&useJDBCCo" +
         "mpliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf8";
   // public static String UrlDB="jdbc:mariadb://localhost/marketplace";

    public static String logindb ="srv164293_Osman";
    public static String password="ghewr123";
  // public static String urlglobal = "jdbc:mysql://ovz3.lyumanov-o-i14.n50jp.vps.myjino.ru:49405/marketplace?useUnicode=true&useJDBCCo" +
    //        "mpliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf8";
   // public static String UrlDB = "jdbc:mysql://localhost/marketplace?useUnicode=true&useJDBCCo" +
     //    " mpliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=
  //    utf8";
    private static final String SQL_SELECT_user = "SELECT * FROM User";
    static final String SQL_SELECT_katalog = "SELECT *  FROM katalog_kurs WHERE id>3 ORDER BY id DESC, id LIMIT 6";// от 15 до 15  ORDER BY id DESC
    //private static final String SQL_SELECT_katalog = "SELECT * FROM katalog_kurs WHERE id>(SELECT COUNT(id) FROM katalog_kurs where id>=7) ";//  WHERE id IN(1,2,3,4,5)
    private static final String SQL_SELECT_Favorites = "SELECT * FROM Favorite";
    static final String SQL_SELECT_dialog = "SELECT * FROM dialog";
    private static FTPDownloader ftpDownloader =null;
    public static boolean Registrati( Map<String, String> email) {
        boolean result = true;
        try (
                Connection conn = DriverManager.getConnection(
                UrlDB, logindb, password);
                PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_user)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String email1 = resultSet.getString("email");
                if (email.get("email").equals(email1)){
                    result = false;
                    //System.out.println(result);
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }
    public static String Forgotpassword( Map<String, String> email) {
        boolean result = true;
        String userpassword ="";
        try (Connection conn = DriverManager.getConnection(
                UrlDB, logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_user)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String email1 = resultSet.getString("email");
                if (email.get("email").equals(email1)){
                    result = false;
                    userpassword =resultSet.getString("password");
                    System.out.println(userpassword);
                    System.out.println(result);
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userpassword;

    }
    public static User Login(Map<String, String> login){
        String SQL_SELECT = "SELECT * FROM User";
        int result = 0;
        User user= new User();
        try (Connection conn = DriverManager.getConnection(UrlDB, logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            user.setId("0");//пользователя нет

            while (resultSet.next()) {

                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                if (login.get("email").equals(user.getEmail()) && login.get("password").equals(user.getPassword())){
                    System.out.println();
                    user.setId("1");//пользователь есть
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setLocation(resultSet.getString("location"));
                    user.setFacebook(resultSet.getString("facebook"));
                    user.setVk(resultSet.getString("vk"));
                    user.setTelegram(resultSet.getString("telegram"));
                    user.setCategory(resultSet.getString("category"));
                    user.setCompanyname(resultSet.getString("companyname"));
                    break;
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public static  HashMap<String, String> UserData(Map<String, String> login){
        String SQL_SELECT = "SELECT * FROM User";
        HashMap user = new HashMap<String, String>();
        try (Connection conn = DriverManager.getConnection(
                UrlDB, logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String email1 = resultSet.getString("email");
                if (login.get("email").equals(email1) ){
                   user.put("name",resultSet.getString("name"));
                    user.put("surname",resultSet.getString("surname"));
                    user.put("email",resultSet.getString("email"));
                    user.put("phone",resultSet.getString("phone"));
                    user.put("location",resultSet.getString("location"));
                    user.put("facebook",resultSet.getString("facebook"));
                    user.put("vk",resultSet.getString("vk"));

                    user.put("telegram",resultSet.getString("telegram"));
                    user.put("category",resultSet.getString("category")); // 0 частное лицо  или 1 компания
                    user.put("companyname",resultSet.getString("companyname"));// имя компании\
                    int i=KatalogSelectcol(login.get("email"));
                   user.put("ad", String.valueOf(i));

                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public static List<Katalog> KatalogSerche(Map<String, String> info)  {
        String favorites= Favorites(info.get("email"));
        List<Katalog> katalog= new ArrayList<>();
        String s1 = "%"+info.get("key")+"%";
        String SQL_SELECT= "SELECT *  FROM katalog_kurs WHERE title LIKE '"+s1+"' ORDER BY id DESC, id LIMIT 30";
        System.out.println(SQL_SELECT);
        try (Connection conn = DriverManager.getConnection(UrlDB,logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            //  System.out.println("size:"+preparedStatement.getMaxFieldSize());
            while (resultSet.next()) {
                Katalog katalog1= new Katalog();
                katalog1.setId(resultSet.getString("id"));
                katalog1.setCategoria(resultSet.getString("categoria"));
                //katalog1.setTitle(resultSet.getString("type"));
                //katalog1.setDuratiom(resultSet.getString("duration"));
                //katalog1.setTitle(resultSet.getString("kol"));
                katalog1.setTitle(resultSet.getString("title"));
                katalog1.setDescription(resultSet.getString("description"));
                katalog1.setTime( resultSet.getString("time"));
                katalog1.setPraice( resultSet.getString("price"));
                String s=resultSet.getString("id");
                // System.out.println(resultSet.getString("id"));
                int b = favorites.indexOf(s);
                if(b>=0){
                    katalog1.setFavorites("1");
                }
                katalog1.setSumfavorites( resultSet.getString("favorites"));
                katalog1.setImage(resultSet.getString("img"));
                //  katalog1.setInfo(resultSet.getString("info"));
                // katalog1.setProgramm(resultSet.getString("programm"));
                // katalog1.setSertificat(resultSet.getString("sertificat"));
                // katalog1.setPeople(resultSet.getString("people"));
                katalog1.setEmail( resultSet.getString("email"));
                katalog.add(katalog1);
            }
            //  System.out.println("katalog size:"+katalog.size());
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return katalog;
    }
    public static List<Katalog> KatalogSelect(Map<String, String> info)  {
        String favorites= Favorites(info.get("email"));
        List<Katalog> katalog= new ArrayList<>();
        String SQL_SELECT="";
        if(info.get("id").equals("0")){
             SQL_SELECT = "SELECT *  FROM katalog_kurs ORDER BY id DESC, id LIMIT 10";
        }
        else {
             SQL_SELECT = "SELECT *  FROM katalog_kurs WHERE id<'"+info.get("id")+"' ORDER BY id DESC, id LIMIT 10";
        }
        try (Connection conn = DriverManager.getConnection(UrlDB,logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
          //  System.out.println("size:"+preparedStatement.getMaxFieldSize());
            while (resultSet.next()) {
                Katalog katalog1= new Katalog();
                katalog1.setId(resultSet.getString("id"));
                katalog1.setCategoria(resultSet.getString("categoria"));
                katalog1.setTitle(resultSet.getString("title"));
                katalog1.setDescription(resultSet.getString("description"));
                katalog1.setTime( resultSet.getString("time"));
                katalog1.setPraice( resultSet.getString("price"));
                String s=resultSet.getString("id");
                int b = favorites.indexOf(s);
                if(b>=0){
                    katalog1.setFavorites("1");
                }
                katalog1.setSumfavorites( resultSet.getString("favorites"));
                katalog1.setImage(resultSet.getString("img"));
                katalog1.setEmail( resultSet.getString("email"));
                katalog.add(katalog1);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return katalog;
    }
    public static List<Katalog> KatalogSelectSimilar(Map<String, String> info)  {


        String favorites= Favorites(info.get("email"));
        List<Katalog> katalog= new ArrayList<>();
        String SQL_SELECT="";

            SQL_SELECT = "SELECT *  FROM katalog_kurs WHERE id !='"+info.get("id")+"' AND categoria='"+info.get("categoria")+"' ORDER BY id DESC, id LIMIT 5";

        try (Connection conn = DriverManager.getConnection(UrlDB,logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            //  System.out.println("size:"+preparedStatement.getMaxFieldSize());
            while (resultSet.next()) {
                Katalog katalog1= new Katalog();
                katalog1.setId(resultSet.getString("id"));
                katalog1.setCategoria(resultSet.getString("categoria"));
                //katalog1.setTitle(resultSet.getString("type"));
                //katalog1.setDuratiom(resultSet.getString("duration"));
                //katalog1.setTitle(resultSet.getString("kol"));
                katalog1.setTitle(resultSet.getString("title"));
                katalog1.setDescription(resultSet.getString("description"));
                katalog1.setTime( resultSet.getString("time"));
                katalog1.setPraice( resultSet.getString("price"));
                String s=resultSet.getString("id");
                // System.out.println(resultSet.getString("id"));
                int b = favorites.indexOf(s);
                if(b>=0){
                    katalog1.setFavorites("1");
                }
                katalog1.setSumfavorites( resultSet.getString("favorites"));
                katalog1.setImage(resultSet.getString("img"));
                //  katalog1.setInfo(resultSet.getString("info"));
                // katalog1.setProgramm("programm");
                // katalog1.setSertificat("sertificat");
                // katalog1.setPeople("people");
                katalog1.setEmail( resultSet.getString("email"));
                katalog.add(katalog1);
            }
            //  System.out.println("katalog size:"+katalog.size());
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return katalog;
    }
    public static  HashMap<String, String>  DetailKatalogSelect(String id)  {
        HashMap<String, String>  katalog= new HashMap<>();
        try (Connection conn = DriverManager.getConnection(UrlDB,logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_katalog)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if(resultSet.getString("id").equals(id)) {
                    katalog.put("type",resultSet.getString("type"));
                    katalog.put("duration",resultSet.getString("duration"));
                    katalog.put("kol",resultSet.getString("kol"));
                    katalog.put("info",resultSet.getString("info"));
                    katalog.put("programm",resultSet.getString("programm"));
                    katalog.put("sertificat",resultSet.getString("sertificat"));
                    katalog.put("people",resultSet.getString("people"));
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return katalog;
    }

    public static List<Katalog> MyFavorit(String email) throws  Exception {

        boolean result = true;

        List<Katalog> katalog= new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(UrlDB,logindb, password
        );
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_katalog)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String favorites= Favorites(email);
                String s=  resultSet.getString("id");
                int b = favorites.indexOf(s);
                if(b>=0){
                Katalog katalog1= new Katalog();
                katalog1.setId(resultSet.getString("id"));
                katalog1.setCategoria(resultSet.getString("categoria"));
                katalog1.setTitle(resultSet.getString("title"));
                    katalog1.setDescription(resultSet.getString("description"));
                    katalog1.setTime( resultSet.getString("time"));
                    katalog1.setPraice( resultSet.getString("price"));
                    katalog1.setImage(resultSet.getString("img"));
                    katalog1.setSumfavorites( resultSet.getString("favorites"));
                    katalog1.setInfo(resultSet.getString("info"));
                    katalog1.setProgramm("programm");
                    katalog1.setSertificat("sertificat");
                    katalog1.setPeople("people");
                    katalog1.setEmail( resultSet.getString("email"));
                    katalog.add(katalog1);

                }
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String favorites= Favorites(email);

        for (int i=0; i<katalog.size();i++){

            String s=katalog.get(i).getId();
            int b = favorites.indexOf(s);
            if(b>=0){
                katalog.get(i).setFavorites("1");
            }
        }
        return katalog;
    }
    public static List<Katalog> MyAD(String email) throws  Exception {

        boolean result = true;

        List<Katalog> katalog= new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(UrlDB,logindb, password
        );
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_katalog)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String s=  resultSet.getString("email");
                if(s.equals(email)){
                    Katalog katalog1= new Katalog();
                    katalog1.setId(resultSet.getString("id"));
                    katalog1.setCategoria(resultSet.getString("categoria"));
                    katalog1.setTitle(resultSet.getString("title"));
                    katalog1.setDescription(resultSet.getString("description"));
                    katalog1.setTime( resultSet.getString("time"));
                    katalog1.setPraice( resultSet.getString("price"));
                    katalog1.setImage(resultSet.getString("img"));
                    katalog1.setSumfavorites( resultSet.getString("favorites"));
                    katalog1.setInfo(resultSet.getString("info"));
                    katalog1.setProgramm("programm");
                    katalog1.setSertificat("sertificat");
                    katalog1.setPeople("people");
                    katalog1.setEmail( resultSet.getString("email"));
                    katalog.add(katalog1);
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String favorites= Favorites(email);

        for (int i=0; i<katalog.size();i++){

            String s=katalog.get(i).getId();
            int b = favorites.indexOf(s);
            if(b>=0){
                katalog.get(i).setFavorites("1");
            }
        }
        return katalog;
    }

   public static List<Sms> infoSms(String id_sender,String email_sender, String email_recipient ){
       String select = "SELECT * FROM sms WHERE  id_dialog ='"+id_sender+"' OR id_dialog =" +
               " (SELECT id FROM dialog WHERE sender='"+email_recipient+"' AND recipient ='"+email_sender+"')";
      // String select = "SELECT *FROM sms WHERE id_dialog ='"+id_sender+"' OR id_dialog = (SELECT id FROM dialog WHERE recipient='"+email_sender+"' AND sender ='"+email_recipient+"')";
       List<Sms> sms= new ArrayList<>();
       try (Connection conn = DriverManager.getConnection(UrlDB,logindb, password);
            PreparedStatement preparedStatement = conn.prepareStatement(select)) {
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()) {
               Sms sms1 = new Sms();
               sms1.setId(resultSet.getString("id"));
               sms1.setRead(resultSet.getString("read_"));
               sms1.setText(resultSet.getString("text"));
               sms1.setId_dialog(resultSet.getString("id_dialog"));
               if(  resultSet.getString("id_dialog").equals(id_sender)){
                   sms1.setEmail(email_sender);

               }else{sms1.setEmail(email_recipient);}
               sms.add(sms1);
           }
       } catch (SQLException e) {
           System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
       } catch (Exception e) {
           e.printStackTrace();
       }
       UpdateWriteSms(sms.get(0).getId_dialog());

      return sms;
    }
    public static Sms EndSms(String id_sender,String email_sender, String email_recipient ){

        String select = "SELECT * FROM sms WHERE id_dialog ='"+id_sender+"' OR id_dialog = (SELECT id FROM dialog WHERE sender='"+email_recipient+"' AND recipient ='"+email_sender+"')";
        List<Sms> sms= new ArrayList<>();
        Sms sms1 = new Sms();
        try (Connection conn = DriverManager.getConnection(UrlDB,logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(select)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // sms1.setId(resultSet.getString("id"));
                sms1.setRead(resultSet.getString("read_"));
                sms1.setText(resultSet.getString("text"));
                sms1.setId_dialog(resultSet.getString("id_dialog"));
                if(  resultSet.getString("id_dialog").equals(id_sender)){
                    sms1.setEmail(email_sender);

                }else{sms1.setEmail(email_recipient);}
                sms.add(sms1);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sms1;
    }
    public static Sms MaxSms(String email){
        String select = "SELECT sms.id, sms.text, sms.read_, sms.id_dialog FROM sms join dialog ON " +
                "sms.id_dialog=dialog.id AND dialog.recipient ='"+email+"'AND dialog.notification='0' AND dialog.recipient='"+email+"' ";
        List<Sms> sms= new ArrayList<>();
        Sms sms1 = new Sms();
        try (Connection conn = DriverManager.getConnection(UrlDB,logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(select)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // sms1.setId(resultSet.getString("id"));
                sms1.setRead(resultSet.getString("sms.read_"));
                sms1.setText(resultSet.getString("sms.text"));
                sms1.setId_dialog(resultSet.getString("sms.id_dialog"));

                sms.add(sms1);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sms1;
    }
        public static List<Dealog> Dialogs(String email) {

       String select = "SELECT dialog.id, dialog.sender, dialog.recipient,  User.email, User.name, User.surname " +
                "FROM dialog join User ON dialog.recipient = User.email AND '" + email + "'= dialog.sender  ORDER BY id DESC ";
        List<Dealog> dialog= new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(UrlDB,logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(select)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Dealog dialog1= new Dealog();
                    dialog1.setId(resultSet.getString("dialog.id"));
                    dialog1.setSender(resultSet.getString("dialog.sender"));
                    dialog1.setRecipient(resultSet.getString("dialog.recipient"));
                Sms sms = EndSms(dialog1.getId(),dialog1.getSender(),dialog1.getRecipient());
                    dialog1.setSms( sms.getText());
                    dialog1.setRead( sms.getRead());
                    dialog1.setName(resultSet.getString("User.name"));
                    dialog1.setSurname(resultSet.getString("User.surname"));
                    dialog.add(dialog1);

            }


        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dialog;
    }
    public static List<Sms>Writ(Map<String, String> info) {// ноль это не прочитано
        Dealog dialog1= new Dealog();
       dialog1= DialogsWrite(info);
        String select = "SELECT *FROM sms WHERE id_dialog= '"+dialog1.getId()+"' AND read_='0'";
        String write = "0";
        List<Sms> sms= new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(UrlDB,logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(select)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Sms sms1 = new Sms();
                System.out.println(resultSet.getString("id"));
                sms1.setId(resultSet.getString("id"));
                sms1.setRead(resultSet.getString("read_"));
                sms1.setText(resultSet.getString("text"));
                sms1.setId_dialog(resultSet.getString("id_dialog"));
                sms1.setEmail(info.get("recipient"));
                sms1.setWrite(dialog1.getWrite());
                sms.add(sms1);
                if(sms1.getRead().equals("0")) {
                    UpdateWriteSms(dialog1.getId());
                   // info.put("write","1");
                   // UpdateWriteDialogs(info);
                    UpdateNotification("1",dialog1.getId());
                }
            }
            if (sms.size()==0){
                Sms sms1 = new Sms();
                sms1.setId("0");
                sms1.setWrite(dialog1.getWrite());
                sms.add(sms1);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sms;
    }
    public static Dealog DialogsWrite(Map<String, String> dialogs) {

        String select = "SELECT *FROM dialog WHERE sender = '"+dialogs.get("recipient")+"' AND  recipient='" + dialogs.get("sender") + "'  ";
        List<Dealog> dialog= new ArrayList<>();
        Dealog dialog1= new Dealog();
        try (Connection conn = DriverManager.getConnection(UrlDB,logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(select)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                dialog1.setId(resultSet.getString("id"));
                dialog1.setWrite(resultSet.getString("write_"));
                dialog1.setSender(resultSet.getString("sender"));
                dialog1.setRecipient(resultSet.getString("recipient"));
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ghhghg"+dialog1.getId());

        return dialog1;
    }
    public static String Dialogsid(Map<String, String> dialogs) {

        String select = "SELECT *FROM dialog WHERE recipient = '" +
                ""+dialogs.get("email_recipient")+"' AND '" + dialogs.get("email_sender") + "'= sender  ";
        List<Dealog> dialog= new ArrayList<>();
        Dealog dialog1= new Dealog();
        try (Connection conn = DriverManager.getConnection(UrlDB,logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(select)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dialog1.setId(resultSet.getString("id"));
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ghhghg"+dialog1.getId());
        return dialog1.getId();

    }


    public static int KatalogSelectcol(String email) {
        int i =0;

        List<Katalog> katalog= new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(UrlDB,logindb, password
        );
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_katalog)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if(email.equals(resultSet.getString("email"))){

                    i=i+1;
                }

            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;

    }

    public static int getindex(Map<String, String> katalog) {
        int id=0;

        boolean result = true;
        try (Connection conn = DriverManager.getConnection(
                UrlDB, logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM katalog_kurs WHERE id=(SELECT max(id)  FROM katalog_kurs WHERE email='"+katalog.get("email")+"')")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                    id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;

    }
    public static List<Dealog> SmsNew(Map<String, String> dialo) {
     Sms sm = MaxSms(dialo.get("email"));
     System.out.println("id="+sm.getId_dialog());
        String select = "SELECT dialog.id, dialog.sender, dialog.recipient,dialog.notification,  User.email, User.name, User.surname " +
                "FROM dialog join User ON dialog.sender = User.email AND '" +sm.getId_dialog() + "'= dialog.id ";
        List<Dealog> dialog= new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(UrlDB,logindb, password);
             PreparedStatement preparedStatement = conn.prepareStatement(select)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Dealog dialog1= new Dealog();

                dialog1.setSender(resultSet.getString("dialog.sender"));
                dialog1.setRecipient(resultSet.getString("dialog.recipient"));
               // Sms sms = EndSms(dialog1.getId(),dialog1.getSender(),dialog1.getRecipient());
                dialog1.setSms( sm.getText());
               dialog1.setRead( resultSet.getString("dialog.notification"));
                dialog1.setName(resultSet.getString("User.name"));
                dialog1.setSurname(resultSet.getString("User.surname"));
                Map<String, String> dealogmap = new HashMap<String, String>();;
                dealogmap.put("email_sender",dialog1.getRecipient());
                dealogmap.put("email_recipient",dialog1.getSender());
                dialog1.setId(Dialogsid(dealogmap));

                dialog.add(dialog1);
                if(dialog1.getRead().equals("0")) {
                    UpdateDialogsNotification(dialog1.getRecipient());
                    UpdateWriteSms(sm.getId_dialog());//новое изменение 
                }
            }


        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return dialog;
    }
 public static String Favorites(String email){
        String favorites ="";

        try (Connection conn = DriverManager.getConnection(UrlDB,logindb, password
        );
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_Favorites)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String em= resultSet.getString("email");
                if(email.equals(em)) {
                  favorites= favorites + resultSet.getString("id")+" ";
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
      //  System.out.println(favorites);
        return  favorites;
    }
}
