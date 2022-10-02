package org.zerock.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.guestbook.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
