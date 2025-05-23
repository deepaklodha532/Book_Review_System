package com.coding.visit.controller;

import com.coding.visit.dtos.BookDto;
import com.coding.visit.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.addBook(bookDto), HttpStatus.CREATED) ;
    }

    @GetMapping
    ResponseEntity<Page<BookDto>>getByAuthorGenre(
            @RequestParam(value = "author", required = false ) String author ,
            @PathVariable(value = "genre",required = false) String genre ,
            @RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize" ,defaultValue = "10", required = false) int pageSize
    ){
        return ResponseEntity.ok(bookService.getAllBooks(author,genre,pageNumber,pageSize));
    }

    @GetMapping("/search")
    ResponseEntity<Page<BookDto>> search(@RequestParam String keyword ,
                                @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return  new ResponseEntity<>(bookService.search(keyword,pageNumber,pageSize),HttpStatus.OK) ;
    }
}
