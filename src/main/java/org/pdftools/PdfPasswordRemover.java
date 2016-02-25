/**
 * 
 */
package org.pdftools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author Ezer
 *
 */
public class PdfPasswordRemover {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pdfFilePath = args[0];
		String password = args[1];
		String dest = args[0] + "_removed.pdf";
		PdfPasswordRemover main = new PdfPasswordRemover();
		main.removePassword(pdfFilePath ,dest, password, null);
	}
	
	
	private void removePassword(String src, String dest, String oldPassword, String newPassword){
		PdfStamper stamper = null;
		PdfReader reader = null;
		try {
			reader = new PdfReader(src, oldPassword.getBytes());
			stamper = new PdfStamper(reader, new FileOutputStream(dest));
			if (newPassword != null){
				stamper.setEncryption(newPassword.getBytes(), newPassword.getBytes(),
						PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128
						        | PdfWriter.DO_NOT_ENCRYPT_METADATA);//FIXME - this one does not work ... 
				System.out.println("new password generated : "+newPassword);
			}
	        System.out.println("removePassword - finished check dest: "+dest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				stamper.close();
				reader.close();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
