package StSShapeShifter.stances;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.Tortoise_Form;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.CalmParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;

public class MammothFormStance extends AbstractStance {
    public static final String STANCE_ID = "MammothFormStance";
    private static final StanceStrings stanceString;
    private static long sfxId;
    private final AbstractPlayer owner;
    private final int amount;

    public MammothFormStance() {
        this.ID = STANCE_ID;
        this.name = STANCE_ID;
        //this.name = null;
        this.updateDescription();
        this.owner = AbstractDungeon.player;
        this.amount = 2;
    }

    public void updateDescription() {
        //this.description = stanceString.DESCRIPTION[0];
        this.description = "Dragon Form Stance";
    }

    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.04F;
                AbstractDungeon.effectsQueue.add(new CalmParticleEffect());
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Calm"));
        }

    }
    public void onExitStance() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ShapeshifterMod.makeID("MammothFormPower")));
    }

    static {
        stanceString = CardCrawlGame.languagePack.getStanceString("MammothFormStance");
        sfxId = -1L;
    }
}



