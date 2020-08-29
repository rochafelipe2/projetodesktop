package Model;

public class ClienteModel implements IModel{

	public ClienteModel(){
		
	}
	
    public ClienteModel(int id,String nome, int idade, String cel){
		this.nome = nome;
		this.idade = idade;
		this.celular = cel;
		this.id = id;
	}
	
	public int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		
		this.id = id;
	}

	public String nome;
	public String celular;
	public int idade;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String toString(){
		return this.id +";"+ this.nome + ";"+ this.idade+ ";" + this.celular;
	}
	
	
	
}
