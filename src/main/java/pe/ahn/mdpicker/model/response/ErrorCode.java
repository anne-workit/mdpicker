package pe.ahn.mdpicker.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND.value()),
    INTER_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value()),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value()),
    ;
    private final int statusCode;
}
