package com.HiBro.repository;

import com.HiBro.dto.MovieChartFormDTO;

import java.util.List;

public interface MovieChartRepository {

    /**whereArg()메서드 사용전 사용했던 메서드
    public List<MovieChartFormDTO> getSearchList(String keyword);*/

    public List<MovieChartFormDTO> getMovieChartFormDTOList(String keyword);

}
