package academy.digitallap.shoppingservice.service;

import academy.digitallap.shoppingservice.model.Invoice;
import academy.digitallap.shoppingservice.repository.InvoiceItemRepository;
import academy.digitallap.shoppingservice.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;
    @Override
    public List<Invoice> findAllInvoice() {
        return invoiceRepository.findAll();
    }

    @Transactional
    @Override
    public Invoice createInvoice(Invoice invoice) {
        log.info(invoice.toString());
        Invoice invoiceBd = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
        if(null==invoiceBd){
            invoice.setState("CREATED");
            return invoiceRepository.save(invoice);
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
        invoiceBd.setState("UPDATED");
        return invoiceRepository.save(invoiceBd);
    }

    @Override
    public Invoice getInvoice(Long id) {
        return invoiceRepository.findById(id).orElseThrow();
    }
}
