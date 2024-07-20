package com.example.e_commerce.controller.ProductManagementController;

import com.example.e_commerce.dto.ProductManagementDto.ProductRequestDto;
import com.example.e_commerce.dto.ProductManagementDto.ProductResponseDto;
import com.example.e_commerce.dto.ProductManagementDto.ProductResponseWithCountDto;
import com.example.e_commerce.entity.productManagementEntity.Product;
import com.example.e_commerce.mapper.ProductMapper;
import com.example.e_commerce.service.productManagementService.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //everyone
    @GetMapping
    public ProductResponseWithCountDto findAll(@RequestParam(value = "category", required = false) Long id,
                                               @RequestParam(value = "filter", required = false) String word,
                                               @RequestParam(value = "sort", required = false) String sort,
                                               @RequestParam(value = "limit", required = false) Integer limit,
                                               @RequestParam(value = "offset", required = false) Integer offset){

        return productService.findByParameter(id,word,sort,limit,offset);
    }

    //Store
    @PostMapping("/add")
    public ResponseEntity<ProductResponseDto> save(@Valid @RequestBody ProductRequestDto product, @AuthenticationPrincipal UserDetails userDetails){

        String username = userDetails.getUsername();

        Product savedProduct = productService.save(product,username);

        return new ResponseEntity<>(ProductMapper.ProductToProductResponse(savedProduct), HttpStatus.CREATED);
    }

    //store
    @PostMapping("/adds")
    public ResponseEntity<ProductResponseWithCountDto> save(@Valid @RequestBody List<ProductRequestDto> products, @AuthenticationPrincipal UserDetails userDetails){

        String username = userDetails.getUsername();

        List<Product> savedProducts = productService.save(products,username);

        ProductResponseWithCountDto response = ProductMapper.ProductToProductResponse(savedProducts);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}