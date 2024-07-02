package br.com.cardgame.jeff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.cardgame.jeff.model.ArtsCard;
import br.com.cardgame.jeff.repository.ArtsCardRepository;

@Service
public class ArtCardsService {
    
    @Autowired
    private ArtsCardRepository artRepo;

    public ArtsCard saveArt(MultipartFile file) throws Exception{
        var img = new ArtsCard();
        img.setName(file.getOriginalFilename());
        img.setType(file.getContentType());
        img.setImageData(file.getBytes());

        return artRepo.save(img);
    } 
}
