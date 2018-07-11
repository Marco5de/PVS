package swc.ctrl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonValue;

import swc.data.Tip;

public class TipUploaderThread extends Thread{
	private Tip tip;
	private String betterEmail, betterPin;
	private static String serverName = "http://swc.dbis.info/api/Betting";
	private static Boolean uploadSuccessful = new Boolean(false);

	public TipUploaderThread(Tip tip, String betterPin, String betterEmail){
		this.tip = tip;
		this.betterPin = betterPin;
		this.betterEmail = betterEmail;
		System.out.println(this.toString()+": created");
	}

	public static synchronized boolean wasUploadSuccesful(){
		return uploadSuccessful;
	}

	public static synchronized void resetUploadSuccesful(){
		uploadSuccessful = new Boolean(true);
	}

	public void run(){
		String websiteName = serverName+"/"+betterEmail+"/"+betterPin;
		websiteName += "/"+String.valueOf(tip.getGameId());
		websiteName += "/"+String.valueOf(tip.getGoalsHome());
		websiteName += "/"+String.valueOf(tip.getGoalsGuest());

		try{
			System.out.println(this.toString()+": calling Betting Service");
			URL website = new URL(websiteName);
			InputStream response = website.openStream();
			JsonReader reader = Json.createReader(response);

			JsonValue value = reader.readValue();
			synchronized(uploadSuccessful) {
				uploadSuccessful = new Boolean(Boolean.parseBoolean(value.toString()));
			}
		}
		catch(IOException e){
			uploadSuccessful = new Boolean(false);
		}
		finally{
			System.out.println(this.toString()+": Betting Service returned "+uploadSuccessful);
		}
	}
}