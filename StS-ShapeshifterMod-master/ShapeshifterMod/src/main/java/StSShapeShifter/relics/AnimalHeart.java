//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.relics;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.potions.AnimalAmalgamationPotion;
import StSShapeShifter.potions.DeathWeedPotion;
import StSShapeShifter.potions.GreenSmoothie;
import StSShapeShifter.potions.PotionOfTheGoldenFlower;
import StSShapeShifter.powers.FreeFormPower;
import StSShapeShifter.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static StSShapeShifter.ShapeshifterMod.makeRelicPath;

public class AnimalHeart extends CustomRelic {
    public static final String ID = ShapeshifterMod.makeID(AnimalHeart.class.getSimpleName());
    private static final String PNG = ".png";
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath(AnimalHeart.class.getSimpleName() + PNG));

    public AnimalHeart() {
        super(ID, IMG, RelicTier.STARTER, LandingSound.SOLID);
    }

    public String getUpdatedDescription() {
        return AbstractDungeon.player != null ? this.setDescription(AbstractDungeon.player.chosenClass) : this.setDescription((PlayerClass)null);
    }

    private String setDescription(PlayerClass c) {
        return DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        this.flash();
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FreeFormPower(AbstractDungeon.player, 1), 1));
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }
    public AbstractRelic makeCopy() {
        return new AnimalHeart();
    }

}



