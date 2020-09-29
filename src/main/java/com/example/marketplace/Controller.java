package com.example.marketplace;
import com.example.marketplace.user.Sms;
import com.example.marketplace.user.User;
import com.example.marketplace.user.database.Katalog;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import static com.example.marketplace.user.MailMessages.Messages;
import static com.example.marketplace.user.database.Delete.DeleteFavorite;
import static com.example.marketplace.user.database.Delete.DeleteKatalog;
import static com.example.marketplace.user.database.Insert.*;
import static com.example.marketplace.user.database.Select.*;
import static com.example.marketplace.user.database.UpData.*;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
@RestController
public class Controller {
   @RequestMapping(value="/login{email,password}", method= POST)
   public HashMap login(@RequestParam Map<String, String> login){
       String email1= login.get("email");
       String password= login.get("password");
       System.out.println(email1);
       User user =Login(login);
       HashMap hashMap = new HashMap<String, String>();
       hashMap.put("success","0");
      System.out.println( user.getId());
       if(user.getId().equals("1")) {
           System.out.println("good");
           hashMap.put("success","1");
           hashMap.put("name",user.getName());
           hashMap.put("surname",user.getSurname());
           hashMap.put("city",user.getLocation());
           hashMap.put("facebook", user.getFacebook());
           hashMap.put("phone",user.getPhone());
           hashMap.put("vk", user.getVk());
           hashMap.put("telegram",user.getTelegram());
           hashMap.put("category",user.getCategory());
           hashMap.put("companyname",user.getCompanyname());
       }
       return hashMap;

   }
    @RequestMapping(value="/createsms{text,id_dialog}", method= POST)//
    public String Createsms(@RequestParam Map<String, String> sms) {
        String result = String.valueOf(InsertSms(sms));
        return result;
    }
    @RequestMapping(value="/createdealog{email_sender, " +
            "email_recipient, text}", method= POST)
    public String CreatDealog(@RequestParam Map<String, String> dialog) {
        String result =(Dialogsid(dialog));
        if(result ==null ) {
            System.out.println("sender= "+ dialog.get("email_sender")+
                    "recipient= "+dialog.get("email_recipient"));
            InsertDialog(dialog.get("email_sender"),dialog.get("email_recipient"));
            InsertDialog(dialog.get("email_recipient"),dialog.get("email_sender"));
            result =(Dialogsid(dialog));
            dialog.put("id_dialog",result);
            InsertSms(dialog);
        }
        return result;
    }
    @RequestMapping(value="/smswrite{sender, recipient, write}", method= POST)
    public void SmsWrite(@RequestParam Map<String, String> dialog) {
        UpdateWriteDialogs(dialog);
    }
    @RequestMapping(value="/write{sender, recipient}", method= POST)
    public List<Sms> Write(@RequestParam Map<String, String> dialog) {
        List<Sms> sms = new ArrayList<>();
        System.out.println(dialog.get("sender"));
        System.out.println(dialog.get("recipient"));
        sms=  Writ(dialog);
      return sms;
    }
    @RequestMapping(value="/id_dealog{email_sender, email_recipient}", method= POST)//
    public String IdDealog(@RequestParam Map<String, String> dialog) {
        String result =(Dialogsid(dialog));
        return result;
    }
    @RequestMapping(value="/dialog{email}", method= POST)//
    public List<Dealog> Dealog(@RequestParam Map<String, String> dealog) throws Exception {
        List<Dealog> dealogs = new ArrayList<>();
        dealogs=Dialogs(dealog.get("email"));

        return dealogs;
    }
    @RequestMapping(value="/newsms{email}", method= POST)//
    public List<Dealog> NewSms(@RequestParam Map<String, String> dealog) throws Exception {
        List<Dealog> dealogs = new ArrayList<>();
       dealogs= SmsNew(dealog);

        return dealogs;
    }

    @RequestMapping(value="/sms{email_sender," +
            " email_recipient}", method= POST)
    public List<Sms> Smsmethods(@RequestParam Map<String, String> dealog)  {
        dealog.put("id_sender", (Dialogsid(dealog)));
        System.out.println(dealog.get("id_sender"));
        System.out.println(dealog.get("email_sender"));
        System.out.println(dealog.get("email_recipient"));
        List<Sms> sms = new ArrayList<>();
        if(dealog.get("id_sender")!=null ) {
            sms = infoSms(dealog.get("id_sender"), dealog.get("email_sender"),
                    dealog.get("email_recipient"));
        }
        return sms;
    }

