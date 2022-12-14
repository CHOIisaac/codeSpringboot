package org.zerock.guestbook.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.guestbook.entity.Member;
import org.zerock.guestbook.entity.Movie;
import org.zerock.guestbook.entity.Review;

import javax.swing.plaf.metal.MetalMenuBarUI;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMovieReviews(){

        IntStream.rangeClosed(1,200).forEach(i->{
            Long mno = (long)(Math.random()*100)+1;

            Long mid = (long)(Math.random()*100)+1;
            Member member = Member.builder().mid(mid).build();

            Review moviewReview = Review.builder()
                    .member(member)
                    .movie(Movie.builder().mno(mno).build())
                    .grade((int)(Math.random()*5)+1)
                    .text("이 영화에 대한 느낌..."+i)
                    .build();
            reviewRepository.save(moviewReview);
        });
    }

    @Test
    public void testGetMovieReviews(){
        Movie movie = Movie.builder().mno(94L).build();
        List<Review> result = reviewRepository.findByMovie(movie);
        result.forEach(movieReview -> {
            System.out.println(movieReview.getReviewnum());
            System.out.println("\t"+movieReview.getGrade());
            System.out.println("\t"+movieReview.getText());
            System.out.println("\t"+movieReview.getMember().getEmail());
            System.out.println("-------------------------------");
        });
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteMember(){
        Long mid = 1L;
        Member member = Member.builder().mid(mid).build();
        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);
    }
}
