package br.com.calazans.shoppingcart.app;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.junit.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.logging.MockServerLogger;
import org.mockserver.socket.PortFactory;
import org.mockserver.socket.tls.KeyStoreFactory;

import javax.net.ssl.HttpsURLConnection;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class TestMockServer {

    private static ClientAndServer mockServer;

    @BeforeClass
    public static void startMockServer() {
        HttpsURLConnection.setDefaultSSLSocketFactory(new KeyStoreFactory(new MockServerLogger()).sslContext().getSocketFactory());
        mockServer = ClientAndServer.startClientAndServer(PortFactory.findFreePort());
    }

    @AfterClass
    public static void stopMockServer() {
        mockServer.stop();
    }

    @Test
    public void testUrl() {
        mockServer.when(request()
                .withPath("/v1/shopping-cart/create")
                .withMethod("POST"))
                .respond(response().withStatusCode(200));
    }

}
