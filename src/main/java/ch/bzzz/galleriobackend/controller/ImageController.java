package ch.bzzz.galleriobackend.controller;

import ch.bzzz.galleriobackend.model.ImageModel;
import ch.bzzz.galleriobackend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
//todo
// 1.java doc
// 2.controller advice for handling exception
// 3.assembler
// reference page:  rest spring boot with react

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "image")
public class ImageController {

    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        return imageService.save(file) ?
                ResponseEntity.ok("thanks for uploading")
                : ResponseEntity.ok("image was not saved") ;
    }

    @GetMapping(path = {"/get/{imageName}"})
    public ImageModel getImage(@PathVariable("imageName") String imageName) throws IOException {
        return imageService.get(imageName);
    }

    @GetMapping(path = "/update/{imageId}")
    public ResponseEntity<String> updateImageName(@PathVariable("imageId") Long imageId,
                                                  @RequestParam("name") String name) {
        return imageService.updateImageName(imageId, name) ?
                ResponseEntity.ok("thanks for updateImageName")
                : ResponseEntity.ok("image was not updateImageName");
    }


    @GetMapping(path="/getAll")
    public List<ImageModel> getAllImages(){
        return imageService.getAll();
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String>  deleteImage(@PathVariable("id") String id) {
        return imageService.deleteImage(Long.parseLong(id)) ?
                ResponseEntity.ok("thanks for deleting")
                : ResponseEntity.ok("image was not deleted") ;
    }
}
