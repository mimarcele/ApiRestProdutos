package com.produtos.apirest.controller;

import com.produtos.apirest.dto.ProdutoDto;
import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repository.ProdutoRepository;
import com.produtos.apirest.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    @ApiOperation(value = "Retornar a lista de produtos cadastrados no banco de dados")
    public ResponseEntity<List<Produto>> listaProdutos(){
        List<Produto> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retornar o produto referente ao id digitado")
    public Produto consultarProduto(@PathVariable long id){ //@ResponseStatus
    return produtoService.consultarProduto(id);
    }

    @PostMapping
    @ApiOperation(value = "Cadastrar produto no banco de dados")
    public ResponseEntity<ProdutoDto> cadastrarProduto(@RequestBody ProdutoDto produtoDto) {
        ProdutoDto produtoDto1 = produtoService.cadastrarProduto(produtoDto);
        return ResponseEntity.ok(produtoDto1);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar produto do banco de dados")
    public void deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar um produto no banco de dados")
    public void atualizaProduto(@RequestBody ProdutoDto produtodto, @PathVariable Long id){
        produtoService.atualizarProduto(produtodto, id);
    }
}
