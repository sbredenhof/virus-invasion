boolean mouseClicked;
void button(int xpos, int ypos, int xwidth, int ywidth, String action, PImage logo) {

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

void GUMMYCANDY() {

  health = health + healthBonus;
  healthBonus = 0;
  healthbarlength =health * 242 / 100;
}

void mouseReleased() {
  mouseClicked = true;
}
