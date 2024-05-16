package kr.ac.kopo.guestbook.service;

import kr.ac.kopo.guestbook.dto.GuestbookDTO;
import kr.ac.kopo.guestbook.dto.PageRequestDTO;
import kr.ac.kopo.guestbook.dto.PageResultDTO;
import kr.ac.kopo.guestbook.entity.Guestbook;

public interface GuestbookService {//    글등록 기능

    Long register(GuestbookDTO dto); //    한 페이지에 보여질 글 목록(GuestbookDTO 객체)이 저장된 List정볼르 갖고 있는 PageReultDTO객체 참조값을 반환하는 기능

    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);


    default Guestbook dtoToEntity(GuestbookDTO dto){
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWrtier())
                .build();
        return entity;
    }

    default GuestbookDTO entityToDto(Guestbook entity){
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .wrtier(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

    return dto;
    }
}
