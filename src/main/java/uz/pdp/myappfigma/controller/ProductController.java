package uz.pdp.myappfigma.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.myappfigma.dto.BaseResponse;
import uz.pdp.myappfigma.dto.product.ProductCreateDto;
import uz.pdp.myappfigma.dto.product.ProductCriteria;
import uz.pdp.myappfigma.dto.product.ProductDto;
import uz.pdp.myappfigma.dto.product.ProductUpdateDto;
import uz.pdp.myappfigma.generic.PageDto;
import uz.pdp.myappfigma.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/page")
    public BaseResponse<PageDto<ProductDto>> getPage(ProductCriteria criteria) {
        PageDto<ProductDto> page = productService.getPage(criteria);
        return new BaseResponse<>(page);
    }

    @GetMapping("/{id}")
    public BaseResponse<ProductDto> get(@PathVariable long id) {
        ProductDto dto = productService.get(id);
        return new BaseResponse<>(dto);
    }

    @PostMapping("/create")
    public BaseResponse<Long> create(@RequestBody ProductCreateDto dto) {
        Long newId = productService.create(dto);
        return new BaseResponse<>(newId);
    }

    @PutMapping("/update/{id}")
    public BaseResponse<Long> update(@PathVariable Long id, @RequestBody ProductUpdateDto dto) {
        Long newId = productService.update(id, dto);
        return new BaseResponse<>(newId);
    }

    @GetMapping("/skidka")
    public BaseResponse<List<ProductDto>> getSkidka() {
        List<ProductDto> allWithDiscount = productService.findAllWithDiscount();
        return new BaseResponse<>(allWithDiscount);
    }
}
