package com.lzt.content.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lzt.content.domain.Categories;
import com.lzt.content.domain.DramaCategories;

import java.util.List;

/**
* @author 53409
* @description 针对表【drama_categories(短剧分类关联表)】的数据库操作Service
* @createDate 2025-09-05 16:23:27
*/
public interface DramaCategoriesService extends IService<DramaCategories> {

//    List<Categories> getDramasCategoryInfo(Long dramaId);
}
