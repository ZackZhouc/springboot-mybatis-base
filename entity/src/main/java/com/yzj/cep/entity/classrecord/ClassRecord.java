package com.yzj.cep.entity.classrecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 上课记录表
 * </p>
 *
 * @author jobob
 * @since 2019-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ClassRecord对象", description="上课记录表")
public class ClassRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程id")
    private Long courseId;

    @ApiModelProperty(value = "教师id")
    private Long teacherId;

    @ApiModelProperty(value = "学生id")
    private Long studentId;

    @ApiModelProperty(value = "上课时间")
    private LocalDateTime beginTime;

    @ApiModelProperty(value = "下课时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "当前上课的节数  (第几节)")
    private Integer currentQty;

    @ApiModelProperty(value = "剩余课程节数")
    private Integer leftQty;

    @ApiModelProperty(value = "签到 0未签到 1已签到 2请假")
    private Integer check;

    @ApiModelProperty(value = "签到时间")
    private LocalDateTime checkTime;


}
