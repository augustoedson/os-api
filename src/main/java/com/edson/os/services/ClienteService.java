package com.edson.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edson.os.domain.Cliente;
import com.edson.os.domain.Pessoa;
import com.edson.os.dtos.ClienteDTO;
import com.edson.os.repositories.ClienteRepository;
import com.edson.os.repositories.PessoaRepository;
import com.edson.os.services.exceptions.DataIntegratyViolationException;
import com.edson.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private PessoaRepository pessoarepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado ! Id: "+ id + ", Tipo: " + Cliente.class.getName()));
		}

		public List<Cliente> findAll() {
			// TODO Auto-generated method stub
			return repository.findAll();
		}
		
		public Cliente create(ClienteDTO objDTO) {
			if (findByCPF(objDTO) != null) {
				throw new DataIntegratyViolationException("CPF já cadastrado na Base de Dados!");
			}

			return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
		}

		public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
			Cliente oldObj = findById(id);
			
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
			Cliente obj = findById(id);
			if (obj.getList().size() > 0) {
				throw new DataIntegratyViolationException("Cliente possui Ordem de Serviço, não pode ser deletado!");
			}
			repository.deleteById(id);
			
		}

	/*
	 * Busca Cliente por CPF	
	 */
//		private Cliente findByCPF(ClienteDTO objDTO) {
//			Cliente obj = repository.findByCPF(objDTO.getCpf());
//			if (obj != null) {
//				return obj;
//			}
//			return null;
//		}


		/*
		 * Busca Pessoa por CPF	, sendo Tecnico ou Cliente
		 */
			private Pessoa findByCPF(ClienteDTO objDTO) {
				Pessoa obj = pessoarepository.findByCPF(objDTO.getCpf());
				if (obj != null) {
					return obj;
				}
				return null;
			}


}
