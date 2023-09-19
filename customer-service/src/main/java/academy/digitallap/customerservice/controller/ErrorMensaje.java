package academy.digitallap.customerservice.controller;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ErrorMensaje {
    private String code;
    private List<Map<String,String>> messages;
}

