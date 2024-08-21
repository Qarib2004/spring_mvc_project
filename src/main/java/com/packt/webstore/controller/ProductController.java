package com.packt.webstore.controller;

import com.packt.webstore.domain.Product;
import com.packt.webstore.exeption.ProductNotFoundException;
import com.packt.webstore.repository.ProductRepository;
import com.packt.webstore.service.ProductService;
import com.packt.webstore.validator.ProductValidator;
import com.packt.webstore.validator.UnitsInStockValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private UnitsInStockValidator unitsInStockValidator;

    @Autowired
    private ProductValidator productValidator;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(Model model) {
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            System.out.println(product);
        }
        model.addAttribute("products", products);
        return "products";
    }

    @RequestMapping("/details")
    public String getProductDetails(@RequestParam("id") String productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "productDetails"; // Имя представления для страницы с подробной информацией о продукте
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddNewProductForm(Model model) {
        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);
        return "addProduct";
    }

    // Объединенный метод для обработки добавления продукта с поддержкой загрузки изображений
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddNewProductForm(@ModelAttribute("newProduct") Product productToBeAdded,
                                           BindingResult result, HttpServletRequest request) {
        // Проверка на наличие ошибок в валидации полей
        if (result.hasErrors()) {
            return "addProduct";
        }

        // Проверка на наличие подавленных полей
        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        // Проверка на существование продукта с таким же productId
        try {
            if (productService.getProductById(productToBeAdded.getProductId()) != null) {
                result.rejectValue("productId", "error.product", "Product with this ID already exists.");
                return "addProduct";
            }
        } catch (ProductNotFoundException e) {
            // Если продукт не найден, продолжаем добавление
        }

        // Обработка загрузки изображения
        MultipartFile productImage = productToBeAdded.getProductImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        if (productImage != null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(rootDirectory + "resourcesImg/images/" + productToBeAdded.getProductId() + ".png"));
            } catch (Exception e) {
                throw new RuntimeException("Product Image saving failed", e);
            }
        }

        // Добавление нового продукта
        productService.addProduct(productToBeAdded);
        return "redirect:/products";
    }

//    @PostMapping("/products/delete/{id}")
//    public String deleteProduct(@PathVariable("id") String productId) {
//        productService.deleteProductById(productId);
//        return "redirect:/products";
//    }
@RequestMapping(value = "/delete/{productId}", method = RequestMethod.GET)
public String deleteProduct(@PathVariable("productId") String productId) {
    productService.deleteProductById(productId);
    return "redirect:/products";  // После удаления перенаправляем на список продуктов
}

    @RequestMapping("/{category}")
    public String getProductsByCategory(Model model,
                                        @PathVariable("category") String productCategory) {
        model.addAttribute("products", productService.getProductsByCategory(productCategory));
        return "products";
    }

    @RequestMapping("/filter/{ByCriteria}")
    public String getProductsByFilter(@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> filterParams, Model model) {
        model.addAttribute("products", productService.getProductsByFilter(filterParams));
        return "products";
    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "product";
    }

    @RequestMapping("/{category}/price;low={low};high={high}")
    public String filterProducts(
            @PathVariable("category") String category,
            @RequestParam("low") int low,
            @RequestParam("high") int high,
            @RequestParam("manufacturer") String manufacturer,
            Model model) {

        List<Product> products = productService.getProductsByManufacturer(manufacturer);

        products = products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .filter(product -> product.getUnitPrice().compareTo(BigDecimal.valueOf(low)) >= 0 &&
                        product.getUnitPrice().compareTo(BigDecimal.valueOf(high)) <= 0)
                .collect(Collectors.toList());

        model.addAttribute("products", products);
        return "products";
    }


    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handleError(HttpServletRequest req,ProductNotFoundException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("invalidProductId", exception.getProductId());
        mav.addObject("exception", exception);
        mav.addObject("url",req.getRequestURL()+"?"+req.getQueryString());
        mav.setViewName("productNotFound");
        return mav;
    }

    @RequestMapping("/invalidPromoCode")
    public String invalidPromoCode() {
        return "invalidPromoCode";
    }



        @InitBinder
    public void initialiseBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("MMM d, YYYY");
        CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, orderDateEditor);
        binder.setAllowedFields("productId", "name", "unitPrice", "description", "manufacturer", "category", "unitsInStock", "productImage");
           // binder.setAllowedFields();
            binder.setValidator(unitsInStockValidator);
            binder.setValidator(productValidator);

    }

}










//    @RequestMapping(value = "/all", method = RequestMethod.GET)
//    public String list(Model model) {
//            model.addAttribute("products", productService.getAllProducts());
//            return "products";
//    }