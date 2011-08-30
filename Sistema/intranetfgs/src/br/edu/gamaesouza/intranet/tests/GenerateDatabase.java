package br.edu.gamaesouza.intranet.tests;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class GenerateDatabase {
	
	public static void main(String[] args) {
		
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure();
		
		SchemaExport schemaExport = new SchemaExport(configuration);
		schemaExport.create(true,true);
		
	}

}
