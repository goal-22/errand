package com.goal.errand.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入数据时自动填充创建时间
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
//        this.strictInsertFill(metaObject, "createTime", String.class, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    /**
     * 修改数据时填充更新时间
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
//        this.strictUpdateFill(metaObject, "updateTime", String.class, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
