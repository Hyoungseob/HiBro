package com.HiBro.repository;

import com.HiBro.constant.ImgType;
import com.HiBro.dto.MovieChartFormDTO;
import com.HiBro.dto.QMovieChartFormDTO;
import com.HiBro.entity.QMovie;
import com.HiBro.entity.QMovieImg;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

@Log
public class MovieChartRepositoryImpl implements MovieChartRepository {

    private JPAQueryFactory queryFactory;

    public MovieChartRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    //검색 조건 유무를 판단해서 where절 조건 추가
    private BooleanExpression whereArg(QMovie qMovie, String keyword){
        if(keyword=="" || keyword==null){
            return null;
        }
        return qMovie.movieTitle.eq(keyword);
    }

    /**@쿼리문 where문에 whereArg()메서드 적용 전 사용 했던 추가 메서드
    public List<MovieChartFormDTO> getSearchList() {
        QMovie movie = QMovie.movie;
        QMovieImg movieImg = QMovieImg.movieImg;

        List<MovieChartFormDTO> movieChartFormDTOList = queryFactory.select(
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

        return movieChartFormDTOList;
    }*/

    //QueryDSL을 활용한 쿼리 커스텀(MovieChartRepository 오버라이딩)
    @Override
    public Page<MovieChartFormDTO> getMovieChartFormDTOList(Pageable moviePageable, String keyword){
        QMovie movie = QMovie.movie;
        QMovieImg movieImg = QMovieImg.movieImg;

        List<MovieChartFormDTO> searchList = queryFactory.select(
                new QMovieChartFormDTO(
                        movie.code,
                        movie.movieTitle,
                        movie.premiereDate,
                        movieImg.imgUrl)
                )
                .from(movieImg)
                .join(movieImg.movie, movie)
                .where(movieImg.imgType.eq(ImgType.POSTER), whereArg(movie, keyword))
                .offset(moviePageable.getOffset())
                .limit(moviePageable.getPageSize())
                .fetch();

        long total = queryFactory.select(Wildcard.count)
                .from(movieImg)
                .join(movieImg.movie, movie)
                .where(movieImg.imgType.eq(ImgType.POSTER), whereArg(movie, keyword))
                .fetchOne();

        return new PageImpl<>(searchList, moviePageable, total);

    }


}
