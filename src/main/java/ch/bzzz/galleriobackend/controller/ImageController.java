package ch.bzzz.galleriobackend.controller;

import ch.bzzz.galleriobackend.model.ImageModel;
import ch.bzzz.galleriobackend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Controller Class for ImageModel Entity
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000") // need to allow Cross-origin request
@RequestMapping(path = "image")
public class ImageController {

    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * UploadImage method handles the upload request from client
     * @Allowed Only HTTP POST request accepted
     * @param file type MultipartFile. This is the file which will be uploaded.
     * @return ResponseEntity with string value
     * @throws IOException
     */
    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        return imageService.save(file) ?
                ResponseEntity.ok("thanks for uploading")
                : ResponseEntity.ok("image was not saved") ;
    }

    /**
     * GetImage method handles the request of single image request from client using image name
     * @Allowed only HTTP GET request accepted
     * @param imageName type String. This is the identifier
     * @return ImageModel Entity. This is the image object.
     * @throws IOException
     */
    @GetMapping(path = {"/get/{imageName}"})
    public ImageModel getImage(@PathVariable("imageName") String imageName) throws IOException {
        return imageService.get(imageName);
    }

    /**
     * UpdateImageName method handles the request from client to update
     * @Allowed Only HTTP GET request accepted
     * @param imageId type Long. This is the identifier.
     * @param name type String. This is the value which will replace the existing name
     * @return ResponseEntity with string value
     */
    @GetMapping(path = "/update/{imageId}")
    public ResponseEntity<String> updateImageName(@PathVariable("imageId") long imageId,
                                                  @RequestParam("name") String name) {
        return imageService.updateImageName(imageId, name) ?
                ResponseEntity.ok("thanks for updateImageName")
                : ResponseEntity.ok("image was not updateImageName");
    }

    /**
     * GetAllImages method handles the request of getting all the images in database
     * @Allowed Only GET request accepted
     * @return List of ImageModel
     */
    @GetMapping(path="/getAll")
    public List<ImageModel> getAllImages(){
        return imageService.getAll();
    }

    /**
     * DeleteImage method handles the delete request from client
     * @Allowed only HTTP DELETE request accepted
     * @param id type string. This is the identifier
     * @return ResponseEntity with string value
     */
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String>  deleteImage(@PathVariable("id") long id) {
        return imageService.deleteImage(id) ?
                ResponseEntity.ok("thanks for deleting")
                : ResponseEntity.ok("image was not deleted") ;
    }
}
