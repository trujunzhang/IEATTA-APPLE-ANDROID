package org.wikipedia.analytics;

import com.ieatta.BaseApp;


public class PageFragmentFunnel extends Funnel {

    private static final String SCHEMA_NAME = "PageFragmentFunnel";
    private static final int REV_ID = 101020;

    public PageFragmentFunnel() {
        super(BaseApp.getInstance(), SCHEMA_NAME, REV_ID, SAMPLE_LOG_1K);
    }

    public void logArticleHeaderViewScrollY(int scrollY, int offset) {
        log("scrollY in the ArticleHeaderView", scrollY, "offset", offset);
    }

    public void logLoadMapView(String status) {
        log("Load map view", status);
    }

    public void logLoadLeadImage(String status) {
        log("Load Lead Image", status);
    }

    public void logNavigateActivated(boolean activated) {
        log("Navigation item on the menubar", activated);
    }

    public void logMapViewActivated(boolean activated) {
        log("Map view on the header view", activated);
    }

    public void logMapboxMarker(String title, String snippet) {
        log("title", title, "snippet", snippet);
    }

//    public void logLoadPage(HistoryEntry entry, int stagedScrollY) {
//        log("Load page: ", stagedScrollY);
//    }
}
