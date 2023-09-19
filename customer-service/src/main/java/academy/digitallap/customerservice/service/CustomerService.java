package academy.digitallap.customerservice.service;

import academy.digitallap.customerservice.model.Customer;
import academy.digitallap.customerservice.model.Region;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    public List<Customer> findAllCustomer();
    public List<Customer> findCustomersByRegion(Region region);

    public Customer CreateCustomer(Customer customer);

    public Customer updateCustomer(Customer customer);
    public Customer DeleteCustomer(Customer customer);

    public Optional<Customer> getCustomer(Long id);
}
