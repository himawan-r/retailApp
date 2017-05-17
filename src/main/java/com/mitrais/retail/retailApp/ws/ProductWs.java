package com.mitrais.retail.retailApp.ws;

import com.mitrais.retail.retailApp.model.Product;
import com.mitrais.retail.retailApp.model.Response;
import com.mitrais.retail.retailApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Himawan_R on 5/17/2017.
 */
@RestController
@RequestMapping("/api/productws")
public class ProductWs {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public Response getAllProducts() {
        List<Product> products = productService.findAllProducts();

        Response response = new Response();
        response.setData(products);
        response.setStatus(Response.SUCCESS);
        return response;
    }
}
