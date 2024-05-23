package kr.ac.kopo.guestbook.dto;

import lombok.Data;
import org.apache.el.lang.FunctionMapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//View계층에서 화면에 출력하기 위해 필요한 정보들이 저장되는 클래스
@Data
public class PageResultDTO<DTO, EN> {
    //DTO 리스트
    private List<DTO> dtoList;

    //총 페이지 번호
    private int totalPage;

    //현재 페이지 번호
    private int page;

    //목록사이즈, 한페이지에 보여지는 글목록 개수
    private int size;

    //한화면에 아래쪽에 보여질 시작 페이지 번호, 끝 페이지 번호
    private int start, end;

    //화면으르 바꿔줄 이전, 다음 링크가 보여지거나 보이지 않게 설정할 때 필요
    private boolean prev, next;

    //한화면에 보여질 페이지 번호 목록이 저장
    private  List<Integer> pageList;
    
    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn){
        //매개변수로 전달 받은 결과행들과 entity를 dto로 변환한 fn을 사용해서 GuestbookDTO객체를 저장한 리스트
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        
        totalPage = result.getTotalPages();//301개의 행을 갖고 있다면 전체 페이지 수는 31페이지다.
        
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1;//0부터 시작하므로 1을 추가
        this.size = pageable.getPageSize();

        //temp end page, 현재 화면에 보여질 임시 마지막 페이지 번호
        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;

        start = tempEnd - 9;

        prev = start > 1; //2~마지막 화면까지 true
        //삼항조건연산자에서 조건식 true면 마지막 화면이 아닌 경우 false면 마지막 화면이라는 뜻
        //전체 페이지 번호가 31일때 : 마지막 화면이 아닌 경우 1~3번째 화면(10, 20, 30), 마지막 화면은 4번째 화면을 의미(31)
        end = totalPage > tempEnd ? tempEnd: totalPage;

        next = totalPage > tempEnd;//1~마지막 바로 전화면까지 true

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
}
