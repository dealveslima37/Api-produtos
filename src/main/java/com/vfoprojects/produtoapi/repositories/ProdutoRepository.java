package com.vfoprojects.produtoapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vfoprojects.produtoapi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	Produto findByNomeContainingIgnoreCase(String nome);

	List<Produto> findByCategoriaContainingIgnoreCase(String categoria);

}
