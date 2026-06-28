package com.delduck.bffagendadortarefas.business;

import com.delduck.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.delduck.bffagendadortarefas.business.dto.out.TarefaResponseDTO;
import com.delduck.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.delduck.bffagendadortarefas.infrastructure.client.UsuarioClient;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    public String email;

    @Value("${usuario.senha}")
    public String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscarTarefasProximaHora() {

        String token = login(converterParaLoginRequestDTO());
        log.info("Iniciando a busca de tarefas");

        LocalDateTime horaAtual = LocalDateTime.now();
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);

        List<TarefaResponseDTO> listasTarefas =
                tarefasService.buscaTarefasAgendadasPorPeriodo(horaAtual, horaFutura, token);
        log.info("Tarefas encontradas: " + listasTarefas);

        listasTarefas.forEach(tarefa -> {
            emailService.enviarEmail(tarefa);
            log.info("Email enviado para o usuário: " + tarefa.getEmailUsuario());
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
        });

        log.info("Finalizada a busca e notificação de tarefas");

    }

    public String login(LoginRequestDTO loginRequestDTO) {
        return usuarioService.loginUsuario(loginRequestDTO);
    }

    public LoginRequestDTO converterParaLoginRequestDTO() {
        return LoginRequestDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
