package ssm.service;

import ssm.model.Room;
import ssm.model.Vo;

public interface RoomService {
    Vo showDetailRoomInfo(Integer roomid);

    Room findRoomById(Integer userid);

    void editRoomInfo(Room room);

    Room createRoom(Room room);


}
