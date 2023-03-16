package com.castle.blog.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.castle.blog.security.entity.Menu;

import java.util.List;

/**
 * @author Lenovo
 * @Description 针对表【sys_menu(权限菜单表)】的数据库操作Mapper
 * Date 2023-03-06 17:44:45
 * @Entity com.castle.blog.security.entity.Menu
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据 用户 id查询权限信息
     *
     * @param userId 用户 id
     * @return 权限列表
     */
    List<String> selectPermissionsByUserId(Long userId);
}




