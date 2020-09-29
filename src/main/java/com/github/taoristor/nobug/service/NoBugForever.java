package com.github.taoristor.nobug.service;

import com.github.taoristor.nobug.utils.NoBugFileUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class NoBugForever {
    private static Boolean already_output = false;

    public NoBugForever(){
        List<String> definitely_no_bug = new ArrayList<String>();
        try{
            for(String ascii_path:  NoBugFileUtils.getResourcesFiles("ascii/")){
                definitely_no_bug.add(NoBugFileUtils.base64Decode(ascii_path));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Random rand = new Random();
        if(!already_output){
            System.out.print(definitely_no_bug.get(rand.nextInt(definitely_no_bug.size())));
            already_output = false;
        }
    }

}