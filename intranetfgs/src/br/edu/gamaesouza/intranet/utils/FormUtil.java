package br.edu.gamaesouza.intranet.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
		return (list.size() +" resultados em ("+ (end - inicio) + " Milisegundo(s))"); 
	}
	
	public static List<Integer> getAnos(){
		List<Integer> anos = new ArrayList<Integer>();
		anos.add(getAnoAtual());
		anos.add(getAnoAnterior(FormUtil.getAnoAtual()));
		return anos;
	}
}
