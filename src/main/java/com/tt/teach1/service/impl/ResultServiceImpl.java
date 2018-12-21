package com.tt.teach1.service.impl;

import com.tt.teach1.dao.ResultDao;
import com.tt.teach1.pojo.Result;
import com.tt.teach1.pojo.Subject;
import com.tt.teach1.service.ResultService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    @Resource
    private ResultDao resultDao;

    public List<Result> getResultList() {
        return resultDao.getResultList();
    }

    //增删改都加事务
    @Transactional
    public int deleteResult(Integer resultNo) {
        return resultDao.deleteResult(resultNo);
    }

    @Transactional
    public int updateResult(Result result) {
        return resultDao.updateResult(result);
    }

    @Transactional
    public int addResult(Result result) {
        return resultDao.addResult(result);
    }

    public List<Subject> getSubject() {
        return resultDao.getSubject();
    }


}
