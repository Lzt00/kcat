package com.lzt.content.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzt.content.domain.Categories;
import com.lzt.content.domain.DramaCategories;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 53409
* @description 针对表【drama_categories(短剧分类关联表)】的数据库操作Mapper
* @createDate 2025-09-05 16:23:27
* @Entity com.lfy.kcat.content.domain.DramaCategories
*/
public interface DramaCategoriesMapper extends BaseMapper<DramaCategories> {

//    List<Categories> getDramasCategoryInfo(@Param("dramaId") Long dramaId);
}




