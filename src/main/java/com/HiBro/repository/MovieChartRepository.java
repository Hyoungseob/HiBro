package com.HiBro.repository;

import com.HiBro.dto.MovieChartFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MovieChartRepository {

    /**whereArg()메서드 사용전 사용했던 메서드
    public List<MovieChartFormDTO> getSearchList(String keyword);*/

    public Page<MovieChartFormDTO> getMovieChartFormDTOList(Optional<Integer> movieCnt, String keyword);

}
