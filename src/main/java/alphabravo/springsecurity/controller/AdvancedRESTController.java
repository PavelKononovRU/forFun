package alphabravo.springsecurity.controller;

import alphabravo.springsecurity.ExceptionHandler.ExceptionInformation;
import alphabravo.springsecurity.ExceptionHandler.NoSuchPersonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdvancedRESTController {
    @ExceptionHandler
    public ResponseEntity<ExceptionInformation> handler(NoSuchPersonException exception) {
        ExceptionInformation data = new ExceptionInformation();
        data.setInform(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}
