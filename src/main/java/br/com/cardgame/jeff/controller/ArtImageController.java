package br.com.cardgame.jeff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.cardgame.jeff.service.ArtCardsService;
import jakarta.persistence.EntityNotFoundException;
@RestController
@RequestMapping( "/api/arts" )
public class ArtImageController {
    
    @Autowired
    private ArtCardsService artServ;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage (@RequestParam("file") MultipartFile file){
        try{
            
            var img = artServ.saveArt(file);
            return ResponseEntity.status(HttpStatus.OK).body("Imagem salva com sucesso: " + img.getId());

        }catch (Exception ex){

            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload the image " + ex.getMessage());

        }
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getImage (@PathVariable int id){
        try{

            var img = artServ.findById(id);
            return ResponseEntity.status(HttpStatus.OK)
            .contentType(MediaType.parseMediaType(img.getType()))
            .body(img.getImageData());

        }catch( EntityNotFoundException ex ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
