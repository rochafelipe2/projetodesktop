package Model;

public class FornecedorModel extends ModelBase implements IModel {

	public int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String nome;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String descricao;
	
	public String toString(){
		return this.id +";"+ this.nome + ";"+ this.descricao;
	}
}
