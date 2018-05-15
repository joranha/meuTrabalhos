package sitema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SistemaBatch {

	//Recebe uma lista de objetos do tipo Conta e insere no Banco de Dados
	//Se tentar inserir em um pk que já existe ele não faz nada
	public void criar(Connection con, List<Conta> contas) {
		String sql = "insert ignore into tb_customer_account values (?,?,?,?,?)";
		try (PreparedStatement stm = con.prepareStatement(sql)) {
			for (Conta conta : contas) {
				stm.setInt(1, conta.id);
				stm.setString(2, conta.cpf_cnpj);
				stm.setString(3, conta.nome_cliente);
				stm.setBoolean(4, conta.esta_ativo);
				stm.setDouble(5, conta.saldo);
				stm.addBatch();
			}
			stm.executeBatch();
		} catch (Exception e) {
			System.out.println("Erro ao inserir os dados na tabela: ");
			e.printStackTrace();
		}
	}
	
	//Salva em uma lista os dados que atendem ao critério pedido no exercício
	public List<Conta> leituraPedida(Connection con) {
		List<Conta> lista = new ArrayList<>();
		String sql = "select * from tb_customer_account where vl_total > '560' and id_customer between '1500' and '2700' order by vl_total desc";
		try (PreparedStatement stm = con.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
			while (rs.next()) {
				lista.add(new Conta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDouble(5)));
			}
		} catch (Exception e) {
			System.out.println("Erro de leitura em leitura pedida:");
			e.printStackTrace();
		}
		return lista;
	}
	
	//Faz a média pedida pelo exercício usando comando SQL
	public double mediaPedida(Connection con) {
		String sql = "select avg(vl_total) from tb_customer_account where vl_total > '560' and id_customer between '1500' and '2700'";
		double media = -1;
		try (PreparedStatement stm = con.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
			if (rs.next())
				media = rs.getFloat(1);
		} catch (Exception e) {
			System.out.println("Erro de leitura em media pedida:");
			e.printStackTrace();
		}
		return media;
	}
	
	//Método que faz a inserção de registros aleatórios
	public void insereRandom(Connection con) {
		List<Conta> contas = new ArrayList<>();
		List<String> nomes = new ArrayList<>();
		List<String> sobrenomes = new ArrayList<>();
		
		Random r = new Random();
		
		nomes.add("Jorge");
		nomes.add("Maria");
		nomes.add("Roberto");
		nomes.add("Pedro");
		nomes.add("Isabel");
		nomes.add("Joaquim");
		nomes.add("Ana");
		nomes.add("Carolina");
		nomes.add("Gilberto");
		nomes.add("Silvana");
		nomes.add("Sirlei");
		nomes.add("Antonio");
		nomes.add("Susana");
		
		sobrenomes.add(" da Silva");
		sobrenomes.add(" de Souza");
		sobrenomes.add(" Cintra");
		sobrenomes.add(" Aranha");
		sobrenomes.add(" Neves");
		sobrenomes.add(" Aragão");
		sobrenomes.add(" Moreira");
		sobrenomes.add(" Ferraz");
		sobrenomes.add(" Oliveira");
		
		for (int i = 50; i < 3000 ; i += 50) {

			StringBuilder cpf = new StringBuilder();
			
			while (cpf.length() < 11) {
				cpf.append(r.nextInt(10));
			}

			String nomeCompleto = (nomes.get(r.nextInt(13)) + sobrenomes.get(r.nextInt(9)));
			contas.add(new Conta(i, cpf.toString(), nomeCompleto, (i % 100) == 0, Math.random()*1000));
			
		}
		criar(con, contas);
	}
	
	public void truncate(Connection con) {
		String sql = "truncate table tb_customer_account";
		try (PreparedStatement stm = con.prepareStatement(sql);) {
			stm.executeUpdate();
			System.out.println("Os dados da tabela foram apagados.");
		} catch (Exception e) {
			System.out.println("Erro ao apagar os dados da tabela.");
			e.printStackTrace();
		}
	}
	
}
