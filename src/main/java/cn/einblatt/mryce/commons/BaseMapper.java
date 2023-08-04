package cn.einblatt.mryce.commons;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.ids.DeleteByIdsMapper;

public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, DeleteByIdsMapper<T>, BatchUpdateMapper<T>{
}
