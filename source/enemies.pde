easyEnemy[] easybad; //<>//
int level1count = 6;
int level2count = 7;
int level3count = 8;
int direction;
float enemySpeed = 1.5;
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

  void firstLoop() { //see button actions tab for where it starts up
    xpos = startingXpos;
    ypos = startingYpos;
    counter = startingXpos;
    dead = false;
  }

  void update() { //see levels tab for where it gets told to update

    image(easyEnemyTexture, xpos, ypos, enemyWidth, enemyHeight);

    if (shooting == true && bulletY> ypos && bulletY < ypos + enemyHeight && bulletX > xpos && bulletX < xpos + enemyWidth) { //if shot by bullet
      dead = true;
      score = score + 10;
    }

    if (get(int(xpos), int(ypos+enemyHeight)) != groundcolor && get(int(xpos), int(ypos+enemyHeight)) != 0) { //check if below it is black also because otherwise it will fall offscreen
      ypos = ypos + 5; //get affected by gravity if not touching the ground
    } 
    
    if (get(int(xpos+enemyWidth), int(ypos+enemyHeight)) != groundcolor && get(int(xpos+enemyWidth), int(ypos+enemyHeight)) != 0) { //check the same thing but the other corner
      ypos = ypos + 5; //get affected by gravity if not touching the ground
    } 

    if (movingRight && moveableXright == true && scrollX > (0-(level1background.width*6))+width) { //still move with the scrolling if it is offscreen but dont scroll when guys no moving
      xpos = xpos - speed; //scroll along with the rest of the level
    }

    if (movingLeft && moveableXleft == true && scrollX < 0) {
      xpos = xpos + speed; //scroll along with the rest of the level
    }

    if (get(int(xpos), int(ypos+enemyHeight-10)) == groundcolor || get(int(xpos+enemyWidth), int(ypos+enemyHeight-10)) == groundcolor) { //check a bit off of the ground to see if it has to climb up
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

void hardEnemy(int x, int y) {
  
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
  if (get(int(enemy2X), int(enemy2Y+enemyHeight)) != groundcolor && get(int(enemy2X), int(enemy2Y+enemyHeight)) != 0) { //check if below it is black also because otherwise it will fall offscreen
    enemy2Y = enemy2Y + 5; //get affected by gravity if not touching the ground
  } 
  if (get(int(enemy2X+enemyWidth), int(enemy2Y+enemyHeight)) != groundcolor && get(int(enemy2X+enemyWidth), int(enemy2Y+enemyHeight)) != 0) { //check the same thing but the other corner
    enemy2Y = enemy2Y + 5; //get affected by gravity if not touching the ground
  } 

  if (movingRight && moveableXright == true && scrollX > (0-(level1background.width*6))+width) { //still move with the scrolling if it is offscreen but dont scroll when guys no moving
    enemy2X = enemy2X - speed; //scroll along with the rest of the level
  }

  if (movingLeft && moveableXleft == true && scrollX < 0) {
    enemy2X = enemy2X + speed; //scroll along with the rest of the level
  }

  if (get(int(enemy2X), int(enemy2Y+enemyHeight-10)) == groundcolor || get(int(enemy2X+enemyWidth), int(enemy2Y+enemyHeight-10)) == groundcolor) { //check a bit off of the ground to see if it has to climb up
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

void setEnemies() { //keep track of the coordinates of the bad guys (x pos, y pos, distance limit)
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
