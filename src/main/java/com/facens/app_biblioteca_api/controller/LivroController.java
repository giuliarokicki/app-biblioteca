package com.facens.app_biblioteca_api.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.facens.app_biblioteca_api.model.Livro;
import com.facens.app_biblioteca_api.service.LivroService;

@RestController // È a combinação do RestController com o ResponseBody, indica que os métodos retornam diretamente no corpo da resposta. Com esse recurso criamos nossa API RestFul

@RequestMapping("/livros") // È utilizado para maquiar as URLS para métodos especifícos, será acessível no navegador através da URL base "/livros"

public class LivroController {

    private final LivroService livroService;

    public livroController(LivroService livroService){
        this.livroService = livroService;
    }

    @GetMapping
    public List<Livro> listarLivros() {
        return livroService.listarTodos();

    }

    
    @GetMapping("/teste")
    public String testeAPI() {
        return "API funcionando perfeitamente!";

    }

    
    @GetMapping("/{id}") //o {id} é utilizado para mapear uma requisição GET que incluí um paramêtro de caminho (path variable)
    public Livro buscaPorId(@PathVariable Long Id) {
        return livroService.buscaPorId(id);

    }

    @PostMapping
    @ResponseStatus(Http.Status.CREATED)
    public Livro criaLivro(RequestBody Livro livro) {
        return livroService.criar(livro);
    }

    @PutMapping("/{id}")
    public Livro atualizarLivro(@PathVariable Long id, @RequestBody Livro livro) {
        return livroService.atualizar(id,livro);
    }

    @DeleteMapping
    @ResponseStatus(Http.Status.NO_CONTENT)
    public void removerLivro(@PathVariable Long id) {
        return livroService.deletar(id);
    }

    @PutMapping("/{id}/emprestar")
    public Livro emprestaLivro(@PathVariable Long id) {
        return livroService.emprestar(id);
    }

    @PutMapping("/{id}/devolver")
    public Livro devolverLivro(@PathVariable Long id) {
        return livroService.devolver(id);
    }
    
}
