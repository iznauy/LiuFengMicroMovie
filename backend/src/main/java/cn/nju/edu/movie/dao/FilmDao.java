package cn.nju.edu.movie.dao;

import cn.nju.edu.movie.common.Comment;
import cn.nju.edu.movie.entity.Cinema;
import cn.nju.edu.movie.entity.Film;
import cn.nju.edu.movie.entity.FilmIntro;

import java.util.List;

/**
 * Created on 16/05/2019.
 * Description:
 *
 * @author iznauy
 */
public interface FilmDao {

    List<Comment> getCommentsByFilmId(long id);

    List<Cinema> getAvailableCinemasByFilmId(long id);

    Film getFilmById(long id);

    List<FilmIntro> getAllFilm();

}
