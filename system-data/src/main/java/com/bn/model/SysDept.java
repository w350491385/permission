package com.bn.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class SysDept extends BaseModel {

    private String name;

    private Long parentId;

    private Integer orderNum;

    private Byte delFlag;
    @TableField(exist = false)
    private List<SysDept> children;

    @TableField(exist = false)
    private String parentName;
    @TableField(exist = false)
    private Integer level;

}
