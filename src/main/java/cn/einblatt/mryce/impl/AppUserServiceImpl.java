package cn.einblatt.mryce.impl;

import cn.einblatt.mryce.commons.BaseCrudServiceImpl;
import cn.einblatt.mryce.mapper.AppUserMapper;
import cn.einblatt.mryce.pojo.AppUser;
import cn.einblatt.mryce.service.AppUserService;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl extends BaseCrudServiceImpl<AppUser, AppUserMapper> implements AppUserService {
}