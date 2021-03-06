package com.comsysto.shop.service.product.impl;

import com.comsysto.common.service.AbstractServiceImpl;
import com.comsysto.shop.repository.product.api.ProductRepository;
import com.comsysto.shop.repository.product.model.Product;
import com.comsysto.shop.repository.product.model.ProductQuery;
import com.comsysto.shop.repository.product.model.ProductType;
import com.comsysto.shop.service.product.api.ProductService;
import com.comsysto.shop.service.product.model.ProductInfo;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("productService")
public class ProductServiceImpl extends AbstractServiceImpl<ProductInfo, Product> implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(@Qualifier("productRepository") ProductRepository productRepository,
                              @Qualifier("dozerMapper") Mapper dozerMapper) {
        super(productRepository, dozerMapper, ProductInfo.class, Product.class);
        this.productRepository = productRepository;
    }


    @Override
    public List<ProductInfo> findAll(ProductType productType) {
        List<Product> classifiedProducts = productRepository.findAll(productType);
        return mapListOfSourceEntitiesToDestinationEntities(classifiedProducts);
    }

    @Override
    public ProductInfo findByUrlname(String urlname) {
        return getDozerMapper().map(productRepository.findByUrlname(urlname), ProductInfo.class);
    }

    @Override
    public List<ProductInfo> findAll() {
        List<ProductInfo> productInfos = super.findAll();
        Collections.sort(productInfos);
        return productInfos;
    }

    @Override
    public void save(ProductInfo entity) {
        super.save(entity);
    }

    @Override
    public List<ProductInfo> findAllSortedByClassifier() {
        return mapListOfSourceEntitiesToDestinationEntities(productRepository.findAllSortedByClassifier());
    }

    @Override
    public ProductInfo findByQuery(ProductQuery productQuery) {
        return getDozerMapper().map(productRepository.findByQuery(productQuery), ProductInfo.class);
    }
}
