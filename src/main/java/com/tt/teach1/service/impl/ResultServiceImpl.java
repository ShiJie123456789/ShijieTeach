package com.tt.teach1.service.impl;

import com.tt.teach1.dao.ResultDao;
import com.tt.teach1.pojo.Result;
import com.tt.teach1.service.ResultService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    @Resource
    private ResultDao resultDao;

    public List<Result> getResultList() {
        return resultDao.getResultList();
    }
}
