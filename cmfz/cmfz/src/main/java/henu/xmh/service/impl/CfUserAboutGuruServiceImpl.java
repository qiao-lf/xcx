package henu.xmh.service.impl;

import henu.xmh.dao.CfUserMapper;
import henu.xmh.pojo.CfUser;
import henu.xmh.pojo.CfUserExample;
import henu.xmh.service.CfUserAboutGuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
@Transactional(propagation =  Propagation.SUPPORTS)
public class CfUserAboutGuruServiceImpl implements CfUserAboutGuruService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CfUserMapper cfUserMapper;

    @Override
    public void aboutGuru(String userId, String guruId) {
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add(userId,guruId);//添加关注
        setOperations.add(guruId,userId);//关注用户添加
    }

    /**
     * 用户关注的上师下的粉丝推荐给该用户，
     * 如果粉丝数过多，随机推荐10位用户
     * 如果用没有关注上师，随机推荐用户
     * @param userId
     * @return
     */
    @Override
    public List<CfUser> recommendCfUser(String userId) {
        List<CfUser> cfUsers = null;
        SetOperations setOperations = redisTemplate.opsForSet();
        Set members = setOperations.members(userId);//关注的上师
        if(members.size()>0){
            Set<String> userIds = new TreeSet<>();//需要推荐的id set集合（需要转换成list集合）
            members.forEach(m->{
                Set userIdInM = setOperations.members(m);//该上师的粉丝
                userIds.addAll(userIdInM);//添加到推荐集合中
            });
            userIds.remove(userId);//将自身剔除

            //处理set集合 userIds
            List<String> ids = new ArrayList<>();//接收目标推荐用户的id（in查询用）
            if(userIds.size()>10){//如果推荐用户超过10个，只取前十个
                int i = 1;
                for (String id : userIds) {
                    if(i>10)break;
                    ids.add(id);
                    i++;
                }//取出前十个用户id
            }else {//少于10个将所有id添加到推荐id中
                userIds.forEach(u->ids.add(u));
            }
            CfUserExample example = new CfUserExample();
            example.createCriteria().andUserIdIn(ids);
            cfUsers = cfUserMapper.selectByExample(example);
        }else {//用户关注为0,随机推荐10个
            CfUserExample cfUserExample = new CfUserExample();
            cfUserExample.setBeginValue(1);
            cfUserExample.setPageSize(9);
            cfUsers = cfUserMapper.selectByExample(cfUserExample);
        }

        return cfUsers;
    }
}
