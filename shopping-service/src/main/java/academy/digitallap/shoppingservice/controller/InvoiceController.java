package academy.digitallap.shoppingservice.controller;

import academy.digitallap.shoppingservice.model.Invoice;
import academy.digitallap.shoppingservice.service.InvoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoice(){
        List<Invoice> invoices = invoiceService.findAllInvoice();
        if(invoices.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(invoices);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Invoice>  getInvoice(@PathVariable(name = "id") long id){
        Invoice invoice = invoiceService.getInvoice(id);
        if(null == invoice){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoice);
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody Invoice invoice, BindingResult result){
        log.info(invoice.toString());
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.createInvoice(invoice));
    }

    @PutMapping(name = "/{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable long id, @RequestBody Invoice invoice){
        invoice.setId(id);
        Invoice invoiceBD = invoiceService.updateInvoice(invoice);
        if(null==invoiceBD){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoiceBD);
    }

    @DeleteMapping(name = "/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable long id){
        Invoice invoice = invoiceService.getInvoice(id);
        if(null== invoice){
            return ResponseEntity.notFound().build();
        }
        invoice = invoiceService.deleteInvoice(invoice);
        return ResponseEntity.ok(invoice);
    }

    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err->{
                            Map<String,String> error = new HashMap<>();
                            error.put(err.getField(),err.getDefaultMessage());
                            return error;
                        }
                ).collect(Collectors.toList());
        ErrorMensaje errorMensaje = ErrorMensaje.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMensaje);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }
}
