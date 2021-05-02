package uz.pdp.appnewssite.ecxeptions;

import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {

    private String typeError;
    private String message;

    public ForbiddenException(String typeError, String message) {
        this.typeError = typeError;
        this.message = message;
    }
}
