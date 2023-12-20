package com.HiBro.repository;

import com.HiBro.dto.MovieChartFormDTO;

import java.util.List;

public interface MovieChartRepository {

    public List<MovieChartFormDTO> getMovieChartFormDTOList();
}
