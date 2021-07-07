package br.com.alura.leilao.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.alura.leilao.dao.LeilaoDao;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

class FinalizarLeilaoServiceTest {

	private FinalizarLeilaoService service;
	
	@Mock
	private LeilaoDao leilaoDao;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this); //Inicia o que tem a anotação @mock
		this.service = new FinalizarLeilaoService(leilaoDao);
	}

	@Test
	void deveriaFinalizarUmLeilao() {
		List<Leilao> leiloes = leiloes(); // Coloca o leilão que crieo para o teste 
		
		Mockito.when(leilaoDao.buscarLeiloesExpirados()).thenReturn(leiloes); //Define o que será retornado na mock
		
		service.finalizarLeiloesExpirados();
		
		Leilao leilao = leiloes.get(0); // Pega o leilao da posiçaõ zero no array
		Assert.assertTrue(leilao.isFechado()); // Verifica se leilão foi fechado
		Assert.assertEquals(new BigDecimal("900"), leilao.getLanceVencedor().getValor());//Verifica se o lance vencedor é o de maior 
		
		Mockito.verify(leilaoDao).salvar(leilao); // assertiva no mock, verifica se salvou no banco
	}
	
	private List<Leilao> leiloes() {
		List<Leilao> lista = new ArrayList<>();
		
		Leilao leilao = new Leilao("Celular", new BigDecimal("500"), new Usuario("Fulano"));
		
		Lance primeiro = new Lance(new Usuario("Beltrano"), new BigDecimal("600"));
		Lance segundo = new Lance(new Usuario("Ciclano"), new BigDecimal("900"));
		
		leilao.propoe(primeiro);
		leilao.propoe(segundo);
		
		lista.add(leilao);
		
		return lista;
	}

}
