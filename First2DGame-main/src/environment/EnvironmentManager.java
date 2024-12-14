// package environment;

// import java.awt.Graphics2D;

// import main.GamePanel;

// public class EnvironmentManager {

// 	GamePanel gp;
// 	public Lighting lighting;
	
// 	public EnvironmentManager(GamePanel gp) {
// 		this.gp = gp;
// 	}
// 	public void setup() {
// 		lighting = new Lighting(gp);
// 	}
// 	public void update() {
// 		lighting.update();
// 	}
// 	public void draw(Graphics2D g2) {
// 		lighting.draw(g2);
// 	}
// }

// package environment;

// import java.awt.*;
// import java.util.Random;

// import main.GamePanel;

// public class EnvironmentManager {
//     GamePanel gp;
//     public Lighting lighting;
//     private final int PARTICLE_COUNT = 300;
//     private Particle[] particles;
//     private final Random random = new Random();
    
//     public EnvironmentManager(GamePanel gp) {
//         this.gp = gp;
//     }

//     public void setup() {
//         lighting = new Lighting(gp);
//         initializeParticles();
//     }

//     private void initializeParticles() {
//         particles = new Particle[PARTICLE_COUNT];
//         for(int i = 0; i < PARTICLE_COUNT; i++) {
//             particles[i] = new Particle(gp);
//             particles[i].x = gp.player.worldX - gp.player.screenX + random.nextInt(gp.screenWidth);
//             particles[i].y = gp.player.worldY - gp.player.screenY + random.nextInt(gp.screenHeight);
//             initParticleProperties(particles[i]);
//         }
//     }

//     private void initParticleProperties(Particle p) {
//         if(gp.currentMap == 0) {
//             p.speed = 1 + random.nextDouble() * 2;
//             p.angle = random.nextDouble() * Math.PI * 2;
//             p.size = 2 + random.nextInt(2);
//         } else {
//             p.speed = 4 + random.nextDouble() * 3;
//             p.angle = Math.PI/6 + (random.nextDouble() - 0.5) * 0.2;
//             p.size = 1;
//         }
//     }

//     private void resetParticle(Particle p) {
//         p.x = gp.player.worldX - gp.player.screenX + random.nextInt(gp.screenWidth * 2) - gp.screenWidth/2;
//         p.y = gp.player.worldY - gp.player.screenY - random.nextInt(50);
//         initParticleProperties(p);
//     }

//     public void update() {
//         lighting.update();
        
//         for(Particle p : particles) {
//             if(gp.currentMap == 0) {
//                 p.y += p.speed * 0.5;
//                 p.x += Math.sin(p.angle) * 0.5;
//             } else {
//                 p.y += p.speed;
//                 p.x += Math.sin(p.angle) * 0.2;
//             }
            
//             if(p.y - gp.player.worldY + gp.player.screenY > gp.screenHeight) {
//                 resetParticle(p);
//             }
            
//             if(p.x - gp.player.worldX + gp.player.screenX < 0) {
//                 p.x = gp.player.worldX - gp.player.screenX + gp.screenWidth;
//             }
//             if(p.x - gp.player.worldX + gp.player.screenX > gp.screenWidth) {
//                 p.x = gp.player.worldX - gp.player.screenX;
//             }
//         }
//     }

//     public void draw(Graphics2D g2) {
//         lighting.draw(g2);

//         if(gp.currentMap == 0 || gp.currentMap == 1) {
//             g2.setColor(new Color(255, 255, 255, 180));
//             for(Particle p : particles) {
//                 int screenX = (int)(p.x - gp.player.worldX + gp.player.screenX);
//                 int screenY = (int)(p.y - gp.player.worldY + gp.player.screenY);
                
//                 if(screenX >= -10 && screenX <= gp.screenWidth + 10 && 
//                    screenY >= -10 && screenY <= gp.screenHeight + 10) {
//                     g2.fillOval(screenX, screenY, p.size, p.size);
//                 }
//             }
//         }
//         else if(gp.currentMap == 2 || gp.currentMap == 3) {
//             g2.setColor(new Color(130, 170, 255, 150));
//             for(Particle p : particles) {
//                 int screenX = (int)(p.x - gp.player.worldX + gp.player.screenX);
//                 int screenY = (int)(p.y - gp.player.worldY + gp.player.screenY);
                
//                 if(screenX >= 0 && screenX <= gp.screenWidth && 
//                    screenY >= 0 && screenY <= gp.screenHeight) {
//                     g2.drawLine(screenX, screenY, screenX, screenY + 8);
//                 }
//             }
//         }
//     }
// }

// class Particle {
//     double x, y;
//     double speed;
//     double angle;
//     int size;
    
