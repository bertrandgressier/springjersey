package com.adeoservices.backend.test.controller;

import com.adeoservices.backend.controller.BUController;
import com.adeoservices.backend.domain.BU;
import com.adeoservices.backend.repository.BURepository;
import com.sun.jersey.api.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: bgr
 * Date: 15/05/13
 * Time: 11:27
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = BackendTestConfiguration.class)
public class BUControllerTest {


    @Mock (name="buRepository") BURepository buRepository;
    @InjectMocks BUController buController;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldGetAllBu(){

        //given

        //when
        buController.getBu();

        //then
        verify(buRepository,times(1)).findAll();
    }

    @Test
    public void shouldGetUniqueBu(){

        //given
        BU bu = new BU();
        when(buRepository.findOne(1l)).thenReturn(bu);

        //when
        buController.getById(1);

        //then
        ArgumentCaptor<Long> buCaptor = ArgumentCaptor.forClass( Long.class );
        verify(buRepository,times(1)).findOne(buCaptor.capture());
        assertEquals(new Long(1), buCaptor.getValue());
    }

    @Test(expected = NotFoundException.class)
    public void shouldGetUniqueBuNotExists(){

        //given
        when(buRepository.findOne(1l)).thenReturn(null);

        //when
        buController.getById(1);
    }
}