    @RequestMapping(value="/userdata{email}", method= POST)//возрат данных  пользователя
    public HashMap UserDat(@RequestParam Map<String, String> login) throws  Exception {
        HashMap user = new HashMap<String, String>();
         user =UserData(login);

        return  user;
    }
    @RequestMapping(value="/updatead{id,title,praice,description,info,type,programm,people,duration,kol,sertificat,image}", method= POST)//возрат данных  пользователя
    public HashMap Updated(@RequestParam Map<String, String> katalog_ad) throws  Exception {//обновление данных в  обявлении
        HashMap user = new HashMap<String, String>();
        UpdateAdd(katalog_ad);
        if(katalog_ad.get("image")!= null) {
            BufferedImage image1 = null;
            byte[] imageByte;
            try {
                BASE64Decoder decoder = new BASE64Decoder();
                imageByte = decoder.decodeBuffer(katalog_ad.get("image"));
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                image1 = ImageIO.read(bis);
                ImageIO.write(image1, "jpg", new File("/root/foto/" + katalog_ad.get("id") + ".jpg"));
                bis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("image null!!!");
        }

        return  user;
    }
    @RequestMapping(value="/profildata{email,phone,facebook,city,telegram,vk,category,companyname}", method= POST) //обновление данные профеля для занесение данных в бд
    public HashMap profildata(@RequestParam Map<String, String> login) {
        User user = new User();
        HashMap hashMap = new HashMap<String, String>();
        user.setEmail(login.get("email"));
        //user.setPassword(login.get("password"));
        user.setPhone(login.get("phone"));
        user.setFacebook(login.get("facebook"));
        user.setLocation(login.get("city"));
        user.setTelegram(login.get("telegram"));
        user.setVk(login.get("vk"));
        user.setCategory(login.get("category"));
        user.setCompanyname(login.get("companyname"));
        UpdateProfile(user);
        return hashMap;
    }

    @RequestMapping(value="/newpassword{email,password}", method= POST) //замена пароля
    public HashMap newPaswor(@RequestParam Map<String, String> login) {
        String email1= login.get("email");
        String password= login.get("password");
        User user = new User();
        HashMap hashMap = new HashMap<String, String>();
        user.setEmail(login.get("email"));
         user.setPassword(login.get("password"));
        UpdatePassword(user);
        return hashMap;

    }

    @RequestMapping(value="/registration{email}", method= POST)
    public HashMap registration(@RequestParam Map<String, String> email) {
        HashMap hashMap = new HashMap<String, String>();
        String email1;
        boolean b= Registrati(email);
        if(!b){
            hashMap.put("success",0);
            hashMap.put("regkod","error");
        }else {
            email1= email.get("email");
            System.out.println(email1);
            String random = String.valueOf( 1000 + (int) (Math.random() * 9999));
            hashMap.put("success",1);
            hashMap.put("regkod", random);
            Messages(email1,"Здравствуйте! "+email1+ "\n" +
                    "Вы регистреруетесь в мобильном приложении MarketplaceEDK. \nКод для подтверждения почты: "+random+".");
        }
         return hashMap;
    }
    @PostMapping(value="/insertdata{name,surname,email,password}")             // вставка данных пользователя при регистрации
    public HashMap insertdata(@RequestParam Map<String, String> user) {
        HashMap hashMap = new HashMap<String, String>();
        System.out.println("fdgdfgdfg"+user.get("name"));
        if(Registrati(user)) {
            String i = String.valueOf(InsertDate(user));
            hashMap.put("success", "0");
        }
        else {
            hashMap.put("success", "1");
        }
        return hashMap;
    }
    @RequestMapping(value="/forgotpassword{email}", method= POST)//забыл пароль
    public HashMap forgotpassword(@RequestParam Map<String, String> email) {
        HashMap hashMap = new HashMap<String, String>();
        String email1;
        String password= Forgotpassword(email);
        if(password.equals("")){
            hashMap.put("success",0);
            hashMap.put("regkod","error");
        }else {
            email1= email.get("email");
            System.out.println(email1);
            hashMap.put("success",1);
            hashMap.put("regkod",password);
           if(!password.equals("")){
               Messages(email1,"Здравствуйте! "+email1+ "\n" +
                       "Вы забыли пароль в мобильном приложении MarketplaceEDK. \n Ваш пароль аккаунта: : "+password+".");
           }
        }
        return hashMap;
    }
    @PostMapping(value="/favorite{id,email,result,sumfavorite}")                                //закладки
    public HashMap InsertFavorite(@RequestParam Map<String, String> info) {
        HashMap hashMap = new HashMap<String, String>();

        if(info.get("result").equals("1")){
            System.out.println("1");
            InsFavorites(info);
            UpdateFavorit(info);
        }if(info.get("result").equals("0")){
            System.out.println("0");
            DeleteFavorite(info);
            UpdateFavorit(info);
        }
        return hashMap;
    }
        @RequestMapping(value="/insertkatalog{email,title, categoria, type," +
                " duration, kol, description, info, programm, people, sertificat, price, image}", method= POST)      //создание курса
    public HashMap InsertKatalog(@RequestParam Map<String, String> katalog) {
        HashMap<String, User> hashMap = new HashMap<>();
            DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            katalog.put("time",sdf.format(date));
            katalog.put("img","0");
            if(katalog.get("image")!= null) { katalog.put("img","");}
            InsKatalog(katalog);
            if(katalog.get("image")!= null) {
           BufferedImage image1 = null;
           byte[] imageByte;
           try {
               BASE64Decoder decoder = new BASE64Decoder();
               imageByte = decoder.decodeBuffer(katalog.get("image"));
               ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
               image1 = ImageIO.read(bis);
               ImageIO.write(image1, "jpg", new File("/root/foto/" + getindex(katalog) + ".jpg"));
               bis.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       else {
           System.out.println("image null!!!");
       }
        return hashMap;
    }
    @RequestMapping(value="/delatekatalog{id}", method= POST)
    public HashMap DeletKatal(@RequestParam Map<String, String> info)  {
        HashMap user = new HashMap<String, String>();
        DeleteKatalog(info);

        return  user;
    }

   @RequestMapping(value="/katalog_similar{id, email, categoria}", method= POST)// имя фамилия фото поль фото каталога  название кампании
   public List Similar(@RequestParam Map<String, String> info) {
       List<Katalog> katalog ;
       katalog=KatalogSelectSimilar(info);
       return katalog;
   }
    @RequestMapping(value="/katalog{email,id}", method= POST)         // имя фамилия фото поль фото каталога  название кампании
    public List Katalog(@RequestParam Map<String, String> info) {
        List<Katalog> katalog ;
            katalog=KatalogSelect(info);
        return katalog;
    }
    @RequestMapping(value="/serche{email,key}", method= POST)
    public List Serche(@RequestParam Map<String, String> info) {
        List<Katalog> katalog ;
        katalog=KatalogSerche(info);
        return katalog;
    }
        @RequestMapping(value="/datail_katalog{id}", method= POST)                                 // имя фамилия фото поль фото каталога  название кампании
    public HashMap<String, String> DatailKatalog(@RequestParam Map<String, String> user) {
        HashMap<String, String> katalog=  new HashMap<>() ;
        katalog=DetailKatalogSelect(user.get("id"));
        return katalog;
    }
    @RequestMapping(value="/katalogimage{id}", method= POST)                                      // имя фамилия фото поль фото каталога  название кампании
    public String KatalogImage(@RequestParam Map<String, String> id) throws Exception{
      String strimage="0";
        File sourceimage = new File("/root/foto/"+id.get("id")+".jpg");
        if(sourceimage.isFile()) {
            InputStream inputStream = new FileInputStream(sourceimage);
                byte[] bytes = IOUtils.toByteArray(inputStream);
                String strnameimg = Base64.getEncoder().encodeToString(bytes);
                strimage = strnameimg;
        }
        return strimage;
    }
    @RequestMapping(value="/myfavorit{email}", method= POST)// имя фамилия фото поль фото каталога  название кампании
    public List Myfavorit(@RequestParam Map<String, String> user) throws Exception{
        List<Katalog> katalog ;
        katalog=MyFavorit(user.get("email"));
        return katalog;
    }
    @RequestMapping(value="/myad{email}", method= POST)// имя фамилия фото поль фото каталога  название кампании
    public List Myad(@RequestParam Map<String, String> user) throws Exception{
        List<Katalog> katalog ;
        katalog=MyAD(user.get("email"));
        return katalog;
    }
    @RequestMapping(value="/imagelogo", method= POST)
    public HashMap Image(@RequestParam Map<String, String> image) throws Exception {
        BufferedImage image1 = null;
            byte[] imageByte;
            try {
        BASE64Decoder decoder = new BASE64Decoder();
        imageByte = decoder.decodeBuffer(image.get("img"));
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image1 = ImageIO.read(bis);
                ImageIO.write(image1, "jpg",new File("/root/foto/"+image.get("email")+".jpg"));
        bis.close();
        } catch (Exception e) {
        e.printStackTrace();
        }
                HashMap<String, User> hashMap = new HashMap<>();
        return hashMap;
    }
    @RequestMapping(value="/imageprofile", method= POST)
    public HashMap ImageKatalog(@RequestParam Map<String, String> image) throws Exception {
        HashMap<String, String> hashMap = new HashMap<>();
        byte[] fileContent = new byte[0];
        File file = null;
        hashMap.put("image","0");
        if(!image.equals("")) {
            System.out.println(image.get("email"));

            File sourceimage = new File("/root/foto/" + image.get("email") + ".jpg");
            if (sourceimage.isFile()) {
                InputStream inputStream = new FileInputStream(sourceimage);
                byte[] bytes = IOUtils.toByteArray(inputStream);
                String strnameimg = Base64.getEncoder().encodeToString(bytes);
                hashMap.put("image", strnameimg);
            }
        }
        else {
            hashMap.put("image","0");
            System.out.println("null not image");
        }
       // System.out.println("rgrdgdgd"+hashMap.get("image"));
        return hashMap;
    }

}
