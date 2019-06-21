package com.xuwei.index.dao.provider;

import com.xuwei.entity.LyEntity;
import com.xuwei.entity.SearchEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by 许伟 on 2018/3/15 0015.
 */
public class IndexDaoProvider {

    public String findAll(SearchEntity searchEntity){
        String string= new SQL(){
            {
                SELECT("W.XH WP_XH,U.NAME,W.USER_XH,W.SCWP,W.YXJHWP,W.WPLX");
                FROM("XW_SCWP W");
                INNER_JOIN("XW_USER U ON U.XH=W.USER_XH");
                if(StringUtils.isNotBlank(searchEntity.getWplx())){
                    WHERE("W.WPLX='"+searchEntity.getWplx()+"'");
                }
                if(StringUtils.isNotBlank(searchEntity.getKeys())){
                    WHERE("W.SCWP LIKE '%"+searchEntity.getKeys()+"%' OR" +
                            " W.WPMS LIKE '%"+searchEntity.getKeys()+"%' OR" +
                            " W.YXJHWP LIKE '%"+searchEntity.getKeys()+"%'");
                }
                if(StringUtils.isNotBlank(searchEntity.getBeginTime())){
                    WHERE("W.SCSJ>='"+searchEntity.getBeginTime()+"'");
                }
                if(StringUtils.isNotBlank(searchEntity.getEndTime())){
                    WHERE("W.SCSJ<='"+searchEntity.getEndTime()+"'");
                }
                ORDER_BY("W.SCSJ DESC");
            }
        }.toString();
        return string;
    }

    public String complete(){
        StringBuilder sql=new StringBuilder();
        sql.append(
                " SELECT COUNT(*) AMOUNT, '未交易' as state FROM XW_SCWP WHERE JYZT=0 GROUP BY JYZT" +
                " union all" +
                " SELECT COUNT(*) AMOUNT, '交易中' as state FROM XW_SCWP WHERE JYZT=1 " +
                " union all" +
                " SELECT COUNT(*) AMOUNT, '交易完' as state FROM XW_SCWP WHERE JYZT=2 ");
        return sql.toString();
    }

    public String totalPercent(String weekTime){
        StringBuilder sb=new StringBuilder();
        sb.append(" SELECT NVL(COUNT(*),0)AMOUNT,WPLX,SUBSTR(SCSJ,0,10)SCSJ FROM XW_SCWP");
        sb.append(" WHERE SCSJ>='"+weekTime+"' GROUP BY SUBSTR(SCSJ,0,10),WPLX ORDER BY SCSJ");
        return sb.toString();
    }

    public String findByXh(int user_xh,int xh){
        return new SQL(){
            {
                SELECT("W.SCWP,W.WPMS,W.WPLX,W.YXJHWP,SUBSTR(W.SCSJ,0,10)SCSJ,W.URL,W.SCDD,U.NAME,W.XH WP_XH,U.XH USER_XH");
                SELECT("CASE W.JYZT WHEN '0' then '未交易' when '1' then '交易中' when '2' then '交易完' END AS JYZT");
                FROM("XW_SCWP W");
                INNER_JOIN("XW_USER U ON U.XH=W.USER_XH");
                WHERE("W.XH='"+xh+"' AND U.XH='"+user_xh+"'");
            }
        }.toString();
    }

    public String addLy(LyEntity lyEntity){
        return new SQL(){
            {
                INSERT_INTO("XW_LYB");
                VALUES("XH","SEQ_XH.NEXTVAL");
                VALUES("USER_XH","'"+lyEntity.getUser_xh()+"'");
                VALUES("WP_XH","'"+lyEntity.getWp_xh()+"'");
                VALUES("LYSJ","'"+lyEntity.getLysj()+"'");
                if(StringUtils.isNotBlank(lyEntity.getLynr())){
                    VALUES("LYNR","'"+lyEntity.getLynr()+"'");
                }
            }
        }.toString();
    }
}
