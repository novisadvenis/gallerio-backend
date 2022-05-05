package ch.bzzz.galleriobackend.service;

import ch.bzzz.galleriobackend.model.ImageModel;
import ch.bzzz.galleriobackend.model.MetaDataModel;
import com.drew.metadata.Metadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageServiceInterface {
    ImageModel get(String name);
    ImageModel get(long id);
    boolean deleteImage(String name);
    boolean deleteImage(long id);
    boolean update(long id, ImageModel imageModel);
    boolean update(String name, ImageModel imageModel);
    boolean save(MultipartFile file) throws IOException;
    boolean updateMetaData(long metaDataId, String value);
    List<ImageModel> getAll();
    boolean updateImageName(String oldName, String newName);
    boolean updateImageName(long id, String name);
    boolean deleteMetaData(long id);
    boolean saveMetaData(MetaDataModel metaDataModel);
}
