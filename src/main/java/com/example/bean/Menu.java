package com.example.bean;

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
public class Menu implements Serializable {
    private static final long serialVersionUID = -7699689859587894757L;

    private Integer id;
    private String name;
    private String code;
    private Integer pid;
    private List<Menu> children;
}
