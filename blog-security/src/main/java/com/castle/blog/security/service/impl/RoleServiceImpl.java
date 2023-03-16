package com.castle.blog.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.castle.blog.security.entity.Role;
import com.castle.blog.security.mapper.RoleMapper;
import com.castle.blog.security.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YuLong
 * Date: 2023/3/6 17:44
 * @Classname RoleServiceImpl
 * @Description 角色业务接口实现类
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 根据 用户 id 查找对应角色列表
     *
     * @param userId 用户 id
     * @return 角色列表
     */
    @Override
    public List<String> queryRolesByUserId(Long userId) {
        return roleMapper.selectRolesByUserId(userId);
    }

    /**
     * 保存角色
     *
     * @param role 角色
     */
    @Override
    public void saveRole(Role role) {
        roleMapper.insertRole(role);
    }
}




