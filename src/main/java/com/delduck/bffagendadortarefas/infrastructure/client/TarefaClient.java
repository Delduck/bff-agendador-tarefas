package com.delduck.bffagendadortarefas.infrastructure.client;


import com.delduck.bffagendadortarefas.business.dto.in.TarefaRequestDTO;
import com.delduck.bffagendadortarefas.business.dto.out.TarefaResponseDTO;
import com.delduck.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefaClient {

    @PostMapping
    TarefaResponseDTO gravarTarefas(@RequestBody TarefaRequestDTO tarefaRequestDTO,
                                    @RequestHeader("Authorization") String token);


    @GetMapping("/eventos")
    List<TarefaResponseDTO> buscaListaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefaResponseDTO> buscaListaTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletarTarefaPorId(@RequestParam("idTarefa") String idTarefa,
                            @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefaResponseDTO alterarStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                               @RequestParam("idTarefa") String idTarefa,
                                               @RequestHeader("Authorization") String token);

    @PutMapping
    TarefaResponseDTO updateTarefas(@RequestBody TarefaRequestDTO tarefaRequestDTO,
                                    @RequestParam("idTarefa") String idTarefa,
                                    @RequestHeader("Authorization") String token);

}
