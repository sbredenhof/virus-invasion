import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class game extends PApplet {

int scene = 0; //0 = title, 1 = level 1...
int unlockedLevel = 1;
int coins = 0;

public void setup() {
  background(0);
  textSize(40);
  text("loading...", 100, 100); //loading the font takes a second so it says loading instead of showing a blank screen
   //set window size
   //makes pixel art actually look like pixel art
  loadTextures(); //check textures tab
  setEnemies();
  setCoins();
}

public void draw() {
println(walkingCounter);
  if (paused == false) { //only run everything if the game is unpaused
    background(255, 255, 255);

    if (scene == 0) { //title scene
      title(); //set up buttons and stuff
    }

    if (scene == 4) { //choose a level
      levelMenu();
    }

    if (scene == 5) {//you win
      button(0, 0, 900, 600, "home and unlock new level", youWinBackground);
      textFont(pixelfont);
      textSize(75);
      fill(0);
      text(score, 412, 375); //coordinates are for bottom corner of text not top like most
      hasVaccine = false;
    }

    if (scene == 6) { //game over
      button(0, 0, 900, 600, "home", gameOverBackground); 
      textFont(pixelfont);
      textSize(60);
      fill(255, 25, 25);
      text(score, 400, 435); //coordinates are for bottom corner of text not top like most
      wearingMask = false;
      ownGun = false;
      hasVaccine = false;
    }

    if (scene == 1) {
      level1();
    }

    if (scene == 2) {
      level2();
    }

    if (scene == 3) {
      level3();
    }
    
    if (scene == 7) {
      shop(); //see title tab
    }
    
    mouseClicked = false;
    
  } else { //pause menu
  
    button(width/2-250, height/2-100, 500, 200, "unpause", playButton);
    button(width/2-100, 500, 200, 100, "home", backButton);
    
  }
}
float scrollX;
int speed = 6; 
int groundcolor;
int lavaColor = -32985;
boolean movingRight;
boolean movingLeft;
boolean finishedLevel;
boolean shooting;
boolean facingRight;
public void level1() {

  image(level1background, scrollX, 0, level1background.width*6, 600); //makes the image's location scroll as a background when you move. it stretches it to 6x the size

  groundcolor=-8421505;

  character();

  showgui();  //see titles tab

  for (int i=0; i <= level1count; i++) { //update the enenmies every loop
    easybad[i].update();
  }

  for (int i = 0; i <= coinCount1; i++) { //update coins
    coinArray[i].update();
  }

  if (scrollX > (0-(level1background.width*6))+width) { //prevents scrolling off the end

    if (movingRight == true&& moveableXright == true) {
      scrollX = scrollX - speed;
    }

    if (movingLeft == true&& moveableXleft == true && scrollX < 0) {
      scrollX = scrollX + speed;
    }
  } else { //if it cant scroll any further, then you finished
    //set character to happy or something...
    score = score + PApplet.parseInt(health); //get points for leftover health
    delay(2000); //pause for a sec once finsihed
    scene = 5;
  }
}

public void level2() {

  image(level2background, scrollX, 0, level2background.width*6, 600); //makes the image's location scroll as a background when you move. it stretches it to 6x the size

  groundcolor=-6052957;

  character();

  showgui();  //see titles tab

  hardEnemy(6215, 156);

  for (int i=0; i <= level2count; i++) { //update the enenmies every loop
    easybad[i].update();
  }

  for (int i = 0; i <= coinCount2; i++) { //update coins
    coinArray[i].update();
  }

  if (scrollX > (0-(level1background.width*6))+width) { //prevents scrolling off the end

    if (movingRight == true&& moveableXright == true) {
      scrollX = scrollX - speed;
    }

    if (movingLeft == true&& moveableXleft == true && scrollX < 0) {
      scrollX = scrollX + speed;
    }
  } else { //if it cant scroll any further, then you finished
    //set character to happy or something...
    score = score + PApplet.parseInt(health); //get points for leftover health

    delay(2000); //pause for a sec once finsihed

    scene = 5;
  }
}

public void level3() {

  image(level3background, scrollX, 0, level3background.width*6, 600); //makes the image's location scroll as a background when you move. it stretches it to 6x the size

  groundcolor=-3092272;

  character();

  showgui();  //see titles tab

  hardEnemy(9000, 230);

  for (int i=0; i <= level2count; i++) { //update the enenmies every loop
    easybad[i].update();
  }

  for (int i = 0; i <= coinCount3; i++) { //update coins
    coinArray[i].update();
  }

  if (scrollX > (0-(level3background.width*6))+width) { //prevents scrolling off the end
    if (movingRight == true&& moveableXright == true) {
      scrollX = scrollX - speed;
    }

    if (movingLeft == true&& moveableXleft == true && scrollX < 0) {
      scrollX = scrollX + speed;
    }
  } else { //if it cant scroll any further, then you finished
    //set character to happy or something...
    score = score + PApplet.parseInt(health); //get points for leftover health
    delay(1000); //pause for a sec once finsihed
    scene = 5;
  }
}
public void keyPressed() {

  if (health > 0) { //controls only work if you have health

    if (key == CODED) {

      if (keyCode == RIGHT) {
        movingRight = true;
        facingRight = true;
      }

      if (keyCode == LEFT) {
        movingLeft = true;
        facingRight = false;
      }

      if (keyCode == UP) {

        if (AbleToJump == true) {

          jumping = true;
        } else if (falling == true) {

          jumplag = true; //this is so if you push jump slightly before you hit the ground it still works
        }
      }
    }

    if (key == ' ' && liveBullet == false && ownGun == true) {

      shooting = true;
      liveBullet = true;

      if (facingRight) {
        bulletX = characterX+characterWidth-22;
      } else {
        bulletX = characterX+10;
      }
      bulletY = characterY+characterHeight/2;

      health = health - 5;

      if (facingRight) {
        bulletDirection = 1;
      } else {
        bulletDirection = 0;
      }
    }
  }
}

public void keyReleased() {

  if (keyCode == RIGHT) {
    movingRight = false;
  }

  if (keyCode == LEFT) {
    movingLeft = false;
  }
}
boolean mouseClicked;
public void button(int xpos, int ypos, int xwidth, int ywidth, String action, PImage logo) {

  if (mouseX > xpos && mouseX < (xpos + xwidth) && mouseY > ypos && mouseY < (ypos + ywidth)) {
    if (xwidth < 500) {
      tint(255, 200);
    }
  }

  image(logo, xpos, ypos, xwidth, ywidth); //creates the image for the button

  noTint();

  if (mouseClicked == true && mouseX > xpos && mouseX < (xpos + xwidth) && mouseY > ypos && mouseY < (ypos + ywidth)) {

    mouseClicked = false;

    if (action == "play") {
      scene = 4; //go to level menu
      health = 100;
      characterX = 200; //starting position
      characterY = 350;
      scrollX = 0;
      falling = false;
      jumpHeight = 0;
      jumping = false;
      jumpstep = 0;
      score = 0;
      dead = false;
      firstLoop2 = false;
      direction2 = 0;
      distanceCounter = 0;
      enemyHealth = 100;
    } 

    if (action == "error") {
      filter(GRAY);
    }
    if (action == "open shop") {
      scene = 7;
    }

    if (action == "play level 1") {
      GUMMYCANDY(); //add bonus health

      easybad = new easyEnemy[level1count+1];//spawn in bad guys

      for (int i = 0; i <= level1count; i++) {
        easybad[i] = new easyEnemy(enemyCoords1[i][0], enemyCoords1[i][1], enemyCoords1[i][2]);
      }

      for (int i=0; i <= level1count; i++) { //run first loop for enemy to set some variables
        easybad[i].firstLoop();
      }

      coinArray = new coin[coinCount1+1];

      for (int i = 0; i <= coinCount1; i++) { //set up the coins 
        coinArray[i] = new coin(coinCoords1[i][0], coinCoords1[i][1]);
      }

      scene = 1;
    }

    if (action == "play level 2") {
      GUMMYCANDY(); //add bonus health

      easybad = new easyEnemy[level2count+1];//spawn in bad guys

      for (int i = 0; i <= level2count; i++) {
        easybad[i] = new easyEnemy(enemyCoords2[i][0], enemyCoords2[i][1], enemyCoords2[i][2]);
      }

      for (int i=0; i <= level2count; i++) { //run first loop for enemy to set some variables
        easybad[i].firstLoop();
      }

      coinArray = new coin[coinCount2+1];

      for (int i = 0; i <= coinCount2; i++) { //set up the coins
        coinArray[i] = new coin(coinCoords2[i][0], coinCoords2[i][1]);
      }

      scene = 2;
    }

    if (action == "play level 3") {
      GUMMYCANDY(); //add bonus health

      easybad = new easyEnemy[level3count+1];//spawn in bad guys

      for (int i = 0; i <= level3count; i++) {
        easybad[i] = new easyEnemy(enemyCoords3[i][0], enemyCoords3[i][1], enemyCoords3[i][2]);
      }

      for (int i=0; i <= level3count; i++) { //run first loop for enemy to set some variables
        easybad[i].firstLoop();
      }

      coinArray = new coin[coinCount3+1];

      for (int i = 0; i <= coinCount3; i++) { //set up the coins
        coinArray[i] = new coin(coinCoords3[i][0], coinCoords3[i][1]);
      }

      scene = 3;
    }

    if (action == "pause") {
      paused = true;
    }

    if (action == "unpause") {
      paused = false;
    }

    if (action == "home") {
      scene = 0;
      paused = false;
    }

    if (action == "home and unlock new level") {

      if (unlockedLevel == 2) {
        unlockedLevel = 3;
      }

      if (unlockedLevel == 1) { //unlock the next level
        unlockedLevel = 2;
      }

      scene = 0;
    }

    if (action == "buy mask") {

      if (coins >= 10) { //if you have enought coins
        wearingMask = true;
        coins = coins - 10;
      } else {
        filter(GRAY);
      }
    }

    if (action == "buy gun") {

      if (coins >= 15) { //if you have enought coins
        ownGun = true;
        coins = coins - 15;
      } else {
        filter(GRAY);
      }
    }

    if (action == "buy vaccine") {

      if (coins >= 10) { //if you have enought coins
        hasVaccine = true;
        coins = coins - 10;
      } else {
        filter(GRAY);
      }
    }

    if (action == "gummy!!") {

      if (coins >= 5) { //if you have enought coins
        healthBonus = healthBonus + 10;
        coins = coins - 5;
      } else {
        filter(GRAY);
      }
    }
  }
  filter(0);
}

public void GUMMYCANDY() {

  health = health + healthBonus;
  healthBonus = 0;
  healthbarlength =health * 242 / 100;
}

public void mouseReleased() {
  mouseClicked = true;
}
boolean moveableXright = true;
boolean moveableXleft = true;
boolean moveableY = true;
boolean jumping;
boolean AbleToJump = true;
float jumpstep, jumpHeight;
boolean falling;
float characterX = 200, characterY = 350; //starting position
float characterHeight = 100; //default player size
float characterWidth = 72;
float gravity = 15;
float health = 100;
boolean firstLoop;
int score = 0;
int oneLoop;
boolean jumplag;
boolean wearingMask;
float healthBonus;
boolean ownGun;
float bulletX, bulletY;
boolean liveBullet;
boolean hasVaccine;
boolean requireSolidLanding;
float walkingCounter;
boolean onWall, onWallLeft;
int bulletDirection;

public void character() { //note that some controls such as movement on x axis are in the levels tab
  falling = false; //falling is always false unless updated later on

  if (hurt) {
    image(damageTakenOverlay, 0, 0);
    //character hurt skin
    //enemy die skin

    if (oneLoop == 2) { //makes it do a loop before delaying
      hurt = false;
      delay(1000); //freeze for a sec when takeing damage
    }

    if (oneLoop == 1) {
      oneLoop = 2;
    }
  }
  if (onWallLeft) {
    image(holdingLeft, characterX, characterY, characterWidth-15, characterHeight);
  } else if (onWall) {
    image(holdingRight, characterX+15, characterY, characterWidth-15, characterHeight);
  } else if (facingRight == false && movingLeft == false && movingRight == false) {
    image(facingLeft, characterX, characterY, characterWidth, characterHeight); //creates the image of teh actual character
  } else if (jumping || AbleToJump == false) {
    if (facingRight) {
      image(jump, characterX-5, characterY, characterWidth, characterHeight);
    } else {
      image(jumpLeft, characterX+5, characterY, characterWidth, characterHeight);
    }
  } else if (movingRight) {
    image(walking[PApplet.parseInt(walkingCounter)], characterX, characterY, characterWidth, characterHeight); //creates the image of teh actual character
    walkingCounter = walkingCounter + 0.4f;

    if (walkingCounter > 5) {
      walkingCounter = 0;
    }
  } else if (movingLeft) {
    image(walking2[PApplet.parseInt(walkingCounter)], characterX, characterY, characterWidth, characterHeight); //creates the image of teh actual character

    walkingCounter = walkingCounter - 0.4f;

    if (walkingCounter <= 0) {
      walkingCounter = 5;
    }
  } else {
    image(characterSkin, characterX, characterY, characterWidth, characterHeight); //creates the image of teh actual character
  }

  onWall = false;
  onWallLeft = false;

  if (wearingMask == true) {
    image(MaskOn, characterX+25, characterY+15, 20, 10);
    health = health - 0.005f;// wearing a mask slowly drains your health
  }

  if (ownGun == true) {
    if (facingRight) {
      image(ingameGun, characterX+characterWidth-34, characterY+characterHeight/2-10, 35, 40);
    } else {
      image(gunLeft, characterX, characterY+characterHeight/2-10, 35, 40);
    }

    if (shooting) {
      fill(100, 100, 255);
      rect(bulletX, bulletY, 15, 5);

      if (bulletDirection == 1) {
        bulletX = bulletX + 20; //bullet speed
      } else {
        bulletX = bulletX - 10;
      }

      if (bulletX > width || bulletX < 0) { //when the bullet goes off the screen you can shoot again
        liveBullet = false;
      }
    }
  }

  if (get(PApplet.parseInt(characterX+5), PApplet.parseInt(characterY+characterHeight-15)) == lavaColor || get(PApplet.parseInt(characterX+characterWidth-5), PApplet.parseInt(characterY+characterHeight-15)) == lavaColor) { //checks if either bottom corner is touching the lava
    health = health - 10;
  }

  if (get(PApplet.parseInt(characterX+5), PApplet.parseInt(characterY+characterHeight)) == groundcolor || get(PApplet.parseInt(characterX+characterWidth-5), PApplet.parseInt(characterY+characterHeight)) == groundcolor) { //checks if either bottom corner is touching the ground

    if (jumping == false) {
      moveableY = false;
    }
  }

  if (moveableY == false) { //this needs to be here or the bool will get messed up
    AbleToJump = true;
  }

  if (get(PApplet.parseInt(characterX+characterWidth/2), PApplet.parseInt(characterY+characterHeight)) != groundcolor &&  moveableY == false) {
    AbleToJump = false;
  } 

  if (get(PApplet.parseInt(characterX+10), PApplet.parseInt(characterY+(characterHeight-10))) == groundcolor || get(PApplet.parseInt(characterX+characterWidth-10), PApplet.parseInt(characterY+(characterHeight-10))) == groundcolor) { //if a little off the ground is touching the ground, go up because there is a bump to climb.

    if (jumping == false) {
      characterY = characterY-6;
      onWall = false;
    }
  }
  if (get(PApplet.parseInt(characterX+8), PApplet.parseInt(characterY+(characterHeight/2))) == groundcolor || get(PApplet.parseInt(characterX+8), PApplet.parseInt(characterY+10)) == groundcolor) { //check halfway up the side if character runs into a wall
    moveableXleft = false;
    if (AbleToJump == false) {
      onWallLeft = true;
    }
  } else {
    moveableXleft = true;
  }

  if (get(PApplet.parseInt(characterX+characterWidth-8), PApplet.parseInt(characterY+(characterHeight/2))) == groundcolor || get(PApplet.parseInt(characterX+characterWidth-8), PApplet.parseInt(characterY+10)) == groundcolor) {
    moveableXright = false;
    if (AbleToJump == false) {
      onWall = true;
    }
  } else {
    moveableXright = true;
  }

  if (moveableY == true&&jumping == false) {
    gravity();
  }

  if (moveableY == false) { //sets the variables pre-jump
    jumpstep = characterY;
    jumpHeight = 0;
    firstLoop = true;
  }

  if (jumplag == true && AbleToJump == true) { //if you press space slightly before you land it still works
    jumping = true;
    jumplag = false;
  }

  if (jumping == true && moveableY == true) {  //does the jump
    characterY = jumpstep;
    AbleToJump = false; //makes it impossible to jump while already jumping

    jumpstep = jumpstep - gravity*(1-(jumpHeight/-260)); //makes the jump slow down the further he goes up
    jumpHeight = jumpHeight - gravity*(1-(jumpHeight/-260));

    if (jumpHeight <= -200) { //end jump at 150
      jumping = false;
    }

    if (get(PApplet.parseInt(characterX+characterWidth - 15), PApplet.parseInt(characterY)) == groundcolor || get(PApplet.parseInt(characterX + 15), PApplet.parseInt(characterY)) == groundcolor) { //stop the jump if he hits his head
      jumping = false;
    }
  }


  if (characterY > height*2) {
    health = health - 10; //take lots of damage if you fall out of the window
  }
  moveableY = true; //check each frame if moveableY is false otherwise its always true
}

public void gravity() { //make it a new function for shorter code because i need to use it more then once

  if (firstLoop == true) {
    jumpHeight = -200;
    firstLoop = false;
  }

  falling = true;
  characterY = jumpstep;
  jumpstep = jumpstep + gravity*(1-(jumpHeight/-260)); //makes the jump slow down the further he goes up
  jumpHeight = jumpHeight + gravity*(1-(jumpHeight/-260));
}
int coinHeight= 50;
int coinWidth = 50;
coin[] coinArray;
class coin {
  int coinX, coinY, counter;
  boolean collected;
  coin(int xpos, int ypos) {
    coinX = xpos;
    coinY = ypos;
  }

  public void update() {
    image(coinTexture, coinX, coinY, coinWidth, coinHeight);

    if ((characterY+characterHeight < coinY+coinHeight && characterY+characterHeight > coinY) || (characterY+characterHeight/3*2 < coinY+coinHeight && characterY+characterHeight/3*2 > coinY) || (characterY > coinY && characterY < coinY+coinHeight )) { //if the player hits it but not jumps on it they take damage and the thing dies
      if ((characterX > coinX+5 && characterX < coinX + coinWidth-5) || (characterX+characterWidth > coinX+5 && characterX+characterWidth < coinX + coinWidth-5) || (characterX+characterWidth/2 > coinX+5 && characterX+characterWidth/2 < coinX + coinWidth-5)) {
        if (collected == false) {
          collected = true;
          coins++;
          score = score + 5;
        }
      }
    }

    if (collected == true) { //bounce up then fall down when collected.
      if (counter > 10) {
        coinY = coinY + 15;
      } else {
        coinY = coinY - 9;
      }
      counter++;
    }
    
    if (movingRight && moveableXright == true && scrollX > (0-(level1background.width*6))+width) { //still move with the scrolling if it is offscreen but dont scroll when guys no moving
      coinX = coinX - speed; //scroll along with the rest of the level
    }

    if (movingLeft && moveableXleft == true && scrollX < 0) {
      coinX = coinX + speed; //scroll along with the rest of the level
    }
  }
}
int coinCount1 = 10; //keep track of coin count for each level
int coinCount2 = 15;
int coinCount3 = 17;

int[][] coinCoords1 = new int[coinCount1+1][]; //keep track of coin coords for each level
int[][] coinCoords2 = new int[coinCount2+1][];
int[][] coinCoords3 = new int[coinCount3+1][];

public void setCoins() {
  coinCoords1[0] = new int[]{ 800, 300};
  coinCoords1[1] = new int[]{ 1200, 200};
  coinCoords1[2] = new int[]{ 2114, 214};
  coinCoords1[3] = new int[]{ 2134, 324};
  coinCoords1[4] = new int[]{ 2700, 230};
  coinCoords1[5] = new int[]{ 3600, 411};
  coinCoords1[6] = new int[]{ 4660, 38};
  coinCoords1[7] = new int[]{ 6650, 322};
  coinCoords1[8] = new int[]{ 7770, 410};
  coinCoords1[9] = new int[]{ 8333, 293};
  coinCoords1[10] = new int[]{ 9500, 120};

  coinCoords2[0] = new int[]{625, 318};
  coinCoords2[1] = new int[]{1574, 180};
  coinCoords2[2] = new int[]{1860, 300};
  coinCoords2[3] = new int[]{2340, 360};
  coinCoords2[4] = new int[]{2900, 415};
  coinCoords2[5] = new int[]{3270, 280};
  coinCoords2[6] = new int[]{3750, 275};
  coinCoords2[7] = new int[]{3970, 140};
  coinCoords2[8] = new int[]{4360, 88};
  coinCoords2[9] = new int[]{4750, 180};
  coinCoords2[10] = new int[]{5070, 240};
  coinCoords2[11] = new int[]{5650, 163};
  coinCoords2[12] = new int[]{7088, 252};
  coinCoords2[13] = new int[]{8050, 290};
  coinCoords2[14] = new int[]{8464, 121};
  coinCoords2[15] = new int[]{8742, 160};

  coinCoords3[0] = new int[]{608, 384};
  coinCoords3[1] = new int[]{950, 420};
  coinCoords3[2] = new int[]{1238, 352};
  coinCoords3[3] = new int[]{1666, 420};
  coinCoords3[4] = new int[]{2450, 485};
  coinCoords3[5] = new int[]{2758, 380};
  coinCoords3[6] = new int[]{3000, 380};
  coinCoords3[7] = new int[]{3285, 200};
  coinCoords3[8] = new int[]{3783, 220};
  coinCoords3[9] = new int[]{4330, 235};
  coinCoords3[10] = new int[]{5060, 204};
  coinCoords3[11] = new int[]{5481, 204};
  coinCoords3[12] = new int[]{6100, 320};
  coinCoords3[13] = new int[]{6353, 260};
  coinCoords3[14] = new int[]{6610, 156};
  coinCoords3[15] = new int[]{7013, 335};
  coinCoords3[16] = new int[]{7417, 335};
  coinCoords3[17] = new int[]{8468, 177};
}
easyEnemy[] easybad; //<>//
int level1count = 6;
int level2count = 7;
int level3count = 8;
int direction;
float enemySpeed = 1.5f;
int distance;
boolean hurt;

class easyEnemy {
  float startingXpos, startingYpos, limit, ypos, xpos, counter;
  float enemyHeight = 80;
  float enemyWidth = 80;
  boolean dead;

  easyEnemy(int startX, int startY, int limiter) {
    startingXpos = startX;
    startingYpos = startY;
    limit = limiter;
  }

  public void firstLoop() { //see button actions tab for where it starts up
    xpos = startingXpos;
    ypos = startingYpos;
    counter = startingXpos;
    dead = false;
  }

  public void update() { //see levels tab for where it gets told to update

    image(easyEnemyTexture, xpos, ypos, enemyWidth, enemyHeight);

    if (shooting == true && bulletY> ypos && bulletY < ypos + enemyHeight && bulletX > xpos && bulletX < xpos + enemyWidth) { //if shot by bullet
      dead = true;
      score = score + 10;
    }

    if (get(PApplet.parseInt(xpos), PApplet.parseInt(ypos+enemyHeight)) != groundcolor && get(PApplet.parseInt(xpos), PApplet.parseInt(ypos+enemyHeight)) != 0) { //check if below it is black also because otherwise it will fall offscreen
      ypos = ypos + 5; //get affected by gravity if not touching the ground
    } 
    
    if (get(PApplet.parseInt(xpos+enemyWidth), PApplet.parseInt(ypos+enemyHeight)) != groundcolor && get(PApplet.parseInt(xpos+enemyWidth), PApplet.parseInt(ypos+enemyHeight)) != 0) { //check the same thing but the other corner
      ypos = ypos + 5; //get affected by gravity if not touching the ground
    } 

    if (movingRight && moveableXright == true && scrollX > (0-(level1background.width*6))+width) { //still move with the scrolling if it is offscreen but dont scroll when guys no moving
      xpos = xpos - speed; //scroll along with the rest of the level
    }

    if (movingLeft && moveableXleft == true && scrollX < 0) {
      xpos = xpos + speed; //scroll along with the rest of the level
    }

    if (get(PApplet.parseInt(xpos), PApplet.parseInt(ypos+enemyHeight-10)) == groundcolor || get(PApplet.parseInt(xpos+enemyWidth), PApplet.parseInt(ypos+enemyHeight-10)) == groundcolor) { //check a bit off of the ground to see if it has to climb up
      ypos = ypos - 5;
    } 

    if (startingXpos - counter >= limit) { //use counter instead of xpos so it is not affected be scrolling
      direction = 1;
    }

    if (counter >= startingXpos) {
      direction = 0;
    }

    if (direction == 0) {
      xpos = xpos - enemySpeed;
      counter = counter - enemySpeed;
    }

    if (direction == 1) {
      xpos = xpos + enemySpeed;
      counter = counter + enemySpeed;           //
    }
    
    fill(200, 100, 100);

    if ((characterY+characterHeight < ypos+enemyHeight && characterY+characterHeight > ypos) || (characterY+characterHeight/3*2 < ypos+enemyHeight && characterY+characterHeight/3*2 > ypos)) { //if the player hits it but not jumps on it they take damage and the thing dies
      if ((characterX > xpos+20 && characterX < xpos + enemyWidth-20) || (characterX+characterWidth-20 > xpos && characterX+characterWidth < xpos + enemyWidth-20)) {
        dead = true;

        if (hasVaccine == true) {
          if (random(2) > 1) { //if you have the vaccine
            if (wearingMask == false) { //if you are wearing a mask, take less damage
              health = health - 25;
            } else {
              health = health - 15;
            }
          }
        } else {
          if (wearingMask == false) { //if you are wearing a mask, take less damage
            health = health - 20;
          } else {
            health = health - 10;
          }
        }

        hurt = true;
        oneLoop = 1;
      }
    }

    if (dead == true) {
      ypos = 10000; //if the thing dies it goes a long ways away
    }
  }
}
boolean firstLoop2;
int enemy2X;
int enemy2Y;
boolean dead;
int enemyHeight = 160;
int enemyWidth = 160;
int direction2; 
int distanceCounter;
int enemyHealth = 100;

public void hardEnemy(int x, int y) {
  
  if (firstLoop2 == false) {
    enemy2Y = y;
    enemy2X = x;
    enemyHealth = 100;
  }
  
  firstLoop2 = true;
  image(easyEnemyTexture, enemy2X, enemy2Y, enemyWidth, enemyHeight);
  
  if (enemyHealth <= 0) {
    dead = true;
  }
  println((-1*scrollX), (-1*scrollX)+width, enemy2X);
  if (get(PApplet.parseInt(enemy2X), PApplet.parseInt(enemy2Y+enemyHeight)) != groundcolor && get(PApplet.parseInt(enemy2X), PApplet.parseInt(enemy2Y+enemyHeight)) != 0) { //check if below it is black also because otherwise it will fall offscreen
    enemy2Y = enemy2Y + 5; //get affected by gravity if not touching the ground
  } 
  if (get(PApplet.parseInt(enemy2X+enemyWidth), PApplet.parseInt(enemy2Y+enemyHeight)) != groundcolor && get(PApplet.parseInt(enemy2X+enemyWidth), PApplet.parseInt(enemy2Y+enemyHeight)) != 0) { //check the same thing but the other corner
    enemy2Y = enemy2Y + 5; //get affected by gravity if not touching the ground
  } 

  if (movingRight && moveableXright == true && scrollX > (0-(level1background.width*6))+width) { //still move with the scrolling if it is offscreen but dont scroll when guys no moving
    enemy2X = enemy2X - speed; //scroll along with the rest of the level
  }

  if (movingLeft && moveableXleft == true && scrollX < 0) {
    enemy2X = enemy2X + speed; //scroll along with the rest of the level
  }

  if (get(PApplet.parseInt(enemy2X), PApplet.parseInt(enemy2Y+enemyHeight-10)) == groundcolor || get(PApplet.parseInt(enemy2X+enemyWidth), PApplet.parseInt(enemy2Y+enemyHeight-10)) == groundcolor) { //check a bit off of the ground to see if it has to climb up
    enemy2Y = enemy2Y - 5;
  }

  if (direction2 == 1) {
    enemy2X = enemy2X + 1;
    distanceCounter = distanceCounter + 1;
    
    if (distanceCounter > 500) {
      direction2 = 0;
      distanceCounter = 0;
    }
    
  } else {
    enemy2X = enemy2X - 1;
    distanceCounter  = distanceCounter - 1;
    
    if (distanceCounter < -500) {
      direction2 = 1;
      distanceCounter = 0;
    }
    
  }

  if ((characterY+characterHeight < enemy2Y+enemyHeight && characterY+characterHeight > enemy2Y) || (characterY+characterHeight/3*2 < enemy2Y+enemyHeight && characterY+characterHeight/3*2 > enemy2Y)) { //if the player hits it but not jumps on it they take damage and the thing dies
    if ((characterX > enemy2X+20 && characterX < enemy2X + enemyWidth-20) || (characterX+characterWidth-20 > enemy2X && characterX+characterWidth < enemy2X + enemyWidth-20)) {
      dead = true;

      if (wearingMask == false) { //if you are wearing a mask, take less damage
        health = health - 70;
      } else {
        health = health - 35;
      }


      hurt = true;
      oneLoop = 1;
    }
  }
   if (shooting == true && bulletY> enemy2Y && bulletY < enemy2Y + enemyHeight && bulletX > enemy2X && bulletX < enemy2X + enemyWidth && enemy2X > 0 && enemy2X < width) { //if shot by bullet
    enemyHealth = enemyHealth - 35;
    shooting = false;
    liveBullet = false;
    score = score + 10;
  }


  if (dead == true) {
    enemy2Y = 10000; //if the thing dies it goes a long ways away
  }
}



int[][] enemyCoords1 = new int[level1count+1][]; //level 1
int[][] enemyCoords2 = new int[level2count+1][]; //level 2
int[][] enemyCoords3 = new int[level3count+1][]; //level 3

public void setEnemies() { //keep track of the coordinates of the bad guys (x pos, y pos, distance limit)
  enemyCoords1[0] = new int[]{800, 400, 250}; //level 1
  enemyCoords1[1] = new int[]{1770, 300, 250};
  enemyCoords1[2] = new int[]{3850, 400, 250};
  enemyCoords1[3] = new int[]{5300, 100, 250};
  enemyCoords1[4] = new int[]{6900, 300, 250};
  enemyCoords1[5] = new int[]{7780, 425, 250};
  enemyCoords1[6] = new int[]{8600, 300, 250};

  enemyCoords2[0] = new int[]{800, 400, 250}; //level 2
  enemyCoords2[1] = new int[]{1320, 270, 250};
  enemyCoords2[2] = new int[]{3520, 290, 250};
  enemyCoords2[3] = new int[]{4037, 146, 250};
  enemyCoords2[4] = new int[]{5791, 270, 250};
  enemyCoords2[5] = new int[]{6780, 250, 250};
  enemyCoords2[6] = new int[]{7430, 442, 250};
  enemyCoords2[7] = new int[]{8600, 300, 250};

  enemyCoords3[0] = new int[]{1080, 435, 250}; //level 3
  enemyCoords3[1] = new int[]{1900, 415, 250};
  enemyCoords3[2] = new int[]{2460, 470, 250};
  enemyCoords3[3] = new int[]{3333, 385, 250};
  enemyCoords3[4] = new int[]{3900, 236, 250};
  enemyCoords3[5] = new int[]{5100, 218, 250};
  enemyCoords3[6] = new int[]{5680, 205, 250};
  enemyCoords3[7] = new int[]{7220, 336, 250};
  enemyCoords3[8] = new int[]{7500, 336, 250};
}
PImage titlebackdrop, vaccine, vaccineCheck, holdingRight, gunLeft, jump, jumpLeft, facingLeft, holdingLeft, playButton, shopButton, shopBackground, Gun, ingameGun, GunCheck, coinTexture, gummy, backButton, Mask, MaskOn, MaskCheck, levelMenuBackdrop1, levelMenuBackdrop2, levelMenuBackdrop3, level1Button, level2Button, level3Button, level1background, characterSkin, healthBar, pauseButton, youWinBackground, gameOverBackground, easyEnemyTexture, damageTakenOverlay, level2background, level3background;//create variables for textures
PFont pixelfont;
PImage[] walking = new PImage[6];
PImage[] walking2 = new PImage[6];

public void loadTextures() {
  
  titlebackdrop = loadImage ("titleBackground.png");
  playButton = loadImage("playButton.png");
  levelMenuBackdrop1 = loadImage("unlocked1.png");
  levelMenuBackdrop2 = loadImage("unlocked2.png");
  levelMenuBackdrop3 = loadImage("unlocked3.png");
  level1Button = loadImage("level1Button.png");
  level2Button = loadImage("level2Button.png");
  level3Button = loadImage("level3Button.png");
  level1background = loadImage("level1.png");
  level2background = loadImage("level2.png");
  level3background = loadImage("level3.png");
  characterSkin = loadImage("maincharacter.png");
  healthBar = loadImage("healthbar.png");
  coinTexture = loadImage("coin.png");
  pauseButton = loadImage("pauseButton.png");
  youWinBackground = loadImage("youWinBackground.png");
  gameOverBackground = loadImage("gameOverBackground.png");
  easyEnemyTexture = loadImage("enemy.png");
  damageTakenOverlay = loadImage("damageTaken.png");
  shopButton = loadImage("shop.png");
  backButton = loadImage("backbutton.png");
  shopBackground = loadImage("shopBackground.png");
  Mask = loadImage("Mask.png");
  MaskOn = loadImage("MaskOn.png");
  MaskCheck = loadImage("MaskCheck.png");
  Gun = loadImage("Gun.png");
  GunCheck = loadImage("GunCheck.png");
  ingameGun = loadImage("ingameGun.png");
  gummy = loadImage("gummy.png");
  vaccine = loadImage("vaccine.png");
  vaccineCheck = loadImage("vaccineCheck.png");
  holdingRight = loadImage("holding.png");
  holdingLeft = loadImage("holdingLeft.png");
  jump = loadImage("jump.png");
  jumpLeft = loadImage("jumpLeft.png");
  facingLeft = loadImage("facingLeft.png");
  gunLeft = loadImage("gunLeft.png");
  
  pixelfont = createFont("ARCADECLASSIC.TTF", 30);
  
  walking[0] = loadImage("./walking/0001.png");
  walking[1] = loadImage("./walking/0002.png");
  walking[2] = loadImage("./walking/0003.png");
  walking[3] = loadImage("./walking/0004.png");
  walking[4] = loadImage("./walking/0005.png");
  walking[5] = loadImage("./walking/0006.png");
  
  walking2[0] = loadImage("./walking2/0001.png");
  walking2[1] = loadImage("./walking2/0002.png");
  walking2[2] = loadImage("./walking2/0003.png");
  walking2[3] = loadImage("./walking2/0004.png");
  walking2[4] = loadImage("./walking2/0005.png");
  walking2[5] = loadImage("./walking2/0006.png");
}
float virus1 = random(100, 550);
float virus2 = random(100, 550);
float virus3 = random(100, 550);

float virus1X;
float virus2X;
float virus3X;

float speed1 = random(3, 10);
float speed2 = random(3, 10);
float speed3 = random(3, 10);

public void title() {
  image(titlebackdrop, 0, 0, 900, 600); //set backdrop
  button((width/2)-100, (height/2)-20, 200, 80, "play", playButton); //makes the play button 
  healthbarlength =health * 242 / 100; //reset healthbar
  image(easyEnemyTexture, virus1X, virus1, 30, 30);
  image(easyEnemyTexture, virus2X, virus2, 60, 60); 
  image(easyEnemyTexture, virus3X, virus3, 40, 40); 

  virus1X = virus1X + speed1;
  virus2X = virus2X + speed2;
  virus3X = virus3X + speed3;

  if (virus1X > width) {
    virus1X = -10;
    virus1 = random(100, 500);
    speed1 = random(3, 10);
  }

  if (virus2X > width) {
    virus2X = -10;
    virus2 = random(100, 500);
    speed2 = random(3, 10);
  }

  if (virus3X > width) {
    virus3X = -10;
    virus3 = random(100, 500);
    speed3 = random(3, 10);
  }
}

public void levelMenu() {

  if (unlockedLevel == 1) {
    image(levelMenuBackdrop1, 0, 0, width, height); //background
  } else if (unlockedLevel == 2) {
    image(levelMenuBackdrop2, 0, 0, width, height);
  } else {
    image(levelMenuBackdrop3, 0, 0, width, height);
  }

  button(15, 15, 60, 60, "home", backButton);

  button(90, 290, 60, 60, "play level 1", level1Button); //buttons for choosing level

  if (unlockedLevel < 2) {
    button(688, 280, 60, 60, "error", level2Button);  //buttons only work if unlocked
  } else {
    button(688, 280, 60, 60, "play level 2", level2Button);
  }

  if (unlockedLevel < 3) {
    button(708, 18, 60, 60, "error", level3Button);  //buttons only work if unlocked
  } else {
    button(708, 18, 60, 60, "play level 3", level3Button);
  }

  button(399, 55, 216, 167, "open shop", shopButton); //button for shop
}

boolean paused;
boolean secondLoop;
float healthbarlength =health * 242 / 100;


public void showgui() {

  if (health < 33) { //different colors for health bar
    fill(0xffF70A0A);
  }

  if (health > 66) {
    fill(0xff00ED11);
  }

  if (health <= 0) {
    fill(0xff000000);
  }

  if (health >= 33 && health <= 66) {
    fill(0xffFFF700);
  }

  float healthbarTarget;
  healthbarTarget = health * 242 / 100; //use cross multiplication to find lenght

  if (healthbarlength > healthbarTarget) {
    healthbarlength = healthbarlength - 3;
  }

  if (healthbarlength <= 0) { //if the bar reaches zero you are dead
    //death skin 
    delay(1000);
    health = 100;
    scene = 6; //gameover scene
  }

  rect(74, 3, healthbarlength, 25); //max is 242
  image(healthBar, 0, 0, 322, 34);
  button(width-40, 10, 30, 30, "pause", pauseButton);

  textSize(15);
  textFont(pixelfont);
  fill(0);
  text("Score   "+ score+"     Coins   " + coins, 335, 25);
}

public void shop() {
  image(shopBackground, 0, 0, 900, 600);
  button(15, 15, 60, 60, "home", backButton);

  textSize(25);
  textFont(pixelfont);
  fill(0);
  text("Coins   " + coins, 25, 570);

  if (wearingMask == false) {
    button(63, 344, 60, 60, "buy mask", Mask);
  } else {
    image(MaskCheck, 63, 344, 60, 60);
  }

  button(495, 364, 60, 60, "gummy!!", gummy);

  if (ownGun == false) {
    button(72, 228, 80, 60, "buy gun", Gun);
  } else {
    image(GunCheck, 72, 228, 80, 60);
  }

  if (hasVaccine == false) {
    button(521, 230, 60, 60, "buy vaccine", vaccine);
  } else {
    image(vaccineCheck, 521, 230, 60, 60);
  }
}
  public void settings() {  size(900, 600);  noSmooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "game" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
