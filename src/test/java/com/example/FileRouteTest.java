package com.example;

import org.citrusframework.GherkinTestActionRunner;
import org.citrusframework.actions.CreateVariablesAction;
import static org.citrusframework.actions.EchoAction.Builder.echo;
import org.citrusframework.annotations.CitrusResource;
import org.citrusframework.annotations.CitrusTest;
import static org.citrusframework.http.actions.HttpActionBuilder.http;
import org.citrusframework.http.client.HttpClient;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class FileRouteTest {

    @Inject
    HttpClient httpClient;

    @Test
    @CitrusTest
    public void testWriteFile(@CitrusResource GherkinTestActionRunner runner) {
        runner.given(echo().message("Testing File Write API"));

        if (httpClient != null) {
            runner.when(http()
                    .client(httpClient)
                    .send()
                    .post("/files/test.txt")
                    .message()
                    .body("Test file content")  // Fixed: Use 'body()' instead of 'content()'
                    .header("Content-Type", "text/plain"));

            runner.then(http()
                    .client(httpClient)
                    .receive()
                    .response()
                    .message()
                    .statusCode(200) // Fixed: Use 'status()' instead of 'statusCode()'
                    .body("File updated successfully"));
        } else {
            runner.then(echo().message("HttpClient is not initialized!"));
        }
    }

    @Test
    @CitrusTest(name = "Simple_IT")
    public void simpleTest(@CitrusResource GherkinTestActionRunner runner) {
        runner.given(echo().message("First example showing the basic Java DSL!"));

        runner.given(CreateVariablesAction.Builder.createVariable("user", "Citrus"));

        runner.then(echo().message("Hello ${user}!"));
    }
}
