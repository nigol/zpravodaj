# Tršický zpravodaj
Repozitář pro Tršický zpravodaj. Tršický zpravodaj je magazín obce Tršice. Existuje jak v tištěné, tak i online verzi. Tento repozitář obsahuje zdrojové kódy online verze magazínu.

## Changelog
### Verze 1.4

* Na stránce s kategorií článků je zobrazen název kategorie.
* Možnost zobrazit články autora.

### Verze 1.3

* Změněna patička webu.
* Upraveno CSS pro obrázky v článku.
* V administraci článků je možné označit, které články vyšli tiskem.

### Verze 1.2

* Upraveno formátování obrázků v článku, změněna patička webu, RSS feed přidán jako alternate do hlavičky.

### Verze 1.1

* Změněn vzhled úvodní strany. Nasazeno do produkce.

### Verze 1.0

* První dostupná verze.

## Nastavení

Je třeba nastavit správný resource pro přístup k databázi. Aplikace očekává, že bude nastaveno _zpravodajdb_. Toto je možné nastavit buď v souboru _/WEB-INF/resources.xml_ nebo přímo v _tomee.xml_ souboru. Zde je příklad nastavení pro HSQL databázi - v produkci pravděpodobně použijete jinou.

```
<Resource id="zpravodajdb" type="DataSource">
JdbcDriver = org.hsqldb.jdbcDriver
JdbcUrl = jdbc:hsqldb:file:hsqldb
</Resource>
```

V souboru _/WEB-INF/resources.xml_ je potřeba nastavit korektní URL aplikace. Například:

```
<Resource id="config" class-name="cz.nigol.zpravodaj.config.Configuration">
url = https://www.trsice.cz/zpravodaj/
</Resource>
```
