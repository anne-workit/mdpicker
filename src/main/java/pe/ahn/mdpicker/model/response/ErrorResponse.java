package pe.ahn.mdpicker.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse extends CommonResponse{
    public ErrorResponse(ErrorCode errorCode, String message) {
        this.setStatusCode(errorCode.getStatusCode());
        this.setMessage(message);
    }
}
