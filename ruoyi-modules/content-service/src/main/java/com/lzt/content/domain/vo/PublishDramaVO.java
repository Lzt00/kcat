package com.lzt.content.domain.vo;

import com.lzt.content.domain.bo.EpisodesBo;
import lombok.Data;

import java.util.List;

@Data
public class PublishDramaVO {
    private DramasVo drama;
    private List<Long> categories;
    private List<Long> tags;
    private List<PublishActorsVo> actors;
    private List<EpisodesBo> episodes;

}
