package be.cegeka.domain;

public enum Severity {
    NORMAL,
    WORRYING,
    DANGEROUS;

    public static Severity selectMostSevere(Severity severity1, Severity severity2) {
        if (severity1 == null) {
            return severity2;
        }

        if (severity2 == null) {
            return severity1;
        }
        if (severity1.ordinal() > severity2.ordinal()) {
            return severity1;
        } else {
            return severity2;
        }
    }
}
