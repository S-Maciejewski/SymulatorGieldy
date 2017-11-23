package Model;

import java.util.Date;

public class Spolka {
    private String nazwa;
    private Date dataPierwszejWyceny;

    private double kursOtwarcia;
    private double kursAktualny;
    private double kursMinimalny;
    private double kursMaksymalny;
    private double zysk;            //TODO Aktualizacje zysku, przychodu i obrotów
    private double przychod;
    private double kapitalWlasny;
    private double kapitalZakladowy;
    private double obroty;
    private double stabilnoscKursu; //TODO Domyślne wartości? 5?

    private int wolumen;
    private int liczbaAkcji;
    private int liczbaAkcjiWObrocie;

    public void przeliczWartosciMinMax(){
        if(kursMinimalny>kursAktualny)
            kursMinimalny=kursAktualny;
        if(kursMaksymalny<kursAktualny)
            kursMaksymalny=kursAktualny;
    }

    public void ustawKursOtwarcia(){
        kursOtwarcia = kursAktualny;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Date getDataPierwszejWyceny() {
        return dataPierwszejWyceny;
    }

    public void setDataPierwszejWyceny(Date dataPierwszejWyceny) {
        this.dataPierwszejWyceny = dataPierwszejWyceny;
    }

    public double getKursOtwarcia() {
        return kursOtwarcia;
    }

    public void setKursOtwarcia(double kursOtwarcia) {
        this.kursOtwarcia = kursOtwarcia;
    }

    public double getKursAktualny() {
        return kursAktualny;
    }

    public void setKursAktualny(double kursAktualny) {
        this.kursAktualny = kursAktualny;
    }

    public double getKursMinimalny() {
        return kursMinimalny;
    }

    public void setKursMinimalny(double kursMinimalny) {
        this.kursMinimalny = kursMinimalny;
    }

    public double getKursMaksymalny() {
        return kursMaksymalny;
    }

    public void setKursMaksymalny(double kursMaksymalny) {
        this.kursMaksymalny = kursMaksymalny;
    }

    public double getZysk() {
        return zysk;
    }

    public void setZysk(double zysk) {
        this.zysk = zysk;
    }

    public double getPrzychod() {
        return przychod;
    }

    public void setPrzychod(double przychod) {
        this.przychod = przychod;
    }

    public double getKapitalWlasny() {
        return kapitalWlasny;
    }

    public void setKapitalWlasny(double kapitalWlasny) {
        this.kapitalWlasny = kapitalWlasny;
    }

    public double getKapitalZakladowy() {
        return kapitalZakladowy;
    }

    public void setKapitalZakladowy(double kapitalZakladowy) {
        this.kapitalZakladowy = kapitalZakladowy;
    }

    public double getObroty() {
        return obroty;
    }

    public void setObroty(double obroty) {
        this.obroty = obroty;
    }

    public int getWolumen() {
        return wolumen;
    }

    public void setWolumen(int wolumen) {
        this.wolumen = wolumen;
    }

    public int getLiczbaAkcji() {
        return liczbaAkcji;
    }

    public void setLiczbaAkcji(int liczbaAkcji) {
        this.liczbaAkcji = liczbaAkcji;
    }

    public int getliczbaAkcjiWObrocie() {
        return liczbaAkcjiWObrocie;
    }

    public void setliczbaAkcjiWObrocie(int liczbaAkcjiWObrocie) {
        this.liczbaAkcjiWObrocie = liczbaAkcjiWObrocie;
    }

    public double getStabilnoscKursu() {
        return stabilnoscKursu;
    }

    public void setStabilnoscKursu(double stabilnoscKursu) {
        this.stabilnoscKursu = stabilnoscKursu;
    }
}
