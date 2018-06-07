package com.github.nnkwrik.kiwiTheater.controller;

import com.github.nnkwrik.kiwiTheater.model.Room;
import com.github.nnkwrik.kiwiTheater.model.User;
import com.github.nnkwrik.kiwiTheater.service.CommonService;
import com.github.nnkwrik.kiwiTheater.service.RoomService;
import com.github.nnkwrik.kiwiTheater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommonService commonService;

    @RequestMapping("/play")
    public String playPage(Model model, HttpSession session,Integer roomid) {
        User user = (User)session.getAttribute("activeUser");
        if (user!=null){//更新当前用户
            session.setAttribute("activeUser",userService.findUserById(user.getId()));
        }

        model.addAttribute("roomVo", roomService.showDetailRoomInfo(roomid));

        return "play";
    }

    @RequestMapping(value = "/editRoomInfo", method = RequestMethod.GET)
    public String editRoomInfoPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("activeUser");
        if (user.getRoomid() != null) {

            model.addAttribute("roomInfo", roomService.findRoomById(user.getRoomid()));
        }

        return "roomInfo";
    }

    @RequestMapping(value = "/editRoomInfo", method = RequestMethod.POST)
    public String editRoomInfo(Model model, Room room, MultipartFile room_img) {

        // 上传图片
        // 原始名称
        String originalFilename = null;
        if (room_img != null) {
            room_img.getOriginalFilename();
        }

        if (room_img != null && originalFilename != null && originalFilename.trim().equals("")) {

            try {
                room.setImg(commonService.uploadPic("room_img", room_img));
            } catch (IOException e) {
                model.addAttribute("errorMsg", e.getMessage());
                return "editRoomInfo";
            }
        }

        if (room.getDescription() != null){

            room.setDescription(room.getDescription().replaceAll("\r\n","<br>"));
        }
        //may change roomname , img, category , description
        roomService.editRoomInfo(room);
        return "misc/success";
    }

    @RequestMapping(value = "/createRoom", method = RequestMethod.GET)
    public String createRoomPage(Model model, HttpSession session) {
        //not null : id,roomname,anchorid

        User user = (User) session.getAttribute("activeUser");
        //给一个模版
        Room room = new Room();
        room.setImg("pic/null_room.jpg");
        room.setRoomname(user.getNickname() + "的直播间");


        model.addAttribute("roomInfo", room);

        return "createRoom";
//        return "forward:/editRoomInfo";
    }

    @RequestMapping(value = "/createRoom", method = RequestMethod.POST)
    public String createRoom(Model model, HttpSession session, Room room,MultipartFile room_img) {

        // 上传图片
        // 原始名称
        String originalFilename = null;
        if (room_img != null) {
            room_img.getOriginalFilename();
        }

        if (room_img != null && originalFilename != null && originalFilename.trim().equals("")) {

            try {
                room.setImg(commonService.uploadPic("room_img", room_img));
            } catch (IOException e) {
                model.addAttribute("errorMsg", e.getMessage());
                return "createRoom";
            }
        }
        if (room.getDescription()!=null){
            room.setDescription(room.getDescription().replaceAll("\r\n","<br>"));
        }

        User user  = (User)session.getAttribute("activeUser");
        room.setAnchorid(user.getId());
        //insert to room table,update user table
        Integer roomid = roomService.createRoom(room).getId();

        //update session user

        user.setRoomid(roomid);
        session.setAttribute("activeUser",user);

        return "misc/success";
    }


}
