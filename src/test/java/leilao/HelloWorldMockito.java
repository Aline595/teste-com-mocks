package leilao;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.alura.leilao.dao.LeilaoDao;
import br.com.alura.leilao.model.Leilao;

public class HelloWorldMockito {

	@Test
	void hello() {
		LeilaoDao mock = Mockito.mock(LeilaoDao.class); // cria mock da classe q eu escolher
		List<Leilao> todos = mock.buscarTodos(); // coloca retorno em variavel
		Assert.assertTrue(todos.isEmpty()); // Verifica se o array veio vazio
	}
}
