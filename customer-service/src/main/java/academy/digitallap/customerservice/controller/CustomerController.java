package academy.digitallap.customerservice.controller;

import academy.digitallap.customerservice.model.Customer;
import academy.digitallap.customerservice.model.Region;
import academy.digitallap.customerservice.service.CustomerService;
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
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> listAllCustomer(@RequestParam(name = "regionId", required = false) Long regionId){
        List<Customer> allCustomer = new ArrayList<>();
        if(null==regionId){
            allCustomer=customerService.findAllCustomer();
        }else{
            Region regionSearch = new Region();
            regionSearch.setId(regionId);
            allCustomer = customerService.findCustomersByRegion(regionSearch);
        }
        if(allCustomer.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allCustomer);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id){
        Optional customer = customerService.getCustomer(id);
        if(!customer.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok((Customer)customer.get());
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        Customer customerBD = customerService.CreateCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerBD);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") long id,@RequestBody Customer customer ){
        Optional currentCustomer = customerService.getCustomer(id);
        if(currentCustomer.isPresent()){
            return ResponseEntity.notFound().build();
        }
        customer.setId(id);
        Customer customerUpdate = customerService.updateCustomer(customer);

        return ResponseEntity.ok(customerUpdate);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id ){
        Optional<Customer> customer = customerService.getCustomer(id);
        if(!customer.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerService.DeleteCustomer(customer.get()));
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
        log.info(jsonString);
        return jsonString;
    }

}
