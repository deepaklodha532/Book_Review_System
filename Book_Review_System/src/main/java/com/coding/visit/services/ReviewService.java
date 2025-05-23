package com.coding.visit.services;

import com.coding.visit.dtos.ReviewDto;

import java.util.Set;

public interface ReviewService {

    // submit review
    ReviewDto submitReview(String bookId, String userId, ReviewDto reviewDto) ;


    // update review
    ReviewDto updateReview(String reviewId, String userId,  ReviewDto reviewDto);

    //delete review
    void deleteReview(String reviewId, String userId) ;

    //getReview
    Set<ReviewDto> getReviewsOfBook(String bookId);

    public Set<ReviewDto> getAllReview();



}
