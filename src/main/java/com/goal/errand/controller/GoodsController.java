package com.goal.errand.controller;


import com.goal.errand.annotation.Auth;
import com.goal.errand.entity.Goods;
import com.goal.errand.enums.AppEnums;
import com.goal.errand.req.GoodsReq;
import com.goal.errand.resp.RestResp;
import com.goal.errand.service.GoodsService;
import com.goal.errand.utils.ResultHandle;
import com.goal.errand.vo.GoodsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    @Transactional()
    public RestResp<String> goodsOrder(@RequestBody GoodsReq goods, HttpServletRequest request){
        String token = request.getHeader("token");
        if (goodsService.goodsOrder(token,goods) == 0){
            return new ResultHandle<String>().resultHandle(AppEnums.ORDER_FAIL.getCode(),AppEnums.ORDER_FAIL.getMsg(),null);
        }
        return new ResultHandle<String>().resultHandle(AppEnums.ORDER_SUCCESS.getCode(),AppEnums.ORDER_SUCCESS.getMsg(),null);
    }

    @ApiOperation(value = "商品列表")
    @GetMapping("/goods-list")
    public RestResp<List<GoodsVo>> goodsList(Integer page ,Integer size){
        List<GoodsVo> goods = goodsService.goodsList(page,size);
        if (goods == null){
            return new RestResp<List<GoodsVo>>(AppEnums.FAIL.getCode(),AppEnums.FAIL.getMsg(),null);
        }
        return new RestResp<List<GoodsVo>>(AppEnums.SUCCESS.getCode(),AppEnums.SUCCESS.getMsg(),goods);
    }

}

