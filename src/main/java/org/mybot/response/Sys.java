package org.mybot.response;

import lombok.Data;

@Data
public class Sys {
    private Integer type;
    private Integer id;
    private String country;
    private Integer sunrise;
    private Integer sunset;
}
