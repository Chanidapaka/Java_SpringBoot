package sit.int202.demo.services;

import org.springframework.stereotype.Service;
import sit.int202.demo.entities.Product;
import sit.int202.demo.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
    public List<Product> findAll() {
        return repository.findAll();
    }
    public Product findById(String productCode) {
        return repository.findById(productCode).orElse(null);
    }

    public List<Product> findProductByText(String searchParam) {
//        String keyword = "%"+ searchParam +"%";
//        return repository.findAllProductsByKeyword(keyword, keyword, keyword);
        String keyword = searchParam;
        return repository.findProductsByProductNameContainingIgnoreCaseOrProductVendorContainingIgnoreCaseOrProductLine_ProductLine(keyword, keyword, keyword);
    }

    public List<Product> filterProductByPrice(Double lower, Double upper) {
        if(lower>upper) {
            double temp = lower;
            lower = upper;
            upper = temp;
        }
        return repository. findAllProductsByBuyPriceBetweenOrderByBuyPriceDesc(BigDecimal.valueOf(lower), BigDecimal.valueOf(upper));
    }
}
