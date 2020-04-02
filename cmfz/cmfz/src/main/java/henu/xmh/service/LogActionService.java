package henu.xmh.service;

import henu.xmh.pojo.LogAction;

import java.util.List;

public interface LogActionService {

    List<LogAction> findAllForPage(Integer beginValue, Integer pageSize);

    void add(LogAction logAction);
}
