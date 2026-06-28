package com.delduck.bffagendadortarefas.business;


import com.delduck.bffagendadortarefas.business.dto.out.TarefaResponseDTO;
import com.delduck.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviarEmail(TarefaResponseDTO tarefasDTO) {
        emailClient.enviarEmail(tarefasDTO);
    }
}
