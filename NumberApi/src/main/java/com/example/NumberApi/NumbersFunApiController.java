package com.example.NumberApi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/numbers")
@CrossOrigin(origins = "*")
public class NumbersFunApiController {
    private final NumbersFunUseCase numbersFunUseCase;

    public NumbersFunApiController(NumbersFunUseCase numbersFunUseCase1) {
        this.numbersFunUseCase = numbersFunUseCase1;
    }

    @GetMapping
    public ResponseEntity<Object> getNumbersFun(
            @RequestParam(value = "number", required = false) String number) {
        if (number == null || number.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BadRequestResponse(number, "true"));
        }
        if (isValidNumber(number)) {
            return ResponseEntity.ok(numbersFunUseCase.getFunFactNumber(Integer.parseInt(number)));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BadRequestResponse(number, "true"));
        }
    }

    private boolean isValidNumber(String number) {

        return number.matches("-?\\d+(\\.\\d+)?");// checks for alphabets, special characters and decimals
    }


    private static class BadRequestResponse {
        private String number;
        private String error;

        public BadRequestResponse(String number, String error) {
            this.number = number;
            this.error = error;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}


