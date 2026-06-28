package com.delduck.bffagendadortarefas.business;

import com.delduck.bffagendadortarefas.business.dto.in.EnderecoRequestDTO;
import com.delduck.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.delduck.bffagendadortarefas.business.dto.in.TelefoneRequestDTO;
import com.delduck.bffagendadortarefas.business.dto.in.UsuarioRequestDTO;
import com.delduck.bffagendadortarefas.business.dto.out.EnderecoResponseDTO;
import com.delduck.bffagendadortarefas.business.dto.out.TelefoneResponseDTO;
import com.delduck.bffagendadortarefas.business.dto.out.UsuarioResponseDTO;
import com.delduck.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;


    public UsuarioResponseDTO salvaUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioClient.salvaUsuario(usuarioRequestDTO);
    }

    public String loginUsuario(LoginRequestDTO loginRequestDTO) {
        return usuarioClient.login(loginRequestDTO);
    }

    public UsuarioResponseDTO buscarUsuarioPorEmail(String email, String token) {
        return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public void deletarUsuarioPorEmail(String email, String token) {
        usuarioClient.deletarUsuarioPorEmail(email, token);
    }

    public UsuarioResponseDTO atualizaDadosUsuario(String token, UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioClient.atualizaDadosUsuario(usuarioRequestDTO, token);
    }

    public EnderecoResponseDTO atualizaEndereco(Long idEndereco, EnderecoRequestDTO enderecoRequestDTO, String token) {
        return usuarioClient.atualizaEndereco(enderecoRequestDTO, idEndereco, token);
    }

    public TelefoneResponseDTO atualizaTelefone(Long idTelefone, TelefoneRequestDTO telefoneRequestDTO, String token) {
        return usuarioClient.atualizaTelefone(telefoneRequestDTO, idTelefone, token);
    }

    public EnderecoResponseDTO cadastraEndereco(String token, EnderecoRequestDTO enderecoRequestDTO) {
        return usuarioClient.cadastraEndereco(enderecoRequestDTO, token);
    }

    public TelefoneResponseDTO cadastraTelefone(String token, TelefoneRequestDTO telefoneRequestDTO) {
        return usuarioClient.cadastraTelefone(telefoneRequestDTO, token);
    }

}
