package Services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Model.ClienteModel;
import Model.IModel;

public class ConsumidorService extends IService {

	public ConsumidorService() {
		super("consumidores");
	}
	
	String pastaHome = System.getProperty("user.home");

	
	@Override
	public boolean Adicionar(IModel model) {
		// TODO Auto-generated method stub
		try{
			arquivo = new BufferedWriter(new FileWriter(new File(dir,table),true));
			((ClienteModel)model).setId(buscarUltimoId());
			arquivo.write(((ClienteModel)model).toString());
			arquivo.newLine();
			arquivo.flush();
			arquivo.close();
		}catch(Exception error){
			error.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean Remover(int id) {
		// TODO Auto-generated method stub
		try {
			saida = new BufferedReader(new FileReader(new File(dir,"consumidores")));
	        StringBuffer inputBuffer = new StringBuffer();

			while(saida.ready()){
				String line = saida.readLine();
				String[] splited =  line.split(";");
				
				if(Integer.parseInt(splited[0]) == id){
					System.out.println("Achou e removeu! -> " + splited[0]);

				}else{
					inputBuffer.append(line);
					  inputBuffer.append('\n');
				}
			}
			saida.close();
			
			 String inputStr = inputBuffer.toString();
			 FileOutputStream fileOut = new FileOutputStream(new File(dir,"consumidores"));
		        fileOut.write(inputStr.getBytes());
		        fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean Atualizar(IModel model) {
		// TODO Auto-generated method stub
		model = ((ClienteModel)model);
		try {
			saida = new BufferedReader(new FileReader(new File(dir,"consumidores")));
	        StringBuffer inputBuffer = new StringBuffer();

			while(saida.ready()){
				String line = saida.readLine();
				ClienteModel item =  Converte(line);
				
				if(item.id == ((ClienteModel)model).id){
				 line = ((ClienteModel)model).id +";"+((ClienteModel)model).nome+";" +((ClienteModel)model).idade + ";"+((ClienteModel)model).celular;
				 inputBuffer.append(line);
				 inputBuffer.append("\n");
				}else{
					inputBuffer.append(line);
					inputBuffer.append("\n");
				}
				
			}
		
			saida.close();
			
			 String inputStr = inputBuffer.toString();
			 FileOutputStream fileOut = new FileOutputStream(new File(dir,"consumidores"));
		        fileOut.write(inputStr.getBytes());
		        fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public IModel Buscar(int id) {
		// TODO Auto-generated method stub
		try {
			saida = new BufferedReader(new FileReader(new File(dir,"consumidores")));
			while(saida.ready()){
				String line = saida.readLine();
				if(!line.isEmpty()){
					ClienteModel item =  Converte(line);
					if(item.id == id){
						saida.close();
						return item;
					}
				}
			}
			saida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<IModel> Buscar(IModel filter) {
		// TODO Auto-generated method stub
		
		ArrayList<IModel> resultados = new ArrayList<IModel>();
		
		try {
			saida = new BufferedReader(new FileReader(new File(dir,"consumidores")));
			while(saida.ready()){
				String line = saida.readLine();
				if(!line.isEmpty()){
					resultados.add(Converte(line));
				}
			}
			saida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultados;
	}
	
	public ClienteModel Converte(String modelString){
		String[] line = modelString.split(";");
		
		ClienteModel model = new ClienteModel();
		model.setId(Integer.parseInt(line[0]));
		model.setCelular(line[3]);
		model.setIdade(Integer.parseInt(line[2]));
		model.setNome(line[1]);
		
		return model;
	}
	

}
