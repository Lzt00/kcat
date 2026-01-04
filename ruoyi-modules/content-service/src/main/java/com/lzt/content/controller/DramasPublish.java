package com.lzt.content.controller;

import cn.hutool.json.JSONUtil;

import com.lzt.content.biz.DramasPublishService;
import com.lzt.content.domain.vo.PublishDramaVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.domain.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Slf4j
@RequiredArgsConstructor
public class DramasPublish {

    private final DramasPublishService dramasPublish;

    @PostMapping("/publish")
    public R DramasPublish(@RequestBody PublishDramaVO dramaPublishVo){
        log.info("短剧发布：内容：{}", dramaPublishVo);
        Long dramasId = dramasPublish.dramasPublish(dramaPublishVo);
        return R.ok("success",dramasId);
    }


}
