package cn.evan.ssm.dao;

import cn.evan.ssm.model.Items;

public interface ItemsMapper {
    int deleteByPrimaryKey(Integer itemsId);

    int insert(Items record);

    int insertSelective(Items record);

    Items selectByPrimaryKey(Integer itemsId);

    int updateByPrimaryKeySelective(Items record);

    int updateByPrimaryKey(Items record);
}