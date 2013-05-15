package com.adeoservices.backend.test.repository;

import com.adeoservices.backend.domain.BU;
import com.adeoservices.backend.repository.BURepository;
import com.adeoservices.backend.test.config.spring.BackendTestConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: bgr
 * Date: 15/05/13
 * Time: 15:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(classes = { BackendTestConfiguration.class })
@Transactional
public class BURepositoryTest {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private BURepository buRepository;

    /** Constants. */
    private static final String LABEL1 = "BU1";

    @Before
    public void initDatas(){

        //test init liquibase

        assertEquals(2, buRepository.count());
    }

    @Test
    public void shouldSaveAnItem(){

        //given
        final BU bu1 = new BU();
        bu1.name = LABEL1;
        buRepository.save(bu1);

        //when
        final List<BU> result = buRepository.findAll();

        //then
        assertNotNull(result);
        assertEquals(3, result.size());
    }

}