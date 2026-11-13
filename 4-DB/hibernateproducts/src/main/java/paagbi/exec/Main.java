package paagbi.exec;

import java.util.List;

import paagbi.model.product.Product;
import paagbi.model.product.ProductAccess;

public class Main {

    public static void main(String[] args) {
        // Create a new product
        Product product = new Product("Porruak",2.5);
        
         // Save the product
         ProductAccess.save(product);

    //     // Fetch and display a product
    //     Product fetchedProduct = ProductAccess.getProductById(4);
    //     System.out.println("Fetched Product: " + fetchedProduct);

    //     // Display al products
         List<Product> products = ProductAccess.getAllProducts();
         for (Product p:products){
             System.out.println(p);
         }

    //     // Update the product
    //     ProductAccess.updateProduct(fetchedProduct);
    //     ProductAccess.updateProduct(8);
        
    //     // Delete the product
    //     //ProductAccess.deleteProduct(fetchedProduct);
    //     Product p = new Product("x",3);
    //     p.setId(11);
    //     ProductAccess.deleteProduct(p);

     }

 
   
}
