package cz.nigol.zpravodaj.beans;

import javax.annotation.*;
import javax.enterprise.context.RequestScoped;
import javax.inject.*;
import java.util.List;

import cz.nigol.zpravodaj.config.Configuration;
import cz.nigol.zpravodaj.entities.*;
import cz.nigol.zpravodaj.qualifiers.LoggedUser;
import cz.nigol.zpravodaj.services.ArticleService;

@Named
@RequestScoped
public class AudioArticlesBean {
    @Inject
    private ArticleService articleService;
    @Resource
    private Configuration configuration;
    private List<Article> audioArticles;

    @PostConstruct
    public void init() {
      audioArticles = articleService.getArticlesWithAudio();
    }

    public List<Article> getAudioArticles() {
      return audioArticles;
    }

    public void setAudioArticles(List<Article> audioArticles) {
      this.audioArticles = audioArticles;
    }
}
