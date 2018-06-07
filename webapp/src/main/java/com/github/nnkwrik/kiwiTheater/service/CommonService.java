package com.github.nnkwrik.kiwiTheater.service;


import com.github.nnkwrik.kiwiTheater.model.User;
import com.github.nnkwrik.kiwiTheater.model.Vo;
import com.github.nnkwrik.kiwiTheater.model.json.Gift;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CommonService {

    List<Vo> getLiveListFromMap(Map<String, String> liveMap);

    List<Vo> getLiveListByKeys(Map<String, String> liveMap,List<String> keywords);

    List<Vo> getLiveListByCate(Map<String, String> liveMap,Integer category);

    String uploadPic(String dir, MultipartFile pic) throws IOException;

    void charge(User user);

    User sendGift(User self, Gift gift);
}
