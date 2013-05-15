package com.adeoservices.backend.controller;

import com.adeoservices.backend.domain.BU;
import com.adeoservices.backend.repository.BURepository;
import com.sun.jersey.api.NotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("bu")
@Component
@Produces(MediaType.APPLICATION_JSON)
public class BUController {

    @Resource
    private BURepository buRepository;

    /**
     * Get the list of all BU.
     * @return {@link List<BU>}
     */
    @GET
    public List<BU> getBu(){

       return buRepository.findAll();
    }

    @GET
    @Path("{id}")
    @Transactional
    //@Secured("ROLE_USER")
    public BU getById(@PathParam("id") long buId) {
        BU bu = buRepository.findOne(buId);
        if (bu == null){
            throw new NotFoundException("Item not found");
        }
        return bu;
    }
}
