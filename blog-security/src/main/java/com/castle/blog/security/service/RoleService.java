package com.castle.blog.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.castle.blog.security.entity.Role;

import java.util.List;

/**
 * @author YuLong
 * Date: 2023/3/6 17:44
 * @Classname RoleService
 * @Description 角色业务接口
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据 用户 id 查找对应角色列表
     *
     * @param userId 用户 id
     * @return 角色列表
     */
    List<String> queryRolesByUserId(Long userId);

    /**
     * 保存角色
     *
     * @param role 角色
     */
    void saveRole(Role role);


}
