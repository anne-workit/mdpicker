package pe.ahn.mdpicker.system;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.ahn.mdpicker.model.response.ErrorCode;
import pe.ahn.mdpicker.model.response.ErrorResponse;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ErrorResponse handleApiException(ApiException e) {
        return new ErrorResponse(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e) {
        return new ErrorResponse(ErrorCode.INTER_SERVER_ERROR, e.getMessage());
    }
}
