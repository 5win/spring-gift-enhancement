package gift.entity;

import gift.constants.ErrorMessage;
import gift.dto.ProductDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product extends BaseEntity {

    @Column(name = "name", length = 15, nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false)
    private long price;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Wishlist> wishlist = new ArrayList<>();

    public Product() {
    }

    public Product(ProductDto productDto) {
        this(productDto.getName(), productDto.getPrice(), productDto.getImageUrl());
    }

    public Product(String name, long price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<Wishlist> getWishlist() {
        return wishlist;
    }

    public void updateProduct(ProductDto productDto) {
        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.imageUrl = productDto.getImageUrl();
    }

    public void addWishlist(Wishlist wishlist) {
        if (wishlist == null) {
            throw new NullPointerException(ErrorMessage.NULL_POINTER_EXCEPTION_MSG);
        }
        this.wishlist.add(wishlist);
    }
}
