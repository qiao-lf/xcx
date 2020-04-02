package henu.xmh.service;

import henu.xmh.pojo.CfUser;

import java.util.List;

public interface CfUserAboutGuruService {

    void aboutGuru(String userId,String guruId);//用户关注上师

    List<CfUser> recommendCfUser(String userId);//推荐用户
}
