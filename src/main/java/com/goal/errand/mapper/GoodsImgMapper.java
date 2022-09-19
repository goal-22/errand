package com.goal.errand.mapper;

import com.goal.errand.entity.GoodsImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商品图片 Mapper 接口
 * </p>
 *
 * @author Goal
 * @since 2022-08-10
 */
@Repository
public interface GoodsImgMapper extends BaseMapper<GoodsImg> {

    /**
     * 查找每个商品第一张图片作为封面
     * @param goodsId
     * @return
     */
    @Select("select * from t_goods_img group by goodsId having goodsId = #{goodsId}")
    public GoodsImg findOneByGoodsId(Integer goodsId);

}
