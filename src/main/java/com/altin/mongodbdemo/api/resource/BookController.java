package com.altin.mongodbdemo.api.resource;

import com.altin.mongodbdemo.api.model.Book;
import com.altin.mongodbdemo.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BookRepository repository;

    @PostMapping("/addBook")
    public String saveBook(@RequestBody Book book){
        repository.save(book);
        return "Added book with id: " + book.getId();
    }

    @GetMapping("/onlyId")
    public List<Book> getOnlyId() {
        Query query = new Query();
        query.fields().include("id");
        List<Book> list = mongoTemplate.find(query, Book.class);
        return list;
    }

    @GetMapping("/2var")
    public List<Book> get2Var() {

        Query query = new Query();
        query.fields().include("bookName").include("id");
        List<Book> list = mongoTemplate.find(query, Book.class);
        return list;
    }

    @GetMapping("/3var")
    public List<Book> get3Var() {
        Query query = new Query();
        query.fields().include("bookName").include("id").include("var1");
        List<Book> list = mongoTemplate.find(query, Book.class);
        return list;
    }

    @GetMapping("/4var")
    public List<Book> get4Var() {
        Query query = new Query();
        query.fields().include("bookName").include("id").include("var1").include("var2");
        List<Book> list = mongoTemplate.find(query, Book.class);
        return list;
    }

    @GetMapping("/5var")
    public List<Book> get5Var(){
        Query query = new Query();
        query.fields().include("bookName").include("id").include("var1").include("var2").include("var3");
        List<Book> list = mongoTemplate.find(query, Book.class);
        return list;
    }

    @GetMapping("/6var")
    public List<Book> get6Var(){
        Query query = new Query();
        query.fields().include("bookName").include("id").include("var1").include("var2").include("var3").include("var4");
        List<Book> list = mongoTemplate.find(query, Book.class);
        return list;
    }

    @GetMapping("/7var")
    public List<Book> get7Var(){
        Query query = new Query();
        query.fields().include("bookName").include("id").include("var1").include("var2").include("var3").include("var4").include("var5");
        List<Book> list = mongoTemplate.find(query, Book.class);
        return list;
    }

    @GetMapping("/8var")
    public List<Book> get8Var(){
        Query query = new Query();
        query.fields().include("bookName").include("id").include("var1").include("var2").include("var3").include("var4").include("var5").include("var6");
        List<Book> list = mongoTemplate.find(query, Book.class);
        return list;
    }

    @GetMapping("/9var")
    public List<Book> get9Var(){
        Query query = new Query();
        query.fields().include("bookName").include("id").include("var1").include("var2").include("var3").include("var4").include("var5").include("var6").include("var7");
        List<Book> list = mongoTemplate.find(query, Book.class);
        return list;
    }

    @GetMapping("/allVars")
    public List<Book> getAllVars(){
        return repository.findAll();
    }

    @GetMapping("/findAllBooks/{id}")
    public Optional<Book> getBook(@PathVariable int id){
        return repository.findById(id);
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id){
        repository.deleteById(id);
        return "Book deleted with id:" + id;
    }
}
