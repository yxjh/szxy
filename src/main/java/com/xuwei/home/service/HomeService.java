package com.xuwei.home.service;

import com.xuwei.entity.ProductEntity;
import com.xuwei.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by 许伟 on 2018/3/26 0026.
 */
public interface HomeService {

    List<Map> myWp(int xh);

    List<Map> myTopic(int xh);

    String excelMyWp(int xh,HttpServletResponse response);

    int upload(MultipartFile file, ProductEntity productEntity);

    Map myInfo(int xh);

    List<Map> myLyFromMe(int xh);

    List<Map> myLyToOther(int xh);

    List<Map> myReply(int xh);

    Map headNum(Integer xh);

    int editMyInfo(MultipartFile file,UserEntity userEntity);

    int updateState(int topic_xh);
}
