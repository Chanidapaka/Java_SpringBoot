package sit.int202.demo.services;

import org.springframework.stereotype.Service;
import sit.int202.demo.entities.Product;
import sit.int202.demo.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    //ประกาศตัวแปร repository ซึ่งเป็นอ็อบเจ็กต์ของ ProductRepository เพื่อเชื่อมต่อและจัดการข้อมูลสินค้าในฐานข้อมูล
    private final ProductRepository repository;

    //Constructor
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    //ฟังก์ชัน findAll() ใช้ดึงข้อมูลสินค้าทั้งหมดจากฐานข้อมูลผ่านเมธอด findAll() ของ ProductRepository
    public List<Product> findAll() {
        return repository.findAll();
    }

    //ฟังก์ชัน findById(String productCode) ใช้ค้นหาสินค้าในฐานข้อมูลด้วยรหัสสินค้า (productCode)
    // โดยใช้เมธอด findById ของ ProductRepository และคืนค่า null หากไม่พบ
    public Product findById(String productCode) {
        return repository.findById(productCode).orElse(null);
    }

    //ฟังก์ชัน findProductByText(String searchParam) ใช้ค้นหาสินค้าด้วยคำค้น (searchParam)
    // ที่ประกอบด้วยชื่อ (ProductName), ผู้ผลิต (ProductVendor), หรือสายผลิตภัณฑ์ (ProductLine)
    public List<Product> findProductByText(String searchParam) {
//        String keyword = "%"+ searchParam +"%";
//        return repository.findAllProductsByKeyword(keyword, keyword, keyword);
        String keyword = searchParam;
        return repository.findProductsByProductNameContainingIgnoreCaseOrProductVendorContainingIgnoreCaseOrProductLine_ProductLine(keyword, keyword, keyword);
    }


    //ฟังก์ชัน filterProductByPrice(Double lower, Double upper) ใช้กรองข้อมูลสินค้าตามช่วงราคาซื้อ (BuyPrice)
    // โดยเช็คให้แน่ใจว่า lower เป็นค่าน้อยกว่า upper และเรียกใช้เมธอด findAllProductsByBuyPriceBetweenOrderByBuyPriceDesc ของ ProductRepository
    public List<Product> filterProductByPrice(Double lower, Double upper) {
        if(lower>upper) {
            double temp = lower;
            lower = upper;
            upper = temp;
        }
        return repository. findAllProductsByBuyPriceBetweenOrderByBuyPriceDesc(BigDecimal.valueOf(lower), BigDecimal.valueOf(upper));
    }
}
