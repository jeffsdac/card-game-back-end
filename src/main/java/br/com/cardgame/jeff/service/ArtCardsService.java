package br.com.cardgame.jeff.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.cardgame.jeff.dtos.ArtSendDto;
import br.com.cardgame.jeff.dtos.ArtUploadDto;
import br.com.cardgame.jeff.dtos.MapperClass;
import br.com.cardgame.jeff.model.ArtsCard;
import br.com.cardgame.jeff.model.tipoArt.ArtType;
import br.com.cardgame.jeff.repository.ArtsCardRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ArtCardsService {
    
    @Autowired
    private ArtsCardRepository artRepo;

    public ArtUploadDto saveArt(MultipartFile file, ArtType typeArt){
        var img = new ArtsCard();
        img.setTipoArt(typeArt);
        img.setName(file.getOriginalFilename());
        img.setType(file.getContentType());
        
        try {
            img.setImageData(file.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        artRepo.save(img);
        

        return MapperClass.artsCardToArtUploadDto(img);
    } 

    @Transactional
    public ArtsCard findById (int id){
        return artRepo.findById(id).orElseThrow( () -> new EntityNotFoundException("Could not found image with this id"));
    }

    @Transactional
    public List<ArtSendDto> findByTypeArt(ArtType artType){
        var arts = artRepo.findByTipoArt(artType)
        .orElseThrow( () -> new EntityNotFoundException("Could not found any art") );

        List<ArtSendDto> artsDtos = arts.stream()
        .map( (obj) -> MapperClass.artToArtSendDto(obj))
        .toList();
        
        return artsDtos;
    }
}
