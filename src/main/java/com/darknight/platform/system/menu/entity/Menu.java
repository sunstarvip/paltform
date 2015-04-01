package com.darknight.platform.system.menu.entity;

import com.darknight.core.base.entity.DefaultEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * Platform平台系统菜单对象
 * Created by DarKnight on 2015/3/17.
 */
@Entity
@DynamicInsert()
@DynamicUpdate()
@Table(name = "t_platform_menu")
public class Menu extends DefaultEntity {
    private String name;  // 菜单名称
    private String type;  // 菜单类型

    private Menu parent;  // 父级菜单
    private List<Menu> children;  // 子级菜单

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent")
    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
