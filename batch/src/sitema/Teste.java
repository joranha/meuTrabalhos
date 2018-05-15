package sitema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Teste {

	public static void main(String[] args) {
		// criando objeto batch da classe SistemaBatch
		SistemaBatch batch = new SistemaBatch();
		
		// url para conex�o com o Banco de Dados
		String url = "jdbc:mysql://localhost:3306/sistema?useSSL=false";
		// nome de usuario do Banco de Dados
		String usuario = "root";
		// senha do usuario
		String senha = "root123";
		
		//Usando o try para fechar a conex�o depois de ser usada
		try(Connection con = DriverManager.getConnection(url, usuario, senha);){
			
			//Inserindo dados na tabela
			batch.insereRandom(con);
			
			//Imprime a m�dia pedida no exerc�cio
			System.out.println("A m�dia do campo vl_total apenas para os itens que este valor seja maior que 560 e o campo id_customer esteja entre 1500 e 2700 �: \n" + batch.mediaPedida(con));
			
			//Lista que recebe todas as contas que atendem ao crit�rio pedido no exerc�cio
			List<Conta> contas = batch.leituraPedida(con);
			
			System.out.println("\n\nid   - cpf ou cnpj - nome    - ativo - saldo");
			//Impress�o dos objetos do tipo Conta na lista contas
			for (Conta conta : contas) {
				System.out.println(conta);
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao tentar se conectar:\n");
			e.printStackTrace();
		}
	}

}
