package com.HiBro.service;

import com.HiBro.entity.MovieImg;
import com.HiBro.repository.MovieImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class MovieImgService {

    MovieImgRepository movieImgRepository;

    public MovieImg saveMovieImg(MovieImg movieImg){

        return movieImgRepository.save(movieImg);

    }

    //이미지 유무 체크
    public boolean checkImg(List<MovieImg> movieImgs){

        if(movieImgs != null){
            return true;
        }
        return false;
    }

    //이미지 일괄 삭제
    public void deleteMovieImg(Long movieCode){
        List<MovieImg> movieImgs = movieImgRepository.findByMovieCodeOrderByMovieCodeAsc(movieCode);

        if(checkImg(movieImgs)){

            for(MovieImg movieImg : movieImgs){
                movieImgRepository.delete(movieImg);
            }
        }
    }
}
