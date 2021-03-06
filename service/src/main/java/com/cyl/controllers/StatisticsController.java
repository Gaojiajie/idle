package com.cyl.controllers;

import com.cyl.enums.GoodsTypeEnum;
import com.cyl.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyl on 2019-03-08.
 */
@RestController
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;


    @ResponseBody
    @GetMapping("/statistics/userAndGoodsCount")
    public ResponseEntity<?> userAndGoodsCount(){
        List list = new ArrayList();
        Long registerUsers = statisticsService.registerCount();
        Long goodsCount = statisticsService.goodsCount();
        Long count = statisticsService.isDealGoodsCount();
        list.add(registerUsers);
        list.add(goodsCount);
        list.add(count);
        return ResponseEntity.ok(list);

    }
    /**
     * 累计注册人数
     * @return
     */
    @ResponseBody
    @GetMapping("/statistics/register")
    public Long registerCount(){
        Long registerUsers = statisticsService.registerCount();
        return registerUsers;
    }

    /**
     * 累计发布商品
     * @return
     */
    @ResponseBody
    @GetMapping("/statistics/goods/count")
    public Long goodsCount() {
        Long goodsCount = statisticsService.goodsCount();
        return goodsCount;
    }

    /**
     * 成交量
     * @return
     */
    @ResponseBody
    @GetMapping("/statistics/goods/isDeal/count")
    public Long isDealGoodsCount() {
        Long count = statisticsService.isDealGoodsCount();
        return count;
    }

    /**
     * 统计类型goods数量
     * @return
     */
    @ResponseBody
    @GetMapping("/statistics/goods/type")
    public ResponseEntity<?> getGoodsCountByType() {
        List list = new ArrayList();
        for (GoodsTypeEnum goodsTypeEnum : GoodsTypeEnum.values()) {

            Long count = statisticsService.getGoodsCountByType(goodsTypeEnum.getCode()) ;
            System.out.println(goodsTypeEnum.getCode() +" "+ count);
            list.add(count);
        }
        return ResponseEntity.ok(list);
    }
}
