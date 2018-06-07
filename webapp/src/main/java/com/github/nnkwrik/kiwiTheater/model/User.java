package com.github.nnkwrik.kiwiTheater.model;

import lombok.Data;

/**
 * 用户PO类
 */
@Data
public class User {
    private Integer id;

    private String username;

    private String nickname;//默认username

    private String password;//md5,32

    private String avatar;

    private Double coin;

    private Double usedcoin;

    private String like;

    private Integer roomid; //有自己的直播间时房间号,否则null

}
