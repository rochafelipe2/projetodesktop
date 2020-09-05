package Control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Model.ConsumidorModel;
import Model.IModel;
import Model.ModelBase;

public class ControllerArquivoBinario extends ControllerArquivo {

	protected ArrayList<ModelBase> objetos = null;
	private ObjectInputStream leitor = null;
	private ObjectOutputStream escritor = null;
	
	@Override
	public String ler() {
		// TODO Auto-generated method stub
		try{
			leitor = new ObjectInputStream(new FileInputStream(arquivo));
			objetos = (ArrayList<ModelBase>) leitor.readObject();//Colocar object generic
			leitor.close();
			return "";
		}catch(ClassNotFoundException error){
			System.err.println(error.getMessage());
			
		}catch(IOException error){
			System.err.println(error.getMessage());
		}
		objetos = new ArrayList<ModelBase>();
		return "";
	}

	@Override
	public boolean escrever(boolean append) {
		// TODO Auto-generated method stub
		
		if(arquivo != null){
			try{
				escritor = new ObjectOutputStream(new FileOutputStream(arquivo,append));
				escritor.writeObject(objetos);
				escritor.close();
			}catch(IOException erro){
				System.err.println(erro.getMessage());
			}
		}
		return false;
	}

	public ModelBase buscar(int id){
		ler();
		System.out.println("ID buscado = "+ id);
		return objetos.get(id-1);
	}
	
	public ArrayList<ModelBase> buscar() {
		ler();
		return objetos;
	}

	public void add(ModelBase model) {
		try {
			((ModelBase)model).setId(buscarUltimoId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(model.toString());
		this.objetos.add(model);
		escrever(false);
	}
	

	
	
	public int buscarUltimoId() throws IOException{
		ler();
		int lastId = 1;
		try {
			int index = objetos.size();
			
			if(index > 0){
				lastId = ((ModelBase)this.objetos.get(index-1)).id+1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lastId;
	}


	
}
