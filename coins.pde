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

  void update() {
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

void setCoins() {
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
