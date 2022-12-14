package com.goal.errand.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author Goal
 * @since 2022-08-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id",name = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者",name = "openId",required = true)
    @TableField("userId")
    private String userId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * 商品类型
     */
    private Integer type;

    /**
     * 创建时间
     */
    @TableField(value = "createTime",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 价格
     */
    private Float price;


}
