package cz.nigol.zpravodaj.services;

import java.util.*;

import cz.nigol.zpravodaj.entities.*;

public interface ShortInfoService {
    List<ShortInfo> getAll();
    ShortInfo getByDate(Date date);
    ShortInfo save(ShortInfo shortInfo);
    void delete(ShortInfo shortInfo);
}
