package com.exemplo.livro.exemplolivro.controllers;

import com.exemplo.livro.exemplolivro.models.ProdutoModel;
import com.exemplo.livro.exemplolivro.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoModel>> getAllProdutos() {
        List<ProdutoModel> produtosList = produtoRepository.findAll();
        if (produtosList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<List<ProdutoModel>>(produtosList, HttpStatus.OK);
        }
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<ProdutoModel> getOneProduto(@PathVariable(value = "id") long id) {
        Optional<ProdutoModel> produtoO = produtoRepository.findById(id);
        if(!produtoO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<ProdutoModel>(produtoO.get(), HttpStatus.OK);
        }
    }

    @PostMapping("/produtos")
    public ResponseEntity<ProdutoModel> saveProduto(@RequestBody @Valid ProdutoModel produto) {
        return new ResponseEntity<ProdutoModel>(produtoRepository.save(produto), HttpStatus.CREATED);
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<ProdutoModel> deleteProduto(@PathVariable(value = "id") long id) {
        Optional<ProdutoModel> produtoO = produtoRepository.findById(id);
        if (!produtoO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            produtoRepository.delete(produtoO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<ProdutoModel> updateProduto(@PathVariable(value = "id")long id, @RequestBody @Valid ProdutoModel produto) {
        Optional<ProdutoModel> produtoO = produtoRepository.findById(id);
        if(!produtoO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            produto.setIdProduto(produtoO.get().getIdProduto());
            return new ResponseEntity<ProdutoModel>(produtoRepository.save(produto), HttpStatus.OK);
        }
    }

}
