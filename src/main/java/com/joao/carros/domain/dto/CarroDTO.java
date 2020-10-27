package com.joao.carros.domain.dto;

import org.modelmapper.ModelMapper;

import com.joao.carros.api.domain.Carro;

import lombok.Data;

@Data
public class CarroDTO {
	private Long id;
	private String nome;
	private String tipo;

	/*
	 * public CarroDTO(Carro carro) { this.id = carro.getId(); this.nome =
	 * carro.getNome(); this.tipo = carro.getTipo(); }
	 */

	public CarroDTO(Carro c) {
		this.setId(c.getId());
		this.setNome(c.getNome());
		this.setTipo(c.getTipo());
	}

	public static CarroDTO create(Carro carro) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(carro, CarroDTO.class);

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}