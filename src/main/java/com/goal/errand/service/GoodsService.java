package com.goal.errand.service;

import com.goal.errand.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.goal.errand.req.GoodsReq;
import com.goal.errand.resp.RestResp;
import com.goal.errand.vo.GoodsVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author Goal
 * @since 2022-08-10
 */
public interface GoodsService {

    /**
     * 图片批量上传
     *
     * @param images
     * @return
     */
    public RestResp<List<String>> imagesUpload(MultipartFile[] images);

    /**
     * 图片上传
     *
     * @param image
     * @return
     */
    public RestResp<String> imageUpload(MultipartFile image);

    /**
     * 商品下单（新增物品）
     * @param token
     * @param goods
     * @return
     */
    public Integer goodsOrder(String token,GoodsReq goods);

    /**
     * 商品分页列表
     * @param page
     * @param size
     * @return
     */
    public List<GoodsVo> goodsList(Integer page,Integer size);

}
