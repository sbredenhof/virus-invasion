float virus1 = random(100, 550);
float virus2 = random(100, 550);
float virus3 = random(100, 550);

float virus1X;
float virus2X;
float virus3X;

float speed1 = random(3, 10);
float speed2 = random(3, 10);
float speed3 = random(3, 10);

void title() {
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

void levelMenu() {

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


void showgui() {

  if (health < 33) { //different colors for health bar
    fill(#F70A0A);
  }

  if (health > 66) {
    fill(#00ED11);
  }

  if (health <= 0) {
    fill(#000000);
  }

  if (health >= 33 && health <= 66) {
    fill(#FFF700);
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

void shop() {
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
