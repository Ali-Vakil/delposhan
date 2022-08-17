package holosen.shop.app.helper.uiModels.people;
import holosen.shop.app.entities.product.Features;
import holosen.shop.app.entities.product.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProductVM {
    private long id;
    private String title;
    private String description;
    private long price;
    private String image;
    private boolean enable;
    private boolean exists;
    private long category;
    private long visitCount;
    private List<Long> colors;
    private List<Long> sizes;
    private List<Long> features;
    private List<Features> featuresDataList;
    private Date lastdate;

    public ProductVM() {
    }

    public ProductVM(Product product) {
        setId(product.getId());
        setTitle(product.getTitle());
        setDescription(product.getDescription());
        setPrice(product.getPrice());
        setImage(product.getImage());
        setEnable(product.getEnable());
        setExists(product.getExists());
        setCategory(product.getCategory().getId());
        setLastdate(product.getAddDate());
        setVisitCount(product.getVisitCount());
        if(product.getFeatures().size() > 0)
            product.getFeatures().forEach(x->getFeaturesDataList().add(x));
        if(product.getColors().size() > 0)
            setColors(product.getColors().stream().map(x->x.getId()).collect(Collectors.toList()));
        if(product.getSizes().size() > 0)
            setSizes(product.getSizes().stream().map(X->X.getId()).collect(Collectors.toList()));
        setFeatures(product.getFeatures().stream().map(x->x.getId()).collect(Collectors.toList()));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }

    public List<Long> getColors() {
        return colors;
    }

    public void setColors(List<Long> colors) {
        this.colors = colors;
    }

    public List<Long> getSizes() {
        if(sizes == null)
            sizes = new ArrayList<>();
        return sizes;
    }

    public void setSizes(List<Long> sizes) {
        this.sizes = sizes;
    }

    public List<Long> getFeatures() {
        if(features == null)
            features = new ArrayList<>();
        return features;
    }

    public void setFeatures(List<Long> features) {
        this.features = features;
    }

    public List<Features> getFeaturesDataList() {
        if(featuresDataList == null)
            featuresDataList = new ArrayList<>();
        return featuresDataList;
    }

    public void setFeaturesDataList(List<Features> featuresDataList) {
        if(featuresDataList == null)
            featuresDataList = new ArrayList<>();
        this.featuresDataList = featuresDataList;
    }
    public Date getLastdate() {
        return lastdate;
    }

    public void setLastdate(Date lastdate) {
        this.lastdate = lastdate;
    }

    public long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(long visitCount) {
        this.visitCount = visitCount;
    }

    public Product convert(){
        Product product = new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setDescription(getDescription());
        product.setPrice(getPrice());
        product.setImage(getImage());
        product.setEnable(isEnable());
        product.setExists(isExists());
        product.setAddDate(getLastdate());
        product.setVisitCount(getVisitCount());
        return product;
    }


}
