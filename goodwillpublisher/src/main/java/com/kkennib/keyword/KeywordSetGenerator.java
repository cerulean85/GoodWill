package com.kkennib.keyword;

import com.kkennib.Util;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class KeywordSetGenerator {

    private String sites;
    private String operators;
    private String keywords;
    private String dates;
    private List<KeywordFormat> keywordSet;

    public KeywordSetGenerator(String sites, String operators, String keywords, String dates) {
        keywordSet = new ArrayList();
        this.sites = sites;
        this.operators = operators;
        this.keywords = keywords;
        this.dates = dates;
    }

    public List<KeywordFormat> generator() {

        String[] operArr = this.operators.split("/");
        String[] kwdArr = this.keywords.split("/");
        String[] dateArr = this.dates.split("/");
        String[] siteArr = this.sites.split("/");

        List<KeywordFormat> tmpKwdSet = new ArrayList();
        if(operArr.length == kwdArr.length && dateArr.length == 2 && siteArr.length > 0) {
            for(int i=0; i< operArr.length; i++) {
                KeywordFormat form = new KeywordFormat();
                form.setOperator(operArr[i]);
                form.setKeyword(kwdArr[i]);
                tmpKwdSet.add(form);
            }

            List<String> kwdStrSet = new ArrayList();
            kwdStrSet.add(tmpKwdSet.get(0).getKeyword());

            for(int i=1; i<tmpKwdSet.size(); i++) {

                int kwdStrSetSize = kwdStrSet.size();
                KeywordFormat kwdFormat = tmpKwdSet.get(i);
                String kwdStr = kwdFormat.getKeyword();
                if(kwdFormat.getOperator().equals("and")) {
                    for(int k=0; k<kwdStrSetSize; k++) {
                        kwdStrSet.set(k, kwdStrSet.get(k) + " " + kwdStr);
                    }
                }
                if(kwdFormat.getOperator().equals("or")) {
                    for(int k=0; k<kwdStrSetSize; k++) {
                        kwdStrSet.add(kwdStrSet.get(k) + " " + kwdStr);
                    }
                }
            //    System.out.println(kwdFormat.getOperator());
            //    System.out.println(kwdStrSet);
            }

            String startDate = dateArr[0];
            String endDate = dateArr[1];

            for(int i=0; i<kwdStrSet.size(); i++) {
                for(int k = 0; k< siteArr.length; k++) {
                    KeywordFormat kwdForm = new KeywordFormat();

                    String uSite = siteArr[k];
                    String uKeyword = kwdStrSet.get(i).replace(" ", "_");
                    String uStartDate = startDate;
                    String uEndDate = endDate;
                    String uTopicNameSrc = uSite + uKeyword + uStartDate + uEndDate + Util.getTimestamp();

                    kwdForm.setKeyword(uKeyword);
                    kwdForm.setSiteType(uSite);
                    kwdForm.setStartDate(uStartDate);
                    kwdForm.setEndDate(uEndDate);
                    kwdForm.setTopicName(Util.getMD5(uTopicNameSrc));
                    this.keywordSet.add(kwdForm);
                }
            }
        }

        return this.keywordSet;
    }



}
