package com.delduck.bffagendadortarefas.business;


import com.delduck.bffagendadortarefas.business.dto.in.TarefaRequestDTO;
import com.delduck.bffagendadortarefas.business.dto.out.TarefaResponseDTO;
import com.delduck.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.delduck.bffagendadortarefas.infrastructure.client.TarefaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefaClient tarefaClient;

    public TarefaResponseDTO gravarTarefa(String token, TarefaRequestDTO tarefaRequestDTO) {
        return tarefaClient.gravarTarefas(tarefaRequestDTO, token);
    }

    public List<TarefaResponseDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal,
                                                                   String token) {
        return tarefaClient.buscaListaTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefaResponseDTO> buscaTarefasAgendadasPorEmail(String token) {
        return tarefaClient.buscaListaTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        tarefaClient.deletarTarefaPorId(id, token);
    }

    public TarefaResponseDTO alteraStatus(StatusNotificacaoEnum status, String id, String token) {
        return tarefaClient.alterarStatusNotificacao(status, id, token);
    }

    public TarefaResponseDTO updateTarefas(TarefaRequestDTO tarefaRequestDTO, String idTarefa, String token) {
        return tarefaClient.updateTarefas(tarefaRequestDTO, idTarefa, token);
    }



}
