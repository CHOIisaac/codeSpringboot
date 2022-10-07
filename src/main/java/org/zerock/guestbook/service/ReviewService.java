package org.zerock.guestbook.service;

import org.zerock.guestbook.dto.ReviewDTO;
import org.zerock.guestbook.entity.Member;
import org.zerock.guestbook.entity.Movie;
import org.zerock.guestbook.entity.Review;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getListOfMovie(Long mno); //영화의 모든 리뷰를 가죠 옴
    Long register(ReviewDTO movieReviewDTO); // 영화 리뷰 추가
    void modify(ReviewDTO movieReviewDTO); // 특정한 영화리뷰 수정
    void remove(Long reviewNum); // 영화 리뷰 삭제

    default Review dtoToEntity(ReviewDTO movieReviewDTO){
        Review movieReview = Review.builder()
                .reviewnum(movieReviewDTO.getReviewNum())
                .movie(Movie.builder().mno(movieReviewDTO.getMno()).build())
                .member(Member.builder().mid(movieReviewDTO.getMid()).build())
                .grade(movieReviewDTO.getGrade())
                .text(movieReviewDTO.getText())
                .build();
        return movieReview;
    }

    default ReviewDTO entityToDto(Review movieReview){
        ReviewDTO movieReviewDTO = ReviewDTO.builder()
                .reviewNum(movieReview.getReviewnum())
                .mno(movieReview.getMovie().getMno())
                .mid(movieReview.getMember().getMid())
                .nickName(movieReview.getMember().getNickname())
                .email(movieReview.getMember().getEmail())
                .grade(movieReview.getGrade())
                .text(movieReview.getText())
                .regDate(movieReview.getRegDate())
                .modDate(movieReview.getModDate())
                .build();
        return movieReviewDTO;
    }
}
