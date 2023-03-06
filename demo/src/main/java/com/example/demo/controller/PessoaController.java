package com.example.demo.controller;


import com.example.demo.entity.Pessoa;
import com.example.demo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PessoaController {
    @Autowired
    private PessoaRepository _pessoaRepository;

    @RequestMapping(value = "/pessoa", method = RequestMethod.GET)
    public List<Pessoa> get(){
        return _pessoaRepository.findAll();
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pessoa> getById(@PathVariable(value = "id") long id){
        Optional<Pessoa> pessoa = _pessoaRepository.findById(id);
        return pessoa.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @RequestMapping(value = "/pessoa", method = RequestMethod.POST)
    public Pessoa post(@Validated @RequestBody Pessoa pessoa){
        return _pessoaRepository.save(pessoa);
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Pessoa> put(@PathVariable(value = "id") long id, @Validated @RequestBody Pessoa newPessoa){
        Optional<Pessoa> oldPessoa = _pessoaRepository.findById(id);
        if(oldPessoa.isPresent()){
            Pessoa pessoa = oldPessoa.get();
            pessoa.setName(newPessoa.getName());
            _pessoaRepository.save(pessoa);
            return new ResponseEntity<Pessoa>(pessoa,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete (@PathVariable(value = "id") long id){
        Optional<Pessoa> pessoa = _pessoaRepository.findById(id);
        if(pessoa.isPresent()){
            _pessoaRepository.delete(pessoa.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
