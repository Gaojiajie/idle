package com.cyl.services;

import com.cyl.models.Subscribe;
import com.cyl.repositories.SubscribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cyl on 2019-04-29.
 */
@Service
public class SubscribeService {
    @Autowired
    private SubscribeRepository subscribeRepository;

    public List<Subscribe> getSubscribe (Integer id, String nickName) {
        if (nickName == null) {
            nickName = "";
        }
        List<Subscribe> list = subscribeRepository.getSubscribe(id, nickName);
        return list;
    }
}
