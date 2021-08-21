package com.altin.mongodbdemo.api.repository;

import com.altin.mongodbdemo.api.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, Integer> {
}
