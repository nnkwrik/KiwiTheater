package com.github.nnkwrik.kiwiTheater.service;

import com.github.nnkwrik.kiwiTheater.model.Room;
import com.github.nnkwrik.kiwiTheater.model.Vo;

import java.util.List;
import java.util.Map;

public interface RoomService {
    Vo showDetailRoomInfo(Integer roomid);

    Room findRoomById(Integer userid);

    void editRoomInfo(Room room);

    Room createRoom(Room room);

//    List<Vo> getLiveListFromMap();

//    List<Vo> getLiveListFromMap(Map<String,String> liveMap);

}
