package br.com.imobi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.imobi.domain.exception.imovel.ImovelNotFoundException;
import br.com.imobi.domain.model.Imovel;
import br.com.imobi.domain.service.ImovelService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class imovelTestIT {
	
	@Autowired
	private ImovelService imovelService;
	
	@Test
	public void deveAtribuirId_QuandoCadastrarImovelComDadosCorretos() {
		// CENÁRIO
		Imovel imovel = new Imovel();
		imovel.setDescricao("integração");
		imovel.setId((long) 20);
		
		// AÇÃO
		imovel = imovelService.save(imovel);
		
		// VALIDAÇÃO
		assertThat(imovel).isNotNull();
		assertThat(imovel.getId()).isNotNull();
	}
	
	@Test(expected = ImovelNotFoundException.class)
	public void deveFalhar_QuandoExcluirImovelInexistente() {
		imovelService.remove("460f6bca-138f-438c-aca6-efb1e5416c90");
	}
	
	
	

}
