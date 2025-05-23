package com.coding.visit.controller;

import com.coding.visit.dtos.ApiResponseMessage;
import com.coding.visit.dtos.ReviewDto;
import com.coding.visit.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService ;
    //submitReview
    @PostMapping("/books/{bookId}/users/{userId}")
    public ResponseEntity<ReviewDto> submitReview(@PathVariable  String bookId, @PathVariable String userId, @RequestBody  ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.submitReview(bookId,userId, reviewDto), HttpStatus.CREATED);
    }
    //update review
    @PutMapping("/{reviewId}/users/{userId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable  String reviewId, @PathVariable String userId, @RequestBody  ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.updateReview(reviewId,userId, reviewDto), HttpStatus.OK);
    }
    //delete review
    @DeleteMapping("/{reviewId}/users/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteReview(@PathVariable  String reviewId, @PathVariable String userId){
        reviewService.deleteReview(reviewId,userId);
        ApiResponseMessage message = new ApiResponseMessage() ;
        message.setMessage("deleted successfully");
        message.setSuccess(true);
        message.setStatus(HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //get book review
    @GetMapping("/books/{bookId}")
    public ResponseEntity<Set<ReviewDto>> getReviewOfBook(@PathVariable String bookId){
        return new ResponseEntity<>(reviewService.getReviewsOfBook(bookId),HttpStatus.OK);
    }

    //get all

    @GetMapping
    public ResponseEntity<Set<ReviewDto>> getAllReview(){
        return new ResponseEntity<>(reviewService.getAllReview(),HttpStatus.OK) ;
    }

}
