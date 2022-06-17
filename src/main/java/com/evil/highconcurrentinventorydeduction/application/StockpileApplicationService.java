package com.evil.highconcurrentinventorydeduction.application;

import com.evil.highconcurrentinventorydeduction.domain.DeliveredStatus;
import com.evil.highconcurrentinventorydeduction.service.StockpileService;
import org.springframework.stereotype.Component;
import com.evil.highconcurrentinventorydeduction.domain.Stockpile;
import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * 商品库存的领域服务
 *
 * @author icyfenix@gmail.com
 * @date 2020/4/19 21:42
 **/
@Component
@Transactional
public class StockpileApplicationService {

    @Resource
    private StockpileService stockpileService;

    /**
     * 根据产品查询库存
     */
    public Stockpile getStockpile(Integer productId) {
        return stockpileService.getByProductId(productId);
    }

    /**
     * 将指定的产品库存调整为指定数额
     */
    public void setStockpileAmountByProductId(Integer productId, Integer amount) {
        stockpileService.set(productId, amount);
    }

    /**
     * 调整商品出库状态
     */
    public void setDeliveredStatus(Integer productId, DeliveredStatus status, Integer amount) {
        switch (status) {
            case DECREASE:
                stockpileService.decrease(productId, amount);
                break;
            case INCREASE:
                stockpileService.increase(productId, amount);
                break;
            case FROZEN:
                stockpileService.frozen(productId, amount);
                break;
            case THAWED:
                stockpileService.thawed(productId, amount);
                break;
        }
    }

    public void decrease(Integer productId, Integer amount) {
        stockpileService.decrease(productId, amount);
    }

}
