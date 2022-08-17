package api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@Getter
public class MultiResponseDto<T> {

    private T data;
    private PageInfo pageInfo;

    public MultiResponseDto(T data, Page page) {

        this.data = data;
        this.pageInfo = new PageInfo(
                page.getNumber()+1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages());
    }

}
