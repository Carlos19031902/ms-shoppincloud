package academy.digitallap.shoppingservice.service;

import academy.digitallap.shoppingservice.model.Invoice;

import java.util.List;

public interface InvoiceService {
    public List<Invoice> findAllInvoice();
    public Invoice createInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public Invoice deleteInvoice(Invoice invoice);
    public Invoice getInvoice(Long id);
}
