package com.coding.visit.services;

import com.coding.visit.dtos.BookDto;
import org.hibernate.dialect.BooleanDecoder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;


public interface BookService {

    //add book
    public BookDto addBook(BookDto bookDto) ;

    //get
    public Page<BookDto> getAllBooks(String author, String genre , int pageNumber, int pageSize) ;


    //search
    public Page<BookDto> search(String keyword, int pageNumber, int pageSize) ;


}
