package com.harmew.uolhost.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Getter
public class CodenameService {

    private final RestTemplate restTemplate;
    private final Environment env;

    private final List<String> avengersCodenameList = new ArrayList<>();
    private final List<String> justiceLeagueCodenameList = new ArrayList<>();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CodenameService(RestTemplate restTemplate, Environment env) {
        this.restTemplate = restTemplate;
        this.env = env;
    }

    @PostConstruct
    public void loadJsonData() {
        try {
            String codinameResponse = restTemplate.getForObject(Objects.requireNonNull(env.getProperty("avengers")), String.class);
            JsonNode jsonNode = objectMapper.readTree(codinameResponse);
            ArrayNode avengers = (ArrayNode) jsonNode.get("vingadores");

            for (JsonNode item : avengers) {
                avengersCodenameList.add(item.get("codinome").asText());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void loadXMLData() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(Objects.requireNonNull(env.getProperty("justice.league")));

            NodeList codenameList = document.getElementsByTagName("codinome");

            for (int i = 0; i < codenameList.getLength(); i++) {
                Element codinameElement = (Element) codenameList.item(i);
                String codiname = codinameElement.getTextContent();
                justiceLeagueCodenameList.add(codiname);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
