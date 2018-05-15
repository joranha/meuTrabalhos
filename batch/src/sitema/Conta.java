package sitema;

public class Conta {
	int id;
	String cpf_cnpj;
	String nome_cliente;
	boolean esta_ativo;
	double saldo;
	
	public Conta(int id, String cpf_cnpj, String nome_cliente, boolean esta_ativo, double saldo) {
		this.id = id;
		this.cpf_cnpj = cpf_cnpj;
		this.nome_cliente = nome_cliente;
		this.esta_ativo = esta_ativo;
		this.saldo = saldo;
	}
	
	@Override
	public String toString() {
		return id + ", " + cpf_cnpj + ", " + nome_cliente + ", " + esta_ativo + ", " + saldo;
	}
	
}
