package com.coding.visit.services.impl;

import com.coding.visit.dtos.ReviewDto;
import com.coding.visit.entities.Book;
import com.coding.visit.entities.Review;
import com.coding.visit.entities.User;
import com.coding.visit.exceptions.ResourceNotFoundException;
import com.coding.visit.repositories.BookRepository;
import com.coding.visit.repositories.ReviewRepository;
import com.coding.visit.repositories.UserRepository;
import com.coding.visit.services.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public ReviewDto submitReview(String bookId, String userId, ReviewDto reviewDto) {
        reviewDto.setReviewId(UUID.randomUUID().toString());
        Book book = bookRepository.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Book id not found")) ;
        User user  = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user id not found")) ;
        Review reviewFind = reviewRepository.findByBookAndUser(book ,user).orElse(null)  ;

        if(reviewFind !=null) throw new ResourceNotFoundException("user already reviewed on this book") ;
        Review review = mapper.map(reviewDto,Review.class);
        review.setBook(book);
        review.setUser(user);
        Review saved =  reviewRepository.save(review);
        return mapper.map(saved, ReviewDto.class);
    }

    @Override
    public ReviewDto updateReview(String reviewId, String userId, ReviewDto reviewDto) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new ResourceNotFoundException("review is not submit"));
        if(!review.getUser().getUserId().equals(userId)) throw new SecurityException("UnAuthorize User");
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        Review update = reviewRepository.save(review);
        return mapper.map(update,ReviewDto.class) ;
    }

    @Override
    public void deleteReview(String reviewId, String userId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new ResourceNotFoundException("review is not submit"));
        if(!review.getUser().getUserId().equals(userId)) throw new SecurityException("UnAuthorize User");
        reviewRepository.delete(review) ;

    }

    @Override
    public Set<ReviewDto> getReviewsOfBook(String bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Book id not found")) ;
        Set<Review> reviews= reviewRepository.findByBook(book);
        return reviews.stream().map(review -> mapper.map(review,ReviewDto.class)).collect(Collectors.toSet());
    }

    @Override
    public Set<ReviewDto> getAllReview(){

       Set<ReviewDto> reviewDtos = reviewRepository.findAll().stream().map(review -> mapper.map(review,ReviewDto.class)).collect(Collectors.toSet());
       return reviewDtos ;
    }
}
