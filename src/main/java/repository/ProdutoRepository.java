package repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import model.Produto;

@Repository
public class ProdutoRepository {
	
	private List<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId = 0;
    
    public List<Produto> obterTodos (){
    	return produtos;
    }
    
    public Optional<Produto> obterPorId(Integer id) {
    	return produtos.stream().filter(produto -> produto.getId() == id).findFirst();
    }
    
    public Produto adicionar(Produto produto) {
    	
    	ultimoId++;
    	
    	produto.setId(ultimoId);
    	produtos.add(produto);
    	
    	return produto;
    }
    
    public void deletar(Integer id){
    	produtos.removeIf(produto -> produto.getId() == id);
    }
    
    public Produto atualizar(Produto produto) {
    	//Encontrar o produto na lista.
    	Optional<Produto> produtoEncontrado = obterPorId(produto.getId());
    	
    	if(produtoEncontrado.isEmpty()) {
    		throw new InputMismatchException("Produto não encontrado!");
    	}
    	
    	//Eu tenho que remover o produto antigo da lista.
    	deletar(produto.getId());
    	
    	//Depois adicionar o produto atualizado na lista.
    	produtos.add(produto);
    	
    	return produto;
    }
}
