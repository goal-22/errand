package com.goal.errand.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：Goal
 * @date ：Created in 2022/8/16 17:52
 * @description：图片路径
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagesReq implements Serializable {
    private String url;
}
