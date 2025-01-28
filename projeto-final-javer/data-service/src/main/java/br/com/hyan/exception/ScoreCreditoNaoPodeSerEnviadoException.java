package br.com.hyan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class ScoreCreditoNaoPodeSerEnviadoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ScoreCreditoNaoPodeSerEnviadoException() {
        super("O campo \"score_credito\" não deve ser enviado através da requisição");
    }
}
