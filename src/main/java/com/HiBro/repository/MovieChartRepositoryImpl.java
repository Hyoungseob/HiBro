package com.HiBro.repository;

import com.HiBro.constant.ImgType;
import com.HiBro.dto.MovieChartFormDTO;
import com.HiBro.dto.QMovieChartFormDTO;
import com.HiBro.entity.QMovie;
import com.HiBro.entity.QMovieImg;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class MovieChartRepositoryImpl implements MovieChartRepository{

    private JPAQueryFactory queryFactory;

    public MovieChartRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<MovieChartFormDTO> getMovieChartFormDTOList() {
        QMovie movie = QMovie.movie;
        QMovieImg movieImg = QMovieImg.movieImg;

        List<MovieChartFormDTO> MovieChartFormDTOList = queryFactory.select(
                new QMovieChartFormDTO(
                        movie.code,
                        movie.movieTitle,
                        movie.premiereDate,
                        movieImg.imgUrl)
        )
                .from(movieImg)
                .join(movieImg.movie, movie)
                .where(movieImg.imgType.eq(ImgType.POSTER))
                .fetch();

        return MovieChartFormDTOList;
    }
}
