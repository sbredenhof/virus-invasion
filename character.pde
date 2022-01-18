boolean moveableXright = true; //<>//
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

void character() { //note that some controls such as movement on x axis are in the levels tab
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
    image(walking[int(walkingCounter)], characterX, characterY, characterWidth, characterHeight); //creates the image of teh actual character
    walkingCounter = walkingCounter + 0.4;

    if (walkingCounter > 5) {
      walkingCounter = 0;
    }
  } else if (movingLeft) {
    image(walking2[int(walkingCounter)], characterX, characterY, characterWidth, characterHeight); //creates the image of teh actual character

    walkingCounter = walkingCounter - 0.4;

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
    health = health - 0.005;// wearing a mask slowly drains your health
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

  if (get(int(characterX+5), int(characterY+characterHeight-15)) == lavaColor || get(int(characterX+characterWidth-5), int(characterY+characterHeight-15)) == lavaColor) { //checks if either bottom corner is touching the lava
    health = health - 10;
  }

  if (get(int(characterX+5), int(characterY+characterHeight)) == groundcolor || get(int(characterX+characterWidth-5), int(characterY+characterHeight)) == groundcolor) { //checks if either bottom corner is touching the ground

    if (jumping == false) {
      moveableY = false;
    }
  }

  if (moveableY == false) { //this needs to be here or the bool will get messed up
    AbleToJump = true;
  }

  if (get(int(characterX+characterWidth/2), int(characterY+characterHeight)) != groundcolor &&  moveableY == false) {
    AbleToJump = false;
  } 

  if (get(int(characterX+10), int(characterY+(characterHeight-10))) == groundcolor || get(int(characterX+characterWidth-10), int(characterY+(characterHeight-10))) == groundcolor) { //if a little off the ground is touching the ground, go up because there is a bump to climb.

    if (jumping == false) {
      characterY = characterY-6;
      onWall = false;
    }
  }
  if (get(int(characterX+8), int(characterY+(characterHeight/2))) == groundcolor || get(int(characterX+8), int(characterY+10)) == groundcolor) { //check halfway up the side if character runs into a wall
    moveableXleft = false;
    if (AbleToJump == false) {
      onWallLeft = true;
    }
  } else {
    moveableXleft = true;
  }

  if (get(int(characterX+characterWidth-8), int(characterY+(characterHeight/2))) == groundcolor || get(int(characterX+characterWidth-8), int(characterY+10)) == groundcolor) {
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

    if (get(int(characterX+characterWidth - 15), int(characterY)) == groundcolor || get(int(characterX + 15), int(characterY)) == groundcolor) { //stop the jump if he hits his head
      jumping = false;
    }
  }


  if (characterY > height*2) {
    health = health - 10; //take lots of damage if you fall out of the window
  }
  moveableY = true; //check each frame if moveableY is false otherwise its always true
}

void gravity() { //make it a new function for shorter code because i need to use it more then once

  if (firstLoop == true) {
    jumpHeight = -200;
    firstLoop = false;
  }

  falling = true;
  characterY = jumpstep;
  jumpstep = jumpstep + gravity*(1-(jumpHeight/-260)); //makes the jump slow down the further he goes up
  jumpHeight = jumpHeight + gravity*(1-(jumpHeight/-260));
}
