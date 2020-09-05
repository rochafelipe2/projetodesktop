package Control;

import java.io.IOException;

import Model.ConsumidorModel;
import Model.ModelBase;

public class ControllerConsumidor extends ControllerArquivoBinario {

	@Override
	public int buscarUltimoId() throws IOException{
		ler();
		int lastId = 1;
		try {
			int index = objetos.size();
			
			if(index > 0){
				lastId = ((ConsumidorModel)this.objetos.get(index-1)).id+1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lastId;
	}
	
	@Override
	public void add(ModelBase model) {
		try {
			((ConsumidorModel)model).setId(buscarUltimoId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.objetos.add(model);
		escrever(false);
	}
	
	
	public void atualizar(ConsumidorModel model) {
		
		ConsumidorModel edited = (ConsumidorModel) objetos.get(model.id);
		edited = model;
		escrever(false);
		
	}
	
	public void remover(int id) {
		System.out.println("remvoendo ---> "+ id);
		ConsumidorModel edited = (ConsumidorModel) objetos.get(id-1);
		objetos.remove(edited);
		escrever(false);
		
	}
}
