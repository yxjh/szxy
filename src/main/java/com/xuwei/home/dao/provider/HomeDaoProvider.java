package com.xuwei.home.dao.provider;

import com.xuwei.entity.ProductEntity;
import com.xuwei.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;


/**
 * Created by 许伟 on 2018/3/26 0026.
 */
public class HomeDaoProvider {

    public String myTopic(Integer xh){
        StringBuilder sb=new StringBuilder();
        sb.append("select t.*,rownum from (");
        sb.append(" select t.ftnr,t.ftsj,count(*)htl,t.xh from xw_topic t");
        sb.append(" left join xw_reply r on r.topic_xh=t.xh");
        sb.append(" where t.user_xh='"+xh+"'");
        sb.append(" group by t.ftnr,t.ftsj,t.xh order by t.ftsj desc)t");
        return sb.toString();
    }

    public String myReply(Integer xh){
        StringBuilder sb=new StringBuilder();
        sb.append(" select rownum,t2.ftnr,t1.htnr,t3.name ftr,t4.name htr,t1.htsj,t2.ftsj from xw_reply t1");
        sb.append(" inner join xw_topic t2 on t1.topic_xh=t2.xh");
        sb.append(" inner join xw_user t3 on t3.xh=t2.user_xh ");
        sb.append(" inner join xw_user t4 on t4.xh=t1.user_xh");
        sb.append(" where t1.user_xh='"+xh+"'");
        return sb.toString();
    }

    public String myWp(Integer xh){
        StringBuilder sb=new StringBuilder();
        sb.append("SELECT ROWNUM,T.* FROM(");
        sb.append(" SELECT X.SCWP,X.WPMS,X.WPLX,X.YXJHWP,X.SCDD,X.SCSJ,W.NAME,");
        sb.append(" CASE X.JYZT WHEN '0' THEN '未交易' WHEN '1' THEN '交易中' WHEN '2' THEN '已交易' ELSE '其他' END JYZT ");
        sb.append(" FROM XW_SCWP X INNER JOIN XW_USER W ON W.XH=X.USER_XH WHERE X.USER_XH='"+xh+"' ORDER BY X.SCSJ DESC)T");
        return sb.toString();
    }

    public String myLyFromMe(Integer xh){
        StringBuilder sb=new StringBuilder();
        sb.append(" select rownum, t2.name lyr,t1.lynr,t1.lysj,t3.scwp,t3.yxjhwp,t4.name ftr from xw_lyb t1");
        sb.append(" inner join xw_user t2 on t1.user_xh=t2.xh ");
        sb.append(" inner join xw_scwp t3 on t3.xh=t1.wp_xh");
        sb.append(" inner join xw_user t4 on t4.xh=t3.user_xh");
        sb.append("  where t2.xh='"+xh+"'");
        return sb.toString();
    }

    public String myLyToOther(Integer xh){
        StringBuilder sb=new StringBuilder();
        sb.append(" select rownum, t2.name ftr,t1.scwp,t1.xh,t1.yxjhwp,nvl(t3.lynr,'无')lynr,nvl(t3.lysj,'无')lysj,nvl(t4.name,'无') lyr from xw_scwp t1");
        sb.append(" left join xw_user t2 on t2.xh=t1.user_xh");
        sb.append(" left join xw_lyb t3 on t3.wp_xh=t1.xh");
        sb.append(" left join xw_user t4 on t3.user_xh=t4.xh");
        sb.append(" where t1.user_xh='"+xh+"'");
        return sb.toString();
    }

    public String headNum(Integer xh){
        StringBuilder sb=new StringBuilder();
        sb.append("select * from");
        sb.append(" (select count(*)zt from xw_topic where user_xh='"+xh+"'),");
        sb.append(" (select count(*)ht from xw_reply where user_xh='"+xh+"')");
        return sb.toString();
    }

    public String editMyInfo(UserEntity userEntity){
        return new SQL(){
            {
                UPDATE("XW_USER");
                if(StringUtils.isNotBlank(userEntity.getName())){
                    SET("NAME='"+userEntity.getName()+"'");
                }
                if(StringUtils.isNotBlank(userEntity.getNc())){
                    SET("NC='"+userEntity.getNc()+"'");
                }
                if(StringUtils.isNotBlank(userEntity.getBirthday())){
                    SET("BIRTHDAY='"+userEntity.getBirthday()+"'");
                }
                if(StringUtils.isNotBlank(userEntity.getEmail())){
                    SET("EMAIL='"+userEntity.getEmail()+"'");
                }
                if(StringUtils.isNotBlank(userEntity.getUrl())){
                    SET("URL='"+userEntity.getUrl()+"'");
                }
                WHERE("XH='"+userEntity.getXh()+"'");
            }
        }.toString();
    }

    public String insert(ProductEntity productEntity){
        return new SQL(){
            {
                INSERT_INTO("XW_SCWP");
                VALUES("XH","SEQ_XH.NEXTVAL");
                if(StringUtils.isNotBlank(productEntity.getScwp())){
                    VALUES("SCWP","'"+productEntity.getScwp()+"'");
                }
                if(StringUtils.isNotBlank(productEntity.getWpms())){
                    VALUES("WPMS","'"+productEntity.getWpms()+"'");
                }
                if(StringUtils.isNotBlank(productEntity.getWplx())){
                    VALUES("WPLX","'"+productEntity.getWplx()+"'");
                }
                if(StringUtils.isNotBlank(productEntity.getYxjhwp())){
                    VALUES("YXJHWP","'"+productEntity.getYxjhwp()+"'");
                }
                if(StringUtils.isNotBlank(productEntity.getScsj())){
                    VALUES("SCSJ","'"+productEntity.getScsj()+"'");
                }
                    VALUES("USER_XH","'"+productEntity.getUser_xh()+"'");
                if(StringUtils.isNotBlank(productEntity.getScdd())){
                    VALUES("SCDD","'"+productEntity.getScdd()+"'");
                }
                if(StringUtils.isNotBlank(productEntity.getBz())){
                    VALUES("BZ","'"+productEntity.getBz()+"'");
                }
                if(StringUtils.isNotBlank(productEntity.getUrl())){
                    VALUES("URL","'"+productEntity.getUrl()+"'");
                }
            }
        }.toString();
    }
}
