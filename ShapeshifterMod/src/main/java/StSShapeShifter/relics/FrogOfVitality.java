//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.relics;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.AllForms;
import StSShapeShifter.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.unique.IncreaseMaxHpAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

import static StSShapeShifter.ShapeshifterMod.makeRelicPath;

public class FrogOfVitality extends CustomRelic {
    public boolean active;
    public static final String ID = ShapeshifterMod.makeID(FrogOfVitality.class.getSimpleName());
    public ArrayList<String> formsPlayed = new ArrayList<>();
    private static final String PNG = ".png";
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath(FrogOfVitality.class.getSimpleName() + PNG));

    public FrogOfVitality() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return AbstractDungeon.player != null ? this.setDescription(AbstractDungeon.player.chosenClass) : this.setDescription((PlayerClass)null);
    }

    private String setDescription(PlayerClass c) {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if(!formsPlayed.contains(targetCard.cardID) && AllForms.getAllForms().contains(targetCard.cardID)) {
            formsPlayed.add(targetCard.cardID);
            this.flash();
            AbstractDungeon.player.increaseMaxHp(1, true);
        }
    }

    public AbstractRelic makeCopy() {
        return new FrogOfVitality();
    }

}





