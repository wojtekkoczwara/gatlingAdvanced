package aceToys.pageObjects;

import aceToys.session.UserSession;
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class Products {

    private static FeederBuilder<Object> productFeeder =
            jsonFile("data/productDetails.json").random();

    public static ChainBuilder addProductToCart = exec(UserSession.increaseItemsInBasketForSession)
            .exec(
                            http("Add Poducts to Cart: Product Name #{name}")
                                    .get("/cart/add/#{id}").check(substring("You have " +
                                            "<span>#{itemsInBasket}</span> products in your Basket"))
                    )
            .exec(UserSession.increaseSessionBasketTotal);
    public static ChainBuilder loadProductDetailsPage = feed(productFeeder)
            .exec(
                    http("Load Products Details page - Product: #{name}")
                            .get("/product/#{slug}")
                            .check(css("#ProductDescription").isEL("#{description}"))
            );

//    public static ChainBuilder addProductToCart_productId4 = exec(
//            http("Add Poducts to Cart: Product Id: 4")
//                    .get("/cart/add/#{id}").check(substring("You have " +
//                            "<span>2</span> products in your Basket"))
//    );
//
//    public static ChainBuilder addProductToCart_productId5 = exec(
//            http("Add Poducts to Cart: Product Id: 5")
//                    .get("/cart/add/5").check(substring("You have " +
//                            "<span>3</span> products in your Basket"))
//    );


}
