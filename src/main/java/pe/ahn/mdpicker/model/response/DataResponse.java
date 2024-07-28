package pe.ahn.mdpicker.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResponse<T> extends CommonResponse<T> {
    private T data;

    public DataResponse(T data) {
        this.data = data;
        this.setStatusCode(HttpStatus.OK.value());
    }
}
