package academy.digitallap.customerservice.service;

import academy.digitallap.customerservice.model.Customer;
import academy.digitallap.customerservice.model.Region;
import academy.digitallap.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findCustomersByRegion(Region region) {
        return customerRepository.findByRegion(region);
    }

    @Override
    public Customer CreateCustomer(Customer customer) {
        Customer customerBD = customerRepository.findByNumberID(customer.getNumberID());
        if(null!=customerBD){
            return customerBD;
        }else {
            customer.setState("CREATED");
            customerBD = customerRepository.save(customer);
            return customerBD;
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerBD = customerRepository.findByNumberID(customer.getNumberID());
        if(null==customerBD){
            return null;
        }else {
            customerBD.setFirstName(customer.getFirstName());
            customerBD.setLastName(customer.getLastName());
            customerBD.setEmail(customer.getEmail());
            customerBD.setPhotoUrl(customer.getPhotoUrl());
            customerBD.setState("UPDATED");
            return customerRepository.save(customerBD);
        }
    }

    @Override
    public Customer DeleteCustomer(Customer customer) {
        Customer customerBD = customerRepository.findByNumberID(customer.getNumberID());
        if(null==customerBD){
            return null;
        }else {
            customerBD.setState("DELETED");
            return customerRepository.save(customerBD);
        }
    }

    @Override
    public Optional<Customer> getCustomer(Long id) {
        return customerRepository.findById(id);
    }
}
