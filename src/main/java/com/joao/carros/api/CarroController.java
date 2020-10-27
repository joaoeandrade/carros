package com.joao.carros.api;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.apache.coyote.http11.Http11AprProtocol;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joao.carros.CarrosApplication;
import com.joao.carros.api.domain.Carro;
import com.joao.carros.api.domain.CarroService;
import com.joao.carros.domain.dto.CarroDTO;

import net.bytebuddy.implementation.bytecode.Throw;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

	@Autowired
	private CarroService service;

	@GetMapping
	public ResponseEntity<List<CarroDTO>> carro() {
		return new ResponseEntity(service.getCarros(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity porId(@PathVariable Long id) {
     Optional<CarroDTO> carro = service.getCarro(id);
     
     	return carro.map(ResponseEntity::ok)
		.orElse(ResponseEntity.badRequest().build());
//     return carro.isPresent() ? 
//    		 ResponseEntity.ok(carro.get()):
//    		ResponseEntity.notFound().build();
//    if(carro.isPresent()) {
//    	return ResponseEntity.ok(carro.get());
//    }else {
//    	return ResponseEntity.notFound().build();
//    }
	}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity porTipo(@PathVariable String tipo) {

		 List<CarroDTO> carros = service.getPorTipo(tipo);
		
		return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
				 }

	@PostMapping
	public Carro post(@RequestBody Carro carro) {
		service.save(carro);

		return carro;
	}

	@PutMapping("/{id}")
	public Carro update(@PathVariable Long id, @RequestBody Carro carro) {

		Carro update = service.update(id, carro);
		return update;
	}

	@DeleteMapping("/{id}")
	public Carro delete(@PathVariable Long id) {

		Carro delete = service.delete(id);
		return delete;
	}

}
