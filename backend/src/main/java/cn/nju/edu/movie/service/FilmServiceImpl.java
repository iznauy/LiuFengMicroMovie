package cn.nju.edu.movie.service;

import cn.nju.edu.movie.common.Comment;
import cn.nju.edu.movie.vo.CinemaVO;
import cn.nju.edu.movie.vo.FilmIntroVO;
import cn.nju.edu.movie.vo.FilmVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 17/05/2019.
 * Description:
 *
 * @author iznauy
 */
@Service
public class FilmServiceImpl implements FilmService {

    @Override
    public List<FilmIntroVO> getFilmList() {
        return null;
    }

    @Override
    public FilmVO getFilmInfo(long filmId) {
        return null;
    }

    @Override
    public List<CinemaVO> getCinemaList(long filmId) {
        return null;
    }

    @Override
    public List<Comment> getCommentList(long filmId) {
        return null;
    }
}
