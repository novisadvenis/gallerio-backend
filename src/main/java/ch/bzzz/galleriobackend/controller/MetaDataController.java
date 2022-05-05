package ch.bzzz.galleriobackend.controller;


import ch.bzzz.galleriobackend.db.MetaDataRepository;
import ch.bzzz.galleriobackend.model.MetaDataModel;
import ch.bzzz.galleriobackend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller Class for MetaDataModel
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000") // needed to allow Cross-origin request
@RequestMapping(path = "metadata")
public class MetaDataController {

    private ImageService imageService;

    @Autowired
    public MetaDataController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Update method handles the request to change metadata value
     * @param metaDataId type long. This is the identifier
     * @param value type string. This is the value that will replace the existing value in Database
     * @return
     */
    @GetMapping(path="update/{metaDataId}")
    public ResponseEntity<String> update(@PathVariable("metaDataId") long metaDataId,
                                                 @RequestParam("value") String value) {
        return imageService.updateMetaData(metaDataId,value) ?
                ResponseEntity.ok("thanks for updating")
                : ResponseEntity.ok("updating was not successful");
    }

    /**
     * Delte
     * @param metaDataId
     * @return
     */
    @DeleteMapping(path = "delete/{metaDataId}")
    public ResponseEntity<String> delete(@PathVariable("metaDataId") long metaDataId) {
        return imageService.deleteMetaData(metaDataId) ?
                ResponseEntity.ok("thanks for deleting")
                : ResponseEntity.ok("deleting was not successful");
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody MetaDataModel metaDataModel) {
        return imageService.saveMetaData(metaDataModel) ?
                ResponseEntity.ok("thanks for creating new metadata")
                : ResponseEntity.ok("creating metadata was not successful");
    }
}
