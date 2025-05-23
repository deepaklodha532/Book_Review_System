package com.coding.visit.dtos;

import lombok.Data;

import java.util.List;
public class BookDto {
    String bookId;
    String title;
    String author;
    String genre;
    String description;
//    List<ReviewDto> reviews;


    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public List<ReviewDto> getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(List<ReviewDto> reviews) {
//        this.reviews = reviews;
//    }
}
