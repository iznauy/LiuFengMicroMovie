package cn.nju.edu.movie.entity;

import lombok.Data;

import java.util.List;

/**
 * Created on 13/05/2019.
 * Description:
 *
 * @author iznauy
 */
@Data
public class Film {

    private long id;

    private String name;

    private String EnName;

    private List<String> categories;

    private String length;

    private String releaseTime;

    private Double score;

    private String scoreCount;

    private boolean hasReleased;

    private String ticketOffice;

    private String picUrl;

    private String description;

    private List<String> directors;

}
