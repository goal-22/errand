package com.goal.errand.vo;

import com.goal.errand.entity.Goods;
import com.goal.errand.entity.GoodsImg;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Goal
 * @date ：Created in 2022/9/17 20:18
 * @description：GoodsVo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo {

    @ApiModelProperty(value = "商品基本信息")
    private Goods goods;
    @ApiModelProperty(value = "商品图片路径")
    private GoodsImg goodsImg;

}
