package henu.xmh.service.impl;

import henu.xmh.dao.LogActionMapper;
import henu.xmh.pojo.LogAction;
import henu.xmh.service.LogActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class LogActionServiceImpl implements LogActionService {
    @Autowired
    private LogActionMapper logActionMapper;

    @Override
    public List<LogAction> findAllForPage(Integer beginValue, Integer pageSize) {
        List<LogAction> logActions = logActionMapper.selectAll();
        return logActions;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void add(LogAction logAction) {
        logActionMapper.insertSelective(logAction);
    }
}
