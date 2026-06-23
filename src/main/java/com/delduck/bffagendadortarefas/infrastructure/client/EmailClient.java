package com.delduck.bffagendadortarefas.infrastructure.client;

import com.delduck.bffagendadortarefas.business.dto.out.TarefaResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

    @PostMapping
    void enviarEmail(@RequestBody TarefaResponseDTO tarefasDTO);

}
