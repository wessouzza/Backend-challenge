package com.user_register.user_register.service;

import java.util.ArrayList;
import java.util.List;

import com.user_register.user_register.exceptions.ErrorMsg;
import com.user_register.user_register.userDto.UserDto;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import jakarta.annotation.PostConstruct;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@Service
public class NicknameService {
    
    private final RestTemplate restTemplate;
    private final Environment env;

    ObjectMapper objectMapper = new ObjectMapper();

    private List<String> avengersList = new ArrayList<>();
    private List<String> justiceLeagueList = new ArrayList<>();


    public NicknameService(RestTemplate restTemplate, Environment env){
        this.restTemplate = restTemplate;
        this.env = env;
    }

    @PostConstruct
    public void readJson(){
        try{
            String dataResponse = restTemplate.getForObject(env.getProperty("avengers"), String.class);
            JsonNode jsonNode = objectMapper.readTree(dataResponse);
            ArrayNode avengers = (ArrayNode) jsonNode.get("vingadores");

            for(JsonNode item : avengers){
                this.avengersList.add(item.get("codinome").asText());
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void readXml(){
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dcBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = dcBuilder.parse(env.getProperty("justice.league"));

            NodeList nodeList = document.getElementsByTagName("codinome");

            for(int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                String codiname = node.getTextContent();
                this.justiceLeagueList.add(codiname);
            }

        }catch(Exception e){
            e.printStackTrace();;
        }
    }

    public static void checkNicknameList(List<String> nickList){
        if(nickList.isEmpty()){
            throw new ErrorMsg("Não há mais espaço na lista.");
        }
    }

    public List<String> getAvengersList() {
        return avengersList;
    }

    public List<String> getJusticeLeagueList() {
        return justiceLeagueList;
    }
}