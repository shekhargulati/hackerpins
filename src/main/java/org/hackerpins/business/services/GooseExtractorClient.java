package org.hackerpins.business.services;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@ApplicationScoped
public class GooseExtractorClient {

    private static final String CLIENT_URL = "http://gooseextractor-t20.rhcloud.com/api/v1/extract";

    public Map<String, String> fetchImageAndDescription(String url) {
        Client client = ClientBuilder.newClient();
        String response = client.target(CLIENT_URL).queryParam("url", url).request().get(String.class);

        JsonReader jsonReader = Json.createReader(new StringReader(response));
        JsonObject jsonObject = jsonReader.readObject();
        String bannerImage = jsonObject.getString("image");
        String description = jsonObject.getString("text");
        String title = jsonObject.getString("title");
        Map<String, String> fetchedData = new HashMap<>();
        fetchedData.put("picUrl", bannerImage);
        fetchedData.put("description", description);
        fetchedData.put("title", title);
        return fetchedData;
    }
}