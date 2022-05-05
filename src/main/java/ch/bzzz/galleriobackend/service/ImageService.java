package ch.bzzz.galleriobackend.service;

import ch.bzzz.galleriobackend.db.MetaDataRepository;
import ch.bzzz.galleriobackend.db.ImageRepository;
import ch.bzzz.galleriobackend.model.MetaDataModel;
import ch.bzzz.galleriobackend.model.ImageModel;
import ch.bzzz.galleriobackend.util.Util;
import com.drew.imaging.ImageProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Class for Image. Implements ImageServiceInterface
 */
@Service
public class ImageService implements ImageServiceInterface {

    private ImageRepository imageRepository;
    private MetaDataRepository metaDataRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, MetaDataRepository metaDataRepository) {
        this.imageRepository = imageRepository;
        this.metaDataRepository = metaDataRepository;
    }

    /**
     * Get method service handles the search of image by name
     * @param name type string. This is the identifier
     * @return ImageModel object
     */
    @Override
    public ImageModel get(String name) {
        final Optional<ImageModel> retrievedImage = imageRepository.findByName(name);
        return retrievedImage.isPresent() ? retrievedImage.get() : null;
    }

    @Override
    public ImageModel get(long id) {
        return null;
    }

    @Override
    public boolean deleteImage(String name) {
        return false;
    }

    /**
     * DeleteImage method service handle the deletion of image by id
     * @param id type long. This is the identifier
     * @return boolean. return always true.
     */
    @Override
    public boolean deleteImage(long id) {
        imageRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean update(long id, ImageModel imageModel) {
        return false;
    }

    @Override
    public boolean update(String name, ImageModel imageModel) {
        return false;
    }

    /**
     * Save method service handles the saving of Image by specified file
     * @param file type MultipartFile. This is the image file that will saved in database
     * @return boolean. return true if the request was successfull else false
     * @throws IOException
     */
    @Override
    public boolean save(MultipartFile file) throws IOException {
        List<MetaDataModel> metaDataModelList = new ArrayList<>();
        try {
            if(!file.isEmpty()) {
                InputStream inputStream = file.getInputStream();
                 metaDataModelList = Util.getMetaData(inputStream);
            }
        } catch (ImageProcessingException | IOException e) {
            e.printStackTrace();
        }
        String contentType = file.getContentType().split("/")[1];

        byte[] thumbnailByte = Util.createThumbnail(file.getInputStream(), file.getName(), contentType);
        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getBytes(),
                contentType ,thumbnailByte, metaDataModelList);
        imageRepository.save(img);
        return true;
    }

    /**
     * UpdateMetaData method service handles the updating request of value defined
     * @param metaDataId type long. This is the identifier
     * @param value type string. This is the value which will replace the existing value
     * @return boolean. returns true if service is successful else false.
     */
    @Override
    public boolean updateMetaData(long metaDataId, String value) {
        Optional<MetaDataModel> metaDataModel = metaDataRepository.findById((long) metaDataId);
        if(metaDataModel.isPresent()) {
            metaDataModel.get().setValue(value);
            metaDataRepository.save(metaDataModel.get());
            return true;
        }
        return false;
    }

    /**
     * GetAll method service handles the getting of all Images in database
     * @return List<ImageModel>. returns the list of ImageModel objects
     */
    @Override
    public List<ImageModel> getAll() {
        List<ImageModel> imageModelList = imageRepository.findAll();
        return imageModelList;
    }

    /**
     * UpdateImageName method service handles the updating of the image name with new name defined
     * @param oldName type string. This is the identifier
     * @param newName type string. This value will replace the existing value
     * @return boolean. return true if the service was successful else false.
     */
    @Override
    public boolean updateImageName(String oldName, String newName) {
        Optional<ImageModel> imageModel =  imageRepository.findByName(oldName);
        if(imageModel.isPresent()) {
            imageModel.get().setName(newName);
            imageRepository.save(imageModel.get());
            return true;
        }
        return false;
    }

    /**
     * UpdateImageName method service handles the updating of image name with name defined
     * @param id type long. This is the identifier
     * @param name type string. This value will replace the existing value
     * @return boolean. return true if the service was successful else false
     */
    @Override
    public boolean updateImageName(long id, String name) {
        Optional<ImageModel> imageModel =  imageRepository.findById((long) id);
        if(imageModel.isPresent()) {
            imageModel.get().setName(name);
            imageRepository.save(imageModel.get());
            return true;
        }
        return false;
    }

    /**
     * DeleteMetaData method service handles the deletion of Metadata
     * @param id type long. This is the identifier
     * @return boolean. returns always true
     */
    @Override
    public boolean deleteMetaData(long id) {
        metaDataRepository.deleteById(id);
        return true;
    }

    /**
     * SaveMetaData method service handles the creation of new Metadata object
     * @param metaDataModel type MetaDataModel. Json Object with same key value attribute as defined
     * in MetaDataModelClass
     * @return boolean. return always true.
     */
    @Override
    public boolean saveMetaData(MetaDataModel metaDataModel) {
        metaDataRepository.save(metaDataModel);
        return true;
    }
}
