package com.castle.blog.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.castle.blog.security.entity.Role;

import java.util.List;

/**
 * @author Lenovo
 * @Description 针对表【sys_role(角色表)】的数据库操作Mapper
 * Date 2023-03-06 17:44:45
 * @Entity com.castle.blog.security.entity.Role
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据 用户 id 查找对应角色
     *
     * @param userId 用户 id
     * @return 角色
     */
    Role selectByUserId(Long userId);

    /**
     * 根据 用户 id 查找对应角色列表
     *
     * @param userId 用户 id
     * @return 角色列表
     */
    List<String> selectRolesByUserId(Long userId);

    /**
     * 保存角色
     *
     * @param role 角色
     */
    void insertRole(Role role);

}




