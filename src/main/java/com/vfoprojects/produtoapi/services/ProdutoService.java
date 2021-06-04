package com.vfoprojects.produtoapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vfoprojects.produtoapi.model.Produto;
import com.vfoprojects.produtoapi.repositories.ProdutoRepository;
import com.vfoprojects.produtoapi.services.exceptions.EntityNotFoundException;
import com.vfoprojects.produtoapi.services.exceptions.IntegrityViolationException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public Produto insert(Produto produto) {
		var nome = repository.findByNomeContainingIgnoreCase(produto.getNome());
		if (nome != null) {
			throw new IntegrityViolationException("Já existe um produto cadastrado com esse nome");
		}

		return repository.save(produto);
	}

	public List<Produto> readAll() {
		return repository.findAll();
	}

	public Produto findById(Integer id) {
		Optional<Produto> produto = repository.findById(id);
		if (produto.isEmpty()) {
			throw new EntityNotFoundException("Não existe produto cadastro com esse ID");
		}

		return produto.get();
	}

	public Produto update(Integer id, Produto produto) {
		findById(id);
		produto.setId(id);
		return repository.save(produto);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	public Produto findByNome(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome);
	}
	
	public List<Produto> findByCategoria(String categoria){
		return repository.findByCategoriaContainingIgnoreCase(categoria);
	}
}
