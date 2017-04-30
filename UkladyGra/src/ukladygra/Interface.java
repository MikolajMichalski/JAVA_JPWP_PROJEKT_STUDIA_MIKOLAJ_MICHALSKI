/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ukladygra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *  Klasa rozszerzająca klasę JFrame, odpowiada za tworzenie okna,
 * ustawione są w niej podstawowe parametry okna taie jak wielkość lub Layout
 * @author Mikołaj
 */
public class Interface extends JFrame {

    Interface() {
        super("Uklady");
        setVisible(true);
        setResizable(true);
        setSize(1280, 1024);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

    }

}
/**
 * Klasa rozszerzająca klasę JPanel, 
 * zawiera przyciski wraz z ich funkcjami
*/
class Menu extends JPanel {
/**
 * pola klasy Przycisk, która rozszerza JButton
 */
    private Przycisk inform;
    private Przycisk start;
    private Przycisk pause;
    private Przycisk restart;
    private Przycisk table;
    /**
    * pole klasy choice, umożliwa wybór trybu gry 
    */
    public Choice picker;
    /**
    * odnośnik do GameArea czyli pola, w którym toczy się gra
    */
    private GameArea gA;
   /**
    *   konstruktor klasy Menu, 
    *   ustawia podstawowe parametry panelu oraz dodaje przyciski wraz z ich ikonami
    *   @param gameArea
    */
    public Menu(GameArea gameArea) {
        /**
        * ustawienie odnosnika na GameArea 
         */ 
        gA = gameArea;
        /**
        * ustawienie parametrów JPanelu, layout, wielkosc 
        */
        setPreferredSize(new Dimension(150, 1024));
        setLayout(new FlowLayout());
        /**
        * utworzenie linii na granicy panelu 
        */
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
/**
 * wczytanie ikon dla przycików
 */
        ImageIcon i1 = new ImageIcon("press_play_button.png");
        ImageIcon i2 = new ImageIcon("pause-button.png");
        ImageIcon i3 = new ImageIcon("refresh-page-arrow-button.png");
        ImageIcon i4 = new ImageIcon("information.png");
        ImageIcon i5 = new ImageIcon("crown.png");
       /**
 * wczytanie przycisku start
 * @param i1
 */
        start = new Przycisk(i1);
        /**
        * utworzenie przycisku Pause
        * @param i2
        */
        pause = new Przycisk(i2);
        /**
        * utworzenie przycisku restart
        * @param i3
        */
        restart = new Przycisk(i3);
         /**
        * utworzenie przycisku informacji
        * @param i4
        */
        inform = new Przycisk(i4);
                /**
        * utworzenie przycisku Tabeli wyników
        * @param i5
        */
        table = new Przycisk(i5);
        /**
         * utworzenie panelu wyboru
         */
        picker = new Choice();
        /**
         * dodanie opcji do wyboru
         */
        picker.add("Pokarmowy");
        picker.add("Nerwowy i zmysłów");
        picker.add("Kostny");
        /**\
         * dodanie powyższych elementów do panelu Menu
         */
        add(start);
        add(new JLabel("Wybierz układ"), FlowLayout.CENTER);
        add(picker);
        add(pause);
        add(restart);
        add(inform);
        add(table);
        /**
         * utworzenie Akcji dla przycisku start
         */
        start.addActionListener((ActionEvent e) -> {
            /**
             * wywołanie funkcji Start w panelu GameArea
             * 
             */
            gA.Start(picker.getSelectedIndex());            
            //jeśli ilość kliknięć jest równa zero wówczas nalezy restartować grę
            if(gA.check.clickCount <= 0 ){
                gA.gO.Restart();
                gA.addMouseListener(gA);
                gA.check.clickCount = 6;
                gA.check.score = 0;
            }
            /**
             * jeśli gra zostałą zatrzymana w trakcie 
             * rozgrywki, zostaje ona wznowiona, 
             * w panelu GameArea wyświetlana jest etykieta Label z pozostałą licbą kliknięć
             */
            gA.gameOver.setText("Pozostało kliknięć: " + gA.check.clickCount);
            gA.gameOver.setEnabled(true);
            

        });
        /**
         * dodanie akcji dla przycisku pause
         */
        pause.addActionListener((ActionEvent e) -> {
            /**
             * wywołanie funkcji stopującej grę w panelu GameArea
             */
            
            gA.Stop(); 
        });

       /**
        * dodanie akcji dla przycisku restart
        */
        restart.addActionListener((ActionEvent e) -> {
            /**
             * zeruje etykietę z wynikiem
             */
            gA.pointsLabel.setText("Score: 0");  
            /**
             * wyzerowanie wartości score w klasie checker
             */
            gA.check.score = 0;
            /**
             * wywołenie funkcji restert
             */
            gA.gO.Restart();
            /**
             * dodanie akcji dla kliknięcia w panelu GameArea, ponieważ usuwa się ono po zakończeniu gry
             * @param gA
             */
            gA.addMouseListener(gA);
            /**
             * ustawienie wartości clickCount w klasie Checker na 6 
             */
            gA.check.clickCount = 6;
            gA.gameOver.setEnabled(true);
            /**
             * ustawienie etykiety z kliknięciami na początkowy stan
             */
            gA.gameOver.setText("Pozostało kliknięć: 6");
            /**
             * wystartowanie timera
             */
            gA.timer.start();
           
        });
        /**
         * dodanie akcji dla przycisku Informacji
         */
        inform.addActionListener((ActionEvent e) ->{
            /**
             * jesli timer jest zatrzymany, czyli gra jest zastopowana tworzy obiekt klasy LoadInfo
             */
            if(gA.timer.isRunning() == false){
            LoadInfo lI = new LoadInfo(this);
            
            
            }
        });

    }
}
/**
 * klasa rozszerzająca JButton, ustaiwa parametry wszystkich przycisków
 * 
 */
