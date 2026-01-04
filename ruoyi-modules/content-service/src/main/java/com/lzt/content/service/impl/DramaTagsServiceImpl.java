package com.lzt.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzt.content.domain.DramaTags;
import com.lzt.content.mapper.DramaTagsMapper;
import com.lzt.content.service.DramaTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 53409
* @description 针对表【drama_tags(短剧标签关联表)】的数据库操作Service实现
* @createDate 2025-09-05 16:23:27
*/
@Service
public class DramaTagsServiceImpl extends ServiceImpl<DramaTagsMapper, DramaTags>
    implements DramaTagsService {

//    @Autowired
//    DramaTagsMapper  dramaTagsMapper;
//
//    @Override
//    public List<Tags> getDramaTagsByDramaId(Long dramaId) {
//        return dramaTagsMapper.getDramaTagsByDramaId(dramaId);
//    }
}




