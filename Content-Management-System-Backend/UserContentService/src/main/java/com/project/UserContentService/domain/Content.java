package com.project.UserContentService.domain;

import org.springframework.data.annotation.Id;

public class Content {
    @Id
    private int contentId;
    private String title;
    private String body;

    public Content() {
    }

    public Content(int contentId, String title, String body) {
        this.contentId = contentId;
        this.title = title;
        this.body = body;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Content{" +
                "contentId=" + contentId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
