package com.goal.errand.service.impl;

import com.goal.errand.entity.Goods;
import com.goal.errand.enums.AppEnums;
import com.goal.errand.mapper.GoodsMapper;
import com.goal.errand.resp.RestResp;
import com.goal.errand.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goal.errand.utils.ResultHandle;
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
        if (!image.isEmpty()) {
            imagePath = uploadPath + "/" + image.getOriginalFilename();
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
        return new ResultHandle<String>().resultHandle(AppEnums.UPLOAD_SUCCESS.getCode(), AppEnums.UPLOAD_SUCCESS.getMsg(), imagePath);
    }
}
