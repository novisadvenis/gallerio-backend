package ch.bzzz.galleriobackend.util;

import ch.bzzz.galleriobackend.model.MetaDataModel;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.icc.IccDirectory;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility Class
 */
@Component
public class Util {

    @Value("${thumbnail.size}")
    private int target;

    private static int targetSize;

    @Value("${thumbnail.size}")
    public void setTargetSize(int target) {
        Util.targetSize = target;
    }


    /**
     * GetMetaData method extract the metadata from the inputstream of file
     * @param inputStream type InputStream. This the File to parse to get the metadata
     * @return List<MetaDataModel>. List of MetaDataModel object
     * @throws ImageProcessingException
     * @throws IOException
     */
    public static List<MetaDataModel> getMetaData(InputStream inputStream) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(inputStream);
        List<MetaDataModel> metaDataList = new ArrayList<>();
        if (metadata.getDirectoryCount() > 0) {
            metadata.getDirectories().forEach(dir->{
                if(!(dir instanceof IccDirectory)){
                    dir.getTags().parallelStream()
                            .distinct()
                            .forEach(tag -> {
                                MetaDataModel metaDataModel = new MetaDataModel(tag.getTagName(), tag.getDescription(), tag.getDirectoryName());
                                metaDataList.add(metaDataModel);
                            });
                }
            });
        }
        return metaDataList;
    }

    /**
     * CreateThumbnail method handles the creation of thumbnail from inputstream of the image
     * @param inputStream type InputStream. This is the inputstream of the image
     * @param name type string. This is the image name
     * @param type type string. This is the mime type of the image
     * @return byte[]. returns the byte array of the created thumbnail
     * @throws IOException
     */
    public static byte[] createThumbnail(InputStream inputStream, String name, String type) throws IOException {
        byte[] scaledImageBytes = null;
        try {
            BufferedImage originalImage = ImageIO.read(inputStream);

            BufferedImage scaledImage = rescale(originalImage);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(scaledImage, type, baos);
            baos.flush();
            scaledImageBytes = baos.toByteArray();
            baos.close();
        } catch (Exception e) {
            System.out.println("Scaling image failed.");
            System.out.println(e);
            e.printStackTrace();
            //throw  new IOException("Scaling couldn't be created");
        }
        return scaledImageBytes;
    }

    /**
     * Rescale method handles the rescale of the image according to the original orentation of the image
     * @param src type BufferedImage. This is the image file
     * @return BufferedImage. This is the image file
     */
    private static BufferedImage rescale(BufferedImage src) {
        final Scalr.Mode mode;

        double ovx = src.getWidth() - targetSize;
        double ovy = src.getHeight() - targetSize;

        // If neither width or height overflows, return the original
        if( ovx <= 0 && ovy <= 0 )
        {
            return src;
        }

        // Find out which dimension is over the limit the most
        if( ( ovx / targetSize ) > ( ovy / targetSize ) )
        {
            // If the width overflows the most, rescale based on the width
            mode = Scalr.Mode.FIT_TO_WIDTH;
        }
        else
        {
            // If the height overflows the most, rescale based on the height
            mode = Scalr.Mode.FIT_TO_HEIGHT;
        }

        return Scalr.resize( src, mode, targetSize, targetSize );
    }



}
