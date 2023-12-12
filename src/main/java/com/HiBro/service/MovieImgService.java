package com.HiBro.service;

import com.HiBro.entity.MovieImg;
import com.HiBro.repository.MovieImgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Log
public class MovieImgService {

    @Value("${movieImgLocation}")
    private String movieImgLocation;

    private final MovieImgRepository movieImgRepository;

    private final FileService fileService;

    //이미지 저장
    public MovieImg saveMovieImg(MovieImg movieImg, MultipartFile movieImgFile){

        String oriImgName = movieImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        try {
            if (!StringUtils.isEmpty(oriImgName)) {
                imgName = fileService.uploadFile(movieImgLocation, oriImgName, movieImgFile.getBytes());

                //Url 처리 하기
                imgUrl = "/images/movie/" + imgName;

                movieImg.setOriImgName(oriImgName);
                movieImg.setImgName(imgName);
                movieImg.setImgUrl(imgUrl);
            }
        }catch (IOException e){
            //TODO 나중 예외 처리
            log.info("이미지 파일 저장 과정에서 에러가 발생하였습니다.");
        }

        return movieImgRepository.save(movieImg);

    }

    public void updateMovieImg(Long movieImgCode, MultipartFile movieImgFile) {

        if(!movieImgFile.isEmpty()){
            MovieImg savedMovieImg = movieImgRepository.findById(movieImgCode).orElseThrow(EntityNotFoundException::new);

            //기존 이미지 파일 삭제
            if(!StringUtils.isEmpty(savedMovieImg.getImgName())){
                fileService.deleteFile(movieImgLocation + "/" + savedMovieImg.getImgName());
            }

            try{
                String oriImgName = movieImgFile.getOriginalFilename();
                String imgName = fileService.uploadFile(movieImgLocation, oriImgName, movieImgFile.getBytes());
                //Url 처리 하기
                String imgUrl = "/images/movie/" + imgName;

                savedMovieImg.updateMovieImg(oriImgName, imgName, imgUrl);
            }catch (IOException e){
                //TODO 영화 이미지 파일 저장 예외처리 할 것
                log.info("이미지 파일 저장중 imgName지정에 실패하였습니다.");
            }

        }
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
