package org.com.contasapagar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ContaNotFoundException extends RuntimeException {

    public ContaNotFoundException(final Long contaId) {
        super("Conta não encontrada, contaId: " + contaId);
    }

}
