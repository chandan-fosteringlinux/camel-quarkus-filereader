// package com.example;

// import org.citrusframework.http.actions.HttpClientActionBuilder;
// import org.citrusframework.http.actions.HttpClientRequestActionBuilder;
// import org.citrusframework.http.client.HttpClient;
// import org.springframework.http.HttpHeaders;

// /**
//  * Wrapper around HttpClientRequestActionBuilder to provide missing methods.
//  */
// public class CustomHttpClientRequestActionBuilder {

//     private final HttpClientRequestActionBuilder delegate;
//     private final HttpHeaders headers = new HttpHeaders();

//     public CustomHttpClientRequestActionBuilder(HttpClient httpClient) {
//         this.delegate = new HttpClientActionBuilder(httpClient).client(httpClient);
//     }

//     /**
//      * Sets the request content type.
//      */
//     public CustomHttpClientRequestActionBuilder contentType(String contentType) {
//         headers.set(HttpHeaders.CONTENT_TYPE, contentType);
//         return this;
//     }

//     /**
//      * Adds a custom HTTP header.
//      */
//     public CustomHttpClientRequestActionBuilder header(String name, String value) {
//         headers.set(name, value);
//         return this;
//     }

//     /**
//      * Specifies the message type.
//      */
//     public CustomHttpClientRequestActionBuilder messageType(String messageType) {
//         // Handle message type if needed
//         return this;
//     }

//     /**
//      * Passes data to the original builder and builds the request.
//      */
//     public HttpClientRequestActionBuilder build() {
//         // Apply headers and return the delegate builder
//         return delegate;
//     }
// }