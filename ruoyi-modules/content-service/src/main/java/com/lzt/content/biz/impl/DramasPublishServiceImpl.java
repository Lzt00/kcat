package com.lzt.content.biz.impl;

import com.lzt.content.biz.DramasPublishService;
import com.lzt.content.domain.*;
import com.lzt.content.domain.bo.EpisodesBo;
import com.lzt.content.domain.vo.PublishActorsVo;
import com.lzt.content.domain.vo.PublishDramaVO;
import com.lzt.content.mapper.ActorsMapper;
import com.lzt.content.mapper.DramasMapper;
import com.lzt.content.mapper.EpisodesMapper;
import com.lzt.content.service.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DramasPublishServiceImpl implements DramasPublishService {

    @Autowired
    DramasMapper dramasMapper;

    @Autowired
    DramaCategoriesService categoriesService;

    @Autowired
    DramaTagsService dramaTagsService;

    @Autowired
    ActorsMapper actorsMapper;

    @Autowired
    DramaActorsService dramaActorsService;

    @Autowired
    IEpisodesService episodesService;


    @Override
    @Transactional
    public Long dramasPublish(PublishDramaVO dramaPublishVo) {
        //1. 保存短剧
        Dramas dramas = new Dramas();
        BeanUtils.copyProperties(dramaPublishVo.getDrama(),dramas);
        dramasMapper.insert(dramas);
        //2. 保存分类
        List<DramaCategories> dramaCategoriesList = getDramaCategories(dramaPublishVo, dramas);
        categoriesService.saveBatch(dramaCategoriesList);
        //3. 保存标签
        List<DramaTags> tagsList = getDramaTags(dramaPublishVo, dramas);
        dramaTagsService.saveBatch(tagsList);
        //4. 保存演员 新演员和已有演员
        List<PublishActorsVo> actors = dramaPublishVo.getActors();
        for(PublishActorsVo actor : actors){
            if(actor.getActorId()==null){
                //创建演员
                Actors actors1 = buildActorEntity(actor);
                actorsMapper.insert(actors1);
                //插入演员和短剧关系表
                DramaActors dramaActors = new DramaActors();
                dramaActors.setDramaId(dramas.getDramaId());
                dramaActors.setActorId(actors1.getActorId());
                dramaActors.setRoleName(actor.getRoleName());
                dramaActors.setRoleType(actor.getRoleType());
                dramaActors.setSortOrder(0);
                dramaActorsService.save(dramaActors);
            }else{
                DramaActors dramaActors = new DramaActors();
                dramaActors.setDramaId(dramas.getDramaId());
                dramaActors.setActorId(actor.getActorId());
                dramaActors.setRoleName(actor.getRoleName());
                dramaActors.setRoleType(actor.getRoleType());
                dramaActors.setSortOrder(0);
                dramaActorsService.save(dramaActors);
            }
        }
        //5、保存剧集； 需要填充关联的短剧id
        List<EpisodesBo> episodes = dramaPublishVo.getEpisodes();
        for (EpisodesBo episode : episodes) {
            //每集设置短剧id
            episode.setDramaId(dramas.getDramaId());
            //保存到数据库
            episodesService.insertByBo(episode);
        }

        return dramas.getDramaId();
    }



    @NotNull
    private static Actors buildActorEntity(PublishActorsVo actor) {
        Actors actorsEntity = new Actors();
        actorsEntity.setActorName(actor.getActorName());
        actorsEntity.setActorImg("");
        actorsEntity.setActorInfo("");
        actorsEntity.setHeight(0L);
        actorsEntity.setWeight(0L);
        actorsEntity.setConstellation("");
        actorsEntity.setNationality("");
        actorsEntity.setDramaCount(0L);
        actorsEntity.setFansCount(0L);
        actorsEntity.setIsHot(0L);
        actorsEntity.setStatus(0L);
        actorsEntity.setSearchValue("");
        actorsEntity.setCreateDept(0L);
        actorsEntity.setCreateBy(0L);
        actorsEntity.setCreateTime(new Date());
        actorsEntity.setUpdateBy(0L);
        actorsEntity.setUpdateTime(new Date());
        return actorsEntity;
    }



    private  static List<DramaTags> getDramaTags(PublishDramaVO dramaPublishVo, Dramas dramas) {
        List<Long> tags = dramaPublishVo.getTags();
        List<DramaTags> tagsList = tags.stream().map(value -> {
            DramaTags dramaTags = new DramaTags();
            dramaTags.setTagId(value);
            dramaTags.setDramaId(dramas.getDramaId());
            return dramaTags;
        }).toList();
        return tagsList;
    }

    private  static List<DramaCategories> getDramaCategories(PublishDramaVO dramaPublishVo, Dramas dramas) {
        //获取分类id
        List<Long> categories = dramaPublishVo.getCategories();
        //组织数据
        List<DramaCategories> dramaCategoriesList = categories.stream().map(value -> {
            DramaCategories dramaCategories = new DramaCategories();
            dramaCategories.setCategoryId(value);
            dramaCategories.setDramaId(dramas.getDramaId());
            return dramaCategories;
        }).toList();
        return dramaCategoriesList;
    }
}
