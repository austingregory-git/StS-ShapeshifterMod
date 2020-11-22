//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.relics;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.AllFruit;
import StSShapeShifter.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;
import java.util.Random;

import static StSShapeShifter.ShapeshifterMod.makeRelicPath;

public class ThornsOfSummi extends AbstractRelic {
    public boolean active;
    public static final String ID = ShapeshifterMod.makeID(ThornsOfSummi.class.getSimpleName());
    private static final String PNG = ".png";
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath(ThornsOfSummi.class.getSimpleName() + PNG));

    public ThornsOfSummi() {
        super(ID, "sunflower.png", RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return AbstractDungeon.player != null ? this.setDescription(AbstractDungeon.player.chosenClass) : this.setDescription((PlayerClass)null);
    }

    private String setDescription(PlayerClass c) {
        return DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ThornsPower(AbstractDungeon.player, 1)));
    }

    @Override
    public void atTurnStart() {
        if(AbstractDungeon.player.hasPower(ThornsPower.POWER_ID)) {
            int dmg = AbstractDungeon.player.getPower(ThornsPower.POWER_ID).amount;
            this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(dmg, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    public AbstractRelic makeCopy() {
        return new ThornsOfSummi();
    }

}





