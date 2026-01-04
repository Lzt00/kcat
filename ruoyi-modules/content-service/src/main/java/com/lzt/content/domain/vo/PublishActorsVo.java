package com.lzt.content.domain.vo;

import lombok.Data;

/**
 * @author leifengyang
 * @version 1.0
 * @date 2025/9/5 14:48
 * @description:
 */
@Data
public class PublishActorsVo {
    private Long actorId;
    private String actorName;
    private String roleName;
    private Integer roleType;
    private Boolean isNewActor;
    private Integer sortOrder;
}
