package application.database;

import application.model.QuizInsertModel;
import java.util.ArrayList;
import java.util.List;

public class MockData {
    private List<QuizInsertModel> list = new ArrayList();
    private final String TRUE = "true";
    private final String FALSE = "false";

    public MockData(){
        createEntries();
    }
    private void createEntries(){
        list.add(new QuizInsertModel("Helmut Kohl wurde 85 Jahre alt.", FALSE, FALSE, "Kohl wurde 87 Jahre alt."));
        list.add(new QuizInsertModel("Die 1. Olympischen Spiele fanden 1896 statt.", TRUE, FALSE, "Sie fanden im Juni 1896 statt."));
        list.add(new QuizInsertModel("Der letzte Porsche Traktor wurde 1976 gebaut.", FALSE, FALSE, "Er wurde 1963 gebaut."));
        list.add(new QuizInsertModel("Deutschland besteht aus 18 Bundesländern.", FALSE, FALSE, "Es sind 16."));
        list.add(new QuizInsertModel("Wachsen Zitronen an Bäumen?", TRUE, FALSE, "Zitronen wachsen an Bäumen."));
        list.add(new QuizInsertModel("Tick, Trick und Track tragen eine blaue, rote und grüne Mütze.", TRUE, FALSE, "Ja tragen sie."));
        list.add(new QuizInsertModel("Britney Spears startete Ihre Karriere erfolgreich in 2005", FALSE, FALSE, "Sie begann 1999"));
        list.add(new QuizInsertModel("Deutschland wurde zuletzt 2012 Fussball-Weltmeister.", FALSE, FALSE, "Zuletzt in 2014."));
        list.add(new QuizInsertModel("Seit dem 01.01.2002 gibt es in Deutschland das Euro Bargeld.", TRUE, FALSE, "Seit dem 01.01.2002."));
        list.add(new QuizInsertModel("Michael Jackson wurde 52 Jahre alt.", FALSE, FALSE, "Jackson wurde 50 Jahre alt."));

        list.add(new QuizInsertModel("Die Spice Girls sind eine Gruppe von 5 Frauen.", TRUE, FALSE, "Spice Grils besteht aus 5 Frauen."));
        list.add(new QuizInsertModel("Eine Fussballmannschaft stellt 11 Personen auf den Platz.", TRUE, FALSE, "11 Personen sind richtig."));
        list.add(new QuizInsertModel("Der Tag beginnt um 24 Uhr.", FALSE, FALSE, "Der Tag beginnt um 0.00Uhr"));
        list.add(new QuizInsertModel("Der Papst ist das Oberhaupt der evg. Kirche.", FALSE, FALSE, "Er ist das Oberhaupt der kathl. Kirche."));
        list.add(new QuizInsertModel("In Deutschland werden jedes Jahr ca 30 Millionen Weihnachtsbäume verkauft.", TRUE, FALSE, "Es sind ca. 30 Millionen."));
        list.add(new QuizInsertModel("Ist der Mond mehr als 400.000km von der Erde entfernt?", FALSE, FALSE, "Es sind 384.400km."));
        list.add(new QuizInsertModel("Der Mars ist der Erde von allen Planeten am nächsten.", FALSE, FALSE, "Es ist der Merkur."));
        list.add(new QuizInsertModel("Das zweitgrößte Land der Welt ist China.", FALSE, FALSE, "Es ist Kanada."));
        list.add(new QuizInsertModel("Johann Wolfgang von Goethe wurde in Frankfurt am Main geboren.", TRUE, FALSE, "Frankfurt am Main war sein Geburtsort."));
        list.add(new QuizInsertModel("Die Kanzlerschaft von Gerhard Schröder endete in 2005 .", TRUE, FALSE, "2005 wurde Schröder abgewählt."));

        list.add(new QuizInsertModel("Brasilien hat bereits 5x die Fussball WM gewonnen.", TRUE, FALSE, "Brasilien gewann die WM schon 5x."));
        list.add(new QuizInsertModel("Ist die Erde rund?", TRUE, FALSE, "Die Erde ist eine runde Kugel."));
        list.add(new QuizInsertModel("Quentin Tarantino war Regisseur von Pulp Fiction.", TRUE, FALSE, "Tarantino war der Regisseur."));
        list.add(new QuizInsertModel("Michael Schumacher hat in seiner Karriere mehr als 95 Rennen gewonnen.", FALSE, FALSE, "Es waren 91."));
        list.add(new QuizInsertModel("Die Hauptstadt Australiens ist Melbourne.", FALSE, FALSE, "Es ist Canberra."));
        list.add(new QuizInsertModel("Muhammad Ali war ein bekannter Schauspieler.", FALSE, FALSE, "Er war Boxer."));
        list.add(new QuizInsertModel("Will Smith war bei Beginn der Serie der Prinz von Bel Air 22 Jahre alt.", TRUE, FALSE, "Er war 22 Jahre alt."));
        list.add(new QuizInsertModel("Das erste Auto der Welt war ein Ford.", FALSE, FALSE, "Es war ein Mercedes."));
        list.add(new QuizInsertModel("Der Höchstgeschwindigkeit-Rekord eines Straßenautos liegt bei 447 km/h", TRUE, FALSE, "447km/h ist der Rekord."));
        list.add(new QuizInsertModel("Madeira gehört zu Spanien.", FALSE, FALSE, "Madeira gehört zu Portugal."));

        list.add(new QuizInsertModel("Windows 7 erschien im Jahr 2007.", FALSE, FALSE, "Windows 7 erschien im Oktober 2009."));
        list.add(new QuizInsertModel("Jeff Bezos ist der Gründer von Google.", FALSE, FALSE, "Bezos gründete Amazon."));
    }
    public List<QuizInsertModel> getList() {
        return list;
    }
}
