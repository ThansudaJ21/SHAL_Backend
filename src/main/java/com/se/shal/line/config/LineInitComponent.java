package com.se.shal.line.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.objectmapper.ModelObjectMapper;
import com.linecorp.bot.model.richmenu.RichMenu;
import com.linecorp.bot.model.richmenu.RichMenuIdResponse;
import com.linecorp.bot.model.richmenu.RichMenuListResponse;
import com.linecorp.bot.model.richmenu.RichMenuResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.core.JsonParser.Feature.*;
import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static com.google.common.util.concurrent.Futures.getUnchecked;

@Component
@Slf4j
public class LineInitComponent {
    @Autowired
    protected LineMessagingClient lineMessagingClient;
    @Value("${line.bot.channel-token}")
    String channelAccessToken;


    String unRegisterMenuId = null;
    String mainMenuId = null;
    @SneakyThrows
    public void initLineApp(){

        String pathImageMainMenu = "line/richmenu/richmenu_shopping.jpg";
        String pathMenuJson = "line/richmenu/richmenu_shopping.json";

        String pathImageRegisterMenu = "line/richmenu/richmenu_register.jpg";
        String pathRegisterMenuJson = "line/richmenu/richmenu_shopping.json";
//         use reset when we need to remove the old one
        resetRichMenu();
        mainMenuId = loadLineMenu(pathMenuJson, pathImageMainMenu);
        unRegisterMenuId = loadLineMenu(pathRegisterMenuJson,pathImageRegisterMenu);

        lineMessagingClient.setDefaultRichMenu(unRegisterMenuId);
        listRichMenu(); // Show created Rich Menus

    }

    public void setMainMenuToUser(String userId){
        lineMessagingClient.linkRichMenuIdToUser(userId,mainMenuId);
    }

    public void changeMenu(int index){
        // use for new customer without creating a new menu
        RichMenuListResponse richMenuListResponse = getUnchecked(
                lineMessagingClient.getRichMenuList());
        List<RichMenuResponse> listMenus = richMenuListResponse.getRichMenus();
        LineMenuIdSingleton.getInstance().setHomeId(listMenus.get(index).getRichMenuId());
        lineMessagingClient.setDefaultRichMenu(listMenus.get(index).getRichMenuId());
    }
    private String loadLineMenu(String pathMenuJson, String pathMenuImage) throws IOException {
        String richMenuId;
        richMenuId = createRichMenu(pathMenuJson);
        LineMenuIdSingleton.getInstance().setHomeId(richMenuId);
        imageUploadRichMenu(richMenuId, pathMenuImage);
        return richMenuId;
    }

    public void resetRichMenu(){
        RichMenuListResponse richMenuListResponse = getUnchecked(
                lineMessagingClient.getRichMenuList());

        List<RichMenuResponse> listMenus = richMenuListResponse.getRichMenus();
        // remove old richmenu
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + channelAccessToken);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        listMenus.forEach(l -> {
                    String url = "https://api.line.me/v2/bot/richmenu/" + l.getRichMenuId();
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
                }
        );
    }
    private void imageUploadRichMenu(String richMenuId, String path) throws IOException {
        val imageClassPath = new ClassPathResource(path);
        String contentType = //getDefaultFileTypeMap().getContentType(imageClassPath.getFile());
                URLConnection.guessContentTypeFromName(imageClassPath.getFilename());
        log.info("Content-Type: {}", contentType);

        byte[] bytes = null;
        try (InputStream is = imageClassPath.getInputStream()) {
            bytes = ByteStreams.toByteArray(is);
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + channelAccessToken);
        headers.set("content-type","image/jpeg");
        HttpEntity<String> entity = new HttpEntity(bytes,headers);
        String url = "https://api-data.line.me/v2/bot/richmenu/" + richMenuId + "/content";
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        log.info("Successfully finished.");
        log.info("{}", responseEntity);
    }

    private String createRichMenu(String path) throws IOException {
        RichMenu richMenu = loadYaml(path);
        log.info("{}", richMenu);

        RichMenuIdResponse richMenuResponse = getUnchecked(
                lineMessagingClient.createRichMenu(richMenu));
        log.info("Successfully finished.");
        log.info("{}", richMenuResponse);
        return richMenuResponse.getRichMenuId();

    }
    private List<String> listRichMenu() {
        List<String> listMenuString = new ArrayList<>();

        RichMenuListResponse richMenuListResponse = getUnchecked(
                lineMessagingClient.getRichMenuList());
        List<RichMenuResponse> listMenus = richMenuListResponse.getRichMenus();
        log.info("You have {} RichMenus", listMenus.size());

        log.info("Successfully finished.");
        listMenus.forEach(richMenuResponse -> {
            listMenuString.add(richMenuResponse.getRichMenuId());
            log.info("{}", richMenuResponse);
        });

        return listMenuString;
    }

    private static RichMenu loadYaml(String path) throws IOException {
        final Yaml YAML = new Yaml();
        final ObjectMapper OBJECT_MAPPER = ModelObjectMapper
                .createNewObjectMapper()
                .configure(ALLOW_UNQUOTED_FIELD_NAMES, true)
                .configure(ALLOW_COMMENTS, true)
                .configure(ALLOW_SINGLE_QUOTES, true)
                .configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, true)
                .configure(INDENT_OUTPUT, true);

        Object yamlAsObject;
        try (InputStream is = (new ClassPathResource(path)).getInputStream()) {
            yamlAsObject = YAML.load(is);
        }

        return OBJECT_MAPPER.convertValue(yamlAsObject, RichMenu.class);
    }
}
