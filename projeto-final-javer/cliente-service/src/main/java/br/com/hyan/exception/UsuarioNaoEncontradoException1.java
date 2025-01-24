package br.com.hyan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Id de usuário inexistente")
public class UsuarioNaoEncontradoException1 extends RuntimeException {
 
	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException1(Long id) {
        super("Usuário com id " + id + " não encontrado.");
    }
}
