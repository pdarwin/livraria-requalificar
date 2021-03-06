package uma.requalificar.livrariarequalificar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uma.requalificar.livrariarequalificar.dto.ListaResposta;
import uma.requalificar.livrariarequalificar.model.Cliente;
import uma.requalificar.livrariarequalificar.service.ClienteService;

@RestController
@CrossOrigin
public class ClienteController {
	private final ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping("/getClientes")
	@CrossOrigin
	public List<Cliente> getClientes() {
		return clienteService.getClientes();
	}

	@PostMapping("/loginCliente")
	@CrossOrigin
	public ResponseEntity<ListaResposta> loginCliente(@RequestBody Cliente cliente) {
		ListaResposta listaResposta = new ListaResposta();

		if ((cliente.getEmail() == null)) {
			listaResposta.addMsg("Email nulo.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaResposta);
		}

		if ((cliente.getPassword() == null)) {
			listaResposta.addMsg("Password nula.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaResposta);
		}

		listaResposta = clienteService.loginCliente(cliente);

		if (!listaResposta.isStatusOk()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaResposta);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(listaResposta);
		}
	}

	@PostMapping("/addCliente")
	@CrossOrigin
	public ResponseEntity<ListaResposta> addCliente(@RequestBody Cliente cliente) {
		ListaResposta listaResposta = new ListaResposta();

		if (cliente.getId() != null) {
			listaResposta.addMsg("Ao adicionar um cliente, o ID tem de ser nulo.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaResposta);
		}

		if ((cliente.getNome() == null)) {
			listaResposta.addMsg("Nome nulo.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaResposta);
		}

		if ((cliente.getEmail() == null)) {
			listaResposta.addMsg("Email nulo.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaResposta);
		}

		if ((cliente.getPassword() == null)) {
			listaResposta.addMsg("Password nula.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaResposta);
		}

		if ((cliente.getMorada() == null)) {
			listaResposta.addMsg("Morada nula.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaResposta);
		}

		if ((cliente.getData_nascimento() == null)) {
			listaResposta.addMsg("Data de nascimento nula.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaResposta);
		}

		listaResposta = clienteService.addCliente(cliente);

		if (!listaResposta.isStatusOk()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaResposta);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(listaResposta);
		}

	}

}
