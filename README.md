# Tršický zpravodaj
Repozitář pro Tršický zpravodaj.

Nastavení:
V souboru _/WEB-INF/resources.xml_ je potřeba nastavit korektní URL aplikace. Například:

```
<Resource id="config" class-name="cz.nigol.zpravodaj.config.Configuration">
      url = https://www.trsice.cz/zpravodaj/
</Resource>
```