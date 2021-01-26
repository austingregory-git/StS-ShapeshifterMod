//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.relics;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.BloomCountUtils;
import StSShapeShifter.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;

import static StSShapeShifter.ShapeshifterMod.makeRelicPath;

public class TheShapeShiftersMagnolia extends CustomRelic {
    public static final String ID = ShapeshifterMod.makeID(TheShapeShiftersMagnolia.class.getSimpleName());
    private static final String PNG = ".png";
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath(TheShapeShiftersMagnolia.class.getSimpleName() + PNG));

    public TheShapeShiftersMagnolia() {
        super(ID, IMG, RelicTier.STARTER, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return AbstractDungeon.player != null ? this.setDescription(AbstractDungeon.player.chosenClass) : this.setDescription((PlayerClass)null);
    }

    private String setDescription(PlayerClass c) {
        return DESCRIPTIONS[0];
    }

    public void onEquip() {
        this.counter = 0;
    }

    public void onUseCard(AbstractCard c, UseCardAction action) {
        this.counter = BloomCountUtils.getBloomCount();
    }

    public void atTurnStart() {
        this.counter = BloomCountUtils.getBloomCount();
    }

    public void onPlayerEndTurn() {
        this.counter = BloomCountUtils.getBloomCount();
    }


    @Override
    public void onVictory() {
        BloomCountUtils.setBloomCount(0);
        this.counter = BloomCountUtils.getBloomCount();
    }

    @Override
    public void atBattleStartPreDraw() {
        this.counter = BloomCountUtils.getBloomCount();
    }

    @Override
    public void renderCounter(SpriteBatch sb, boolean inTopPanel) {
        if (inTopPanel) {
            FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, Integer.toString(this.counter), 0.0F + this.currentX + 30.0F * Settings.scale, this.currentY - 7.0F * Settings.scale, Color.WHITE);
        } else {
            FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, Integer.toString(this.counter), this.currentX + 30.0F * Settings.scale, this.currentY - 7.0F * Settings.scale, Color.WHITE);
        }
    }

    public AbstractRelic makeCopy() {
        return new TheShapeShiftersMagnolia();
    }

}


