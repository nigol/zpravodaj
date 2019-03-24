package cz.nigol.zpravodaj.enums;

public enum Category {
    OBEC("Obec"),
    KULTURA("Kultura"),
    SKOLA("Škola"),
    SPOLKY("Spolky"),
    ZABAVA("Zábava & Zajímavosti"),
    JINE("Jiné");

    private String label;

    private Category(String label) {
        this.label = label;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }
}
