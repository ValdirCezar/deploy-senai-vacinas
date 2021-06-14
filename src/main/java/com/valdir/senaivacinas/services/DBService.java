package com.valdir.senaivacinas.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.valdir.senaivacinas.domain.Agendamento;
import com.valdir.senaivacinas.domain.Cidade;
import com.valdir.senaivacinas.domain.Endereco;
import com.valdir.senaivacinas.domain.Estado;
import com.valdir.senaivacinas.domain.Pais;
import com.valdir.senaivacinas.domain.UnidadeAtendimento;
import com.valdir.senaivacinas.domain.Usuario;
import com.valdir.senaivacinas.domain.Vacina;
import com.valdir.senaivacinas.domain.enums.Perfil;
import com.valdir.senaivacinas.repositories.AgendamentoRepository;
import com.valdir.senaivacinas.repositories.CidadeRepository;
import com.valdir.senaivacinas.repositories.EnderecoRepository;
import com.valdir.senaivacinas.repositories.EstadoRepository;
import com.valdir.senaivacinas.repositories.PaisRepository;
import com.valdir.senaivacinas.repositories.UnidadeAtendimentoRepository;
import com.valdir.senaivacinas.repositories.UsuarioRepository;
import com.valdir.senaivacinas.repositories.VacinaRepository;

@Service
public class DBService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PaisRepository paisRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private UnidadeAtendimentoRepository unidadeAtendimentoRepository;
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	@Autowired
	private VacinaRepository vacinaRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instanciaDB() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Pais p1 = new Pais(null, "Brasil");
		paisRepository.saveAll(Arrays.asList(p1));
		
		Estado e1 = new Estado(null, "Paraná", p1);
		estadoRepository.saveAll(Arrays.asList(e1));
		 
		Cidade c1 = new Cidade(null, "Londrina", e1);
		Cidade c2 = new Cidade(null, "Mauá da Serra", e1);
		cidadeRepository.saveAll(Arrays.asList(c1, c2));
		
		Usuario u1 = new Usuario(null, "465.947.630-83", "Valdir", "Cezar de Jesus", 1.75, 70.0, 'M', "43984634308", sdf.parse("12/02/1995"), false, null, "valdir@email.com", encoder.encode("123"));
		u1.addPerfil(Perfil.ADMIN);
		
		Usuario u2 = new Usuario(null, "589.386.040-39", "Matheus", "Aparecido Teodoro", 1.76, 74.0, 'M', "43984634312", sdf.parse("12/02/1995"), false, null, "matheus@email.com", encoder.encode("123"));
		usuarioRepository.saveAll(Arrays.asList(u1, u2));
		
		Endereco end1 = new Endereco(null, "Rua das Flores", "1325", "Casa frente", "Luiz de sá", "86087090", u1, c1);
		enderecoRepository.saveAll(Arrays.asList(end1));
		u1.setEndereco(end1);
		
		UnidadeAtendimento uni1 = new UnidadeAtendimento(null, "UPA Luiz de Sá", 0);
		unidadeAtendimentoRepository.saveAll(Arrays.asList(uni1));
		
		Agendamento a1 = new Agendamento(null, sdf.parse("01/04/2021"), false, "Teste instancias", u1, uni1);
		agendamentoRepository.saveAll(Arrays.asList(a1));
		u1.getAgendamentos().add(a1);
		
		Vacina v1 = new Vacina(null, uni1);
		vacinaRepository.saveAll(Arrays.asList(v1));
		
	}
}
