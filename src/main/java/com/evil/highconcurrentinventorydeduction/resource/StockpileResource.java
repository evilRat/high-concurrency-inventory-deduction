package com.evil.highconcurrentinventorydeduction.resource;

import com.evil.highconcurrentinventorydeduction.application.StockpileApplicationService;
import com.evil.highconcurrentinventorydeduction.domain.DeliveredStatus;
import com.evil.highconcurrentinventorydeduction.infrastructure.CommonResponse;
import com.evil.highconcurrentinventorydeduction.infrastructure.Response;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 库存相关的资源
 *
 * @author icyfenix@gmail.com
 * @date 2020/4/19 21:40
 **/

@RequestMapping("/products")
@RestController
@CacheConfig(cacheNames = "resource.product")
public class StockpileResource {

    @Resource
    private StockpileApplicationService service;

    /**
     * 将指定的产品库存调整为指定数额
     */
    @PatchMapping("/stockpile/{productId}")
    public Response updateStockpile(@PathVariable("productId") Integer productId, @RequestParam("amount") Integer amount) {
        return CommonResponse.op(() -> service.setStockpileAmountByProductId(productId, amount));
    }

    /**
     * 根据产品查询库存
     */
    @GetMapping("/stockpile/{productId}")
    public Stockpile queryStockpile(@PathVariable("productId") Integer productId) {
        return service.getStockpile(productId);
    }

    // 以下是开放给内部微服务调用的方法

    /**
     * 将指定的产品库存调整为指定数额
     */
    @PatchMapping("/stockpile/delivered/{productId}")
    public Response setDeliveredStatus(@PathVariable("productId") Integer productId, @RequestParam("status") DeliveredStatus status, @QueryParam("amount") Integer amount) {
        return CommonResponse.op(() -> service.setDeliveredStatus(productId, status, amount));
    }
}
