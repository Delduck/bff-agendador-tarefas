package com.delduck.bffagendadortarefas.controller;

import com.delduck.bffagendadortarefas.business.TarefasService;
import com.delduck.bffagendadortarefas.business.dto.in.TarefaRequestDTO;
import com.delduck.bffagendadortarefas.business.dto.out.TarefaResponseDTO;
import com.delduck.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.delduck.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar Tarefas de Usuário", description = "Criar uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salvo com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefaResponseDTO> gravarTarefas(@RequestBody TarefaRequestDTO tarefaRequestDTO,
                                                           @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, tarefaRequestDTO));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Buscar lista de tarefas por Periodo", description = "Buscar tarefas cadastradas por período")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefaResponseDTO>> buscaListaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Buscar lista de tarefas por email do usuário",
            description = "Buscar tarefas cadastradas por usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefaResponseDTO>> buscaListaTarefasPorEmail(@RequestHeader(name = "Authorization",
            required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deletar tarefas por ID", description = "Deletar tarefas cadastradas por ID")
    @ApiResponse(responseCode = "200", description = "Tarefas deletadas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam("idTarefa") String idTarefa,
                                                   @RequestHeader(name = "Authorization", required = false) String token) {
        tarefasService.deletaTarefaPorId(idTarefa, token);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status da tarefa", description = "Altera status da tarefa cadastrada")
    @ApiResponse(responseCode = "200", description = "Status da tarefa alterada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefaResponseDTO> alterarStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                      @RequestParam("idTarefa") String idTarefa,
                                                                      @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.alteraStatus(status, idTarefa, token));
    }

    @PutMapping
    @Operation(summary = "Altera dados de tarefas", description = "Altera dados das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Tarefas alteradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefaResponseDTO> updateTarefas(@RequestBody TarefaRequestDTO tarefaRequestDTO,
                                                           @RequestParam("id") String id,
                                                           @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.updateTarefas(tarefaRequestDTO, id, token));
    }

}
