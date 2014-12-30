package com.sau.socialsau;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import net.tanesha.recaptcha.ReCaptchaImpl;

import com.sau.socialsau.util.DB;
import com.sau.socialsau.util.MD5;

public class Main {

	public static void main(String[] args) throws Exception {
	       
		/*DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
		Date date = df.parse("2012-08-15T22:56:02.038Z");
		System.out.println(date);*/
		
		/*String dateStr = "2014-08-25 17:20:47";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		System.out.println(df.parse(dateStr));*/
		
		/*String video = "http://www.youtube.com/watch?v=efvZmFd1prA";
		String videoUpload = null;
		if (video.contains("www.youtube.com/embed/")) {
			
		}else {
			String[] videoSplit = video.split("=");
			videoUpload = "http://www.youtube.com/embed/" + videoSplit[1];
		}*/
		
		//String[] workspace = System.getProperty("user.dir").split("socialsau");
		//String workspaceSocialSau = workspace[0] + "socialsau" + File.separator + "WebContent" + File.separator + "resources" + File.separator + "photos-tutorial" + File.separator;
		
		//System.out.println(MD5.getInstance("123456789"));

		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		System.out.println(Integer.parseInt(df.format(today)) - 543);
	}

}
