package vikrant.khaiwal.displayimage.model;

/**
 * Created by vikrant on 22/5/16.
 */
public class ViewModel {
    private String text;
    private String image;
    private String imageId;
    public ViewModel(){

    }
    public ViewModel(String text, String image, String imageId) {
        this. text = text;
        this.image = image;
        this.imageId = imageId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }

    public String getImageId() {
        return imageId;
    }
}

