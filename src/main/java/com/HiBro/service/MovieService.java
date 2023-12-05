package com.HiBro.service;

import com.HiBro.dto.MovieDTO;
import com.HiBro.repository.MovieImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    @Value("")
    private String movieImgLocation;
    private final MovieImgRepository movieImgRepository;
}
