package com.github.nnkwrik.kiwiTheater.dao;

import com.github.nnkwrik.kiwiTheater.model.Room;
import com.github.nnkwrik.kiwiTheater.model.Vo;

import java.util.List;


public interface RoomMapper {

    Room findRoomById(Integer roomId);

    void updateRoomInfo(Room room);

    void createRoom(Room room);

    List<Room> findSimpleRoomListById (List<Integer> id);

}
