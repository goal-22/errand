package com.goal.errand.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Goal
 * @date ：Created in 2022/8/16 17:47
 * @description：商品请求类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GoodsReq {

    private String openId;
    private String name;
    private String desc;
    private String phone;
    private Float price;
    private Integer type;
    private String imagesUrl;

}
