package com.joao.carros.api.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.joao.carros.domain.dto.CarroDTO;
@Service
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;
	
	public List<CarroDTO> getCarros(){
		
			List<Carro> carros = carroRepository.findAll();
			List<CarroDTO> List = carros.stream().map(CarroDTO::new).collect(Collectors.toList());
			return List;
	}
	
	
	public Optional<CarroDTO> getCarro(Long id) {
		
		return carroRepository.findById(id).map(CarroDTO::create);
	}


	public List<CarroDTO> getPorTipo(String tipo) {
		
	return carroRepository.findByTipo(tipo).stream().map(c->new CarroDTO(c)).collect(Collectors.toList());
	
	}
	
	public Carro save (Carro carro) {
		carroRepository.save(carro);
		return carro;
	}


	public Carro update(Long id, Carro carro) {
		Assert.notNull(id,"Não foi possivel atualizar o registro");
		
		Optional<Carro> optional = carroRepository.findById(id);
		
		if(optional.isPresent()) {
		Carro db = optional.get();
		
		db.setNome(carro.getNome());
		db.setTipo(carro.getTipo());
		return db;
		
		}else {
			throw new RuntimeException("Não foi possivel atualizar os dados");
		}
		
				
	}

	public Carro delete(Long id) {
		Optional<Carro> carro = carroRepository.findById(id);
		if(carro.isPresent()) {
			carroRepository.deleteById(id);
		}
		
		return carro.get();
		
	}
}
	