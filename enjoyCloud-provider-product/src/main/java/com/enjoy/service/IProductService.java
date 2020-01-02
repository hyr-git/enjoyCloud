package com.enjoy.service;
import java.util.List;

import com.enjoy.vo.Product;

public interface IProductService {
    Product get(long id);
    boolean add(Product product);
    List<Product> list();
}