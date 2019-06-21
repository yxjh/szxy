package com.xuwei.index.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuwei.entity.LyEntity;
import com.xuwei.entity.SearchEntity;
import com.xuwei.index.dao.IndexDao;
import com.xuwei.index.service.IndexService;
import com.xuwei.util.CommUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 许伟 on 2018/3/15 0015.
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexDao indexMapper;
    @Override
    public PageInfo findAll(SearchEntity searchEntity) {
        PageHelper.startPage(searchEntity.getPageIndex(),searchEntity.getPageSize());
        List<Map>list=indexMapper.findAll(searchEntity);
        PageInfo result=new PageInfo(list);
        return result;
    }

    @Override
    public List<Map> photoList() {
        return indexMapper.photoList();
    }

    @Override
    public List<Map> titleList() {
        return indexMapper.titleList();
    }

    @Override
    public List<Map> complete() {
        return indexMapper.complete();
    }

    @Override
    public Map totalPercent(String weekTime) {
        int j=1;
        List<Map>list=indexMapper.totalPercent(weekTime);
        Set allList = new HashSet<>();
        List<Map> list1 = null;
        Map result = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            Map data = list.get(i);
            if (allList.add(data.get("scsj"))) {
                list1 = new ArrayList<>();
                result.put("day_"+j, list1);
                list1.add(data);
                j++;
            } else {
                list1.add(data);
            }
        }
        return result;
    }

    @Override
    public Map findByXh(int user_xh,int xh) {
        return indexMapper.findByXh(user_xh,xh);
    }

    @Override
    public int addLy(LyEntity lyEntity) {
        lyEntity.setLysj(CommUtils.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"));
        indexMapper.addLy(lyEntity);
        indexMapper.updateState(lyEntity.getWp_xh());
        return 1;
    }


}
