package com.goal.errand.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Goal
 * @date ：Created in 2022/8/12 11:20
 * @description：微信登录结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "WeChatLoginVo" ,description = "微信登录结果vo")
public class WeChatLoginVo {

    @ApiModelProperty(value = "sessionKey")
    private String sessionKey;
    @ApiModelProperty(value = "openId")
    private String openId;

}


