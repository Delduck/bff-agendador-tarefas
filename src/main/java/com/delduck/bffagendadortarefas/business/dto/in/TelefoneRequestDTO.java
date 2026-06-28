package com.delduck.bffagendadortarefas.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneRequestDTO {

    private String numero;
    private String ddd;
}
