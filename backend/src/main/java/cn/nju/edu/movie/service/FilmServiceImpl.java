package cn.nju.edu.movie.service;

import cn.nju.edu.movie.common.Comment;
import cn.nju.edu.movie.common.Source;
import cn.nju.edu.movie.dao.FilmDao;
import cn.nju.edu.movie.entity.Film;
import cn.nju.edu.movie.entity.FilmIntro;
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
        Map<Long, FilmIntro> filmIntrosFromMaoYan = maoyanDao.getAllFilm().stream().collect(Collectors.toMap(FilmIntro::getId, k1 -> k1));
        Map<Long, FilmIntro> filmIntrosFromMTime = mTimeDao.getAllFilm().stream().collect(Collectors.toMap(FilmIntro::getId, k1 -> k1));
        Map<Long, FilmIntroVO> resultMap = new HashMap<>();

        for (Map.Entry<Long, FilmIntro> entry: filmIntrosFromMaoYan.entrySet()) {
            long id = entry.getKey();
            FilmIntro intro = entry.getValue();
            FilmIntroVO introVO = new FilmIntroVO();
            introVO.setId(id);
            introVO.setCategories(intro.getCategories());
            introVO.setLength(intro.getLength());
            introVO.setPicUrl(intro.getPicUrl());
            introVO.setName(intro.getName());
            Map<Source, Double> scoreMap = new HashMap<>();
            scoreMap.put(Source.MAO_YAN, 0.0);
            introVO.setScores(scoreMap);
            resultMap.put(id, introVO);
        }

        for (Map.Entry<Long, FilmIntro> entry: filmIntrosFromMTime.entrySet()) {
            long id = entry.getKey();
            FilmIntro intro = entry.getValue();
            if (resultMap.get(id) == null) {
                FilmIntroVO introVO = new FilmIntroVO();
                introVO.setId(id);
                introVO.setCategories(intro.getCategories());
                introVO.setLength(intro.getLength());
                introVO.setPicUrl(intro.getPicUrl());
                introVO.setName(intro.getName());
                Map<Source, Double> scoreMap = new HashMap<>();
                scoreMap.put(Source.SHI_GUANG_WANG, intro.getScore());
                introVO.setScores(scoreMap);
                resultMap.put(id, introVO);
            } else {
                FilmIntroVO introVO = resultMap.get(id);
                Set<String> categories = new HashSet<>();
                categories.addAll(intro.getCategories());
                categories.addAll(introVO.getCategories());
                introVO.setCategories(new ArrayList<>(categories));
                introVO.setLength(introVO.getLength());
                Map<Source, Double> scoreMap = new HashMap<>();
                scoreMap.put(Source.SHI_GUANG_WANG, intro.getScore());
                introVO.setScores(scoreMap);
                resultMap.put(id, introVO);
            }
        }

        return new ArrayList<>(resultMap.values());
    }

    @Override
    public FilmVO getFilmInfo(long filmId) {
        FilmVO filmVO = new FilmVO();
        Film filmFromMTime = mTimeDao.getFilmById(filmId);
        Film filmFromMaoYan = maoyanDao.getFilmById(filmId);
        if (filmFromMaoYan != null) {
            filmVO.setId(filmFromMaoYan.getId());
            filmVO.setCategories(filmFromMaoYan.getCategories());
            filmVO.setDescription(filmFromMaoYan.getDescription());
            filmVO.setEnName(filmFromMaoYan.getEnName());
            filmVO.setDirectors(filmFromMaoYan.getDirectors());
            filmVO.setHasReleased(filmFromMaoYan.isHasReleased());
            filmVO.setLength(filmFromMaoYan.getLength());
            filmVO.setPicUrl(filmFromMaoYan.getPicUrl());
            filmVO.setReleaseTime(filmFromMaoYan.getReleaseTime());
            filmVO.setName(filmFromMaoYan.getName());

            FilmVO.FilmDetailVO detailVO = new FilmVO.FilmDetailVO(0.0, "未知", "未知");
            Map<Source, FilmVO.FilmDetailVO> map = new HashMap<>();
            map.put(Source.MAO_YAN, detailVO);
            filmVO.setFilmDetailVOMap(map);
        }
        if (filmFromMTime != null) {
            filmVO.setId(filmFromMTime.getId());
            filmVO.setCategories(filmFromMTime.getCategories());
            filmVO.setDescription(filmFromMTime.getDescription());
            filmVO.setEnName(filmFromMTime.getEnName());
            filmVO.setDirectors(filmFromMTime.getDirectors());
            filmVO.setHasReleased(filmFromMTime.isHasReleased());
            filmVO.setLength(filmFromMTime.getLength());
            filmVO.setPicUrl(filmFromMTime.getPicUrl());
            filmVO.setReleaseTime(filmFromMTime.getReleaseTime());
            filmVO.setName(filmFromMTime.getName());

            Map<Source, FilmVO.FilmDetailVO> map = filmVO.getFilmDetailVOMap();
            if (map == null)
                map = new HashMap<>();
            map.put(Source.SHI_GUANG_WANG, new FilmVO.FilmDetailVO(filmFromMTime.getScore(), filmFromMTime.getScoreCount(), filmFromMTime.getTicketOffice()));
            filmVO.setFilmDetailVOMap(map);
        }
        return filmVO;
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
