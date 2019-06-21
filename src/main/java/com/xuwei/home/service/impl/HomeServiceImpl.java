package com.xuwei.home.service.impl;

import com.github.pagehelper.PageHelper;
import com.xuwei.config.ImgConfig;
import com.xuwei.entity.ProductEntity;
import com.xuwei.entity.UserEntity;
import com.xuwei.home.dao.HomeDao;
import com.xuwei.home.service.HomeService;
import com.xuwei.topic.dao.TopicDao;
import com.xuwei.util.CommUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.MediaSize;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by 许伟 on 2018/3/26 0026.
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private HomeDao homeDao;

    @Autowired
    private TopicDao topicDao;

    @Override
    public int upload(MultipartFile file, ProductEntity productEntity) {
        if(file!=null){
            String name=file.getOriginalFilename();
            productEntity.setUrl(ImgConfig.getUrl()+"product/"+name);
            download(file,"true");
        }
        try{
            productEntity.setScsj(CommUtils.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"));
            homeDao.insert(productEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Map> myWp(int xh) {
        return homeDao.myWp(xh);
    }

    @Override
    public Map myInfo(int xh) {
        return homeDao.myInfo(xh);
    }

    @Override
    public int editMyInfo( MultipartFile file,UserEntity userEntity) {
        if(file!=null){
            String name=file.getOriginalFilename();
            userEntity.setUrl(ImgConfig.getUrl()+"user/"+name);
            download(file,"false");
        }
        homeDao.editMyInfo(userEntity);
        return 1;
    }

    @Override
    public List<Map> myLyFromMe(int xh) {
        return homeDao.myLyFromMe(xh);
    }

    @Override
    public List<Map> myLyToOther(int xh) {
        return homeDao.myLyToOther(xh);
    }

    @Override
    public List<Map> myTopic(int xh) {
        List<Map>list=homeDao.myTopic(xh);
        for(int i=0;i<list.size();i++){
            Map map=list.get(i);
            BigDecimal topic_xh1= (BigDecimal) map.get("xh");
            int topic_xh=topic_xh1.intValue();
            Map m2=topicDao.findZhhtr(topic_xh);
            if(m2!=null){
                map.put("zhhtr",m2.get("zhhtr"));
            }else{
                map.put("zhhtr","无");
            }
        }
        return list;
    }

    @Override
    public List<Map> myReply(int xh) {
        return homeDao.myReply(xh);
    }

    @Override
    public Map headNum(Integer xh) {
        return homeDao.headNum(xh);
    }

    @Override
    public String excelMyWp(int xh,HttpServletResponse response) {
        List<Map>list=homeDao.myWp(xh);
        String name= (String) list.get(0).get("name");
        name+="上传的闲置物品";
        final int maxNumPreSheet = 10;
        HSSFWorkbook wb = new HSSFWorkbook();
        int size = list != null ? list.size() : 0;
        //当size为65535时,刚好占满一个sheet
        int sheetNum = size % maxNumPreSheet == 0 ? size / maxNumPreSheet : size / maxNumPreSheet + 1;
        //单元格样式
        for (int j = 0; j < sheetNum; j++) {
            //创建一个excel标签页
            HSSFSheet sheet = wb.createSheet("sheet" + (j + 1));
            //这里createRow的参数范围为[0,65535]
            HSSFRow row0 = sheet.createRow(0);

            HSSFCell cell00 = row0.createCell(0);
            cell00.setCellValue("物品序号");
            //sheet.setColumnWidth(0, 7000);

            HSSFCell cell01 = row0.createCell(1);
            cell01.setCellValue("闲置物品");
            //sheet.setColumnWidth(1, 7000);

            HSSFCell cell02 = row0.createCell(2);
            cell02.setCellValue("物品描述");
            //sheet.setColumnWidth(2, 7000);

            HSSFCell cell03 = row0.createCell(3);
            cell03.setCellValue("物品类型");
            //sheet.setColumnWidth(3, 7000);

            HSSFCell cell04 = row0.createCell(4);
            cell04.setCellValue("意向交换物品");
            //sheet.setColumnWidth(4, 7000);

            HSSFCell cell05 = row0.createCell(5);
            cell05.setCellValue("上传地点");
            //sheet.setColumnWidth(5, 7000);

            HSSFCell cell06 = row0.createCell(6);
            cell06.setCellValue("上传时间");
            sheet.setColumnWidth(6, 7000);

            int offset = j * maxNumPreSheet;
            int rowNum = size - offset;

            for (int i = 0; i < rowNum && i < maxNumPreSheet; i++) {
                HSSFRow row = sheet.createRow(i + 1);
                Map<String, Object> map = list.get(offset + i);

                HSSFCell cell = row.createCell(0);
                cell.setCellValue(CommUtils.nullDeal(map.get("rownum")));

                HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(CommUtils.nullDeal(map.get("scwp")));

                HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(CommUtils.nullDeal(map.get("wpms")));

                HSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(CommUtils.nullDeal(map.get("wplx")));

                HSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(CommUtils.nullDeal(map.get("yxjhwp")));

                HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(CommUtils.nullDeal(map.get("scdd")));

                HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(CommUtils.nullDeal(map.get("scsj")));
            }

        }
        ServletOutputStream outStream = null;
        try {
            response.reset();
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(name.getBytes("gb2312"), "ISO-8859-1")
                    + ".xls");
            outStream = response.getOutputStream();
            wb.write(outStream);
        } catch (Exception e) {
           // LOGGER.error("巡检结果导出EXCEL失败", e);
        } finally {
        }
        return "导出EXCEL成功！！！";
    }

    @Override
    public int updateState(int topic_xh) {
        homeDao.updateState(topic_xh);
        return 1;
    }

    /**
    * @Description: 图片下载到服务器中
    * @author: 许伟
    * @date: 2018/3/28 0028 9:57
    */
    private void download(MultipartFile file,String string) {
            FileOutputStream fos=null;
        String name=file.getOriginalFilename();
        String path=null;
        if(Objects.equals(string,"true")){
            path=ImgConfig.getPath()+"product/"+ name;
        }else{
            path=ImgConfig.getPath()+"user/"+name;
        }
        File file1=new File(path);
        if(!file1.getParentFile().exists()){
            file1.getParentFile().mkdirs();
        }
        try {
            fos=new FileOutputStream(file1);
            fos.write(file.getBytes());
            fos.flush();
        }catch (Exception e){
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
