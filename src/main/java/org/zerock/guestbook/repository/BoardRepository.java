package org.zerock.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.guestbook.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
