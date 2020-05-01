package almu.testing;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AppTest {

    @Test
    public void testApp() {
        // given
        Map<String, String> testCases = new HashMap<>();
        testCases.put("сапог", "[]");
        testCases.put("сапог болт", "[]");
        testCases.put("сапог болт биржа", "[б=[биржа, болт]]");
        testCases.put("сапог сарай арбуз болт бокс биржа", "[б=[биржа, бокс, болт], с=[сапог, сарай]]");
        App app = new App();

        for (Map.Entry<String, String> testCase: testCases.entrySet()) {
            // when
            String answer = app.run(testCase.getKey());

            // then
            assertEquals(answer, testCase.getValue());
        }
    }
}
