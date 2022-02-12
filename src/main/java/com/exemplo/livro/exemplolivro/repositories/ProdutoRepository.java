package com.exemplo.livro.exemplolivro.repositories;

import com.exemplo.livro.exemplolivro.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

}
