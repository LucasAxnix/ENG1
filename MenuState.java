import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class MenuState extends GameState{

    private BufferedImage background;
    private BufferedImage startGame;
    private JButton startButton;

    public MenuState(GameStateManager gsm){
        super(gsm);
    }

    @Override
    public void initImages() {
        try{
            background = ImageIO.read(getClass().getResource("/Resources/MenuStateBackground.png"));
            startGame = ImageIO.read(getClass().getResource("/Resources/MenuStateStartGame.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initButtons(){
        startButton = new JButton(new ImageIcon(getClass().getResource("/Resources/MenuStateStartGame.png")));
        startButton.setBounds(150, 200, 167, 47);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gsm.setState(1);
            }
        });
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background,0,0,null);
    }

    @Override
    public void update() {    }

    @Override
    public void showButtons(){
        Game.instance.add(startButton);
    }

    @Override
    public void hideButtons(){
        Game.instance.remove(startButton);
    }
}
