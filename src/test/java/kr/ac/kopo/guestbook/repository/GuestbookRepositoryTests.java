package kr.ac.kopo.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import kr.ac.kopo.guestbook.entity.Guestbook;
import kr.ac.kopo.guestbook.entity.QGuestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1, 300).forEach(i ->{
            Guestbook guestbook = Guestbook.builder()
                    .title("Title=====" + i)
                    .content("Content=====" + i)
                    .writer("Writer " + (i%10+1))
                    .build();
            guestbookRepository.save(guestbook);
        });
    }

    @Test
    public void updateTest(){
        Optional<Guestbook> result =  guestbookRepository.findById(300L);

        if(result.isPresent()){
            Guestbook guestbook = result.get();

            guestbook.changeTitle("Changed Title .......");
            guestbook.changeContent("Changed Content .......");

            guestbookRepository.save(guestbook);
        }
    }
    //단일항목 검색
    @Test
    public void testQuery1(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression exp1 = qGuestbook.title.contains(keyword);
        builder.and(exp1);
        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);

        });

    }
    // 다중항목 검색
    @Test
    public void testQuery2(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "2";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression ex1 = qGuestbook.title.contains(keyword);
        BooleanExpression ex2 = qGuestbook.content.contains(keyword);
        BooleanExpression exp = ex1.or(ex2);
        builder.and(exp);
        builder.and(qGuestbook.gno.gt(0L));
        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);


        });

    }
}
