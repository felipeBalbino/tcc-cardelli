package br.edu.gamaesouza.intranet.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class InternacionalizacaoUtil {

	
	private static Properties brProps = new Properties();
	private static Properties enProps = new Properties();
	
	
	public static Properties getBr() throws IOException{
		if(brProps == null){
			FileInputStream in = new FileInputStream("/home/felipeb/workspace/intranetfgs/src/messages_pt_BR.properties");
			brProps.load(in);
			in.close();
			return brProps;
		}else{
			return brProps;
		}
	}
	
	public static Properties getEn() throws IOException{
		if(enProps == null){
			FileInputStream in = new FileInputStream("/home/felipeb/workspace/intranetfgs/src/messages_en_US.properties");
			enProps.load(in);
			in.close();
			return enProps;
		}else{
			return enProps;
		}
	}
}
