package utils;

import java.util.Random;

public class RandomOTPS{
	public static String randomString(){
		Random random = new Random();
		String str="";
		while(str.length()<=9){	
			int z = random.nextInt(2);
			switch(z){
				case (0):
				char c = (char)(65+random.nextInt(26));
				str+=c;
				break;

				case (1):
				int i = random.nextInt(10);
				str+=i;
				break;
			}
		}
		return str;
	}

	public static String randomNumber(){
		String str = "";
		Random random = new Random();
		while(str.length()<=5){
			Integer i =random.nextInt(10);
			str+=i;
		}
		return str;
	}
}