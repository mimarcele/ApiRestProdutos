package com.produtos.apirest.service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.produtos.apirest.dto.ProdutoDto;
import com.produtos.apirest.exceptions.ProdutoNotFoundException;
import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    public ProdutoDto cadastrarProduto(ProdutoDto produtoDto) {

        Produto produto = new Produto();
        produto.setNome(produtoDto.getNome());
        produto.setQuantidade(produtoDto.getQuantidade());
        produto.setValor(produtoDto.getValor());

        produto = produtoRepository.save(produto);
        produtoDto.setId(produto.getId());
        return produtoDto;
    }
    public List<Produto> listarProdutos(){
    List<Produto> produtos = produtoRepository.findAll();
    return produtos;
    }

    public Produto consultarProduto(Long id){
      Optional<Produto> produto = produtoRepository.findById(id);

        if (!produto.isPresent()){
        throw new ProdutoNotFoundException("Produto " + id + " não cadastrado");
        }

        return produto.get();
    }

    public void deletarProduto(Long id){
        Produto produto = produtoRepository.findById(id).get();
        if (produto == null){
            throw new ProdutoNotFoundException("Produto " + id + " não encontrado");
        }
        produtoRepository.delete(produto);
    }


    public void atualizarProduto(ProdutoDto produtoDto, Long id){
        produtoRepository.findById(id)
                .map(produto -> {
                    BeanUtils.copyProperties(produtoDto, produto,"id");
                    produtoRepository.save(produto);
                    return produto;
                }).orElseThrow(()-> new ProdutoNotFoundException("Produto não cadastrado"));
    }

}
