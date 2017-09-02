package pers.platform.common.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import pers.platform.common.service.IdService;

@Service("idService")
public class IdServiceImpl implements IdService {

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
    }

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
