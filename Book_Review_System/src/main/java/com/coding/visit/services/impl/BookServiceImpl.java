package com.coding.visit.services.impl;

import com.coding.visit.dtos.BookDto;
import com.coding.visit.entities.Book;
import com.coding.visit.repositories.BookRepository;
import com.coding.visit.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public BookDto addBook(BookDto bookDto) {

        bookDto.setBookId(UUID.randomUUID().toString());
        Book saved =  bookRepository.save(mapper.map(bookDto, Book.class))  ;
        return mapper.map(saved, BookDto.class) ;
    }

    @Override
    public Page<BookDto> getAllBooks(String author, String genre, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize) ;
        if(author!=null){

            Page<Book> page=bookRepository.findByAuthorContainingIgnoreCase(author,pageable);
           List<BookDto> bookDtoList=page.stream().map(book->mapper.map(book,BookDto.class)).collect(Collectors.toList());
           return new PageImpl<>(bookDtoList, pageable,page.getTotalElements()) ;
        }

        if(genre!=null){
            Page<Book> page=bookRepository.findByGenreContainingIgnoreCase(genre,pageable);
            List<BookDto> bookDtoList=page.stream().map(book->mapper.map(book,BookDto.class)).collect(Collectors.toList());
            return new PageImpl<>(bookDtoList, pageable,page.getTotalElements()) ;
        }

        Page<Book> page=bookRepository.findAll(pageable);
        List<BookDto> bookDtoList=page.stream().map(book->mapper.map(book,BookDto.class)).collect(Collectors.toList());
        return new PageImpl<>(bookDtoList, pageable,page.getTotalElements());
    }

    @Override
    public Page<BookDto> search(String keyword, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize) ;
        Page<Book> page = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword,keyword,pageable) ;
        List<BookDto> bookDtoList=page.stream().map(book->mapper.map(book,BookDto.class)).collect(Collectors.toList());
        return new PageImpl<>(bookDtoList,pageable,page.getTotalElements());
    }
}
