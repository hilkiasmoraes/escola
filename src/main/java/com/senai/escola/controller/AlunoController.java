package com.senai.escola.controller;

import com.senai.escola.model.Aluno;
import com.senai.escola.service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Aluno> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Aluno criar(@RequestBody Aluno aluno) {
        return service.salvar(aluno);
    }

    @GetMapping("/{id}")
    public Aluno buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Aluno atualizar(@PathVariable Long id, @RequestBody Aluno novoAluno) {
        Aluno alunoExistente = service.buscarPorId(id);
        if (alunoExistente == null) return null;

        alunoExistente.setNomeAluno(novoAluno.getNomeAluno());
        alunoExistente.setEmailAluno(novoAluno.getEmailAluno());
        alunoExistente.setTelefoneAluno(novoAluno.getTelefoneAluno());
        return service.salvar(alunoExistente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}