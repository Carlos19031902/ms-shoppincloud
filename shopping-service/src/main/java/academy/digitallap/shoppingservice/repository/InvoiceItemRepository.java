package academy.digitallap.shoppingservice.repository;

import academy.digitallap.shoppingservice.model.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem,Long> {

}
