package sit.int202.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sit.int202.demo.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findProductsByProductNameContainingIgnoreCaseOrProductVendorContainingIgnoreCaseOrProductLine_ProductLine(String productName, String vendor, String productLine);
    @Query("""
        select p from Product p where p.productName like ?1 
        or p.productVendor like ?1
        or p.productDescription like ?1
        or p.productVendor like ?1
    """)
    List<Product> findByAnyContents(String searchParam);

    List<Product> findAllProductsByKeyword(String productName, String vendor, String productLine);
    List<Product> findAllProductsByBuyPriceBetweenOrderByBuyPriceDesc(BigDecimal lower, BigDecimal upper);
}