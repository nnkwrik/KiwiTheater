package ssm.model;

import lombok.Data;

@Data
public class Room {
    private Integer id;

    private String roomname;

    private Integer anchorid;

    private String img;

    private String description;

    private Integer category;

    private Boolean living;

}
