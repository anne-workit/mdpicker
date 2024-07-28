package pe.ahn.mdpicker.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse<T> {
    private int statusCode;
    private String message;
}
