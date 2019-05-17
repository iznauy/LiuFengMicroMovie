package cn.nju.edu.movie.service;

import cn.nju.edu.movie.common.Comment;
import cn.nju.edu.movie.vo.CinemaVO;
import cn.nju.edu.movie.vo.FilmIntroVO;
import cn.nju.edu.movie.vo.FilmVO;

import java.util.List;

/**
 * Created on 17/05/2019.
 * Description:
 *
 * @author iznauy
 */
public interface FilmService {

    List<FilmIntroVO> getFilmList();

    FilmVO getFilmInfo(long filmId);

    List<CinemaVO> getCinemaList(long filmId);

    List<Comment> getCommentList(long filmId);

}
