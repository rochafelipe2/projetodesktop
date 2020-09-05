package Services;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Model.IModel;

public abstract class IBinaryService {
	
	String pastaHome = System.getProperty("user.home");
	protected BufferedWriter arquivo = null;
	protected BufferedReader saida = null;
	protected File dir = null;
	protected String table = "";
	IBinaryService(String table){
		pastaHome += "/database/";
		this.table = table;
		try{
			dir = new File(pastaHome);
			if(!dir.exists()){
				dir.mkdir();
			}

		}catch(Exception error){
			System.err.println(error.getMessage());
		}
		
	}
	
	public int buscarUltimoId() throws IOException{
		saida = new BufferedReader(new FileReader(new File(dir,table)));
		int lastId = 1;
		try {
			
			while(saida.ready()){
				int lasted = Integer.parseInt(saida.readLine().split(";")[0]);
				lastId = lasted+1;
			}
			saida.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lastId;
	}
	
	public abstract boolean Adicionar(IModel model);
	public abstract boolean Remover(int id);
	public abstract boolean Atualizar(IModel model);
	public abstract IModel Buscar (int id);
	public  abstract ArrayList<IModel> Buscar(IModel filter);
	
}
