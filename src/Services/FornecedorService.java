package Services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Model.ConsumidorModel;
import Model.FornecedorModel;
import Model.IModel;

public class FornecedorService extends IService {

	public FornecedorService(String table) {
		super(table);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean Adicionar(IModel model) {
		try{
			arquivo = new BufferedWriter(new FileWriter(new File(dir,table),true));
			((FornecedorModel)model).setId(buscarUltimoId());
			arquivo.write(((FornecedorModel)model).toString());
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
			saida = new BufferedReader(new FileReader(new File(dir,"fornecedores")));
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
			 FileOutputStream fileOut = new FileOutputStream(new File(dir,"fornecedores"));
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
		model = ((FornecedorModel)model);
		try {
			saida = new BufferedReader(new FileReader(new File(dir,"fornecedores")));
	        StringBuffer inputBuffer = new StringBuffer();

			while(saida.ready()){
				String line = saida.readLine();
				FornecedorModel item =  Converte(line);
				
				if(item.id == ((FornecedorModel)model).id){
				 line = ((FornecedorModel)model).id +";"+((FornecedorModel)model).nome+";" +((FornecedorModel)model).descricao;
				 inputBuffer.append(line);
				 inputBuffer.append("\n");
				}else{
					inputBuffer.append(line);
					inputBuffer.append("\n");
				}
				
			}
		
			saida.close();
			
			 String inputStr = inputBuffer.toString();
			 FileOutputStream fileOut = new FileOutputStream(new File(dir,"fornecedores"));
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
			saida = new BufferedReader(new FileReader(new File(dir,"fornecedores")));
			while(saida.ready()){
				String line = saida.readLine();
				if(!line.isEmpty()){
					FornecedorModel item =  Converte(line);
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
			saida = new BufferedReader(new FileReader(new File(dir,"fornecedores")));
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
	
	public FornecedorModel Converte(String modelString){
		String[] line = modelString.split(";");
		
		FornecedorModel model = new FornecedorModel();
		model.setId(Integer.parseInt(line[0]));
		model.setNome(line[1]);
		model.setDescricao(line[2]);
		
		
		return model;
	}

}
