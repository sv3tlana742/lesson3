package HomeworkMain.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {

    @JsonProperty("id")
    private String id;
    @JsonProperty("deletehash")
    private String deletehash;
    @JsonProperty("account_id")
    private Integer accountId;
    @JsonProperty("account_url")
    private String accountUrl;
    @JsonProperty("ad_type")
    private Object adType;
    @JsonProperty("ad_url")
    private Object adUrl;
    @JsonProperty("title")
    private Object title;
    @JsonProperty("description")
    private Object description;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("views")
    private Integer views;
    @JsonProperty("section")
    private Object section;
    @JsonProperty("vote")
    private Object vote;
    @JsonProperty("bandwidth")
    private Integer bandwidth;
    @JsonProperty("animated")
    private Boolean animated;
    @JsonProperty("favorite")
    private Boolean favorite;
    @JsonProperty("in_gallery")
    private Boolean inGallery;
    @JsonProperty("in_most_viral")
    private Boolean inMostViral;
    @JsonProperty("has_sound")
    private Boolean hasSound;
    @JsonProperty("is_ad")
    private Boolean isAd;
    @JsonProperty("nsfw")
    private Object nsfw;
    @JsonProperty("link")
    private String link;
    @JsonProperty("tags")
    private List<Object> tags = new ArrayList<Object>();
    @JsonProperty("datetime")
    private Integer datetime;
    @JsonProperty("mp4")
    private String mp4;
    @JsonProperty("hls")
    private String hls;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}