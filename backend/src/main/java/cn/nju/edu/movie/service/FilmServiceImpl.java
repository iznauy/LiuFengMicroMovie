package cn.nju.edu.movie.service;

import cn.nju.edu.movie.common.Comment;
import cn.nju.edu.movie.dao.FilmDao;
import cn.nju.edu.movie.entity.FilmIntro;
import cn.nju.edu.movie.vo.CinemaVO;
import cn.nju.edu.movie.vo.FilmIntroVO;
import cn.nju.edu.movie.vo.FilmVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created on 17/05/2019.
 * Description:
 *
 * @author iznauy
 */
@Service
public class FilmServiceImpl implements FilmService {

    @Resource(name = "mTime")
    private FilmDao mTimeDao;

    @Resource(name = "maoYan")
    private FilmDao maoyanDao;

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
