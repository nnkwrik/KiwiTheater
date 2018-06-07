package com.github.nnkwrik.kiwiTheater.dao;

import com.github.nnkwrik.kiwiTheater.model.User;
import com.github.nnkwrik.kiwiTheater.model.Vo;
import com.github.nnkwrik.kiwiTheater.model.json.Charge;

import java.util.List;
import java.util.Set;

public interface CommonMapper {
    List<Vo> findSimpleVoListByRooomIds(List<Integer> roomid);

    List<Vo> findSimpleVoListByKeys(List<String> keywords);
    List<Vo> findSimpleVoListByCate(Integer category);
    void updateCoin(User user);




}
