package com.example.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author JYuan
 * @create 2021-08-19 20:10
 */
@Data
@NoArgsConstructor
@ApiModel
public class Menu implements Serializable {
    private static final long serialVersionUID = -7699689859587894757L;

    private Integer id;
    @ApiModelProperty(value = "菜单名称")
    private String name;
    @ApiModelProperty(value = "按钮权限",example = "页面权限统一为page，按钮权限各不相同")
    private String code;
    private Integer pid;
    @ApiModelProperty(value = "菜单类型",allowableValues = "0,1")
    private Integer type;
    private List<Menu> children;
}
