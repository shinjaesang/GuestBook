package kr.ac.kopo.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import kr.ac.kopo.guestbook.entity.Guestbook;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long>, QuerydslPredicateExecutor<Guestbook> {

}
