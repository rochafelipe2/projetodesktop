package Model;

public class ProdutoModel extends ModelBase implements IModel {

	public int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String nome;
	public String descricao;
	public int quantidade;
	
	public String toString(){
		return id + ";" + nome + ";" + descricao + ";" + quantidade;
	}	
}
