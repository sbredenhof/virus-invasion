PImage titlebackdrop, vaccine, vaccineCheck, holdingRight, gunLeft, jump, jumpLeft, facingLeft, holdingLeft, playButton, shopButton, shopBackground, Gun, ingameGun, GunCheck, coinTexture, gummy, backButton, Mask, MaskOn, MaskCheck, levelMenuBackdrop1, levelMenuBackdrop2, levelMenuBackdrop3, level1Button, level2Button, level3Button, level1background, characterSkin, healthBar, pauseButton, youWinBackground, gameOverBackground, easyEnemyTexture, damageTakenOverlay, level2background, level3background;//create variables for textures
PFont pixelfont;
PImage[] walking = new PImage[6];
PImage[] walking2 = new PImage[6];

void loadTextures() {
  
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
