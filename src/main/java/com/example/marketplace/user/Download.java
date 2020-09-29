package com.example.marketplace.user;

import org.apache.commons.net.ftp.FTPClient;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.FileOutputStream;
import java.net.URLDecoder;

public class Download{
    public static void main(String[] args) {
        // The local filename and remote filename to be downloaded.
        String filename = "image2.jpg";

        FTPClient client = new FTPClient();
        try (OutputStream os = new FileOutputStream(filename)) {
            client.connect("ftp-164293.srv.hoster.ru");
            client.login("srv164293_Osman", "ghewr123");

            // Download file from FTP server.
            boolean status = client.retrieveFile("/fhoto/toptalyat@gmail.com.jpg", os);
           // FileInputStream in = new FileInputStream("marketplace/image2.png");
            //BufferedInputStream buf = new BufferedInputStream(in);
            //byte[] bMapArray= new byte[buf.available()];
            //System.out.println(bMapArray);
            File currentClass = new File(URLDecoder.decode(Download.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath(), "UTF-8"));
            String classDirectory = currentClass.getParent();
            System.out.println(classDirectory);
            File file = new File("image2.jpg");
            BufferedImage bImage = ImageIO.read(new File("image2.jpg"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos );
            byte [] data = bos.toByteArray();
            System.out.println(data);
           Image  image = ImageIO.read(file);
            JFrame frame = new JFrame();
            JLabel label = new JLabel(new ImageIcon(image));
            frame.getContentPane().add(label, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
            System.out.println("status = " + status);
            System.out.println("reply  = " + client.getReplyString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
