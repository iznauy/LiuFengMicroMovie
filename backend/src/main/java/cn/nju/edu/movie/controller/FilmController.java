package cn.nju.edu.movie.controller;

import cn.nju.edu.movie.common.Comment;
import cn.nju.edu.movie.service.FilmService;
import cn.nju.edu.movie.vo.CinemaVO;
import cn.nju.edu.movie.vo.FilmIntroVO;
import cn.nju.edu.movie.vo.FilmVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created on 17/05/2019.
 * Description:
 *
 * @author iznauy
 */
@RestController
public class FilmController {
    
    private FilmService filmService;

    @GetMapping("/list")
    public List<FilmIntroVO> getFilmList() {
        return filmService.getFilmList();
    }

    @GetMapping("/info")
    public FilmVO getFilmInfo(long id) {
        return filmService.getFilmInfo(id);
    }

    @GetMapping("/cinema")
    public List<CinemaVO> getCinemaList(long id) {
        return filmService.getCinemaList(id);
    }

    @GetMapping("/comment")
    public List<Comment> getCommentList(long id) {
        return filmService.getCommentList(id);
    }

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }
}
