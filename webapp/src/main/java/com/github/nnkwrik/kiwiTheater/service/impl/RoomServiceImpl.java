package com.github.nnkwrik.kiwiTheater.service.impl;

import com.github.nnkwrik.kiwiTheater.dao.RoomMapper;
import com.github.nnkwrik.kiwiTheater.dao.UserMapper;
import com.github.nnkwrik.kiwiTheater.dao.CommonMapper;
import com.github.nnkwrik.kiwiTheater.model.Room;
import com.github.nnkwrik.kiwiTheater.model.Vo;
import com.github.nnkwrik.kiwiTheater.redis.RedisHandler;
import com.github.nnkwrik.kiwiTheater.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private RedisHandler redisHandler;

    public Vo showDetailRoomInfo(Integer roomid){
        Vo vo = new Vo();
        //TODO 应该封装成一个mapper
        Room room = roomMapper.findRoomById(roomid);
        vo.setRoom(room);
        vo.setUser(userMapper.findSimpleUserById(room.getAnchorid()));

        //TODO 从redis取viewer,需要事务
        String viewer = redisHandler.getLiveViewers(roomid+"");
        vo.setViewer(Integer.parseInt(viewer));
        return vo;
    }

    public Room findRoomById(Integer roomid) {

        return roomMapper.findRoomById(roomid);
    }

    public void editRoomInfo(Room room) {

        roomMapper.updateRoomInfo(room);
    }

    public Room createRoom(Room room) {

        //插入并注入roomid
        roomMapper.createRoom(room);

        //update user table
        userMapper.registerAnchor(room);

        return room;

    }


}
