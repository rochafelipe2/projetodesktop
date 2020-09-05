package Control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import javax.swing.JFileChooser;

public abstract class ControllerArquivo {

	String pastaHome = System.getProperty("user.home");
	protected File dir = null;
	protected String table = "";
	
	protected File arquivo = null;
	public abstract String ler();
	public abstract boolean escrever(boolean append);
	
	
	public File getArquivo() {
		return arquivo;
	}
	public void setArquivo(String table) {
		pastaHome += "/database_binario/";
		dir = new File(pastaHome);
		if(!dir.exists()){
			dir.mkdir();
		}
		arquivo = new File(dir,table);
	}
	
}
