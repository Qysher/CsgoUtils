package sample;

import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SteamProfile {

    public static final String URL_STEAM_PROFILE = "https://steamcommunity.com/profiles/";
    public static final String URL_STEAM_AVATAR = "https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/";

    private static final String REGEX_STEAMID = "\"steamid\":\"(.*?)(?<!\\\\)\"";
    private static final String REGEX_PERSONANAME = "\"personaname\":\"(.*?)(?<!\\\\)\"";
    private static final String REGEX_SUMMARY = "\"summary\":\"(.*?)(?<!\\\\)\"";
    private static final String REGEX_AVATAR = "<img src=\"https:\\/\\/steamcdn-a\\.akamaihd\\.net\\/steamcommunity\\/public\\/images\\/avatars\\/(.*?)\">";

    public String profileURL = "";
    public String steamID = "";
    public String personaName = "";
    public String summary = "";
    public String avatarURL = "";
    public Image avatarImage = null;

    public SteamProfile() {

    }

    public SteamProfile query(String url) {
        try {
            String rawHtml = readWebsite(url);
            // Profile URL
            Matcher matcher_steamID = Pattern.compile(REGEX_STEAMID).matcher(rawHtml);
            if(matcher_steamID.find()) {
                this.steamID = matcher_steamID.group(1);
                this.profileURL = URL_STEAM_PROFILE + this.steamID;
            }

            // Persona Name
            Matcher matcher_personaName = Pattern.compile(REGEX_PERSONANAME).matcher(rawHtml);
            if(matcher_personaName.find()) this.personaName = matcher_personaName.group(1);

            // Summary
            Matcher matcher_summary = Pattern.compile(REGEX_SUMMARY).matcher(rawHtml);
            if(matcher_summary.find()) this.summary = matcher_summary.group(1);

            // Avatar
            Matcher matcher_avatar = Pattern.compile(REGEX_AVATAR).matcher(rawHtml);
            if(matcher_avatar.find()) this.avatarURL = URL_STEAM_AVATAR + matcher_avatar.group(1);
            try {
                avatarImage = new Image(new URL(this.avatarURL).openStream());
            } catch (Exception ignored) { }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public static String getSteamID(String profileUrl) {
        if(profileUrl.startsWith("https://steamcommunity.com/profiles/"))
            return profileUrl.replaceFirst("https://steamcommunity.com/profiles/", "");
        return regExWebsite(profileUrl, "\"steamid\":\"(.*?)\"", 1);
    }

    public static String regExWebsite(String url, String regex, int group) {
        try {
            String rawHtml = readWebsite(url);
            Matcher matcher = Pattern.compile(regex).matcher(rawHtml);

            if(matcher.find()) {
                return matcher.group(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String readWebsite(String url) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String currentLine;

            do {
                currentLine = reader.readLine();
                stringBuilder.append(currentLine).append("\n");
            } while(currentLine != null);

            reader.close();

            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
