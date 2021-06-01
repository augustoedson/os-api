package com.edson.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edson.os.domain.Pessoa;
import com.edson.os.domain.Tecnico;
import com.edson.os.dtos.TecnicoDTO;
import com.edson.os.repositories.PessoaRepository;
import com.edson.os.repositories.TecnicoRepository;
import com.edson.os.services.exceptions.DataIntegratyViolationException;
import com.edson.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoRepository repository;

	@Autowired
	private PessoaRepository pessoarepository;

	
	public Tecnico findById(Integer id) {
	Optional<Tecnico> obj = repository.findById(id);
	return obj.orElseThrow(()-> new ObjectNotFoundException(
			"Objeto não encontrado ! Id: "+ id + ", Tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
	public Tecnico create(TecnicoDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na Base de Dados!");
		}

		return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico oldObj = findById(id);
		
		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na Base de Dados!");
		
		}
		
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		
		return repository.save(oldObj);
		  
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		Tecnico obj = findById(id);
		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Técnico possui Ordem de Serviço, não pode ser deletado!");
		}
		repository.deleteById(id);
		
	}

/*
 * Busca Tecnico por CPF	
 */
//	private Tecnico findByCPF(TecnicoDTO objDTO) {
//		Tecnico obj = repository.findByCPF(objDTO.getCpf());
//		if (obj != null) {
//			return obj;
//		}
//		return null;
//	}


	/*
	 * Busca Pessoa por CPF	, sendo Tecnico ou Cliente
	 */
		private Pessoa findByCPF(TecnicoDTO objDTO) {
			Pessoa obj = pessoarepository.findByCPF(objDTO.getCpf());
			if (obj != null) {
				return obj;
			}
			return null;
		}


}
