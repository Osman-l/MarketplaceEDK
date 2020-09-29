package com.example.marketplace;

import java.awt.image.BufferedImage;
import java.io.*;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import javax.imageio.ImageIO;

public class FTPUploader {
	
	FTPClient ftp = null;
	
	public FTPUploader(String host, String user, String pwd) throws Exception{
		ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		int reply;
		ftp.connect(host);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			throw new Exception("Exception in connecting to FTP Server");
		}
		ftp.login(user, pwd);
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		ftp.enterLocalPassiveMode();
	}
	public void uploadFile(BufferedImage localFileFullName, String fileName, String hostDir)
			throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(localFileFullName, "jpg", os);
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		this.ftp.storeFile( hostDir+fileName, is);

		//this.ftp.storeFile(hostDir + fileName, is);

	}

	public void disconnect(){
		if (this.ftp.isConnected()) {
			try {
				this.ftp.logout();
				this.ftp.disconnect();
			} catch (IOException f) {
				// do nothing as file is already saved to server
			}
		}
	}



/*	public static void main(String[] args) throws Exception {
		System.out.println("Start");
		FTPUploader ftpUploader = new FTPUploader("ftp-164137.srv.hoster.ru", "srv164137_Osman", "ghewr123");
		//FTP server path is relative. So if FTP account HOME directory is "/home/pankaj/public_html/" and you need to upload 
		// files to "/home/pankaj/public_html/wp-content/uploads/image2/", you should pass directory parameter as "/wp-content/uploads/image2/"
		ftpUploader.uploadFile("D:\\audi.jpg", "image.jpg", "/fhoto/");
		ftpUploader.disconnect();
		System.out.println("Done");
	}
*/

}