class Przycisk extends JButton {
/**
 * konstruktor klasy Przycisk
 * @param i 
 */
    Przycisk(ImageIcon i) {
        super(i);
        Dimension d = new Dimension(100, 100);
        setPreferredSize(d);
        setContentAreaFilled(false);
        setBorderPainted(false);

    }

}
/**
 * klasa rozszerzająca JPanel oraz implementująca MouseListener
 * 
 */
class GameArea extends JPanel implements MouseListener {
    /**
     * utworzenie obiektu klasy Menu
     */
    Menu menu = new Menu(this);
    /**
     * utworzenie obiektu klasy GameObjects
     */
    public GameObjects gO = new GameObjects(menu);
    /**
     * zmienna przechowująca wartość wybranego trybu gry
     */
    public int modeNumber;
    /**
     * etykieta wyświetlająca wynik
     */
    public Label pointsLabel = new Label("Score: 0");
    /**
     * etykieta wyświetlana po zakończeniu gry
     */
    public Label gameOver = new Label();    
    ArrayList<Point> locations = new ArrayList<>();
    /**
     * utworzenie obiektu klasy ObjectMover, odpowaidającej za poruszanie elementów
     */
    ObjectMover om = new ObjectMover(gO, this);
    /**
     * Utworzenie obiektu klasy Checker sprawdzającej trafienia i naliczającej punkty
     * 
     */
    public Checker check = new Checker(gO, this);
/**
 * konstruktor klasy GameArea,
 * utawia parametry panelu oraz dodaje etykiety do panelu, a takze dodaje Akcję dla kliknięcia myszki oraz startuje timer
 * 
 */
    
    public GameArea() {
        setPreferredSize(new Dimension(1115, 1024));
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black));
        setLayout(new FlowLayout());
        setBackground(Color.YELLOW);
        this.add(pointsLabel);
        this.add(gameOver);
        
        
        
        this.addMouseListener(this);

       
        timer = new Timer(10, (ActionEvent e) -> {
            om.run();
        });

    }
    /**
     * pole Timera, tworzy odwołanie do timera
     */
    Timer timer;
/**
 * funkcja startująca timer oraz uruchamia grę w zaleznosci od trybu
 * @param modeNumber 
 */
    public void Start(int modeNumber) {
        timer.start();
        this.modeNumber = modeNumber;
        

    }
/**
 * funkcja pauzująca grę
 */
    public void Stop() {
        /**
         * stopowanie timera
         */
        timer.stop();
    }
   /**
    * funkcja rysyjąca elementy gry w panelu GameArea
    * @param g 
    */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

       /**
        * dla każdego elemetu z listy obiektów gObjects rysuje obiekt w podanych współrzędnych względem panelu GameArea
        */
        for (int i = 0; i < gO.gObjects.size(); i++) {
            if (gO.doDraw.get(i) == false) {
                continue;
            }
            g.drawImage(gO.gObjects.get(i), gO.locations.get(i).x, gO.locations.get(i).y, this);
        }

    }
/**
 * utworzenie w panelu akcji dla kliknięcia myszką
 * @param e 
 */
    @Override
    public void mouseClicked(MouseEvent e) {
       /**
        * pobranie współrzędnych kliknięcia
        */
       
        int x = e.getX();
        int y = e.getY();
        /**
         * wywołanie funkcji CommpareLoc w klasy Checker 
         */
        check.CompareLoc(x, y);
        /**
         * jeśli liczba kliknięc rowna się zero to usuń MouseListener, co uniemozliwia dalsze znikanie elementow
         * wyswietl etykietę z koncowym wynikiem
         * stopuj timer
         */
        if(check.clickCount <= 0 ){
             this.pointsLabel.setText("Final Score: "+ Integer.toString(this.check.score));   
             this.pointsLabel.setSize(100, 20);
             this.gameOver.setVisible(false);
             this.removeMouseListener(this);
             this.timer.stop();
            
        }
    
                 
        
    }
