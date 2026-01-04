package com.lzt.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzt.content.domain.DramaTags;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 53409
* @description 针对表【drama_tags(短剧标签关联表)】的数据库操作Mapper
* @createDate 2025-09-05 16:23:27
* @Entity com.lfy.kcat.content.domain.DramaTags
*/
public interface DramaTagsMapper extends BaseMapper<DramaTags> {

//    List<Tags> getDramaTagsByDramaId(@Param("dramaId") Long dramaId);
}




