package com.identity.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Tools 
{
	
	//Método para generar la huella MD5
	public static String generateMD5Signature(String input) {
	    try {
	        //Cambiando MD5 por SHA-1 podríamos obtener la huella usando este otro algoritmo
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] huella = md.digest(input.getBytes());
	        return transformaAHexadecimal(huella);
	    } catch (NoSuchAlgorithmException ex) {
//	        Logger.getLogger(Tools.class.getName()).log(Level.SEVERE,
//	                "No se ha encontrado el algoritmo MD5", ex);
	        return "-1";
	    }
	}
	 
	//Método para transformar el array de bytes en una cadena hexadecimal
	private static String transformaAHexadecimal(byte buf[]) {
	    StringBuilder strbuf = new StringBuilder(buf.length * 2);
	    for (int i = 0; i < buf.length; i++) {
	        if (((int) buf[i] & 0xff) < 0x10) {
	            strbuf.append("0");
	        }
	        strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
	    }
	    return strbuf.toString();
	}
	
	
	
	
	public static boolean isBlank(String input) 
	{
		if(input==null||input.trim().equalsIgnoreCase(""))
			return true;
		else 
			return false;
					
	}

}
