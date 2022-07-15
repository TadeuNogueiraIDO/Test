package br.com.idolink.api.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;

import br.com.idolink.api.execption.BaseFullException;

@SuppressWarnings("unused")
public class IdoStringUtils {
	
	public static boolean haveSpecialCharacter(String s) {
		
		if (s == null || s.trim().isEmpty()) {
	         return true;
	    }
	     Pattern p = Pattern.compile("[^A-Za-z0-9]");
	     Matcher m = p.matcher(s);
	     boolean b = m.find();
	     if (b == true)
	       return true;
	     else
	         return false;
	}
	
	public static String generateNumberOrder() {
		
		LocalDateTime datetime = LocalDateTime.now();
		
		StringBuffer str = new StringBuffer();
		str
		.append("#")
		.append(String.valueOf(datetime.getNano()))
		.append(String.valueOf(datetime.getSecond()))
		.append(String.valueOf(datetime.getMinute()))
		.append(String.valueOf(datetime.getHour()));
		return str.toString();
		
	}
	
	public static List<Long> convertStringLong(String ids){
		
		String[]splitDataUser = ids.split(",");
		List<Long> idosIds = new ArrayList<Long>();
		
		
		for (String data : splitDataUser) {
			try {
				idosIds.add(Long.valueOf(data));
			}catch (NumberFormatException e) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "Array", "api.error.array.conflict");
		}
		}
		return idosIds;
	}
	
	/**
	 * TODO -> melhorar esse codigo
	 * @param link
	 * @return
	 */
	public static String validateLink(String link) {
		String[] linkSplit = link.split("//");
		
		if (linkSplit.length >= 2) {
			link = "https://" + linkSplit[1];
		} else {
			link = "https://" + linkSplit[0];
		}
		
		try {
			@SuppressWarnings("unused")
			URL u = new URL(link);
		} catch (MalformedURLException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST,"url", "api.error.url.incorrect");
		}
		
		return link;
		
	}
}
