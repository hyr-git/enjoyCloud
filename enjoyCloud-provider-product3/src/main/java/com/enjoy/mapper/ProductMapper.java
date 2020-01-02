package com.enjoy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.enjoy.vo.Product;

@Mapper
public interface ProductMapper {
    boolean create(Product product);
    public Product findById(Long id);
    public List<Product> findAll();
}