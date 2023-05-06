package com.scarlxrd.products.controller;

import com.scarlxrd.products.entity.Product;
import com.scarlxrd.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository _productRepository;
    @RequestMapping(value="/produto", method = RequestMethod.GET)
    public List<Product> get(){
        return _productRepository.findAll();
    }
    @RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getById(@PathVariable(value = "id") long id){
        Optional<Product> product = _productRepository.findById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @RequestMapping(value ="/produto", method = RequestMethod.POST)
    public Product post(@Validated @RequestBody Product product){
        return _productRepository.save(product);
    }
    @RequestMapping(value = "/produto/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> put (@PathVariable(value = "id")long id , @Validated @RequestBody Product newProduct){
        Optional<Product> oldProduct = _productRepository.findById(id);
        if(oldProduct.isPresent()){
            Product product = oldProduct.get();
            product.setName(newProduct.getName());
            _productRepository.save(product);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/produto/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete (@PathVariable(value = "id") long id){
        Optional<Product> product = _productRepository.findById(id);
        if(product.isPresent()){
            _productRepository.delete(product.get());
            return  new ResponseEntity<>(HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
