package org.zerock.guestbook.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.guestbook.entity.Movie;
import org.zerock.guestbook.entity.MovieImage;
import org.zerock.guestbook.entity.Poster;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MovieRepositoryTests {
    @Autowired
    private MovieRepository movieRepository;

//    @Autowired
//    private MovieImageRepository imageRepository;
//
//    @Commit
//    @Transactional
//    @Test
//    public void insertMovies(){
//        IntStream.rangeClosed(1,100).forEach(i -> {
//            Movie movie = Movie.builder().title("Movie..." +i).build();
//
//            System.out.println("--------------------------------------");
//
//            movieRepository.save(movie);
//
//            int count = (int) (Math.random()*5)+1;
//
//            for(int j = 0; j < count; j++){
//                MovieImage movieImage = MovieImage.builder()
//                        .uuid(UUID.randomUUID().toString())
//                        .movie(movie)
//                        .imgName("test"+j+".jpg")
//                        .build();
//                imageRepository.save(movieImage);
//            }
//            System.out.println("=============================================");
//        });
//    }

//    @Test
//    public void testListPage(){
//        PageRequest pageRequest = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC, "mno"));
//
//        Page<Object[]> result = movieRepository.getListPage(pageRequest);
//
//        for(Object[] objects : result.getContent()){
//            System.out.println(Arrays.toString(objects));
//        }
//    }
//
//    @Test
//    public void testGetMovieWithAll(){
//        List<Object[]> result = movieRepository.getMovieWithAll(94L);
//        System.out.println(result);
//        for(Object[] arr : result){
//            System.out.println(Arrays.toString(arr));
//        }
//    }

    @Test
    public void testInsert(){
        log.info("testInsert....................");
        Movie movie = Movie.builder().title("극한직업").build();
        movie.addPoster(Poster.builder().fname("극한직업포스터1.jpg").build());
        movie.addPoster(Poster.builder().fname("극한직업포스터2.jpg").build());
        movieRepository.save(movie);
        log.info(movie.getMno());
    }
    @Test
    @Transactional
    @Commit
    public void testAddPoster(){
        Movie movie = movieRepository.getOne(1L);
        movie.addPoster(Poster.builder().fname("극한직업포스터3.jpg").build());
        movieRepository.save(movie);
    }
}
