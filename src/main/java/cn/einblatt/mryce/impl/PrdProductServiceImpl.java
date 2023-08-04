package cn.einblatt.mryce.impl;

import cn.einblatt.mryce.commons.BaseCrudServiceImpl;
import cn.einblatt.mryce.mapper.PrdProductMapper;
import cn.einblatt.mryce.pojo.PrdProduct;
import cn.einblatt.mryce.service.PrdProductService;
import org.springframework.stereotype.Service;

@Service
public class PrdProductServiceImpl extends BaseCrudServiceImpl<PrdProduct, PrdProductMapper> implements PrdProductService {
}