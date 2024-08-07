package br.com.cardgame.jeff.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.cardgame.jeff.dtos.ArtDto;
import br.com.cardgame.jeff.dtos.ArtSendDto;
import br.com.cardgame.jeff.dtos.ArtUploadDto;
import br.com.cardgame.jeff.model.tipoArt.ArtType;
import br.com.cardgame.jeff.service.ArtCardsService;
@RestController
@RequestMapping( "/api/arts" )
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class ArtImageController {
    
    @Autowired
    private ArtCardsService artServ;

    @PostMapping("/upload")
    public ResponseEntity<ArtUploadDto> uploadImage (@RequestBody ArtDto artDto){
        
        String base64Image = artDto.content();
        String extensionName = artDto.type();
        if (extensionName == "" || extensionName.isEmpty()){
            System.out.println("Sem extensão nos arquivos");
            return ResponseEntity.badRequest().build();
        }
        String contentType = "image/"+extensionName;
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        String fileName = "image." + extensionName;

        MultipartFile file = new MockMultipartFile(fileName, fileName ,contentType ,imageBytes);
        
        var art = artServ.saveArt(file, artDto.typeArt());
        return ResponseEntity.status(HttpStatus.OK).body(art);
    }


    // TO DO: DTO PARA SÓ ESSE ENDPOINT SEM ID!
    @GetMapping("{id}")
    public ResponseEntity<ArtSendDto> getImage (@PathVariable int id){

            var img = artServ.findById(id);
            var dtoResp = new ArtSendDto(
                img.getType().split("/")[1], 
                img.getImageData(),
                id);
            
            return ResponseEntity.status(HttpStatus.OK).body(dtoResp);
    }

    @GetMapping("/type")
    public ResponseEntity<List<ArtSendDto>> getByArtType (ArtType typeArt){

        List<ArtSendDto> arts = artServ.findByTypeArt(typeArt);

        return ResponseEntity.status(HttpStatus.OK).body(arts);
    }
}
