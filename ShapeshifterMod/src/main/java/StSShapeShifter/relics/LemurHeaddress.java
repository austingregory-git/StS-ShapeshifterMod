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
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;

import java.util.Iterator;

import static StSShapeShifter.ShapeshifterMod.makeRelicPath;

public class LemurHeaddress extends CustomRelic {
    public boolean active;
    public static final String ID = ShapeshifterMod.makeID(LemurHeaddress.class.getSimpleName());
    private static final String PNG = ".png";
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath(LemurHeaddress.class.getSimpleName() + PNG));

    public LemurHeaddress() {
        super(ID, IMG, RelicTier.BOSS, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return AbstractDungeon.player != null ? this.setDescription(AbstractDungeon.player.chosenClass) : this.setDescription((PlayerClass)null);
    }

    private String setDescription(PlayerClass c) {
        return DESCRIPTIONS[0];
    }

    public void onUseCard(AbstractCard c, UseCardAction action) {
        if (AllForms.getAllForms().contains(c.cardID) && active) {
            this.addToBot(new GainEnergyAction(1));
            active = false;
        }
    }

    public void atTurnStart() {
        active = true;
    }

    public AbstractRelic makeCopy() {
        return new LemurHeaddress();
    }

}



