package com.goal.errand.controller;


import com.goal.errand.entity.Goods;
import com.goal.errand.enums.AppEnums;
import com.goal.errand.req.GoodsReq;
import com.goal.errand.resp.RestResp;
import com.goal.errand.service.GoodsService;
import com.goal.errand.utils.ResultHandle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author Goal
 * @since 2022-08-10
 */
@Api(value = "商品Api")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "图片批量上传接口")
    @PostMapping(value = "/images-upload-multiple")
    public RestResp<List<String>> imagesUpload(@RequestPart("images") MultipartFile[] images) {
        return goodsService.imagesUpload(images);
    }

    @ApiOperation(value = "图片上传接口")
    @PostMapping(value = "/image-upload")
    public RestResp<String> imagesUpload(@RequestPart("image") MultipartFile image) {
        return goodsService.imageUpload(image);
    }

    @ApiOperation(value = "商品下单接口")
    @PostMapping("/goods-order")
    public RestResp<String> goodsOrder(@RequestBody GoodsReq goods){
        System.out.println(goodsService.goodsOrder(goods));
        return new ResultHandle<String>().resultHandle(1001,"ok","OKK!");
    }

    @ApiOperation(value = "商品列表")
    @GetMapping("/goods-list")
    public RestResp<List<Goods>> goodsList(){
        List<Goods> goods = goodsService.goodsList();
        if (goods == null){
            return new RestResp<List<Goods>>(AppEnums.FAIL.getCode(),AppEnums.FAIL.getMsg(),null);
        }
        return new RestResp<List<Goods>>(AppEnums.SUCCESS.getCode(),AppEnums.SUCCESS.getMsg(),goods);
    }

}

