package api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@AllArgsConstructor
@Getter
public class MultiResponseListDto<T> {

    private T data;

    public MultiResponseListDto(T data, Page page) {

        this.data = data;
    }

}
