package br.com.imobi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class imovelTesteApiIT {
	
	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() {
		//HABILITA OS LOGS QUANDO FALAHAR
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		//ATRIBUI O VALOR ALEATORIO DA PORTA
		RestAssured.port = port;
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarImoveis() {
		given()
			.basePath("/imoveis")
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveConter7Imoveis_QuandoConsultarImoveis() {
		given()
			.basePath("/imoveis")
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(7))
			.body("descricao", hasItems("teste2", "oi"));
	}
	
	@Test
	public void testRetornarStatus201_QuandoCadastrarImovel() {
		given()
			.basePath("/imoveis")
			.body("{\"id\":\"100\",\"descricao\":\"oi\",\"code\":\"b2572bff-77ee-4b24-a3a5-a8ae6431dee8\"\"endereco\": {\"id\":\"1\",\"logradouro\":\"teste\",\"cidade\":\"teste\",\"bairro\":\"teste\",\"numero\": 1,\"cep\": 1}}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarImovelExistente() {
		given()
			.basePath("/imoveis")
			.pathParam("code", "460f6bca-138f-438c-aca6-efb1e5416c90")
			.accept(ContentType.JSON)
		.when()
			.get("/{code}")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
		given()
			.basePath("/imoveis")
			.pathParam("code", "460f6bca-138f-438c-aca6-efb1e5416c903")
			.accept(ContentType.JSON)
		.when()
			.get("/{code}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	
}
