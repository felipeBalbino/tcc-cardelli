package br.edu.gamaesouza.intranet.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import br.edu.gamaesouza.intranet.bean.Arquivo;

public class ArquivoUtil {
	
	public static String saveFile(Arquivo arquivo) {
		ServletContext sContext = ServletActionContext.getServletContext();  
		String diretorio = sContext.getRealPath("/arquivos");
		String fileName = arquivo.getNome();
		fileName += System.currentTimeMillis();
		if(arquivo.getUploadContentType().contains("powerpoint")){
			fileName += ".ppt";
		}else if (arquivo.getUploadContentType().contains("word")){
			fileName += ".doc";
		}else if((arquivo.getUploadContentType().contains("pdf"))){
			fileName += ".pdf";
		}else if((arquivo.getUploadContentType().contains("exe"))){
			fileName += ".exe";
		}else if((arquivo.getUploadContentType().contains("avi"))){
			fileName += ".avi";
		}else if((arquivo.getUploadContentType().contains("gif"))){
			fileName += ".gif";
		}else if((arquivo.getUploadContentType().contains("jpg"))){
			fileName += ".jpg";
		}else if((arquivo.getUploadContentType().contains("mp3"))){
			fileName += ".mp3";
		}else if((arquivo.getUploadContentType().contains("mpg"))){
			fileName += ".mpg";
		}else if((arquivo.getUploadContentType().contains("mov"))){
			fileName += ".mov";
		}else if((arquivo.getUploadContentType().contains("png"))){
			fileName += ".png";
		}else if((arquivo.getUploadContentType().contains("ppt"))){
			fileName += ".ppt";
		}else if((arquivo.getUploadContentType().contains("swf"))){
			fileName += ".swf";
		}else if((arquivo.getUploadContentType().contains("wav"))){
			fileName += ".wav";
		}else if((arquivo.getUploadContentType().contains("zip"))){
			fileName += ".zip";
		}else if((arquivo.getUploadContentType().contains("jpeg"))){
			fileName += ".jpeg";
		}else if((arquivo.getUploadContentType().contains("rar"))){
			fileName += ".rar";
		}else if((arquivo.getUploadContentType().contains("xls"))){
			fileName += ".xls";
		}else if((arquivo.getUploadContentType().contains("mp3"))){
			fileName += ".mp3";
		}else if((arquivo.getUploadContentType().contains("mp4"))){
			fileName += ".mp4";
		}else{
			fileName += ".txt";
		}	

		try {

			File file = new File(diretorio + "/" + fileName); // Caminho que quero salvar
			byte[] tempBytes = getBytesFromFile(arquivo.getUpload()); // Bytes do Arquivo temporário
			
			FileOutputStream ouputStream = new FileOutputStream( file ); 
                        ouputStream.write(tempBytes, 0, tempBytes.length);  
                        ouputStream.flush();  
                        ouputStream.close();  
            
			return fileName;
		} catch (IOException e) {
			return fileName;
		}
	}
	
	public static byte[] getBytesFromFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);

	  
	    long length = file.length();

	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }


	    byte[] bytes = new byte[(int)length];


	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }

	  
	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }

	
	    is.close();
	    return bytes;
	}


}
