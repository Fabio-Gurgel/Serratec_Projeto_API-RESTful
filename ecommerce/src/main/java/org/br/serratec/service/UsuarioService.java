package org.br.serratec.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.br.serratec.config.MailConfig;
import org.br.serratec.domain.Perfil;
import org.br.serratec.domain.Usuario;
import org.br.serratec.domain.UsuarioPerfil;
import org.br.serratec.dto.UsuarioDto;
import org.br.serratec.dto.UsuarioInserirDto;
import org.br.serratec.exception.EmailException;
import org.br.serratec.exception.SenhaException;
import org.br.serratec.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	@Autowired
	private MailConfig mailConfig;
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	public List<UsuarioDto> lista(){
		UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Usuario: " + userDetail.getUsername() + " - " + userDetail.getPassword());
	       		
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDto> usuariosDTO = new ArrayList<>();
		for(int i = 0 ; i<usuarios.size(); i++) {
		    Usuario usuario = usuarios.get(i);
			usuariosDTO.add(new UsuarioDto(usuario));
		}
		
		//return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
		return usuariosDTO;
	}
	
	@Transactional
	public UsuarioDto inserir(UsuarioInserirDto usuarioInserirDTO) throws EmailException, SenhaException {
		if (!usuarioInserirDTO.getSenha().equals(usuarioInserirDTO.getConfirmaSenha())) {
			throw new SenhaException("Senhas não conferem");
		}
		Usuario usuarioBanco = usuarioRepository.findByEmail(usuarioInserirDTO.getEmail());
		if (usuarioBanco!=null) {
			throw new EmailException("Já existe um usuário com o e-mail" + usuarioInserirDTO.getEmail());
		}
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setEmail(usuarioInserirDTO.getEmail());
		usuario.setSenha(encoder.encode(usuarioInserirDTO.getSenha()));
		
		Set<UsuarioPerfil> perfis = new HashSet<>();
		for(Perfil perfil:usuarioInserirDTO.getPerfis()) {
			  perfil = perfilService.buscar(perfil.getId());
			  UsuarioPerfil usuarioPerfil = new UsuarioPerfil(usuario,perfil,LocalDate.now());
			  perfis.add(usuarioPerfil);
		}
		usuario.setUsuarioPerfis(perfis);
		usuario = usuarioRepository.save(usuario);
		mailConfig.sendMail(usuario.getEmail(), 
				"Usuario cadastrado com sucesso", 
				"Usuario " + usuario.getNome() + " cadastrado com o id: " + usuario.getId());
		return new UsuarioDto(usuario);
	}
	
	

}
