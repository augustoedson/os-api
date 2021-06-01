package com.edson.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edson.os.domain.Cliente;
import com.edson.os.domain.OS;
import com.edson.os.domain.Tecnico;
import com.edson.os.domain.enuns.Prioridade;
import com.edson.os.domain.enuns.Status;
import com.edson.os.repositories.ClienteRepository;
import com.edson.os.repositories.OSRepository;
import com.edson.os.repositories.TecnicoRepository;

@Service

public class DBService {
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;
	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Edson Augusto", "305.573.460-29", "(19) 99279-9008");
		Tecnico t2 = new Tecnico(null, "João Paulo", "018.881.710-73", "(19) 98888-8888");
		Cliente c1 = new Cliente(null, "Alessandra Landgraf", "162.459.460-34", "(19) 91234-4321");
		Cliente c2 = new Cliente(null, "Paloma Helena", "446.817.820-50", "(11) 11111-4321");
		OS os1 = new OS(null, Prioridade.ALTA, "Trocar o desktop do cliente", Status.ANDAMENTO, t1, c1);
		OS os2 = new OS(null, Prioridade.ALTA, "Reinstalar o Sistema Operacional", Status.ANDAMENTO, t2, c2);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1, c2));
		osRepository.saveAll(Arrays.asList(os1, os2));
	}
	

}
