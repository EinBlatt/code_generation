package cn.einblatt.mryce.controller;

import cn.einblatt.mryce.commons.CommonController;
import cn.einblatt.mryce.pojo.PrdProduct;
import cn.einblatt.mryce.service.PrdProductService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prdProduct")
@Api(tags = "PrdProductController")
public class PrdProductController extends CommonController<PrdProductService, PrdProduct> {
}