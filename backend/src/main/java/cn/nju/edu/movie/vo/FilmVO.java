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

    private long id;

    private String name;

    private String EnName;

    private List<String> categories;

    private String length;

    private String releaseTime;

    private boolean hasReleased;

    private String picUrl;

    private List<String> directors;

    private String description;

    private Map<Source, FilmDetailVO> filmDetailVOMap;

    private static class FilmDetailVO {

        private Double score;

        private String scoreCount;

        private String ticketOffice;

        public FilmDetailVO(Double score, String scoreCount, String ticketOffice) {
            this.score = score;
            this.scoreCount = scoreCount;
            this.ticketOffice = ticketOffice;
        }

        public Double getScore() {
            return score;
        }

        public String getScoreCount() {
            return scoreCount;
        }

        public String getTicketOffice() {
            return ticketOffice;
        }
    }

}
