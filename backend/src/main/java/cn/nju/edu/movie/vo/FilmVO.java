package cn.nju.edu.movie.vo;

import cn.nju.edu.movie.common.Comment;
import cn.nju.edu.movie.common.Source;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created on 13/05/2019.
 * Description:
 *
 * @author iznauy
 */
@Data
public class FilmVO {

    private int id;

    private String name;

    private String EnName;

    private List<String> category;

    private String length;

    private String releaseTime;

    private boolean hasReleased;

    private String picUrl;

    private List<String> director;

    private List<CinemaVO> cinemaVOs;

    private String description;

    private List<Comment> comments;

    private Map<Source, FilmDetailVO> filmDetailVOMap;

    private static class FilmDetailVO {

        private Double score;

        private Integer scoreCount;

        private String ticketOffice;

    }

}
