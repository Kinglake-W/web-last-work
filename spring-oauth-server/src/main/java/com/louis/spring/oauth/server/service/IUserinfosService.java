package com.louis.spring.oauth.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.spring.oauth.server.model.Userinfos;

import java.util.List;

public interface IUserinfosService extends IService<Userinfos> {
    List<Userinfos> getalluserinfo();
    String addoneuserinfo(Userinfos userinfos);
    String updateoneuserinfo(Userinfos userinfos);
    String deleteoneuserinfo(Userinfos userinfos);
}
