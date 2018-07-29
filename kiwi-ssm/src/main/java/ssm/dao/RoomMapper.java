package ssm.dao;

import ssm.model.Room;

import java.util.List;


public interface RoomMapper {

    Room findRoomById(Integer roomId);

    void updateRoomInfo(Room room);

    void createRoom(Room room);

    List<Room> findSimpleRoomListById(List<Integer> id);

}