//     public Particle(GamePanel gp) {
//         this.angle = Math.PI/6;
//         this.size = 2;
//     }
// }

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
    
    // Thêm các biến thời tiết
    private boolean isRaining = false;
    private boolean isSnowing = false;
    private float weatherIntensity = 0f;
    private boolean isWeatherChanging = false;
    private boolean isIncreasing = false;
    private final float FADE_SPEED = 0.005f;
    private boolean isInitialized = false;
    private int previousMap = -1;
    private final int WEATHER_DURATION = 3600;
    private final int WEATHER_PAUSE = 1200;
    private int weatherTimer = 0;
    private boolean isPaused = false;
    private boolean isDayChanging = false;
    private int previousDayState = -1;

    public EnvironmentManager(GamePanel gp) {
        this.gp = gp;
    }

    public void setup() {
        lighting = new Lighting(gp);
        initializeParticles();
        // Bắt đầu với thời tiết fade in
        if(gp.currentMap == 0) {
            isSnowing = true;
            isRaining = false;
        } else {
            isRaining = true;
            isSnowing = false;
        }
        weatherIntensity = 0f;
        isWeatherChanging = true;
        isIncreasing = true;
        isInitialized = true;
    }

    private void initializeParticles() {
        particles = new Particle[PARTICLE_COUNT];
        for(int i = 0; i < PARTICLE_COUNT; i++) {
            particles[i] = new Particle(gp);
            resetParticle(particles[i]);
        }
    }

    private void startWeatherChange() {
        // Không bắt đầu thời tiết mới nếu đang đêm
        if(lighting.dayState == lighting.night) {
            return;
        }

        int chance = random.nextInt(100);
        
        if(chance < 75) { // 75% có thời tiết
            if(gp.currentMap == 0) {
                isSnowing = true;
                isRaining = false;
            } else {
                isRaining = true;
                isSnowing = false;
            }
            weatherIntensity = 0f;
            isWeatherChanging = true;
            isIncreasing = true;
            reinitializeParticles();
            weatherTimer = 0;
            isPaused = false;
        } else {
            isWeatherChanging = true;
            isIncreasing = false;
        }
    }

    public void update() {
        if(!isInitialized) {
            return;
        }

        if(lighting != null) {
            lighting.update();
            
            // Kiểm tra chuyển đổi ngày/đêm
            if(previousDayState != lighting.dayState) {
                if(lighting.dayState == lighting.night && (isRaining || isSnowing)) {
                    // Nếu chuyển sang đêm và đang có thời tiết, bắt đầu fade out
                    isWeatherChanging = true;
                    isIncreasing = false;
                    isDayChanging = true;
                } else if(lighting.dayState != lighting.night && isDayChanging) {
                    // Nếu chuyển sang ngày và trước đó là đêm, có thể bắt đầu thời tiết mới
                    isDayChanging = false;
                    startWeatherChange();
                }
                previousDayState = lighting.dayState;
            }
        }
        
        // Update weather intensity
        if(isWeatherChanging) {
            if(isIncreasing) {
                weatherIntensity += FADE_SPEED;
                if(weatherIntensity >= 1.0f) {
                    weatherIntensity = 1.0f;
                    isWeatherChanging = false;
                }
            } else {
                weatherIntensity -= FADE_SPEED;
                if(weatherIntensity <= 0.0f) {
                    weatherIntensity = 0.0f;
                    isWeatherChanging = false;
                    if(!isPaused) {
                        isRaining = false;
                        isSnowing = false;
                    }
                }
            }
        }
        
        // Update weather timer
        if(!isWeatherChanging) {
            weatherTimer++;
            if(isPaused) {
                if(weatherTimer >= WEATHER_PAUSE) {
                    weatherTimer = 0;
                    isPaused = false;
                    startWeatherChange();
                }
            } else {
                if(weatherTimer >= WEATHER_DURATION) {
                    weatherTimer = 0;
                    isPaused = true;
                    isWeatherChanging = true;
                    isIncreasing = false;
                }
            }
        }
        
        // Update particles ngay cả khi là night
        if((isRaining || isSnowing) && !isPaused) {
            updateParticles();
        }
    }

    private void updateParticles() {
        for(Particle p : particles) {
            if(isSnowing) {
                p.y += p.speed * 0.5;
                p.x += Math.sin(p.angle) * 0.8;
            } else if(isRaining) {
                p.y += p.speed;
                p.x += Math.sin(p.angle) * 0.3;
            }
            
            if(p.y - gp.player.worldY + gp.player.screenY > gp.screenHeight) {
                resetParticle(p);
            }
            
            if(p.x - gp.player.worldX + gp.player.screenX < -20) {
                p.x = gp.player.worldX - gp.player.screenX + gp.screenWidth + 20;
            }
            if(p.x - gp.player.worldX + gp.player.screenX > gp.screenWidth + 20) {
                p.x = gp.player.worldX - gp.player.screenX - 20;
            }
        }
    }

    public void draw(Graphics2D g2) {
        if(!isInitialized) {
            return;
        }

        if(lighting != null) {
            lighting.draw(g2);
        }

        // Chỉ vẽ thời tiết khi KHÔNG phải là night
        if((isRaining || isSnowing) && lighting.dayState != lighting.night) {
            int alpha = getWeatherAlpha();
            if(alpha > 0) {
                drawWeatherEffects(g2, alpha);
            }
        }
    }

    private void drawWeatherEffects(Graphics2D g2, int baseAlpha) {
        for(Particle p : particles) {
            int screenX = (int)(p.x - gp.player.worldX + gp.player.screenX);
            int screenY = (int)(p.y - gp.player.worldY + gp.player.screenY);
            
            if(screenX >= -10 && screenX <= gp.screenWidth + 10 && 
               screenY >= -10 && screenY <= gp.screenHeight + 10) {
                
                if(isSnowing) {
                    g2.setColor(new Color(255, 255, 255, baseAlpha));
                    g2.fillOval(screenX, screenY, p.size, p.size);
                } else if(isRaining) {
                    g2.setColor(new Color(130, 170, 255, baseAlpha));
                    if(p.size > 1) {
                        g2.drawLine(screenX, screenY, screenX, screenY + p.length);
                        g2.drawLine(screenX + 1, screenY, screenX + 1, screenY + p.length);
                    } else {
                        g2.drawLine(screenX, screenY, screenX, screenY + p.length);
                    }
                }
            }
        }
    }

    private int getWeatherAlpha() {
        if(lighting.dayState == lighting.night) {
            return 0;
        } else if(lighting.dayState == lighting.dusk) {
            // Giảm dần alpha khi chuyển sang dusk
            return (int)(100 * weatherIntensity * (1 - lighting.filterAlpha));
        } else if(lighting.dayState == lighting.dawn) {
            // Tăng dần alpha khi chuyển sang dawn
            return (int)(100 * weatherIntensity * lighting.filterAlpha);
        } else { // day
            return (int)(180 * weatherIntensity);
        }
    }

    // Thêm debug info để kiểm tra
    public void printDebugInfo() {
        System.out.println("Weather Status:");
        System.out.println("isRaining: " + isRaining);
        System.out.println("isSnowing: " + isSnowing);
        System.out.println("weatherIntensity: " + weatherIntensity);
        System.out.println("isWeatherChanging: " + isWeatherChanging);
        System.out.println("isPaused: " + isPaused);
        System.out.println("currentMap: " + gp.currentMap);
    }

    private void reinitializeParticles() {
        if(particles == null) {
            particles = new Particle[PARTICLE_COUNT];
        }
        
        for(int i = 0; i < PARTICLE_COUNT; i++) {
            if(particles[i] == null) {
                particles[i] = new Particle(gp);
            }
            // Khởi tạo vị trí ngẫu nhiên trên toàn màn hình
            particles[i].x = gp.player.worldX - gp.player.screenX + random.nextInt(gp.screenWidth * 2) - gp.screenWidth/2;
            particles[i].y = gp.player.worldY - gp.player.screenY - random.nextInt(50);
            initParticleProperties(particles[i]);
        }
    }

    private void initParticleProperties(Particle p) {
        if(gp.currentMap == 0) { // Snow
            p.speed = 1 + random.nextDouble() * 2;
            p.angle = random.nextDouble() * Math.PI * 2;
            p.size = 2 + random.nextInt(2);
            p.alpha = 180 + random.nextInt(75);
        } else { // Rain
            p.speed = 4 + random.nextDouble() * 3;
            p.angle = Math.PI/6 + (random.nextDouble() - 0.5) * 0.2;
            p.size = 2;
            p.length = 10 + random.nextInt(5);
            p.alpha = 140 + random.nextInt(40);
        }
    }

    private void resetParticle(Particle p) {
        p.x = gp.player.worldX - gp.player.screenX + random.nextInt(gp.screenWidth * 2) - gp.screenWidth/2;
        p.y = gp.player.worldY - gp.player.screenY - random.nextInt(50);
        initParticleProperties(p);
    }
}

class Particle {
    double x, y;
    double speed;
    double angle;
    int size;
    int alpha;
    int length;
    
    public Particle(GamePanel gp) {
        this.angle = Math.PI/6;
        this.size = 2;
        this.alpha = 180;
        this.length = 10;
    }
}