package com.delduck.bffagendadortarefas.infrastructure.client.config;

import com.delduck.bffagendadortarefas.infrastructure.exceptions.BusinessException;
import com.delduck.bffagendadortarefas.infrastructure.exceptions.ConflictException;
import com.delduck.bffagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.delduck.bffagendadortarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {

    @Override  //faremos um tratamento para cada um dos response, de acordo com o status do response feignclient
    public Exception decode(String methodKey, Response response) {

        return switch (response.status()) {
            case 409 -> new ConflictException("Erro, recurso já existente");
            case 403 -> new ResourceNotFoundException("Erro, recurso não encontrado");
            case 401 -> new UnauthorizedException("Erro, usuário não autorizado");
            default -> new BusinessException("Erro de servidor");
        };
    }

}
