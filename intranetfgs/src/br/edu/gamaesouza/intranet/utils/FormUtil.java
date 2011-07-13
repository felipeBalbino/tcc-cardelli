package br.edu.gamaesouza.intranet.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import sun.misc.BASE64Encoder;

public class FormUtil {
	public static List<Integer> getSemestresList(){
		List<Integer> semestres = new ArrayList<Integer>();
		semestres.add(1);
		semestres.add(2);
		
		return semestres;
	}
	
	public static List<String> getTurnosList(){
		List<String> turnos = new ArrayList<String>();
		turnos.add("manha");
		turnos.add("tarde");
		turnos.add("noite");
		return turnos;
	}
	
	public static List<String> getDiasSemana(){
		List<String> dias = new ArrayList<String>();
		dias.add("SEGUNDA");
		dias.add("TER�A");
		dias.add("QUARTA");
		dias.add("QUINTA");
		dias.add("SEXTA");
		dias.add("SADADO");
		dias.add("DOMINGO");
		return dias;
	}
	
	
	   
	public static String encripta (String senha) {     
	      try {     
	         MessageDigest digest = MessageDigest.getInstance("MD5");      
	         digest.update(senha.getBytes());      
	         BASE64Encoder encoder = new BASE64Encoder ();      
	         return encoder.encode (digest.digest ());      
	     } catch (NoSuchAlgorithmException ns) {     
	         ns.printStackTrace ();      
	         return senha;      
	     }      
	}  
	
	public static String getRandomPass(){
		char[] chart ={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

		char[] senha= new char[8];

		int chartLenght = chart.length;
		Random rdm = new Random();

		for (int x=0; x<8; x++)
			senha[x] = chart[rdm.nextInt(chartLenght)];

		return new String(senha);
	}

	
	public static List<Integer> getAnosList(Integer quantidade){
		Integer ano = Calendar.getInstance().get( Calendar.YEAR );
		List<Integer> anos = new ArrayList<Integer>();
		
		for(int cont = 0; cont < quantidade ; cont++){
			anos.add( ano - cont );
		}
		
		return anos;
		
	}
	
	public static Integer getAnoAtual(){
		return Calendar.getInstance().get( Calendar.YEAR );
	}
	
	
	public static Integer getAnoAnterior(Integer anoAtual){
		return  anoAtual - 1;
	}
	
	
	public static String tempoResposta(List list, Long inicio, Long end){
		return (list.size() +" resultado(s) em ("+ (end - inicio) + " Milisegundos)"); 
	}
	
	public static List<Integer> getAnos(){
		List<Integer> anos = new ArrayList<Integer>();
		anos.add(getAnoAtual());
		anos.add(getAnoAnterior(FormUtil.getAnoAtual()));
		return anos;
	}
}
