package academy.digitallap.shoppingservice.service;

import academy.digitallap.shoppingservice.client.CustomerClient;
import academy.digitallap.shoppingservice.client.ProductClient;
import academy.digitallap.shoppingservice.clientmodel.Customer;
import academy.digitallap.shoppingservice.clientmodel.Product;
import academy.digitallap.shoppingservice.model.Invoice;
import academy.digitallap.shoppingservice.model.InvoiceItem;
import academy.digitallap.shoppingservice.repository.InvoiceItemRepository;
import academy.digitallap.shoppingservice.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;
    
    @Autowired
    private CustomerClient customerCLient;
    
    @Autowired
    private ProductClient productClient;
    
    @Override
    public List<Invoice> findAllInvoice() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        log.info(invoice.toString());
        Invoice invoiceBd = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
        if(null==invoiceBd){
            invoice.setState("CREATED");
            invoiceBd = invoiceRepository.save(invoice);
            invoiceBd.getItems().forEach(invoceItem->{
            	productClient.updateStockProduct(invoceItem.getProductId(), invoceItem.getQuantity()*-1);
            });
            
        }
        return invoiceBd;
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice invoiceBd = getInvoice(invoice.getId());
        if (null == invoiceBd) {
            return null;
        }
        invoiceBd.setNumberInvoice(invoice.getNumberInvoice());
        invoiceBd.setCustomerId(invoice.getCustomerId());
        invoiceBd.setDescription(invoice.getDescription());
        invoiceBd.getItems().clear();
        invoiceBd.setItems(invoice.getItems());
        return invoiceRepository.save(invoiceBd);
    }
    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Invoice invoiceBd = getInvoice(invoice.getId());
        if (null == invoiceBd) {
            return null;
        }
        invoiceBd.setState("DELETED");
        return invoiceRepository.save(invoiceBd);
    }

    @Override
    public Invoice getInvoice(Long id) {
    	Invoice invoice = invoiceRepository.findById(id).orElseThrow();
    
    	if(null!=invoice) {
    		
    		 Customer customer = customerCLient.getCustomer(Long.parseLong(invoice.getCustomerId())).getBody();
    		
    		 invoice.setCustomer(customer);
    		 List<InvoiceItem> items = invoice.getItems().stream().map(item->{
    			 Product product = productClient.getProduct(item.getProductId()).getBody();
    			 item.setProduct(product);
    			 return item;
    		 }).collect(Collectors.toList());
    		 invoice.setItems(items);
    	}
    	
    	return invoice;
    }
}
