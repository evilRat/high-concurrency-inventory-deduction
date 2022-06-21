/*
 * Copyright 2012-2020. the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. More information from:
 *
 *        https://github.com/fenixsoft
 */

package com.evil.highconcurrentinventorydeduction.resource;

import com.evil.highconcurrentinventorydeduction.application.ProductApplicationService;
import com.evil.highconcurrentinventorydeduction.domain.Product;
import com.evil.highconcurrentinventorydeduction.infrastructure.CommonResponse;
import com.evil.highconcurrentinventorydeduction.infrastructure.Response;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 产品相关的资源
 *
 * @author icyfenix@gmail.com
 * @date 2020/3/6 20:52
 **/

@RequestMapping("/products")
@RestController
@CacheConfig(cacheNames = "resource.product")
public class ProductResource {

    @Resource
    ProductApplicationService service;

    /**
     * 获取仓库中所有的货物信息
     */
    @GetMapping
    @Cacheable(key = "'ALL_PRODUCT'")
    public Iterable<Product> getAllProducts() {
        return service.getAllProducts();
    }

    /**
     * 获取仓库中指定的货物信息
     */
    @GetMapping("/{id}")
    @Cacheable(key = "#id")
    public Product getProduct(@PathVariable("id") Integer id) {
        return service.getProduct(id);
    }

    /**
     * 更新产品信息
     */
    @PutMapping
    @Caching(evict = {
            @CacheEvict(key = "#product.id"),
            @CacheEvict(key = "'ALL_PRODUCT'")
    })
    public Response updateProduct(@Valid Product product) {
        return CommonResponse.op(() -> service.saveProduct(product));
    }

    /**
     * 创建新的产品
     */
    @PostMapping
    @Caching(evict = {
            @CacheEvict(key = "#product.id"),
            @CacheEvict(key = "'ALL_PRODUCT'")
    })
    public Product createProduct(@RequestBody @Valid Product product) {
        return service.saveProduct(product);
    }

    /**
     * 创建新的产品
     */
    @DeleteMapping("/{id}")
    @Caching(evict = {
            @CacheEvict(key = "#id"),
            @CacheEvict(key = "'ALL_PRODUCT'")
    })
    public Response removeProduct(@PathVariable("id") Integer id) {
        return CommonResponse.op(() -> service.removeProduct(id));
    }


}
