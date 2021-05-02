package uz.pdp.appnewssite.ecxeptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    private final String recourseName; //role

    private final String recourseFile; // name

    private final Object object; //USER, ADMIN

}
