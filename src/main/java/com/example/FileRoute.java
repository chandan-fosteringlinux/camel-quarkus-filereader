package com.example;

import org.apache.camel.builder.RouteBuilder;

public class FileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Configure REST to use Quarkus' platform-http
        restConfiguration()
            .component("platform-http")
            .port(8080)
            .enableCORS(true);

        // Define REST endpoints
        rest("/file")
            // GET endpoint to read a file
            .get("/{filename}")
                .produces("text/plain")
                .to("direct:readFile") // Route to a direct endpoint
            // POST endpoint to write a file
            .post("/{filename}")
                .consumes("text/plain")
                .to("direct:writeFile"); // Route to a direct endpoint

        // Read file route
        // Read File Route (GET)
        from("direct:readFile")
            .log("Reading file: ${header.filename}")
            // Read the file using pollEnrich (consumer)
            .pollEnrich()
                .simple("file:{{file.directory}}?fileName=${header.filename}&noop=true&idempotent=false")
                .timeout(1000)
            .choice()
                .when(body().isNotNull())
                    .log("File read: ${header.filename}")
                .otherwise()
                    .setBody(constant("File not found"))
                    .log("File not found: ${header.filename}");

        // Write file route
        from("direct:writeFile")
            .log("Writing file: ${header.filename}")
            .setHeader("CamelFileName", simple("${header.filename}"))
            .toD("file:{{file.directory}}") // Write file
            .log("File written successfully")
            .setBody(constant("File updated successfully"));
    }
}