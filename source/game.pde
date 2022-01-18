int scene = 0; //0 = title, 1 = level 1...
int unlockedLevel = 1;
int coins = 0;

void setup() {
  background(0);
  textSize(40);
  text("loading...", 100, 100); //loading the font takes a second so it says loading instead of showing a blank screen
  size(900, 600); //set window size
  noSmooth(); //makes pixel art actually look like pixel art
  loadTextures(); //check textures tab
  setEnemies();
  setCoins();
}

void draw() {
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
