package com.example.practice;

import com.example.practice.exceptions.ProductNotFoundException;
import com.example.practice.product.ProductRepository;
import com.example.practice.product.model.Product;
import com.example.practice.product.model.ProductDTO;
import com.example.practice.product.service.GetProductService;
import com.example.practice.product.service.GetSingleProductService;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GetProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetSingleProductService productService;

    @Before
    public void setUp(){
        // This helps load our mocks which are the repository and service classes
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public  void given_product_when_id_ispassed_then_return_product(){
       //GIVEN
        Product product = new Product();
        product.setId(1L);
        product.setName("IPHONE");
        product.setDescription("A new IPHONE");
        product.setPrice(9.99);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        //WHEN
        ResponseEntity<ProductDTO> response = productService.execute(1L);

        //THEN
        assertEquals(ResponseEntity.ok(new ProductDTO(product)),response);
        // assert that product repository called findbyId only once
        verify(productRepository,times(1)).findById(1L);
    }

    @Test
    public void given_product_when_wron_id_is_passed_then_return_null(){
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,()->productService.execute(1L));

        verify(productRepository,times(1)).findById(1L);

    }

}
