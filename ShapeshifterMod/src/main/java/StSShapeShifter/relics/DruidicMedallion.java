//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.relics;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.AllForms;
import StSShapeShifter.util.BloomCountUtils;
import StSShapeShifter.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;
import com.megacrit.cardcrawl.stances.AbstractStance;

import java.util.Iterator;

import static StSShapeShifter.ShapeshifterMod.makeRelicPath;

public class DruidicMedallion extends CustomRelic {
    public boolean active;
    public static final String ID = ShapeshifterMod.makeID(DruidicMedallion.class.getSimpleName());
    private static final String PNG = ".png";
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath(DruidicMedallion.class.getSimpleName() + PNG));

    public DruidicMedallion() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return AbstractDungeon.player != null ? this.setDescription(AbstractDungeon.player.chosenClass) : this.setDescription((PlayerClass)null);
    }

    private String setDescription(PlayerClass c) {
        return DESCRIPTIONS[0];
    }

    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
        this.addToBot(new GainBlockAction(AbstractDungeon.player, 3));
    }

    public AbstractRelic makeCopy() {
        return new DruidicMedallion();
    }

}





