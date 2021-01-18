package com.kkennib.goodwill;
import lombok.Data;

@Data
public class KeywordFormat {
    private String operator;
    private String keyword;
    private String startDate;
    private String endDate;
    private String siteType;
    private String topicName;
    private String groupId;
    private String currentState;
    private String packagedFileName;
    private long    finishedWorkCount;
    private long    totalWorkCount;
}
