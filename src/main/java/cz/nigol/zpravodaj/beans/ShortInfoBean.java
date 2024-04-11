package cz.nigol.zpravodaj.beans;

import java.util.*;
import java.io.Serializable;

import javax.inject.*;
import javax.enterprise.context.*;
import javax.annotation.PostConstruct;

import cz.nigol.zpravodaj.entities.*;
import cz.nigol.zpravodaj.services.*;

@Named
@SessionScoped
public class ShortInfoBean implements Serializable {
    private static final long serialVersionUID = 9212583897602369531L;
    @Inject
    private ShortInfoService shortInfoService;
    private List<ShortInfo> shortInfos;
    private List<ShortInfo> shortInfosToday;
    private ShortInfo shortInfo;

    @PostConstruct
    public void init() {
      shortInfos = shortInfoService.getAll();
    }

    public void save() {
      shortInfoService.save(shortInfo);
    }

    public void newShortInfo() {
      shortInfo = new ShortInfo();
      shortInfo.setCreatedAt(new Date());
      shortInfo.setInfoDate(new Date());
    }

    public ShortInfo getShortInfo() {
      return this.shortInfo;
    }

    public void setShortInfo(ShortInfo shortInfo) {
      this.shortInfo = shortInfo;
    }

    public List<ShortInfo> getShortInfosToday() {
      return this.shortInfosToday;
    }

    public void setShortInfosToday(List<ShortInfo> shortInfosToday) {
      this.shortInfosToday = shortInfosToday;
    }

    public List<ShortInfo> getShortInfos() {
      return this.shortInfos;
    }

    public void setShortInfos(List<ShortInfo> shortInfos) {
      this.shortInfos = shortInfos;
    }
}
