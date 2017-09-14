package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

//Nucleo do jogo, logica de um jogo generico
abstract public class Game implements WindowListener {
    private JFrame mainWindow;
    private boolean active = false;
    private BufferStrategy bufferStrategy;
    
    public Game() {
        this.mainWindow = new JFrame("Desenvolvendo Jogo em Java =D");
        this.mainWindow.setSize(500, 250);
        this.mainWindow.addWindowListener(this);
        
        this.active = false;
    }
    
    public void terminate() {
        this.active = false;
    }
    
    public void run() {
        this.active = true;
        load();
        
        //Game loop//FPS
        while(this.active) {
            update();
            render();
        }
        unload();
    }
    //Carrega a janela e recursos do jogo
    private void load() {
        this.mainWindow.setIgnoreRepaint(true);//Ignorar graficos vindos do SO
        this.mainWindow.setLocation(100, 50);
        this.mainWindow.setVisible(true);
        this.mainWindow.createBufferStrategy(2);//Estrategia de dois buffers, um sendo mostrado e outro sendo desenhado; o backbuffer se transforma no frontbuffer e vice-versa
        this.bufferStrategy = this.mainWindow.getBufferStrategy();
        
        onLoad();
    }
    //Atualiza os dados do jogo
    private void update() {
        onUpdate();
        Thread.yield();//Chamamos a Yield na Thread para que outros processos do sistema tambem rodem
    }
    //Atualiza os graficos do jogo de acordo com os dados atualizados em update()
    private void render() {
        Graphics2D g = (Graphics2D) this.bufferStrategy.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, this.mainWindow.getWidth(), this.mainWindow.getHeight());//Desenha um retangulo do tamanho da janela do jogo//Background
        onRender(g);
        g.dispose();//Libera os recursos dos sistema que estavam sendo usados
        this.bufferStrategy.show();
    }
    //Fecha e libera a janela
    private void unload() {
        onUnload();
        
        //Libera os recursos do sistemas que estavam sendo utilizados bufferStrategy e mainWindow
        this.bufferStrategy.dispose();
        this.mainWindow.dispose();
    }
    //Metodos abstratos nao precisam de corpo, pois seram usados de acordo com a necessidade de quem utilizara o core
    abstract public void onLoad();
    abstract public void onUnload();
    abstract public void onUpdate();
    abstract public void onRender(Graphics2D g);

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        terminate();
    }
    
    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
