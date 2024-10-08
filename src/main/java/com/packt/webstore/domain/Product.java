package com.packt.webstore.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.packt.webstore.validator.ProductId;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

//@XmlRootElement(name = "product")
    public class Product {
       @Pattern(regexp="P[0-9]+", message=" {Pattern.Product.productId.validation}")
       @ProductId
        private String productId;
        @NotNull(message="{NotNull.Product.name.validation}")
        private String name;
        @Min(value=0, message="Min.Product.unitPrice.validation}")
        @Digits(integer=8, fraction=2, message=" {Digits.Product.unitPrice.validation}")
        @NotNull(message= "{NotNull.Product.unitPrice.validation}")
        private BigDecimal unitPrice;
        private String description;
        private String manufacturer;
        private String category;
        private long unitsInStock;
        private long unitsInOrder;
        private boolean discontinued;
        private String condition;
//        @JsonIgnore
        private MultipartFile productImage;
        public Product() {
            super();
        }
        public Product(String productId, String name, BigDecimal unitPrice) {
            this.productId = productId;
            this.name = name;
            this.unitPrice = unitPrice;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public long getUnitsInStock() {
            return unitsInStock;
        }

        public void setUnitsInStock(long unitsInStock) {
            this.unitsInStock = unitsInStock;
        }

        public long getUnitsInOrder() {
            return unitsInOrder;
        }

        public void setUnitsInOrder(long unitsInOrder) {
            this.unitsInOrder = unitsInOrder;
        }

        public boolean isDiscontinued() {
            return discontinued;
        }

        public void setDiscontinued(boolean discontinued) {
            this.discontinued = discontinued;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }
//        @XmlTransient
//        @JsonIgnore
        public MultipartFile getProductImage() {
            return productImage;
        }

        public void setProductImage(MultipartFile productImage) {
            this.productImage = productImage;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            com.packt.webstore.domain.Product other = (com.packt.webstore.domain.Product) obj;
            if (productId == null) {
                if (other.productId != null)
                    return false;
            } else if (!productId.equals(other.productId))
                return false;
            return true;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((productId == null) ? 0 : productId.hashCode());
            return result;
        }

        @Override
        public String toString() {
            return "Product [productId=" + productId + ", name=" + name + "]";
        }
    }
