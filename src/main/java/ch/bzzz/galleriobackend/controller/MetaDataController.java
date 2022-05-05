package ch.bzzz.galleriobackend.controller;


import ch.bzzz.galleriobackend.db.MetaDataRepository;
import ch.bzzz.galleriobackend.model.MetaDataModel;
import ch.bzzz.galleriobackend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "metadata")
public class MetaDataController {

    private ImageService imageService;

    @Autowired
    public MetaDataController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(path="update/{metaDataId}")
    public ResponseEntity<String> update(@PathVariable("metaDataId") long metaDataId,
                                                 @RequestParam("value") String value) {
        return imageService.updateMetaData(metaDataId,value) ?
                ResponseEntity.ok("thanks for updating")
                : ResponseEntity.ok("updating was not successful");
    }

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
