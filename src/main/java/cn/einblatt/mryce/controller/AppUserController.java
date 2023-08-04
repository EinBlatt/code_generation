package cn.einblatt.mryce.controller;

import cn.einblatt.mryce.commons.CommonController;
import cn.einblatt.mryce.pojo.AppUser;
import cn.einblatt.mryce.service.AppUserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appUser")
@Api(tags = "AppUserController")
public class AppUserController extends CommonController<AppUserService, AppUser> {
}