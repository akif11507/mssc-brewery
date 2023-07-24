package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/Customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<?> handleGet(@PathVariable UUID customerId) {
        return ResponseEntity.ok().body(customerService.getCustomerById(customerId));
    }

    @PostMapping
    public ResponseEntity<?> handlePost(@RequestBody CustomerDto customerDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/Customer");
        return ResponseEntity.ok().headers(headers).body(customerService.saveNewCustomer(customerDto));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<?> handleUpdate(@PathVariable UUID customerId, @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok().body(customerService.updateCustomer(customerId, customerDto));
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getCustomer(@PathVariable UUID customerId) {
        customerService.deleteCustomerById(customerId);
    }

}
