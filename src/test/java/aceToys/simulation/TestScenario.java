package aceToys.simulation;

import io.gatling.javaapi.core.Choice;
import io.gatling.javaapi.core.ScenarioBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;

public class TestScenario {

    public static final Duration TEST_DURATION = Duration.ofSeconds(Integer
            .parseInt(System.getenv("DURATION")));

    public static ScenarioBuilder defaultLoadTest =
            scenario("Default Load Test")
                    .during(TEST_DURATION)
                    .on(randomSwitch()
                            .on(Choice.withWeight(60, exec(
                                    UserJourney.browserStore)),
                                    Choice.withWeight(30, exec(UserJourney.abandonBasket)),
                                    Choice.withWeight(10, exec(UserJourney.completePurchase))
                            )
                    );

    public static ScenarioBuilder highPurchaseLoadTest =
            scenario("High purchase Load Test")
                    .during(TEST_DURATION)
                    .on(randomSwitch()
                            .on(Choice.withWeight(30, exec(
                                    UserJourney.browserStore)),
                                    Choice.withWeight(30, exec(UserJourney.abandonBasket)),
                                    Choice.withWeight(40, exec(UserJourney.completePurchase))
                            )
                    );

}
