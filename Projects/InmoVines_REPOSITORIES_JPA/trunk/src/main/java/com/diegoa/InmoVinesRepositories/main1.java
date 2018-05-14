package com.diegoa.InmoVinesRepositories;

import entities.inmuebles.Inmuebles;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.InmuebleRepository;

import java.util.List;

public class main1 {


    @Autowired
    static InmuebleRepository repo;

    public static void main(String[] args) {

        List<Inmuebles> inmuebles = repo.list();


        for (Inmuebles i : inmuebles){

            System.out.println(i);


        }





    }
}
