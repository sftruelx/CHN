package com.mycompany.dao.common.jdbc;

import com.mycompany.model.sys.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by liaoxiang on 2016/6/22.
 */

@Repository
public class JdbcTemplateImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public  int findAllUser(){
        String sql = "SELECT count(*) from dbo.his_mz_brxx";
        int list = jdbcTemplate.queryForInt(sql);
        return list;
    }
}
