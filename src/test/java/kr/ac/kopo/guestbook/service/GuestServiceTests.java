package kr.ac.kopo.guestbook.service;

import kr.ac.kopo.guestbook.dto.GuestbookDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestServiceTests {

    @Autowired
    private  GuestbookService service;

    @Test
    public void testRegister(){
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("등록 연습 title 1")
                .content("등록 연습 content 1")
                .wrtier("등록 연습 writer 1")
                .build();

        service.register(guestbookDTO);
    }

}
