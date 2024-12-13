package environment;

import java.awt.*;
import java.util.Random;

import main.GamePanel;

public class EnvironmentManager {
    GamePanel gp;
    public Lighting lighting;
    private final int PARTICLE_COUNT = 300;
    private Particle[] particles;
    private final Random random = new Random();
    
    public EnvironmentManager(GamePanel gp) {
        this.gp = gp;
    }

    public void setup() {
        lighting = new Lighting(gp);
        initializeParticles();
    }

    private void initializeParticles() {
        particles = new Particle[PARTICLE_COUNT];
        for(int i = 0; i < PARTICLE_COUNT; i++) {
            particles[i] = new Particle(gp);
            particles[i].x = gp.player.worldX - gp.player.screenX + random.nextInt(gp.screenWidth);
            particles[i].y = gp.player.worldY - gp.player.screenY + random.nextInt(gp.screenHeight);
            initParticleProperties(particles[i]);
        }
    }

    private void initParticleProperties(Particle p) {
        if(gp.currentMap == 0) {
            p.speed = 1 + random.nextDouble() * 2;
            p.angle = random.nextDouble() * Math.PI * 2;
            p.size = 2 + random.nextInt(2);
        } else {
            p.speed = 4 + random.nextDouble() * 3;
            p.angle = Math.PI/6 + (random.nextDouble() - 0.5) * 0.2;
            p.size = 1;
        }
    }

    private void resetParticle(Particle p) {
        p.x = gp.player.worldX - gp.player.screenX + random.nextInt(gp.screenWidth * 2) - gp.screenWidth/2;
        p.y = gp.player.worldY - gp.player.screenY - random.nextInt(50);
        initParticleProperties(p);
    }

    public void update() {
        lighting.update();
        
        for(Particle p : particles) {
            if(gp.currentMap == 0) {
                p.y += p.speed * 0.5;
                p.x += Math.sin(p.angle) * 0.5;
            } else {
                p.y += p.speed;
                p.x += Math.sin(p.angle) * 0.2;
            }
            
            if(p.y - gp.player.worldY + gp.player.screenY > gp.screenHeight) {
                resetParticle(p);
            }
            
            if(p.x - gp.player.worldX + gp.player.screenX < 0) {
                p.x = gp.player.worldX - gp.player.screenX + gp.screenWidth;
            }
            if(p.x - gp.player.worldX + gp.player.screenX > gp.screenWidth) {
                p.x = gp.player.worldX - gp.player.screenX;
            }
        }
    }

    public void draw(Graphics2D g2) {
        lighting.draw(g2);

        if(gp.currentMap == 0) {
            g2.setColor(new Color(255, 255, 255, 180));
            for(Particle p : particles) {
                int screenX = (int)(p.x - gp.player.worldX + gp.player.screenX);
                int screenY = (int)(p.y - gp.player.worldY + gp.player.screenY);
                
                if(screenX >= -10 && screenX <= gp.screenWidth + 10 && 
                   screenY >= -10 && screenY <= gp.screenHeight + 10) {
                    g2.fillOval(screenX, screenY, p.size, p.size);
                }
            }
        }
        else if(gp.currentMap == 1) {
            g2.setColor(new Color(130, 170, 255, 150));
            for(Particle p : particles) {
                int screenX = (int)(p.x - gp.player.worldX + gp.player.screenX);
                int screenY = (int)(p.y - gp.player.worldY + gp.player.screenY);
                
                if(screenX >= 0 && screenX <= gp.screenWidth && 
                   screenY >= 0 && screenY <= gp.screenHeight) {
                    g2.drawLine(screenX, screenY, screenX, screenY + 8);
                }
            }
        }
    }
}

class Particle {
    double x, y;
    double speed;
    double angle;
    int size;
    
    public Particle(GamePanel gp) {
        this.angle = Math.PI/6;
        this.size = 2;
    }
}