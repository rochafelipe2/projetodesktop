package Services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Model.*;

public class ProdutoService extends IService {

	public ProdutoService(String table) {
		super(table);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean Adicionar(IModel model) {
		try{
			arquivo = new BufferedWriter(new FileWriter(new File(dir,table),true));
			((ProdutoModel)model).setId(buscarUltimoId());
			arquivo.write(((ProdutoModel)model).toString());
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
			saida = new BufferedReader(new FileReader(new File(dir,"produtos")));
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
			 FileOutputStream fileOut = new FileOutputStream(new File(dir,"produtos"));
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
		model = ((ProdutoModel)model);
		try {
			saida = new BufferedReader(new FileReader(new File(dir,"produtos")));
	        StringBuffer inputBuffer = new StringBuffer();

			while(saida.ready()){
				String line = saida.readLine();
				ProdutoModel item =  Converte(line);
				
				if(item.id == ((ProdutoModel)model).id){
				 line = ((ProdutoModel)model).id +";"+((ProdutoModel)model).nome+";" +((ProdutoModel)model).descricao + ";" + ((ProdutoModel)model).quantidade;
				 inputBuffer.append(line);
				 inputBuffer.append("\n");
				}else{
					inputBuffer.append(line);
					inputBuffer.append("\n");
				}
				
			}
		
			saida.close();
			
			 String inputStr = inputBuffer.toString();
			 FileOutputStream fileOut = new FileOutputStream(new File(dir,"produtos"));
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
			saida = new BufferedReader(new FileReader(new File(dir,"produtos")));
			while(saida.ready()){
				String line = saida.readLine();
				if(!line.isEmpty()){
					ProdutoModel item =  Converte(line);
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
			saida = new BufferedReader(new FileReader(new File(dir,"produtos")));
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
	
	public ProdutoModel Converte(String modelString){
		String[] line = modelString.split(";");
		
		ProdutoModel model = new ProdutoModel();
		model.setId(Integer.parseInt(line[0]));
		model.setNome(line[1]);
		model.setDescricao(line[2]);
		model.setQuantidade(Integer.parseInt(line[3]));
		
		return model;
	}

}