/**
 * implementacja wszystkich metod abstrakcyjnych dla MouseListenera
 * @param e 
 */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
/**
 * klasa rozszerzjąca JFrame wyświetla okna z informacjami o 
 * ukladach w zaleznosci od wybranego układu
 * @author Mikołaj
 */
class LoadInfo extends JFrame{
    JTextArea pokarmowy = new JTextArea();
    JTextArea nerwowy = new JTextArea();
    JTextArea kostny = new JTextArea();
    Menu choice;
    LoadInfo(Menu choice){
    this.choice = choice;
    this.setBackground(Color.YELLOW);
    this.setSize(500, 500);
    this.setLayout(new FlowLayout()); 
    if(choice.picker.getSelectedIndex() == 0){
    this.add(pokarmowy, FlowLayout.LEFT);
    
    pokarmowy.setEditable(false);
    pokarmowy.setText("W skład układu pokarmowego wchodzą: \n" +
                      "Przewód pokarmowy:\n" +
                      "       -jama ustna\n" +
                      "       -gardło\n" +
                      "       -przełyk\n" +
                      "       -żołądek\n" +
                      "       -jelito cienkie\n" +
                      "       -jelito grube\n" +
                      "Gruczoły:\n" +
                      "       -wątroba,\n" +
                      "       -trzustka\n\n\n WSZYSTKIE GRAFIKI UŻYTE W GRZE POBRANO ZE STRONY http://www.flaticon.com, \n Autorami są: Freepik oraz Yannick" );
    pokarmowy.setBackground(Color.YELLOW);
    }
    if(choice.picker.getSelectedIndex() == 1){
    this.add(nerwowy, FlowLayout.LEFT);
    nerwowy.setText("W skład układu nerwowego wchodzą: \n" +
                    "       -mózgowie,\n" +
                    "       -móżdżek,\n" +
                    "       -rdzeń przedłużony,\n" +
                    "       -rdzeń kręgowy,\n" +
                    "       -nerwy i zwoje układu współczulnego\n" +
                    "       -nerwy i zwoje układu przywspółczulnego\n" +
                    "Nerwy składają się z neuronów, które tworzą synapsy.\n\n\n" +
                    "Narządami zmysłów są:\n" +
                    "       -oczy,\n" +
                    "       -uszy,\n" +
                    "       -receptory czucia,\n" +
                    "       -nos,\n" +
                    "       -kubki smakowe na języku. \n\n WSZYSTKIE GRAFIKI UŻYTE W GRZE POBRANO ZE STRONY http://www.flaticon.com, \n Autorami są: Freepik oraz Yannick" );
    nerwowy.setSize(500,500);
    nerwowy.setEditable(false);
    nerwowy.setBackground(Color.YELLOW);
    }
    if(choice.picker.getSelectedIndex() == 2){
    this.add(kostny, FlowLayout.LEFT);
    kostny.setText("W skład układu kostnego wchodzą:\n" +
                    "       -czaszka,\n" +
                    "       -kręgosłup,\n" +
                    "       -obręcz barkowa,\n" +
                    "       -kości kończyny górnej (kość ramienna, kość promieniowa, kość  łokciowa\n" +
                    "       kości  nadgarstka, kości śródręcza, paliczki),\n" +
                    "       -obręcz miednicza,\n" +
                    "       -kości kończyny  dolnej (kość udowa, kość piszczelowa, kość strzałkowa, \n" +
                    "       kości stępu, kości śródstopia, paliczki),\n" +
                    "       oraz stawy, np:\n" +
                    "       -łokciowy,\n" +
                    "       -barkowy,\n" +
                    "       -kolanowy.\n\n WSZYSTKIE GRAFIKI UŻYTE W GRZE POBRANO ZE STRONY http://www.flaticon.com, \n Autorami są: Freepik oraz Yannick");
    }   
    kostny.setEditable(false);
    kostny.setBackground(Color.YELLOW);
    setVisible(true);
    
    }
}
/**
 * klasa wczytująca wszystkie poprzednie elementy 
 * @author Mikołaj
 */
class LoadInterface {

    private final GameArea p2;
    /**
     * utworzenie obiektu interface czyli okna gry
     */
    private final Interface i = new Interface();
    
    
    /**
     * konstruktor klasy LoadInterface
     */
    LoadInterface() {

        
/**
 * utworzenie obiektów klas Menu oraz GameArea
 */
        p2 = new GameArea();
        Menu p1 = new Menu(p2);
/**
 * dodanie paneli do okna
 */
        i.add(p1, BorderLayout.WEST);
        i.add(p2, BorderLayout.EAST);
        i.validate();

    }
   
}
