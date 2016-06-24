package com.mycompany.jpa;

import com.mycompany.model.sys.SysUser;
import com.mycompany.service.BaseManagerTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.ldap.userdetails.Person;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;

/**
 * Created by liaoxiang on 2016/6/13.
 */
@Service
@Transactional
public class JpaTest  extends BaseManagerTestCase {
    @Autowired
    EntityManagerFactory entityManagerFactory;


    @Test
    public void save(){
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyEntityManager");
/*        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        SysUser user = new SysUser();
        user.setUsername("111111");
        em.persist(user); //持久化实体
        em.getTransaction().commit();
        em.close();
        entityManagerFactory.close();*/

    }

    @Test
    public void find(){
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyEntityManager");
        EntityManager em = entityManagerFactory.createEntityManager();
        SysUser person = em.find(SysUser.class, 16L); //类似于hibernate的get方法,没找到数据时，返回null
        System.out.println(person.getName());
        assertNotNull(person);
        em.close();
        entityManagerFactory.close();
    }


}
