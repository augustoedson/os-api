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
		Tecnico t1 = new Tecnico(null, "Edson Augusto",   "305.573.460-29", "(19) 99279-9008");
		Tecnico t2 = new Tecnico(null, "João Paulo",      "074.790.480-41", "(19) 92381-0888");
		Tecnico t3 = new Tecnico(null, "Guilherme Andre", "973.304.740-61", "(19) 93482-8088");
		Tecnico t4 = new Tecnico(null, "Jose Luiz",       "287.409.570-20", "(19) 94583-8808");
		Tecnico t5 = new Tecnico(null, "Luiz  Paulo",     "378.105.180-38", "(19) 95684-8880");
		Tecnico t6 = new Tecnico(null, "Carlos Oliveira", "161.573.190-38", "(19) 96785-8880");
		Tecnico t7 = new Tecnico(null, "Pedro  Paulo",    "190.675.420-91", "(19) 97886-8808");
		Tecnico t8 = new Tecnico(null, "Fabio Henrique",  "094.813.220-50", "(19) 98987-8188");
		
		
		Cliente c1 = new Cliente(null, "Alessandra Landgraf", "162.459.460-34", "(19) 91234-4321");
		Cliente c2 = new Cliente(null, "Paloma Helena",       "446.817.820-50", "(11) 12111-4221");
		Cliente c3 = new Cliente(null, "Lucila Oliveira",     "802.458.580-40", "(11) 13111-4221");
		Cliente c4 = new Cliente(null, "HJelena Oliveira",    "867.799.080-10", "(11) 14111-4341");
		Cliente c5 = new Cliente(null, "Paloma Duarte",       "433.943.250-47", "(11) 15111-1321");
		Cliente c6 = new Cliente(null, "Silvia Maria",        "244.746.070-82", "(11) 16111-1321");
		Cliente c7 = new Cliente(null, "Maria Helena",        "772.847.970-96", "(11) 18111-3321");
		Cliente c8 = new Cliente(null, "Luiza Helena",        "024.596.340-56", "(11) 17111-3321");
		
		OS os1 = new OS(null, Prioridade.MEDIA, "Trocar o desktop do cliente", Status.ANDAMENTO, t1,  c1);
		OS os2 = new OS(null, Prioridade.MEDIA, "Reinstalar o Sistema Operacional", Status.ANDAMENTO, t2, c2);
		OS os3 = new OS(null, Prioridade.ALTA,  "Trocar o mouse do cliente", Status.ANDAMENTO, t1,  c1);
		OS os4 = new OS(null, Prioridade.ALTA,  "Reinstalar o Office", Status.ANDAMENTO, t2, c2);
		OS os5 = new OS(null, Prioridade.BAIXA, "Trocar o teclado do cliente", Status.ANDAMENTO, t1,  c4);
		OS os6 = new OS(null, Prioridade.BAIXA, "Reinstalar Java", Status.ANDAMENTO, t3, c2);
		OS os7 = new OS(null, Prioridade.ALTA,  "Trocar impressora do cliente", Status.ANDAMENTO, t3,  c5);
		OS os8 = new OS(null, Prioridade.ALTA,  "Reinstalar o Sistema Operacional", Status.ANDAMENTO, t4, c6);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8));
		clienteRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8));
		osRepository.saveAll(Arrays.asList(os1, os2, os3, os4, os5, os6, os7, os8));
	}
	

}
