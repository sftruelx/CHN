package com.mycompany.dao.sys;

import com.mycompany.model.sys.SysUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liaoxiang on 2016/6/13.
 */
@Repository
public interface IUserDao extends PagingAndSortingRepository<SysUser, Long> {
}
