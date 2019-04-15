package com.gqk.protoss.model;

import com.gqk.protoss.entity.Image;
import com.gqk.protoss.entity.Theme;

public class ThemeImageModel {
    private Theme theme;
    private Image topicImage;
    private Image headImage;

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Image getTopicImage() {
        return topicImage;
    }

    public void setTopicImage(Image topicImage) {
        this.topicImage = topicImage;
    }

    public Image getHeadImage() {
        return headImage;
    }

    public void setHeadImage(Image headImage) {
        this.headImage = headImage;
    }
}
