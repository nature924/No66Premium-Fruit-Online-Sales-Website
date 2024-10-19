package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ShuiguoCollectionEntity;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 水果收藏 服务类
 */
public interface ShuiguoCollectionService extends IService<ShuiguoCollectionEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}