package Control;

import java.awt.image.BufferedImageFilter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ControllerArquivoTexto extends ControllerArquivo {

	private String texto = "";
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	private BufferedReader leitor = null;
	private BufferedWriter escritor = null;
	
	
	@Override
	public String ler() {
		StringBuilder line = new StringBuilder();
		try {
			leitor = new BufferedReader(new FileReader(arquivo));
			
			while(leitor.ready()){
				line.append(leitor.readLine()).append("\n");
			}
			leitor.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		}
		return line.toString();
	}
	
	@Override
	public boolean escrever(boolean append) {

		boolean verificador = false;
		try{
			escritor = new BufferedWriter(new FileWriter(arquivo,append));
			escritor.write(getTexto());
			escritor.close();
			verificador = true;
		}catch(IOException exp){
			System.err.println(exp.getMessage());
		}
		
		return verificador;
	}
	
	
}
