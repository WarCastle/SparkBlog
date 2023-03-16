package com.castle.blog.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.castle.blog.security.entity.Menu;
import com.castle.blog.security.mapper.MenuMapper;
import com.castle.blog.security.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * @author YuLong
 * Date: 2023/3/6 17:44
 * @Classname MenuServiceImpl
 * @Description 权限菜单业务接口实现类
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {

}




