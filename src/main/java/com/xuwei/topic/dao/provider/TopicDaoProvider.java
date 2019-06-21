package com.xuwei.topic.dao.provider;

import com.xuwei.entity.ReplyEntity;
import com.xuwei.entity.TopicEntity;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by 许伟 on 2018/3/16 0016.
 */
public class TopicDaoProvider {
    public String findAllTopic(String key){
        return new SQL(){
            {
                SELECT("u.name,t.ftnr,t.xh,t.user_xh");
                FROM("xw_topic t");
                INNER_JOIN("xw_user u on u.xh=t.user_xh");
                WHERE("T.FTNR LIKE '%"+key+"%'");
                ORDER_BY("t.ftsj desc");
            }
        }.toString();
    }

    public String findHtl(Integer xh){
        StringBuilder sb=new StringBuilder();
        sb.append(" SELECT nvl(COUNT(*),0)htl FROM XW_REPLY R");
        sb.append(" WHERE R.TOPIC_XH="+xh);
        return sb.toString();
    }

    public String findZhhtr(Integer xh){
        StringBuilder sb=new StringBuilder();
        sb.append(" select nvl(t1.name,'无')zhhtr from(select u.name,r.htsj from xw_reply r  inner join xw_user u on u.xh=r.user_xh  where r.topic_xh='"+xh+"'order by r.htsj desc)t1 where rownum<2");
        return sb.toString();
    }

    public String startTopic(TopicEntity topic){
        return new SQL(){
            {
                INSERT_INTO("XW_TOPIC");
                VALUES("XH","SEQ_XH.NEXTVAL");
                VALUES("FTNR","'"+topic.getFtnr()+"'");
                VALUES("FTSJ","'"+topic.getFtsj()+"'");
                VALUES("USER_XH","'"+topic.getUser_xh()+"'");
            }
        }.toString();
    }

    public String reply(ReplyEntity reply){
        return new SQL(){
            {
                INSERT_INTO("XW_REPLY");
                VALUES("XH","SEQ_XH.NEXTVAL");
                VALUES("HTNR","'"+reply.getHtnr()+"'");
                VALUES("HTSJ","'"+reply.getHtsj()+"'");
                VALUES("USER_XH","'"+reply.getUser_xh()+"'");
                VALUES("TOPIC_XH","'"+reply.getTopic_xh()+"'");
            }
        }.toString();
    }

    public String num(String yesterday,String today){
        StringBuilder sb=new StringBuilder();
        sb.append(" select * from(select sum(today)today from (select count(*)today from xw_topic where ftsj>='"+today+"'");
        sb.append(" union all");
        sb.append(" select count(*)today from xw_reply where htsj>='"+today+"')),");
        sb.append(" (select sum(yesterday)yesterday from(select count(*)yesterday from xw_topic where ftsj>='"+yesterday+"' and ftsj<'"+today+"'");
        sb.append(" union all");
        sb.append(" select count(*)yesterday from xw_reply where htsj>='"+yesterday+"' and htsj<'"+today+"')),");
        sb.append("  ( select sum(total)total from(select count(*)total from xw_topic ");
        sb.append(" union all");
        sb.append("  select count(*)total from xw_reply))");
        return sb.toString();
    }
}
