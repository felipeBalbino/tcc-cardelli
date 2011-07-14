package br.edu.gamaesouza.intranet.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import br.edu.gamaesouza.intranet.bean.Aluno;

public class teste {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		// create and load default properties
		Properties defaultProps = new Properties();
		FileInputStream in = new FileInputStream("/home/felipeb/workspace/intranetfgs/src/messages_pt_BR.properties");
		defaultProps.load(in);
		System.out.println(defaultProps.getProperty("menu.cursos"));
		in.close();


	}

}
