package com.delduck.bffagendadortarefas.infrastructure.client;

import com.delduck.bffagendadortarefas.business.dto.in.EnderecoRequestDTO;
import com.delduck.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.delduck.bffagendadortarefas.business.dto.in.TelefoneRequestDTO;
import com.delduck.bffagendadortarefas.business.dto.in.UsuarioRequestDTO;
import com.delduck.bffagendadortarefas.business.dto.out.EnderecoResponseDTO;
import com.delduck.bffagendadortarefas.business.dto.out.TelefoneResponseDTO;
import com.delduck.bffagendadortarefas.business.dto.out.UsuarioResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioResponseDTO buscaUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioResponseDTO salvaUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO);

    // Vamos passar os dados do UsuarioDTO, que serao recebidos metodo login em usuario
    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO loginRequestDTO);

    @DeleteMapping
    void deletarUsuarioPorEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioResponseDTO atualizaDadosUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO,
                                            @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoResponseDTO atualizaEndereco(@RequestBody EnderecoRequestDTO enderecoRequestDTO,
                                         @RequestParam("idEndereco") Long idEndereco,
                                         @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneResponseDTO atualizaTelefone(@RequestBody TelefoneRequestDTO telefoneRequestDTO,
                                         @RequestParam("idTelefone") Long idTelefone,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoResponseDTO cadastraEndereco(@RequestBody EnderecoRequestDTO enderecoRequestDTO,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneResponseDTO cadastraTelefone(@RequestBody TelefoneRequestDTO telefoneRequestDTO,
                                         @RequestHeader("Authorization") String token);

}
