package aceToys;

import aceToys.pageObjects.*;
import aceToys.session.UserSession;
import aceToys.simulation.TestPopulation;
import aceToys.simulation.TestScenario;
import aceToys.simulation.UserJourney;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class AceToysSimulation extends Simulation {

    public static final String TEST_TYPE = System.getProperty("TEST_TYPE", "INSTANT_USERS");
    private static final String DOMAIN = "acetoys.uk";

    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://" + DOMAIN)
            .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*detectportal\\.firefox\\.com.*"))
//    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
            .acceptEncodingHeader("gzip, deflate")
            .acceptLanguageHeader("pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7")
//    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
            ;

//  private Map<CharSequence, String> headers_0 = Map.ofEntries(
//    Map.entry("Sec-Fetch-Dest", "document"),
//    Map.entry("Sec-Fetch-Mode", "navigate"),
//    Map.entry("Sec-Fetch-Site", "same-origin"),
//    Map.entry("Sec-Fetch-User", "?1"),
//    Map.entry("Upgrade-Insecure-Requests", "1"),
//    Map.entry("sec-ch-ua", "Not?A_Brand\";v=\"8\", \"Chromium\";v=\"108\", \"Google Chrome\";v=\"108"),
//    Map.entry("sec-ch-ua-mobile", "?0"),
//    Map.entry("sec-ch-ua-platform", "Windows")
//  );
//
//  private Map<CharSequence, String> headers_7 = Map.ofEntries(
//    Map.entry("Accept", "*/*"),
//    Map.entry("Sec-Fetch-Dest", "empty"),
//    Map.entry("Sec-Fetch-Mode", "cors"),
//    Map.entry("Sec-Fetch-Site", "same-origin"),
//    Map.entry("X-Requested-With", "XMLHttpRequest"),
//    Map.entry("sec-ch-ua", "Not?A_Brand\";v=\"8\", \"Chromium\";v=\"108\", \"Google Chrome\";v=\"108"),
//    Map.entry("sec-ch-ua-mobile", "?0"),
//    Map.entry("sec-ch-ua-platform", "Windows")
//  );
//
//  private Map<CharSequence, String> headers_12 = Map.ofEntries(
//    Map.entry("Cache-Control", "max-age=0"),
//    Map.entry("Origin", "https://acetoys.uk"),
//    Map.entry("Sec-Fetch-Dest", "document"),
//    Map.entry("Sec-Fetch-Mode", "navigate"),
//    Map.entry("Sec-Fetch-Site", "same-origin"),
//    Map.entry("Sec-Fetch-User", "?1"),
//    Map.entry("Upgrade-Insecure-Requests", "1"),
//    Map.entry("sec-ch-ua", "Not?A_Brand\";v=\"8\", \"Chromium\";v=\"108\", \"Google Chrome\";v=\"108"),
//    Map.entry("sec-ch-ua-mobile", "?0"),
//    Map.entry("sec-ch-ua-platform", "Windows")
//  );


//    private ScenarioBuilder scn = scenario("aceToys")
//            .exec(UserSession.initSession)
//            .exec(StaticPages.homepage)
//            .pause(2)
//            .exec(StaticPages.ourStory)
//            .pause(2)
//            .exec(StaticPages.getInTouch)
//            .pause(2)
//            .exec(Category.productListByCategory)
//            .pause(2)
//            .exec(Category.cyclePagesOfProducts)
//            .pause(2)
//            .exec(Products.loadProductDetailsPage)
//            .pause(2)
//            .exec(Products.addProductToCart)
//            .pause(2)
//            .exec(Category.productListByCategory)
//            .pause(2)
//            .exec(Products.addProductToCart)
//            .pause(2)
//            .exec(Cart.viewCart)
//            .pause(2)
//            .exec(Cart.increaseQuantityInCart)
//            .pause(2)
//            .exec(Cart.increaseQuantityInCart)
//            .pause(2)
//            .exec(Cart.decreaseQuantityInCart)
//            .pause(2)
//            .exec(Cart.checkout)
//            .pause(2)
//            .exec(Customer.logout);

//    private ScenarioBuilder scn = scenario("Ace Toys simulation")
//            .exec(UserJourney.browserStore);
//    {
//        setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
//    }

//    {
//        setUp(TestScenario.highPurchaseLoadTest
//                .injectOpen(atOnceUsers(10))).protocols(httpProtocol);
//    }
//
    {
        if (TEST_TYPE == "INSTANT_USERS") {
            setUp(TestPopulation.instantUsers).protocols(httpProtocol);
        } else if(TEST_TYPE == "COMPLEX_INJECTION") {
            setUp(TestPopulation.complexInjection).protocols(httpProtocol);
        } else if (TEST_TYPE == "RAMP_USERS") {
        setUp(TestPopulation.rampUsers).protocols(httpProtocol);
        } else if (TEST_TYPE == "CLOSED_MODEL") {
        setUp(TestPopulation.closedModelInjection).protocols(httpProtocol);
        } else {
            setUp(TestPopulation.instantUsers).protocols(httpProtocol);
        }

    }
}
