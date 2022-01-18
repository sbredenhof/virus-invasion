float scrollX;
int speed = 6; 
int groundcolor;
int lavaColor = -32985;
boolean movingRight;
boolean movingLeft;
boolean finishedLevel;
boolean shooting;
boolean facingRight;
void level1() {

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
    score = score + int(health); //get points for leftover health
    delay(2000); //pause for a sec once finsihed
    scene = 5;
  }
}

void level2() {

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
    score = score + int(health); //get points for leftover health

    delay(2000); //pause for a sec once finsihed

    scene = 5;
  }
}

void level3() {

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
    score = score + int(health); //get points for leftover health
    delay(1000); //pause for a sec once finsihed
    scene = 5;
  }
}
void keyPressed() {

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

void keyReleased() {

  if (keyCode == RIGHT) {
    movingRight = false;
  }

  if (keyCode == LEFT) {
    movingLeft = false;
  }
}
