package com.qiao.service;

import com.qiao.dao.AdminDao;
import com.qiao.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AdminServiceImpl implements AdminServicce{
    @Autowired
    private AdminDao adminDao;

    //事务管理
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Admin login(Admin admin) {
      return adminDao.selectOne(admin);
    }
}
