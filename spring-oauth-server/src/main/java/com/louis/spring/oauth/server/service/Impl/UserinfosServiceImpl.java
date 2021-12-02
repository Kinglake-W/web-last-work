package com.louis.spring.oauth.server.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.spring.oauth.server.mapper.UserinfosMapper;
import com.louis.spring.oauth.server.model.Userinfos;
import com.louis.spring.oauth.server.model.Users;
import com.louis.spring.oauth.server.service.IUserinfosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserinfosServiceImpl extends ServiceImpl<UserinfosMapper, Userinfos> implements IUserinfosService {
    @Autowired
    private UserinfosMapper userinfosMapper;

    @Override
    public List<Userinfos> getalluserinfo() {
        QueryWrapper<Userinfos> queryWrapper = new QueryWrapper<>();
        List<Userinfos> usersList =  userinfosMapper.selectList(queryWrapper);
        return usersList;
    }

    @Override
    public String addoneuserinfo(Userinfos userinfos) {
        userinfosMapper.insert(userinfos);
        return "success";
    }

    @Override
    public String updateoneuserinfo(Userinfos userinfos) {
        userinfosMapper.updateById(userinfos);
        return "对id="+userinfos.getId()+"进行更新操作了";
    }

    @Override
    public String deleteoneuserinfo(Userinfos userinfos) {
        userinfosMapper.deleteById(userinfos.getId());
        System.out.println("对id="+userinfos.getId()+"进行删除操作了");
        return "对id="+userinfos.getId()+"进行删除操作了";
    }


}
