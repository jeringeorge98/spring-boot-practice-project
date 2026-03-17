package com.example.practice;

import com.example.practice.product.ProductRepository;
import com.example.practice.product.service.UpdateProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UpdateProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private UpdateProductService updateProductService;
    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void given_product_when_update_product_is_called_then_update_the_product(){

    }
}
