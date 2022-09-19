package com.goal.errand.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author Goal
 * @since 2022-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id
     */
    @TableField("goodsId")
    private Integer goodsId;

    /**
     * 创建时间
     */
    @TableField(value = "createTime" ,fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 付款时间
     */
    @TableField("payTime")
    private Date payTime;

    /**
     * 完成时间
     */
    @TableField(value = "finishTime")
    private Date finishTime;

    /**
     * 订单状态 0:进行中 1:已完成
     */
    private Integer status;


}
