package com.vfoprojects.produtoapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vfoprojects.produtoapi.model.Produto;
import com.vfoprojects.produtoapi.services.ProdutoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	@ApiOperation(value="Cadastrar produto")
	@PostMapping
	public ResponseEntity<Produto> create(@RequestBody Produto produto) {
		service.insert(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(produto);
	}

	@ApiOperation(value="Listar todos os produtos")
	@GetMapping
	public ResponseEntity<List<Produto>> read() {
		List<Produto> produtos = service.readAll();
		return ResponseEntity.ok().body(produtos);
	}
	
	@ApiOperation(value="Buscar um produto por id")
	@GetMapping("/{id}")
	public ResponseEntity<Produto> readById(@PathVariable Integer id) {
		Produto produto = service.findById(id);
		return ResponseEntity.ok().body(produto);
	}
	
	@ApiOperation(value="Atualizar um produto por id")
	@PutMapping("/{id}")
	public ResponseEntity<Produto> update(@PathVariable Integer id, @RequestBody Produto produto) {
		produto = service.update(id, produto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@ApiOperation(value="Deletar um produto por id")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@ApiOperation(value="Buscar um produto por nome")
	@GetMapping("/produto/{nome}")
	public ResponseEntity<Produto> findByNome(@PathVariable String nome) {
		Produto produto = service.findByNome(nome);
		return ResponseEntity.ok().body(produto);
	}

	@ApiOperation(value="Buscar os produtos de uma determinada categoria")
	@GetMapping("/categoria/{categoria}")
	public ResponseEntity<List<Produto>> read(@PathVariable String categoria) {
		List<Produto> produtos = service.findByCategoria(categoria);
		return ResponseEntity.ok().body(produtos);
	}

}
