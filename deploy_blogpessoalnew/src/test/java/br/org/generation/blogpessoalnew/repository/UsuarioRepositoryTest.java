package br.org.generation.blogpessoalnew.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.blogpessoalnew.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {

		usuarioRepository.save(new Usuario(0L, "Ana Araujo", "anaaraujo@email.com.br", "12465278", "  "));
		
		usuarioRepository.save(new Usuario(0L, "Jonas Araujo", "jonasaraujo@email.com.br", "13465278", "  "));
		
		usuarioRepository.save(new Usuario(0L, "Julia Araujo", "julinha@email.com.br", "13465278", "	"));
	
		usuarioRepository.save(new Usuario(0L, "Larissa So", "larissaso@email.com", "13465278", "	"));
	
	}
	
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("anaaraujo@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("anaaraujo@email.com.br"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Araujo");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Ana Araujo"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Jonas Araujo"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Julia Araujo"));
		
	}
	
}	
