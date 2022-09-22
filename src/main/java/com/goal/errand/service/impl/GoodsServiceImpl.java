package com.goal.errand.service.impl;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.goal.errand.entity.Goods;
import com.goal.errand.entity.GoodsImg;
import com.goal.errand.enums.AppEnums;
import com.goal.errand.mapper.GoodsImgMapper;
import com.goal.errand.mapper.GoodsMapper;
import com.goal.errand.req.GoodsReq;
import com.goal.errand.req.ImagesReq;
import com.goal.errand.resp.RestResp;
import com.goal.errand.service.GoodsService;
import com.goal.errand.utils.JWTUtil;
import com.goal.errand.utils.ResultHandle;
import com.goal.errand.vo.GoodsVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author Goal
 * @since 2022-08-10
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    private static Logger log = Logger.getLogger(GoodsServiceImpl.class);

    @Value("${prefix}")
    private String prefix;

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsImgMapper imgMapper;

    @Override
    public RestResp<List<String>> imagesUpload(MultipartFile[] images) {
        //        获取当前jar包所在的目录
        ApplicationHome applicationHome = new ApplicationHome(getClass());
        File homeSource = applicationHome.getSource();
//        在jar包所在目录下创建upload目录
        String uploadPath = homeSource.getParent() + "/upload";
        File uploadDir = new File(uploadPath);
//        目录不存在创建
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        ArrayList<String> imagePathList = new ArrayList<>();
        for (MultipartFile image : images) {
            if (!image.isEmpty()) {
                String imagePath = uploadPath + "/" + image.getOriginalFilename();
                File imageFile = new File(imagePath);
                try {
                    image.transferTo(imageFile);
                    imagePathList.add(imagePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                ResultHandle<List<String>> resultHandle = new ResultHandle<>();
                return resultHandle.resultHandle(AppEnums.FILE_ERROR.getCode(), AppEnums.FILE_ERROR.getMsg(), null);
            }
        }
        return new ResultHandle<List<String>>().resultHandle(AppEnums.UPLOAD_SUCCESS.getCode(), AppEnums.UPLOAD_SUCCESS.getMsg(), imagePathList);
    }

    @Override
    public RestResp<String> imageUpload(MultipartFile image) {
        //        获取当前jar包所在的目录
        ApplicationHome applicationHome = new ApplicationHome(getClass());
        File homeSource = applicationHome.getSource();
//        在jar包所在目录下创建upload目录
        String uploadPath = homeSource.getParent() + "/upload";
        File uploadDir = new File(uploadPath);
//        目录不存在创建
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        String imagePath = null;
        String result = null;
        if (!image.isEmpty()) {
            imagePath = uploadPath + "/" + image.getOriginalFilename();
            result = prefix + image.getOriginalFilename();
            File imageFile = new File(imagePath);
            try {
                image.transferTo(imageFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ResultHandle<String> resultHandle = new ResultHandle<>();
            return resultHandle.resultHandle(AppEnums.FILE_ERROR.getCode(), AppEnums.FILE_ERROR.getMsg(), null);
        }
        return new ResultHandle<String>().resultHandle(AppEnums.UPLOAD_SUCCESS.getCode(), AppEnums.UPLOAD_SUCCESS.getMsg(), result);
    }

    @Override
    public Integer goodsOrder(String token ,GoodsReq goods) {
        DecodedJWT verify = JWTUtil.verify(token);
        String openId = verify.getClaim("openId").asString();
        Goods goods1 = new Goods(null, openId, goods.getName(), goods.getPhone(), goods.getDesc(),goods.getType(), null, goods.getPrice());
        Integer result1 = goodsMapper.insert(goods1);
        Integer result2 = null;
        List<ImagesReq> imagesUrlList = JSON.parseArray(goods.getImagesUrl(), ImagesReq.class);
        for (ImagesReq imagesReq : imagesUrlList) {
            log.debug(imagesReq);
            GoodsImg goodsImg = new GoodsImg();
            goodsImg.setGoodsId(goods1.getId());
            goodsImg.setPath(imagesReq.getUrl());
            result2 = imgMapper.insert(goodsImg);
            if (result2 == 0){
                return 0;
            }
        }
        return result1;
    }

    @Override
    public List<GoodsVo> goodsList(Integer page,Integer size) {
        Page<Goods> goodsPage = new Page<>(page,size);
        Page<Goods> goodsList = goodsMapper.selectPage(goodsPage, null);
        ArrayList<GoodsVo> goodsVos = new ArrayList<>();
        for (Goods goods : goodsList.getRecords()) {
            GoodsImg img = imgMapper.findOneByGoodsId(goods.getId());
            System.out.println(img);
            GoodsVo goodsVo = new GoodsVo();
            goodsVo.setGoods(goods);
            goodsVo.setGoodsImg(img);
            goodsVos.add(goodsVo);
        }
        return goodsVos;
    }
}
