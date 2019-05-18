package cn.nju.edu.movie.service;

import cn.nju.edu.movie.common.Comment;
import cn.nju.edu.movie.common.Source;
import cn.nju.edu.movie.dao.FilmDao;
import cn.nju.edu.movie.entity.Cinema;
import cn.nju.edu.movie.vo.CinemaVO;
import cn.nju.edu.movie.vo.FilmIntroVO;
import cn.nju.edu.movie.vo.FilmVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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
        List<Cinema> records = maoyanDao.getAvailableCinemasByFilmId(filmId);
        records.addAll(mTimeDao.getAvailableCinemasByFilmId(filmId));

        return convertToVos(records);
    }

    @Override
    public List<Comment> getCommentList(long filmId) {
        List<Comment> result = maoyanDao.getCommentsByFilmId(filmId);
        result.addAll(mTimeDao.getCommentsByFilmId(filmId));
        return result.stream().sorted(Comparator.comparing(Comment::getTime).reversed())
                .collect(Collectors.toList());
    }

    private List<CinemaVO> convertToVos(List<Cinema> cinemas) {
        Map<String, CinemaVO> record = new HashMap<>();
        for (Cinema cinema : cinemas) {
            String name = cinema.getName();


            if (record.get(name) == null) {
                CinemaVO vo = new CinemaVO();
                vo.setName(name);
                Map<Source, CinemaVO.CinemaDetailVO> detail = new HashMap<>();
                detail.put(
                        cinema.getSource(),
                        new CinemaVO.CinemaDetailVO(cinema.getPosition(), cinema.getPrice(), cinema.getUrl())
                );
                vo.setDetails(detail);
                record.put(name, vo);
            } else {
                CinemaVO vo = record.get(name);
                vo.getDetails().put(
                        cinema.getSource(),
                        new CinemaVO.CinemaDetailVO(cinema.getPosition(), cinema.getPrice(), cinema.getUrl())
                );
            }
        }

        return new ArrayList<>(record.values());
    }

}
